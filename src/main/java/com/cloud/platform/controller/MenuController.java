package com.cloud.platform.controller;

import com.cloud.platform.DTO.MenuDTO;
import com.cloud.platform.DTO.ResultDTO;
import com.cloud.platform.service.MenuService;
import com.cloud.platform.service.UserService;
import com.cloud.platform.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MenuController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/menus")
    @ResponseBody
    @CrossOrigin(value = UrlUtils.CROS,maxAge = 1800,allowedHeaders = "*")
    public Object getMenu(HttpServletRequest request){
        Integer userRid = (Integer)request.getAttribute("userRid");
        return menuService.getMenuByRid(userRid);
    }
}
