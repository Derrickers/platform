package com.cloud.platform.controller;

import com.cloud.platform.DTO.ResultDTO;
import com.cloud.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestParam Map<String,String> params){
        return userService.validate(params.get("username"),params.get("password"));
    }

    @GetMapping("/users")
    @ResponseBody
    public Object getUsers(HttpServletRequest request,
                           @RequestParam(name = "query",required = false) String query,
                           @RequestParam(name = "pagenum",defaultValue = "1") Integer pagenum,
                           @RequestParam(name = "pagesize",defaultValue = "2") Integer pagesize){
        return userService.getUserPagination((Integer) request.getSession().getAttribute("userRid"),pagenum,pagesize,query);
    }

    @PostMapping("/users/modifyPass")
    @ResponseBody
    public Object modifyPassword(HttpServletRequest request,
                                 @RequestParam(name = "oldPass") String oldPass,
                                 @RequestParam(name = "newPass") String newPass){
        Integer userId = (Integer) request.getAttribute("userId");
        return userService.modifyPassword(userId,oldPass,newPass);
    }

    @PostMapping("/users")
    @ResponseBody
    public Object addUser(@RequestParam Map<String,String> params){
        String username = params.get("username");
        String password = params.get("password");
        String email = params.get("email");
        String mobile = params.get("mobile");
        return userService.addUser(username,password,email,mobile);
    }
}