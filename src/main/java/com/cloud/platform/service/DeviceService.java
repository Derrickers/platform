package com.cloud.platform.service;

import com.cloud.platform.DTO.*;
import com.cloud.platform.Enum.ResponseType;
import com.cloud.platform.mapper.*;
import com.cloud.platform.model.*;
import com.cloud.platform.utils.DateUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private AccessoryTypeMapper accessoryTypeMapper;

    @Autowired
    private AccessoryDeviceMapper accessoryDeviceMapper;

    @Autowired
    private UnAssetTypeMapper unAssetTypeMapper;

    @Autowired
    private UnAssetDeviceMapper unAssetDeviceMapper;

    public ResultDTO<PaginationDTO> getDeviceErrorCode(int page, int size, String classification, String code, String query) {
        ResultDTO<PaginationDTO> resultDTO = new ResultDTO<>();
        PaginationDTO<List<DeviceErrorCodeDTO>> paginationDTO = new PaginationDTO<>();
        DeviceErrorCodeExample deviceErrorCodeExample = new DeviceErrorCodeExample();
        DeviceErrorCodeExample.Criteria deviceErrorCodeExampleCriteria = deviceErrorCodeExample.createCriteria();
        if (classification != null && !"".equals(classification))
            deviceErrorCodeExampleCriteria.andClassificationEqualTo(classification);
        if (code != null && !"".equals(code))
            deviceErrorCodeExampleCriteria.andErrorCodeLike("%" + code + "%");
        if (query != null && !"".equals(query))
            deviceErrorCodeExampleCriteria.andDescriptionLike("%" + query + "%");
        long total = deviceErrorCodeMapper.countByExample(deviceErrorCodeExample);
        paginationDTO.setTotal((int) total);
        paginationDTO.setPagenum((int) (total / size));
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
        DeviceClassificationExample.Criteria deviceClassificationExampleCriteria = deviceClassificationExample.createCriteria();
        if (classification != null && !"".equals(classification))
            deviceClassificationExampleCriteria.andClassificationEqualTo(classification);
        if (classificationCode != null && !"".equals(classificationCode))
            deviceClassificationExampleCriteria.andClassificationCodeLike("%" + classificationCode + "%");
        if (level != 0)
            deviceClassificationExampleCriteria.andLevelEqualTo(level);
        if (query != null && !"".equals(query))
            deviceClassificationExampleCriteria.andClassificationLike("%" + query + "%");
        long total = deviceClassificationMapper.countByExample(deviceClassificationExample);
        paginationDTO.setTotal((int) total);
        paginationDTO.setPagenum((int) (total / size));
        List<DeviceClassification> deviceClassifications = deviceClassificationMapper.selectByExampleWithRowbounds(deviceClassificationExample, new RowBounds((page - 1) * size, size));
        paginationDTO.setData(deviceClassifications);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setData(paginationDTO);
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "获取设备分类列表成功"));
        return resultDTO;
    }

    public ResultDTO addNewClassification(String classification, String classificationCode, int level) {
        if (level > 3 || level < 1)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(), "设备层级应在1到3之间");
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
        if (level > 3 || level < 1)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(), "设备层级应在1到3之间");
        DeviceClassificationExample deviceClassificationExample = new DeviceClassificationExample();
        deviceClassificationExample.createCriteria().andClassificationCodeEqualTo(classificationCode);
        List<DeviceClassification> deviceClassifications = deviceClassificationMapper.selectByExample(deviceClassificationExample);
        if (deviceClassifications != null && deviceClassifications.size() != 0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(), "设备类型码已存在，请重新设置");
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

        if (affiliation != null && !"".equals(affiliation))
            deviceAssetGoodExampleCriteria.andAffiliationEqualTo(affiliation);
        if (classification != null && !"".equals(classification))
            deviceAssetGoodExampleCriteria.andClassificationEqualTo(classification);
        if (manufacture != null && !"".equals(manufacture))
            deviceAssetGoodExampleCriteria.andManufactureLike("%" + manufacture + "%");
        if (deviceName != null && !"".equals(deviceName))
            deviceAssetGoodExampleCriteria.andDeviceNameLike("%" + deviceName + "%");
        long total = deviceAssetGoodMapper.countByExample(deviceAssetGoodExample);
        paginationDTO.setTotal((int) total);
        paginationDTO.setPagenum((int) (total / size));
        List<DeviceAssetGood> deviceAssetGoods = deviceAssetGoodMapper.selectByExampleWithRowbounds(deviceAssetGoodExample, new RowBounds((page - 1) * size, size));
        List<DeviceAssetGoodDTO> deviceAssetGoodDTOS = new ArrayList<>();
        deviceAssetGoods.forEach(item -> {
            DeviceAssetGoodDTO deviceAssetGoodDTO = new DeviceAssetGoodDTO();
            BeanUtils.copyProperties(item, deviceAssetGoodDTO);
            deviceAssetGoodDTOS.add(deviceAssetGoodDTO);
        });
        paginationDTO.setData(deviceAssetGoodDTOS);
        resultDTO.setData(paginationDTO);
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "获取资产设备列表成功"));
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
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "创建新资产设备成功"));
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
        if (deviceAssetGood == null)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(), "该条记录已被删除，请刷新列表");
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
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "更新新资产设备成功"));
        return resultDTO;
    }

    public ResultDTO deleteAssetDevice(Integer id) {
        int i = deviceAssetGoodMapper.deleteByPrimaryKey(id);
        if (i == 0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(), "删除失败");
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "删除成功"));
        return resultDTO;
    }

    public ResultDTO getAccessoryTypeList(int page, int size, String deviceType, String accessoryType) {
        AccessoryTypeExample accessoryTypeExample = new AccessoryTypeExample();
        PaginationDTO<List<AccessoryType>> paginationDTO = new PaginationDTO<>();
        AccessoryTypeExample.Criteria accessoryTypeExampleCriteria = accessoryTypeExample.createCriteria();
        if(deviceType!=null&&!"".equals(deviceType))
            accessoryTypeExampleCriteria.andDeviceTypeEqualTo(deviceType);
        if(accessoryType!=null&&!"".equals(accessoryType))
            accessoryTypeExampleCriteria.andAccessoryTypeNameLike("%"+accessoryType+"%");
        long total = accessoryTypeMapper.countByExample(accessoryTypeExample);
        paginationDTO.setTotal((int) total);
        paginationDTO.setPagenum((int) (total / size));
        List<AccessoryType> accessoryTypes = accessoryTypeMapper.selectByExampleWithRowbounds(accessoryTypeExample, new RowBounds((page - 1) * size, size));
        paginationDTO.setData(accessoryTypes);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"获取配件类型列表成功"));
        resultDTO.setData(paginationDTO);
        return resultDTO;
    }

    public ResultDTO addNewAccessoryType(String deviceType, String accessoryTypeIndex, String accessoryTypeName) {
        AccessoryTypeExample accessoryTypeExample = new AccessoryTypeExample();
        accessoryTypeExample.createCriteria().andAccessoryTypeIndexEqualTo(accessoryTypeIndex);
        List<AccessoryType> accessoryTypes = accessoryTypeMapper.selectByExample(accessoryTypeExample);
        if(accessoryTypes!=null&&accessoryTypes.size()!=0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"配件类型编号已存在，请重新设置");
        AccessoryType accessoryType = new AccessoryType();
        accessoryType.setDeviceType(deviceType);
        accessoryType.setAccessoryTypeIndex(accessoryTypeIndex);
        accessoryType.setAccessoryTypeName(accessoryTypeName);
        int code = accessoryTypeMapper.insert(accessoryType);
        if(code==0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"服务器错误，插入失败");
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"插入成功"));
        return resultDTO;
    }

    public ResultDTO modifyAccessoryType(int id, String deviceType, String accessoryTypeIndex, String accessoryTypeName) {
        AccessoryType accessoryType = accessoryTypeMapper.selectByPrimaryKey(id);
        if(accessoryType==null)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"该条记录已被删除");
        AccessoryTypeExample accessoryTypeExample = new AccessoryTypeExample();
        accessoryTypeExample.createCriteria().andAccessoryTypeIndexEqualTo(accessoryTypeIndex);
        List<AccessoryType> accessoryTypes = accessoryTypeMapper.selectByExample(accessoryTypeExample);
        if(accessoryTypes!=null&&accessoryTypes.size()!=0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"配件类型编号已存在，请重新设置");
        accessoryType.setAccessoryTypeName(accessoryTypeName);
        accessoryType.setAccessoryTypeIndex(accessoryTypeIndex);
        accessoryType.setDeviceType(deviceType);
        int code = accessoryTypeMapper.updateByPrimaryKey(accessoryType);
        if(code==0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"服务器错误，更新失败");
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"更新成功"));
        return resultDTO;
    }

    public Object deleteAccessoryType(Integer id) {
        int code = accessoryTypeMapper.deleteByPrimaryKey(id);
        if(code==0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"服务器错误，删除失败");
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"删除成功"));
        return resultDTO;
    }

    public ResultDTO getAccessoryDevice(int page, int size, String affiliation, String accessoryName, String accessoryType) {
        AccessoryDeviceExample accessoryDeviceExample = new AccessoryDeviceExample();
        PaginationDTO<List<AccessoryDevice>> paginationDTO = new PaginationDTO<>();
        AccessoryDeviceExample.Criteria accessoryDeviceExampleCriteria = accessoryDeviceExample.createCriteria();
        if(affiliation!=null&&!"".equals(affiliation))
            accessoryDeviceExampleCriteria.andAffiliationEqualTo(affiliation);
        if(accessoryName!=null&&!"".equals(accessoryName))
            accessoryDeviceExampleCriteria.andAccessoryNameLike("%"+accessoryName+"%");
        if(accessoryType!=null&&!"".equals(accessoryType))
            accessoryDeviceExampleCriteria.andAccessoryTypeEqualTo(accessoryType);
        long total = accessoryDeviceMapper.countByExample(accessoryDeviceExample);
        paginationDTO.setTotal((int) total);
        paginationDTO.setPagenum((int) (total / size));
        List<AccessoryDevice> accessoryDevices = accessoryDeviceMapper.selectByExampleWithRowbounds(accessoryDeviceExample, new RowBounds((page - 1) * size, size));
        paginationDTO.setData(accessoryDevices);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"获取配件类型列表成功"));
        resultDTO.setData(paginationDTO);
        return resultDTO;
    }

    public ResultDTO addNewAccessoryDevice(String affiliation, String affiliateDevice, String accessoryType, String accessoryName) {
        AccessoryDevice accessoryDevice = new AccessoryDevice();
        accessoryDevice.setAccessoryName(accessoryName);
        accessoryDevice.setAccessoryType(accessoryType);
        accessoryDevice.setAffiliateDevice(affiliateDevice);
        accessoryDevice.setAffiliation(affiliation);
        int code = accessoryDeviceMapper.insert(accessoryDevice);
        if(code==0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"服务器错误，插入失败");
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"新增成功"));
        return resultDTO;
    }

    public Object modifyAccessoryDevice(int id, String affiliation, String affiliateDevice, String accessoryType, String accessoryName) {
        AccessoryDevice accessoryDevice = accessoryDeviceMapper.selectByPrimaryKey(id);
        if(accessoryDevice == null)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"该条记录已被删除，请重试");
        accessoryDevice.setAffiliation(affiliation);
        accessoryDevice.setAffiliateDevice(affiliateDevice);
        accessoryDevice.setAccessoryType(accessoryType);
        accessoryDevice.setAccessoryName(accessoryName);
        int code = accessoryDeviceMapper.updateByPrimaryKey(accessoryDevice);
        if(code==0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"服务器错误，更新失败");
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"更新成功"));
        return resultDTO;
    }

    public ResultDTO deleteAccessoryDevice(Integer id) {
        int code = accessoryDeviceMapper.deleteByPrimaryKey(id);
        if(code==0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"服务器错误，删除失败");
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"删除成功"));
        return resultDTO;
    }

    public ResultDTO getUnAssetTypeList(int page, int size, String deviceType, String unAssetTypeName) {
        PaginationDTO<List<UnAssetType>> paginationDTO = new PaginationDTO<>();
        UnAssetTypeExample unAssetTypeExample = new UnAssetTypeExample();
        UnAssetTypeExample.Criteria unAssetTypeExampleCriteria = unAssetTypeExample.createCriteria();
        if(deviceType!=null&&!"".equals(deviceType))
            unAssetTypeExampleCriteria.andDeviceTypeEqualTo(deviceType);
        if(unAssetTypeName!=null&&!"".equals(unAssetTypeName))
            unAssetTypeExampleCriteria.andUnAssetTypeNameLike("%"+unAssetTypeName+"%");
        long total = unAssetTypeMapper.countByExample(unAssetTypeExample);
        paginationDTO.setTotal((int) total);
        paginationDTO.setPagenum((int) (total/size));
        List<UnAssetType> unAssetTypes = unAssetTypeMapper.selectByExampleWithRowbounds(unAssetTypeExample, new RowBounds((page - 1) * size, size));
        paginationDTO.setData(unAssetTypes);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setData(paginationDTO);
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"获取非资产类型列表成功"));
        return resultDTO;
    }

    public ResultDTO addNewUnAssetType(String deviceType, String unAssetTypeIndex, String unAssetTypeName) {
        UnAssetTypeExample unAssetTypeExample = new UnAssetTypeExample();
        unAssetTypeExample.createCriteria().andUnAssetTypeIndexEqualTo(unAssetTypeIndex);
        List<UnAssetType> unAssetTypes = unAssetTypeMapper.selectByExample(unAssetTypeExample);
        if(unAssetTypes!=null&&unAssetTypes.size()!=0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"该非资产类型编号已存在，请重新输入");
        UnAssetType unAssetType = new UnAssetType();
        unAssetType.setDeviceType(deviceType);
        unAssetType.setUnAssetTypeIndex(unAssetTypeIndex);
        unAssetType.setUnAssetTypeName(unAssetTypeName);
        int code = unAssetTypeMapper.insert(unAssetType);
        if(code==0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"服务器错误，插入失败");
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"新增成功"));
        return resultDTO;
    }

    public ResultDTO modifyNewUnAssetType(int id, String deviceType, String unAssetTypeIndex, String unAssetTypeName) {
        UnAssetType unAssetType = unAssetTypeMapper.selectByPrimaryKey(id);
        if(unAssetType==null)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"该条记录已被删除，请重新选择");
        UnAssetTypeExample unAssetTypeExample = new UnAssetTypeExample();
        unAssetTypeExample.createCriteria().andUnAssetTypeIndexEqualTo(unAssetTypeIndex);
        List<UnAssetType> unAssetTypes = unAssetTypeMapper.selectByExample(unAssetTypeExample);
        if(unAssetTypes!=null&&unAssetTypes.size()!=0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"该非资产类型编号已存在，请重新输入");
        unAssetType.setDeviceType(deviceType);
        unAssetType.setUnAssetTypeIndex(unAssetTypeIndex);
        unAssetType.setUnAssetTypeName(unAssetTypeName);
        int code = unAssetTypeMapper.updateByPrimaryKey(unAssetType);
        if(code==0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"服务器错误，更新失败");
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"更新成功"));
        return resultDTO;
    }

    public ResultDTO deleteUnAssetType(Integer id) {
        int code = unAssetTypeMapper.deleteByPrimaryKey(id);
        if(code==0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"服务器错误，删除失败");
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"删除成功"));
        return resultDTO;
    }

    public ResultDTO getUnAssetDeviceList(int page, int size, String affiliation, String unAssetType, String unAssetDeviceName) {
        PaginationDTO<List<UnAssetDevice>> paginationDTO = new PaginationDTO<>();
        UnAssetDeviceExample unAssetDeviceExample = new UnAssetDeviceExample();
        UnAssetDeviceExample.Criteria unAssetDeviceExampleCriteria = unAssetDeviceExample.createCriteria();
        if(affiliation!=null&&!"".equals(affiliation))
            unAssetDeviceExampleCriteria.andAffiliationEqualTo(affiliation);
        if(unAssetType!=null&&!"".equals(unAssetType))
            unAssetDeviceExampleCriteria.andUnAssetTypeEqualTo(unAssetType);
        if(unAssetDeviceName!=null&&!"".equals(unAssetDeviceName))
            unAssetDeviceExampleCriteria.andUnAssetTypeLike("%"+unAssetType+"%");
        long total = unAssetDeviceMapper.countByExample(unAssetDeviceExample);
        paginationDTO.setTotal((int) total);
        paginationDTO.setPagenum((int) (total/size));
        List<UnAssetDevice> unAssetDevices = unAssetDeviceMapper.selectByExampleWithRowbounds(unAssetDeviceExample,new RowBounds((page-1)*size,size));
        paginationDTO.setData(unAssetDevices);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setData(paginationDTO);
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"获取非资产设备设施列表成功"));
        return resultDTO;
    }

    public ResultDTO addNewUnAssetDevice(String affiliation, String unAssetType, String unit, String specification, int count) {
        UnAssetDevice unAssetDevice = new UnAssetDevice();
        unAssetDevice.setAffiliation(affiliation);
        unAssetDevice.setCount(count);
        unAssetDevice.setSpecification(specification);
        unAssetDevice.setUnit(unit);
        unAssetDevice.setUnAssetType(unAssetType);
        int code = unAssetDeviceMapper.insert(unAssetDevice);
        if(code==0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"服务器错误，插入失败");
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"新增成功"));
        return resultDTO;
    }

    public ResultDTO modifyUnAssetDevice(int id, String affiliation, String unAssetType, String unit, int count, String specification) {
        UnAssetDevice unAssetDevice = unAssetDeviceMapper.selectByPrimaryKey(id);
        if(unAssetDevice==null)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"该条记录已被删除，请重试");
        unAssetDevice.setAffiliation(affiliation);
        unAssetDevice.setCount(count);
        unAssetDevice.setSpecification(specification);
        unAssetDevice.setUnit(unit);
        unAssetDevice.setUnAssetType(unAssetType);
        int code = unAssetDeviceMapper.updateByPrimaryKey(unAssetDevice);
        if(code==0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"服务器错误，更新失败");
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"更新成功"));
        return resultDTO;
    }

    public ResultDTO deleteUnAssetDevice(Integer id) {
        int code = unAssetDeviceMapper.deleteByPrimaryKey(id);
        if(code==0)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"服务器错误，删除失败");
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"删除成功"));
        return resultDTO;
    }
}
