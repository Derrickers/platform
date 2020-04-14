package com.cloud.platform.service;

import com.cloud.platform.DTO.MetaDTO;
import com.cloud.platform.DTO.ResultDTO;
import com.cloud.platform.DTO.TreeListDTO;
import com.cloud.platform.Enum.ResponseType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeService {

    public ResultDTO<List<Object>> getAffiliationTree(){
        ResultDTO<List<Object>> listResultDTO = new ResultDTO<>();
        listResultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"获取所属单位树成功"));
        listResultDTO.setData(TreeListDTO.getAffiliationTree());
        return listResultDTO;
    }

    public ResultDTO<List<Object>> getDeviceClassification() {
        ResultDTO<List<Object>> listResultDTO = new ResultDTO<>();
        listResultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"获取设备分类树成功"));
        listResultDTO.setData(TreeListDTO.getDeviceClassificationTree());
        return listResultDTO;
    }
}
