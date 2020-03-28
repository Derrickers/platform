package com.cloud.platform.controller;

import com.cloud.platform.DTO.ResultDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {

    @PostMapping("/user/login")
    @ResponseBody
    public Object login(@RequestParam(name = "username") String user,
                        @RequestParam(name = "password", required = false) String password){
        return ResultDTO.okOf();
    }
}
