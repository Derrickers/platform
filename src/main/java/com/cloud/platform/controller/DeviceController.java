package com.cloud.platform.controller;

import com.cloud.platform.DTO.ResultDTO;
import com.cloud.platform.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/errorCode")
    @ResponseBody
    public Object getDeviceErrorCode(@RequestParam Map<String,String> params){
        int page = Integer.parseInt(params.get("page"));
        int size = Integer.parseInt(params.get("size"));
        String classification = params.get("classification");
        String code = params.get("code");
        String query = params.get("query");
        return deviceService.getDeviceErrorCode(page,size,classification,code,query);
    }

    @PostMapping("/newErrorCode")
    @ResponseBody
    public Object addNewErrorCode(@RequestParam Map<String,String> params){
        String classification = params.get("classification");
        String errorCode = params.get("errorCode");
        String description = params.get("description");
        return deviceService.addNewErrorCode(classification,errorCode,description);
    }
    @PostMapping("/modifyErrorCode")
    @ResponseBody
    public Object modifyErrorCode(@RequestParam Map<String,String> params){
        int id = Integer.parseInt(params.get("id"));
        String classification = params.get("classification");
        String errorCode = params.get("errorCode");
        String description = params.get("description");
        return deviceService.modifyErrorCode(id,classification,errorCode,description);
    }
}
