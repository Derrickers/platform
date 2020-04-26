package com.cloud.platform.Controller;

import com.cloud.platform.DTO.ResultDTO;
import com.cloud.platform.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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

    @PostMapping("/deleteErrorCode")
    @ResponseBody
    public Object deleteErrorCode(@RequestParam(name = "id") Integer id){
        return deviceService.deleteErrorCode(id);
    }

    @GetMapping("/classification")
    @ResponseBody
    public Object getDeviceClassification(@RequestParam Map<String,String> params){
        int page = Integer.parseInt(params.get("page"));
        int size = Integer.parseInt(params.get("size"));
        String classification = params.get("classification");
        String classificationCode = params.get("classificationCode");
        int level = 0;
        if(!"".equals(params.get("level")))
            level = Integer.parseInt(params.get("level"));
        String query = params.get("query");
        return deviceService.getDeviceClassificationList(page,size,classification,classificationCode,level,query);
    }

    @PostMapping("/newClassification")
    @ResponseBody
    public Object addNewClassification(@RequestParam Map<String,String> params){
        String classification = params.get("classification");
        String classificationCode = params.get("classificationCode");
        int level = Integer.parseInt(params.get("level"));
        return deviceService.addNewClassification(classification,classificationCode,level);
    }
    @PostMapping("/modifyClassification")
    @ResponseBody
    public Object modifyClassification(@RequestParam Map<String,String> params){
        int id = Integer.parseInt(params.get("id"));
        String classification = params.get("classification");
        String classificationCode = params.get("classificationCode");
        int level = Integer.parseInt(params.get("level"));
        return deviceService.modifyClassification(id,classification,classificationCode,level);
    }

    @PostMapping("/deleteClassification")
    @ResponseBody
    public Object deleteClassification(@RequestParam(name = "id") Integer id){
        return deviceService.deleteClassifcation(id);
    }
    @GetMapping("/assetDevice")
    @ResponseBody
    public Object getAssetDevice(@RequestParam Map<String,String> params){
        int page = Integer.parseInt(params.get("page"));
        int size = Integer.parseInt(params.get("size"));
        String affiliation = params.get("affiliation");
        String classification = params.get("classification");
        String  manufacture = params.get("manufacture");
        String deviceName = params.get("deviceName");
        return deviceService.getDeviceAssetGood(page,size,affiliation,classification,manufacture,deviceName);
    }

    @PostMapping("/newAssetDevice")
    @ResponseBody
    public Object addNewAssetDevice(@RequestParam Map<String,String> params){
        return deviceService.addNewAssetGood(params);
    }
    @PostMapping("/modifyAssetDevice")
    @ResponseBody
    public Object modifyAssetDevice(@RequestParam Map<String,String> params){
        return deviceService.modifyAssetDevice(params);
    }

    @PostMapping("/deleteAssetDevice")
    @ResponseBody
    public Object deleteAssetDevice(@RequestParam(name = "id") Integer id){
        return deviceService.deleteAssetDevice(id);
    }

    @GetMapping("/accessoryType")
    @ResponseBody
    public Object getAccessoryType(@RequestParam Map<String,String> params){
        int page = Integer.parseInt(params.get("page"));
        int size = Integer.parseInt(params.get("size"));
        String deviceType = params.get("deviceType");
        String accessoryType = params.get("accessoryType");
        return deviceService.getAccessoryTypeList(page,size,deviceType,accessoryType);
    }

    @PostMapping("/newAccessoryType")
    @ResponseBody
    public Object addNewAccessoryType(@RequestParam Map<String,String> params){
        String deviceType = params.get("deviceType");
        String accessoryTypeIndex = params.get("accessoryTypeIndex");
        String accessoryTypeName = params.get("accessoryTypeName");
        return deviceService.addNewAccessoryType(deviceType,accessoryTypeIndex,accessoryTypeName);
    }

    @PostMapping("/modifyAccessoryType")
    @ResponseBody
    public Object modifyAccessoryType(@RequestParam Map<String,String> params){
        int id = Integer.parseInt(params.get("id"));
        String deviceType = params.get("deviceType");
        String accessoryTypeIndex = params.get("accessoryTypeIndex");
        String accessoryTypeName = params.get("accessoryTypeName");
        return deviceService.modifyAccessoryType(id,deviceType,accessoryTypeIndex,accessoryTypeName);
    }

    @PostMapping("/deleteAccessoryType")
    @ResponseBody
    public Object deleteAccessoryType(@RequestParam(name = "id") Integer id){
        return deviceService.deleteAccessoryType(id);
    }

    @GetMapping("/accessoryDevice")
    @ResponseBody
    public Object getAccessoryDevice(@RequestParam Map<String,String> params){
        int page = Integer.parseInt(params.get("page"));
        int size = Integer.parseInt(params.get("size"));
        String affiliation = params.get("affiliation");
        String accessoryName = params.get("accessoryName");
        String accessoryType = params.get("accessoryType");
        return deviceService.getAccessoryDevice(page,size,affiliation,accessoryName,accessoryType);
    }

    @PostMapping("/newAccessoryDevice")
    @ResponseBody
    public Object addNewAccessoryDevice(@RequestParam Map<String,String> params){
        String affiliation = params.get("affiliation");
        String affiliateDevice = params.get("affiliateDevice");
        String accessoryType = params.get("accessoryType");
        String accessoryName = params.get("accessoryName");
        return deviceService.addNewAccessoryDevice(affiliation,affiliateDevice,accessoryType,accessoryName);
    }

    @PostMapping("/modifyAccessoryDevice")
    @ResponseBody
    public Object modifyAccessoryDevice(@RequestParam Map<String,String> params){
        int id = Integer.parseInt(params.get("id"));
        String affiliation = params.get("affiliation");
        String affiliateDevice = params.get("affiliateDevice");
        String accessoryType = params.get("accessoryType");
        String accessoryName = params.get("accessoryName");
        return deviceService.modifyAccessoryDevice(id,affiliation,affiliateDevice,accessoryType,accessoryName);
    }

    @PostMapping("/deleteAccessoryDevice")
    @ResponseBody
    public Object deleteAccessoryDevice(@RequestParam(name = "id") Integer id){
        return deviceService.deleteAccessoryDevice(id);
    }

    @GetMapping("/unAssetType")
    @ResponseBody
    public Object getUnAssetType(@RequestParam Map<String,String> params){
        int page = Integer.parseInt(params.get("page"));
        int size = Integer.parseInt(params.get("size"));
        String deviceType = params.get("deviceType");
        String unAssetTypeName = params.get("unAssetTypeName");
        return deviceService.getUnAssetTypeList(page,size,deviceType,unAssetTypeName);
    }

    @PostMapping("/newUnAssetType")
    @ResponseBody
    public Object addNewUnAssetType(@RequestParam Map<String,String> params){
        String deviceType = params.get("deviceType");
        String unAssetTypeIndex = params.get("unAssetTypeIndex");
        String unAssetTypeName = params.get("unAssetTypeName");
        return deviceService.addNewUnAssetType(deviceType,unAssetTypeIndex,unAssetTypeName);
    }

    @PostMapping("/modifyUnAssetType")
    @ResponseBody
    public Object modifyUnAssetType(@RequestParam Map<String,String> params){
        int id = Integer.parseInt(params.get("id"));
        String deviceType = params.get("deviceType");
        String unAssetTypeIndex = params.get("unAssetTypeIndex");
        String unAssetTypeName = params.get("unAssetTypeName");
        return deviceService.modifyNewUnAssetType(id,deviceType,unAssetTypeIndex,unAssetTypeName);
    }

    @PostMapping("/deleteUnAssetType")
    @ResponseBody
    public Object deleteUnAssetType(@RequestParam(name = "id") Integer id){
        return deviceService.deleteUnAssetType(id);
    }

}