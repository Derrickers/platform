package com.cloud.platform.controller;

import com.cloud.platform.DTO.PaginationDTO;
import com.cloud.platform.DTO.ResultDTO;
import com.cloud.platform.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/repair")
    @ResponseBody
    public Object getRepairOrder(@RequestParam Map<String, String> params){
        int page = Integer.parseInt(params.get("page"));
        int size = Integer.parseInt(params.get("size"));
        String query = params.get("query");
        String type = params.get("type");
        String affiliation = params.get("affiliation");
        String status = params.get("status");
        return orderService.getRepairOrderList(page,size,query,type,affiliation,status);
    }
    @GetMapping("/detect")
    @ResponseBody
    public Object getDetectOrder(@RequestParam Map<String, String> params){
        int page = Integer.parseInt(params.get("page"));
        int size = Integer.parseInt(params.get("size"));
        String query = params.get("query");
        String date = params.get("date");
        String affiliation = params.get("affiliation");
        String server = params.get("server");
        return orderService.getDetectOrder(page,size,query,date,affiliation,server);
    }
}
