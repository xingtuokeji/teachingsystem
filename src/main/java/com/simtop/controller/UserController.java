package com.simtop.controller;

import com.simtop.annotation.SystemLog;
import com.simtop.entity.User;
import com.simtop.enums.PageEnum;
import com.simtop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @SystemLog(description = "伪登陆")
    @RequestMapping(value = "/vlogin",method = RequestMethod.GET)
    public String vlogin(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response,@RequestParam Integer id){
        User user = userService.vlogin(id);
        if(user != null){
            httpSession.setAttribute("user",user);
            request.setAttribute("page", PageEnum.INDEX.getCode());
        }else {
            request.setAttribute("page" , PageEnum.LOGIN_HINT.getCode());
        }
        return "index";
    }
}
