package com.cloud.platform.Controller;

import com.cloud.platform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/management")
public class ManagementController {

    @Autowired
    private LogService logService;

    @GetMapping("/log")
    @ResponseBody
    public Object getLogs(@RequestParam Map<String,String> params){
        int page = Integer.parseInt(params.get("page"));
        int size = Integer.parseInt(params.get("size"));
        String query = params.get("query");
        int type = Integer.parseInt(params.get("type"));
        return logService.getLogList(page,size,query,type);
    }
}
