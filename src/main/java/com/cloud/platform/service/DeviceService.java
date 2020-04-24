package com.cloud.platform.service;

import com.cloud.platform.DTO.*;
import com.cloud.platform.Enum.ResponseType;
import com.cloud.platform.mapper.DeviceAssetGoodMapper;
import com.cloud.platform.mapper.DeviceClassificationMapper;
import com.cloud.platform.mapper.DeviceErrorCodeMapper;
import com.cloud.platform.model.*;
import com.cloud.platform.utils.DateUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DeviceService {
    @Autowired
    private DeviceErrorCodeMapper deviceErrorCodeMapper;

    @Autowired
    private DeviceClassificationMapper deviceClassificationMapper;

    @Autowired
    private DeviceAssetGoodMapper deviceAssetGoodMapper;

    public ResultDTO<PaginationDTO> getDeviceErrorCode(int page, int size, String classification, String code, String query) {
        ResultDTO<PaginationDTO> resultDTO = new ResultDTO<>();
        PaginationDTO<List<DeviceErrorCodeDTO>> paginationDTO = new PaginationDTO<>();
        DeviceErrorCodeExample deviceErrorCodeExample = new DeviceErrorCodeExample();
        long total = deviceErrorCodeMapper.countByExample(deviceErrorCodeExample);
        paginationDTO.setTotal((int) total);
        paginationDTO.setPagenum((int) (total / size));
        DeviceErrorCodeExample.Criteria deviceErrorCodeExampleCriteria = deviceErrorCodeExample.createCriteria();
        if (classification != null && !"".equals(classification))
            deviceErrorCodeExampleCriteria.andClassificationEqualTo(classification);
        if (code != null && !"".equals(code))
            deviceErrorCodeExampleCriteria.andErrorCodeLike("%" + code + "%");
        if (query != null && !"".equals(query))
            deviceErrorCodeExampleCriteria.andDescriptionLike("%" + query + "%");
        List<DeviceErrorCode> deviceErrorCodes = deviceErrorCodeMapper.selectByExampleWithRowbounds(deviceErrorCodeExample, new RowBounds((page - 1) * size, size));
        List<DeviceErrorCodeDTO> deviceErrorCodeDTOS = new ArrayList<>();
        deviceErrorCodes.forEach(item -> {
            DeviceErrorCodeDTO deviceErrorCodeDTO = new DeviceErrorCodeDTO();
            BeanUtils.copyProperties(item, deviceErrorCodeDTO);
            deviceErrorCodeDTOS.add(deviceErrorCodeDTO);
        });
        paginationDTO.setData(deviceErrorCodeDTOS);
        resultDTO.setData(paginationDTO);
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "获取设备故障代码成功"));
        return resultDTO;
    }

    public ResultDTO addNewErrorCode(String classification, String errorCode, String description) {
        ResultDTO resultDTO = new ResultDTO();
        DeviceErrorCode deviceErrorCode = new DeviceErrorCode();
        deviceErrorCode.setClassification(classification);
        deviceErrorCode.setDescription(description);
        deviceErrorCode.setErrorCode(errorCode);
        deviceErrorCode.setCreateTime(DateUtils.SimpleFormatDate(new Date()));
        DeviceErrorCodeExample deviceErrorCodeExample = new DeviceErrorCodeExample();
        deviceErrorCodeExample.createCriteria().andErrorCodeEqualTo(errorCode);
        List<DeviceErrorCode> deviceErrorCodes = deviceErrorCodeMapper.selectByExample(deviceErrorCodeExample);
        if (deviceErrorCodes != null && deviceErrorCodes.size() != 0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(), "该错误代码已存在");
        int insert = deviceErrorCodeMapper.insert(deviceErrorCode);
        if (insert != 0) {
            resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "新增成功"));
        } else {
            resultDTO.setMeta(MetaDTO.errorOf(ResponseType.FAIL.getValue(), "数据库插入错误"));
        }
        return resultDTO;
    }

    public ResultDTO modifyErrorCode(int id, String classification, String errorCode, String description) {
        DeviceErrorCode deviceErrorCode = deviceErrorCodeMapper.selectByPrimaryKey(id);
        if (!errorCode.equals(deviceErrorCode.getErrorCode())) {
            DeviceErrorCodeExample deviceErrorCodeExample = new DeviceErrorCodeExample();
            deviceErrorCodeExample.createCriteria().andErrorCodeEqualTo(errorCode);
            List<DeviceErrorCode> deviceErrorCodes = deviceErrorCodeMapper.selectByExample(deviceErrorCodeExample);
            if (deviceErrorCodes != null && deviceErrorCodes.size() != 0)
                return ResultDTO.errorOf(ResponseType.FAIL.getValue(), "该错误代码已存在");
        }
        deviceErrorCode.setErrorCode(errorCode);
        deviceErrorCode.setDescription(description);
        deviceErrorCode.setClassification(classification);
        int code = deviceErrorCodeMapper.updateByPrimaryKey(deviceErrorCode);
        if (code != 0) {
            ResultDTO resultDTO = new ResultDTO();
            resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "修改成功"));
            return resultDTO;
        } else {
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(), "更新失败");
        }
    }

    public ResultDTO deleteErrorCode(Integer id) {
        int key = deviceErrorCodeMapper.deleteByPrimaryKey(id);
        if (key == 0) {
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(), "删除记录失败");
        }
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "删除成功"));
        return resultDTO;
    }

    public ResultDTO getDeviceClassificationList(int page, int size, String classification, String classificationCode, int level, String query) {
        PaginationDTO<List<DeviceClassification>> paginationDTO = new PaginationDTO<>();
        DeviceClassificationExample deviceClassificationExample = new DeviceClassificationExample();
        deviceClassificationExample.setOrderByClause("level asc");
        long total = deviceClassificationMapper.countByExample(deviceClassificationExample);
        paginationDTO.setTotal((int) total);
        paginationDTO.setPagenum((int) (total / size));
        DeviceClassificationExample.Criteria deviceClassificationExampleCriteria = deviceClassificationExample.createCriteria();
        if (classification != null && !"".equals(classification))
            deviceClassificationExampleCriteria.andClassificationEqualTo(classification);
        if (classificationCode != null && !"".equals(classificationCode))
            deviceClassificationExampleCriteria.andClassificationCodeLike("%" + classificationCode + "%");
        if (level != 0)
            deviceClassificationExampleCriteria.andLevelEqualTo(level);
        if (query != null && !"".equals(query))
            deviceClassificationExampleCriteria.andClassificationLike("%" + query + "%");
        List<DeviceClassification> deviceClassifications = deviceClassificationMapper.selectByExampleWithRowbounds(deviceClassificationExample, new RowBounds((page - 1) * size, size));
        paginationDTO.setData(deviceClassifications);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setData(paginationDTO);
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "获取设备分类列表成功"));
        return resultDTO;
    }

    public ResultDTO addNewClassification(String classification, String classificationCode, int level) {
        if(level>3||level<1)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"设备层级应在1到3之间");
        DeviceClassification deviceClassification = new DeviceClassification();
        deviceClassification.setClassification(classification);
        DeviceClassificationExample deviceClassificationExample = new DeviceClassificationExample();
        deviceClassificationExample.createCriteria().andClassificationCodeEqualTo(classificationCode);
        List<DeviceClassification> deviceClassifications = deviceClassificationMapper.selectByExample(deviceClassificationExample);
        if (deviceClassifications != null && deviceClassifications.size() != 0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(), "设备类型码已存在，请重新设置");
        deviceClassification.setClassificationCode(classificationCode);
        deviceClassification.setLevel(level);
        deviceClassificationMapper.insert(deviceClassification);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "新增分类成功"));
        return resultDTO;
    }

    public ResultDTO deleteClassifcation(int id) {
        deviceClassificationMapper.deleteByPrimaryKey(id);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "删除分类成功"));
        return resultDTO;
    }

    public ResultDTO modifyClassification(int id, String classification, String classificationCode, Integer level) {
        if(level>3||level<1)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"设备层级应在1到3之间");
        DeviceClassificationExample deviceClassificationExample = new DeviceClassificationExample();
        deviceClassificationExample.createCriteria().andClassificationCodeEqualTo(classificationCode);
        List<DeviceClassification> deviceClassifications = deviceClassificationMapper.selectByExample(deviceClassificationExample);
        if(deviceClassifications!=null&&deviceClassifications.size()!=0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"设备类型码已存在，请重新设置");
        DeviceClassification deviceClassification = deviceClassificationMapper.selectByPrimaryKey(id);
        deviceClassification.setLevel(level);
        deviceClassification.setClassificationCode(classificationCode);
        deviceClassification.setClassification(classification);
        deviceClassificationMapper.updateByPrimaryKey(deviceClassification);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "修改设备类型成功"));
        return resultDTO;
    }

    public ResultDTO getDeviceAssetGood(int page, int size, String affiliation, String classification, String manufacture, String deviceName) {
        PaginationDTO<List<DeviceAssetGoodDTO>> paginationDTO = new PaginationDTO<>();
        ResultDTO<PaginationDTO> resultDTO = new ResultDTO<>();
        DeviceAssetGoodExample deviceAssetGoodExample = new DeviceAssetGoodExample();
        DeviceAssetGoodExample.Criteria deviceAssetGoodExampleCriteria = deviceAssetGoodExample.createCriteria();
        long total = deviceAssetGoodMapper.countByExample(deviceAssetGoodExample);
        paginationDTO.setTotal((int) total);
        paginationDTO.setPagenum((int) (total/size));
        if(affiliation!=null&&!"".equals(affiliation))
            deviceAssetGoodExampleCriteria.andAffiliationEqualTo(affiliation);
        if(classification!=null&&!"".equals(classification))
            deviceAssetGoodExampleCriteria.andClassificationEqualTo(classification);
        if(manufacture!=null&&!"".equals(manufacture))
            deviceAssetGoodExampleCriteria.andManufactureLike("%"+manufacture+"%");
        if(deviceName!=null&&!"".equals(deviceName))
            deviceAssetGoodExampleCriteria.andDeviceNameLike("%"+deviceName+"%");
        List<DeviceAssetGood> deviceAssetGoods = deviceAssetGoodMapper.selectByExampleWithRowbounds(deviceAssetGoodExample, new RowBounds((page - 1) * size, size));
        List<DeviceAssetGoodDTO> deviceAssetGoodDTOS = new ArrayList<>();
        deviceAssetGoods.forEach(item->{
            DeviceAssetGoodDTO deviceAssetGoodDTO = new DeviceAssetGoodDTO();
            BeanUtils.copyProperties(item,deviceAssetGoodDTO);
            deviceAssetGoodDTOS.add(deviceAssetGoodDTO);
        });
        paginationDTO.setData(deviceAssetGoodDTOS);
        resultDTO.setData(paginationDTO);
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"获取资产设备列表成功"));
        return resultDTO;
    }

    public ResultDTO addNewAssetGood(Map<String, String> params) {
        String assetCode = params.get("assetCode");
        String deviceName = params.get("deviceName");
        String affiliation = params.get("affiliation");
        String classification = params.get("classification");
        String specification = params.get("specification");
        int count = Integer.parseInt(params.get("count"));
        String unit = params.get("unit");
        String storageCharge = params.get("storageCharge");
        String manufacture = params.get("manufacture");
        String productDate = params.get("productDate");
        String useDate = params.get("useDate");
        String warrantyDate = params.get("warrantyDate");
        int serviceLife = Integer.parseInt(params.get("serviceLife"));
        String status = params.get("status");

        DeviceAssetGood deviceAssetGood = new DeviceAssetGood();
        deviceAssetGood.setAffiliation(affiliation);
        deviceAssetGood.setAssetCode(assetCode);
        deviceAssetGood.setClassification(classification);
        deviceAssetGood.setCount(count);
        deviceAssetGood.setDeviceName(deviceName);
        deviceAssetGood.setManufacture(manufacture);
        deviceAssetGood.setUnit(unit);
        deviceAssetGood.setServiceLife(serviceLife);
        deviceAssetGood.setStatus(status);
        deviceAssetGood.setSpecification(specification);
        deviceAssetGood.setStorageCharge(storageCharge);
        deviceAssetGood.setStorageLocation("0402");
        deviceAssetGood.setProductDate(productDate);
        deviceAssetGood.setUseDate(useDate);
        deviceAssetGood.setWarrantyDate(warrantyDate);
        deviceAssetGoodMapper.insert(deviceAssetGood);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"创建新资产设备成功"));
        return resultDTO;
    }

    public ResultDTO modifyAssetDevice(Map<String, String> params) {
        int id = Integer.parseInt(params.get("id"));
        String assetCode = params.get("assetCode");
        String deviceName = params.get("deviceName");
        String affiliation = params.get("affiliation");
        String classification = params.get("classification");
        String specification = params.get("specification");
        int count = Integer.parseInt(params.get("count"));
        String unit = params.get("unit");
        String storageCharge = params.get("storageCharge");
        String manufacture = params.get("manufacture");
        String productDate = params.get("productDate");
        String useDate = params.get("useDate");
        String warrantyDate = params.get("warrantyDate");
        int serviceLife = Integer.parseInt(params.get("serviceLife"));
        String status = params.get("status");

        DeviceAssetGood deviceAssetGood = deviceAssetGoodMapper.selectByPrimaryKey(id);
        if(deviceAssetGood == null)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"该条记录已被删除，请刷新列表");
        deviceAssetGood.setAffiliation(affiliation);
        deviceAssetGood.setAssetCode(assetCode);
        deviceAssetGood.setClassification(classification);
        deviceAssetGood.setCount(count);
        deviceAssetGood.setDeviceName(deviceName);
        deviceAssetGood.setManufacture(manufacture);
        deviceAssetGood.setUnit(unit);
        deviceAssetGood.setServiceLife(serviceLife);
        deviceAssetGood.setStatus(status);
        deviceAssetGood.setSpecification(specification);
        deviceAssetGood.setStorageCharge(storageCharge);
        deviceAssetGood.setStorageLocation("0402");
        deviceAssetGood.setProductDate(productDate);
        deviceAssetGood.setUseDate(useDate);
        deviceAssetGood.setWarrantyDate(warrantyDate);
        deviceAssetGoodMapper.updateByPrimaryKey(deviceAssetGood);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"更新新资产设备成功"));
        return resultDTO;
    }

    public ResultDTO deleteAssetDevice(Integer id) {
        int i = deviceAssetGoodMapper.deleteByPrimaryKey(id);
        if(i == 0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"删除失败");
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"删除成功"));
        return resultDTO;
    }
}
