package com.cloud.platform.Controller;

import com.cloud.platform.DTO.MetaDTO;
import com.cloud.platform.DTO.ResultDTO;
import com.cloud.platform.Enum.LogType;
import com.cloud.platform.Enum.ResponseType;
import com.cloud.platform.service.LogService;
import com.cloud.platform.service.UserService;
import com.cloud.platform.utils.DateUtils;
import com.cloud.platform.utils.IPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestParam Map<String,String> params,HttpServletRequest request){
        return userService.validate(params.get("account"),params.get("password"),IPUtils.getIp(request));
    }

    @GetMapping("/logout")
    @ResponseBody
    public Object logout(HttpServletRequest request){
        String account = (String) request.getAttribute("account");
        String username = (String) request.getAttribute("username");
        String ip = (String) request.getAttribute("ip");
        logService.addLog(account,username,"用户注销",ip, DateUtils.SimpleFormatDate(new Date()), LogType.LOGOUT.getValue());
        ResultDTO<Object> resultDTO = new ResultDTO<>();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"登出成功"));
        return resultDTO;
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