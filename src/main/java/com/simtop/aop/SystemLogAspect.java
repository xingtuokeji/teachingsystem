package com.simtop.aop;

import com.simtop.annotation.SystemLog;
import com.simtop.entity.OperateLogInfo;
import com.simtop.entity.User;
import com.simtop.service.OperateLogInfoService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class SystemLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
    /**
     * 记录每个用户刚开始访问方法的时间
     */
    private static final ThreadLocal<Date> BEGIN_TIME_THREAD_LOCAL = new NamedThreadLocal<>("ThreadLocal beginTime");
    private static final ThreadLocal<OperateLogInfo> LOG_THREAD_LOCAL = new NamedThreadLocal<>("ThreadLocal log");

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired
    private OperateLogInfoService operateLogInfoService;

    /**
     * spring框架自带的线程池
     */
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    /**
     * 对使用@SystemLog注释的方法进行拦截
     */

    public SystemLogAspect(){

    }
    @Pointcut("@annotation(com.simtop.annotation.SystemLog)")
    public void systemLogAspectCtrl() {
    }

    /**
     * 前置通知 用于拦截记录用户操作的开始时间
     */
    @Before("systemLogAspectCtrl()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException {
        System.out.println("进入controller之前被拦截");
        Date beginTime = new Date();
        BEGIN_TIME_THREAD_LOCAL.set(beginTime);
        if (logger.isDebugEnabled()) {
            logger.debug("开始计时: {}，URI: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                    .format(beginTime), request.getRequestURI());
        }
    }

    /**
     * 后置通知 用于拦截用户操作
     */
    @After("systemLogAspectCtrl()")
    public void doAfter(JoinPoint joinPoint) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null){
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserId(user.getId());
            operateLogInfo.setName(user.getName());
            operateLogInfo.setStatus(1);
            operateLogInfo.setMethod(request.getMethod());
            operateLogInfo.setUri(request.getRequestURI());
            operateLogInfo.setLogType("info");
            operateLogInfo.setOperateFunc(getMethodDescription(joinPoint));
            operateLogInfo.setVisitMethod(getMethod(joinPoint));
            operateLogInfo.setMethodCostTime(new Date().getTime()-BEGIN_TIME_THREAD_LOCAL.get().getTime()+"");
            //开启新线程进行记录
            taskExecutor.execute(new SaveLogThread(operateLogInfo , operateLogInfoService));
            LOG_THREAD_LOCAL.set(operateLogInfo);
        }
    }

    /**
     * 获取注解中对方法的描述信息
     */
    private String getMethodDescription(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemLog systemLog = method.getAnnotation(SystemLog.class);
        String description = systemLog.description();
        return description;
    }

    private String getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getName();
    }

    /**
     * 异常通知 记录操作报错日志
     */
    @AfterThrowing(pointcut = "systemLogAspectCtrl()" , throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint , Throwable e) {
        OperateLogInfo operateLogInfo = LOG_THREAD_LOCAL.get();
        operateLogInfo.setLogType("error");
        operateLogInfo.setVisitMethodErrorInfo(e.getMessage());
        taskExecutor.execute(new UpdateLogThread(operateLogInfo , operateLogInfoService));
    }

    /**
     * 保存日志线程
     */
    private static class SaveLogThread implements Runnable {
        private OperateLogInfo operateLogInfo;
        private OperateLogInfoService operateLogInfoService;

        public SaveLogThread(OperateLogInfo operateLogInfo, OperateLogInfoService operateLogInfoService) {
            this.operateLogInfo = operateLogInfo;
            this.operateLogInfoService = operateLogInfoService;
        }

        @Override
        public void run() {
            //这里会将插入后的记录ID给，写入operateLogInfo所指的对象实例中（如何实现看OperateLogInfoMapper.xml中insert）
            operateLogInfoService.insert(operateLogInfo);
        }
    }

    /**
     * 日志更新线程
     */
    private static class UpdateLogThread extends Thread {

        private OperateLogInfo operateLogInfo;
        private OperateLogInfoService operateLogInfoService;

        public UpdateLogThread(OperateLogInfo operateLogInfo, OperateLogInfoService operateLogInfoService) {
            super(UpdateLogThread.class.getSimpleName());
            this.operateLogInfo = operateLogInfo;
            this.operateLogInfoService = operateLogInfoService;
        }

        @Override
        public void run() {
            operateLogInfoService.update(operateLogInfo);
        }
    }
}

