package com.simtop.entity;

import java.util.Date;

public class OperateLogInfo {
    private Integer id;

    private Integer userId;

    private String name;

    private String fromIp;

    private String operateFunc;

    private String visitMethod;

    private String methodCostTime;

    private String logType;

    private String uri;

    private String method;

    private String visitMethodErrorInfo;

    private Integer status;

    private Date loginOutTime;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFromIp() {
        return fromIp;
    }

    public void setFromIp(String fromIp) {
        this.fromIp = fromIp == null ? null : fromIp.trim();
    }

    public String getOperateFunc() {
        return operateFunc;
    }

    public void setOperateFunc(String operateFunc) {
        this.operateFunc = operateFunc == null ? null : operateFunc.trim();
    }

    public String getVisitMethod() {
        return visitMethod;
    }

    public void setVisitMethod(String visitMethod) {
        this.visitMethod = visitMethod == null ? null : visitMethod.trim();
    }

    public String getMethodCostTime() {
        return methodCostTime;
    }

    public void setMethodCostTime(String methodCostTime) {
        this.methodCostTime = methodCostTime == null ? null : methodCostTime.trim();
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getVisitMethodErrorInfo() {
        return visitMethodErrorInfo;
    }

    public void setVisitMethodErrorInfo(String visitMethodErrorInfo) {
        this.visitMethodErrorInfo = visitMethodErrorInfo == null ? null : visitMethodErrorInfo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLoginOutTime() {
        return loginOutTime;
    }

    public void setLoginOutTime(Date loginOutTime) {
        this.loginOutTime = loginOutTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}