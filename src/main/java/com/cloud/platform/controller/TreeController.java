package com.cloud.platform.controller;

import com.cloud.platform.DTO.TreeListDTO;
import com.cloud.platform.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tree")
public class TreeController {
    @Autowired
    private TreeService treeService;

    @GetMapping("/affiliation")
    @ResponseBody
    public Object getAffiliation(){
        return treeService.getAffiliationTree();
    }

    @GetMapping("/deviceClassification")
    @ResponseBody
    public Object getDeviceClassification(){
        return treeService.getDeviceClassification();
    }
}
