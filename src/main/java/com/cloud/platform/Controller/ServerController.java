package com.cloud.platform.Controller;

import com.cloud.platform.DTO.PaginationDTO;
import com.cloud.platform.DTO.ResultDTO;
import com.cloud.platform.model.ServerInfo;
import com.cloud.platform.service.ServersService;
import com.cloud.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class ServerController {
    @Autowired
    private ServersService serversService;
    @Autowired
    private UserService userService;

    @GetMapping("/servers")
    @ResponseBody
    public Object getServers(HttpServletRequest request,
                             @RequestParam Map<String,String> params){
        Integer userRid = (Integer) request.getAttribute("userRid");
        int page = Integer.parseInt(params.get("page"));
        int size = Integer.parseInt(params.get("size"));
        String query = params.get("query");
        String  type = params.get("type");
        return serversService.getServersPagination(page,size,query,type);
    }
}