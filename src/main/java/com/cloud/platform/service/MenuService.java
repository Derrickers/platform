package com.cloud.platform.service;

import com.cloud.platform.DTO.MenuDTO;
import com.cloud.platform.DTO.MetaDTO;
import com.cloud.platform.DTO.ResultDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MenuService {


    public ResultDTO<ArrayList<MenuDTO>> getMenuByRid(Integer userRid) {
        ResultDTO<ArrayList<MenuDTO>> resultDTO = new ResultDTO<>();
        MetaDTO meta = new MetaDTO();
        meta.setCode(200);
        meta.setMessage("获取菜单成功");
        resultDTO.setMeta(meta);
        resultDTO.setData(MenuDTO.getAdminMenu());
        return resultDTO;
    }
}
