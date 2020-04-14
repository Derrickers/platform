package com.cloud.platform.service;

import com.cloud.platform.DTO.DeviceErrorCodeDTO;
import com.cloud.platform.DTO.MetaDTO;
import com.cloud.platform.DTO.PaginationDTO;
import com.cloud.platform.DTO.ResultDTO;
import com.cloud.platform.Enum.ResponseType;
import com.cloud.platform.mapper.DeviceErrorCodeMapper;
import com.cloud.platform.model.DeviceErrorCode;
import com.cloud.platform.model.DeviceErrorCodeExample;
import com.cloud.platform.utils.DateUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceErrorCodeMapper deviceErrorCodeMapper;

    public ResultDTO<PaginationDTO> getDeviceErrorCode(int page,int size,String classification, String code, String query) {
        ResultDTO<PaginationDTO> resultDTO = new ResultDTO<>();
        PaginationDTO<List<DeviceErrorCodeDTO>> paginationDTO = new PaginationDTO<>();
        DeviceErrorCodeExample deviceErrorCodeExample = new DeviceErrorCodeExample();
        long total = deviceErrorCodeMapper.countByExample(deviceErrorCodeExample);
        paginationDTO.setTotal((int) total);
        paginationDTO.setPagenum((int)(total/size));
        DeviceErrorCodeExample.Criteria deviceErrorCodeExampleCriteria = deviceErrorCodeExample.createCriteria();
        if(classification!=null&&!"".equals(classification))
            deviceErrorCodeExampleCriteria.andClassificationEqualTo(classification);
        if(code!=null&&!"".equals(code))
            deviceErrorCodeExampleCriteria.andErrorCodeLike("%"+code+"%");
        if(query != null &&!"".equals(query))
            deviceErrorCodeExampleCriteria.andDescriptionLike("%"+query+"%");
        List<DeviceErrorCode> deviceErrorCodes = deviceErrorCodeMapper.selectByExampleWithRowbounds(deviceErrorCodeExample,new RowBounds((page-1)*size,size));
        List<DeviceErrorCodeDTO> deviceErrorCodeDTOS = new ArrayList<>();
        deviceErrorCodes.stream().forEach(item ->{
            DeviceErrorCodeDTO deviceErrorCodeDTO = new DeviceErrorCodeDTO();
            BeanUtils.copyProperties(item,deviceErrorCodeDTO);
            deviceErrorCodeDTO.setCreateTime(DateUtils.SimpleFormatDate(item.getCreateTime()));
            deviceErrorCodeDTOS.add(deviceErrorCodeDTO);
        });
        paginationDTO.setData(deviceErrorCodeDTOS);
        resultDTO.setData(paginationDTO);
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"获取设备故障代码成功"));
        return resultDTO;
    }

    public ResultDTO addNewErrorCode(String classification, String errorCode, String description) {
        ResultDTO resultDTO = new ResultDTO();
        DeviceErrorCode deviceErrorCode = new DeviceErrorCode();
        deviceErrorCode.setClassification(classification);
        deviceErrorCode.setDescription(description);
        deviceErrorCode.setErrorCode(errorCode);
        deviceErrorCode.setCreateTime(new Date());
        DeviceErrorCodeExample deviceErrorCodeExample = new DeviceErrorCodeExample();
        deviceErrorCodeExample.createCriteria().andErrorCodeEqualTo(errorCode);
        List<DeviceErrorCode> deviceErrorCodes = deviceErrorCodeMapper.selectByExample(deviceErrorCodeExample);
        if(deviceErrorCodes!=null&&deviceErrorCodes.size()!=0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"该错误代码已存在");
        int insert = deviceErrorCodeMapper.insert(deviceErrorCode);
        if(insert!=0){
            resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"新增成功"));
        }else{
            resultDTO.setMeta(MetaDTO.errorOf(ResponseType.FAIL.getValue(),"数据库插入错误"));
        }
        return resultDTO;
    }

    public ResultDTO modifyErrorCode(int id, String classification, String errorCode, String description) {
        DeviceErrorCode deviceErrorCode = deviceErrorCodeMapper.selectByPrimaryKey(id);
        if(!errorCode.equals(deviceErrorCode.getErrorCode())){
            DeviceErrorCodeExample deviceErrorCodeExample = new DeviceErrorCodeExample();
            deviceErrorCodeExample.createCriteria().andErrorCodeEqualTo(errorCode);
            List<DeviceErrorCode> deviceErrorCodes = deviceErrorCodeMapper.selectByExample(deviceErrorCodeExample);
            if(deviceErrorCodes!=null&&deviceErrorCodes.size()!=0)
                return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"该错误代码已存在");
        }
        deviceErrorCode.setErrorCode(errorCode);
        deviceErrorCode.setDescription(description);
        deviceErrorCode.setClassification(classification);
        int code = deviceErrorCodeMapper.updateByPrimaryKey(deviceErrorCode);
        if(code != 0){
            ResultDTO resultDTO = new ResultDTO();
            resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"修改成功"));
            return resultDTO;
        }else{
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"更新失败");
        }
    }
}
