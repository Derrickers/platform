package com.cloud.platform.controller;

import com.cloud.platform.service.UserService;
import com.cloud.platform.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    @ResponseBody
    @CrossOrigin(value = UrlUtils.CROS,maxAge = 1800,allowedHeaders = "*")
    public Object login(@RequestParam Map<String,String> params){
        System.out.println("登陆");
        return userService.validate(params.get("username"),params.get("password"));
    }
}