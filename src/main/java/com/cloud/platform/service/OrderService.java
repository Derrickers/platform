package com.cloud.platform.service;

import com.cloud.platform.DTO.DetectOrderDTO;
import com.cloud.platform.DTO.MetaDTO;
import com.cloud.platform.DTO.PaginationDTO;
import com.cloud.platform.DTO.ResultDTO;
import com.cloud.platform.Enum.ResponseType;
import com.cloud.platform.mapper.DetectOrderMapper;
import com.cloud.platform.mapper.RepairOrderMapper;
import com.cloud.platform.model.DetectOrder;
import com.cloud.platform.model.DetectOrderExample;
import com.cloud.platform.model.RepairOrder;
import com.cloud.platform.model.RepairOrderExample;
import com.cloud.platform.utils.DateUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private RepairOrderMapper repairOrderMapper;
    @Autowired
    private DetectOrderMapper detectOrderMapper;

    public ResultDTO<PaginationDTO> getRepairOrderList(int page, int size, String query, String type, String affiliation, String status) {
        RepairOrderExample repairOrderExample = new RepairOrderExample();
        long total = repairOrderMapper.countByExample(repairOrderExample);
        RepairOrderExample.Criteria repairOrderExampleCriteria = repairOrderExample.createCriteria();
        repairOrderExampleCriteria.andDeviceNameLike("%" + query + "%");
        if (type != null && !"".equals(type)) {
            repairOrderExampleCriteria.andTypeEqualTo(type);
        }
        if (affiliation != null && !"".equals(affiliation)) {
            repairOrderExampleCriteria.andAffiliationEqualTo(affiliation);
        }
        if (status != null && !"".equals(status)) {
            repairOrderExampleCriteria.andStatusEqualTo(status);
        }
        List<RepairOrder> repairOrders = repairOrderMapper.selectByExampleWithRowbounds(repairOrderExample, new RowBounds((page - 1) * size, size));
        ResultDTO<PaginationDTO> resultDTO = new ResultDTO<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagenum((int) (total / size));
        paginationDTO.setTotal((int) total);
        paginationDTO.setData(repairOrders);
        resultDTO.setData(paginationDTO);
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "获取维修工单列表成功"));
        return resultDTO;
    }

    public ResultDTO<PaginationDTO> getDetectOrder(int page, int size, String query, String date, String affiliation, String server) {
        DetectOrderExample detectOrderExample = new DetectOrderExample();
        long total = detectOrderMapper.countByExample(detectOrderExample);
        DetectOrderExample.Criteria detectOrderExampleCriteria = detectOrderExample.createCriteria();
        detectOrderExampleCriteria.andOrderIndexLike("%" + query + "%");
        if (date != null && !"".equals(date)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS SSSS");
            Date sqlDate = Date.valueOf(date);
            try {
                System.out.println(sqlDate.toString());
                java.util.Date parse = format.parse(sqlDate.toString());
                System.out.println(parse);
            } catch (ParseException e) {
                return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"日期输入有误");
            }

            //detectOrderExampleCriteria.andDetectDateEqualTo(time);
        }
        if (affiliation != null && !"".equals(affiliation)) {
            detectOrderExampleCriteria.andAffiliationEqualTo(affiliation);
        }
        if (server != null && !"".equals(server)) {
            detectOrderExampleCriteria.andServerLike("%" + server + "%");
        }
        List<DetectOrder> detectOrders = detectOrderMapper.selectByExampleWithRowbounds(detectOrderExample, new RowBounds((page - 1) * size, size));
        List<DetectOrderDTO> detectOrderDTOS = new ArrayList<>();
        detectOrders.stream().forEach(item->{
            DetectOrderDTO target = new DetectOrderDTO();
            BeanUtils.copyProperties(item, target);
            target.setDetectDate(DateUtils.SimpleFormatDate(item.getDetectDate()));
            detectOrderDTOS.add(target);
        });
        ResultDTO<PaginationDTO> resultDTO = new ResultDTO<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagenum((int) (total / size));
        paginationDTO.setTotal((int) total);
        paginationDTO.setData(detectOrderDTOS);
        resultDTO.setData(paginationDTO);
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "获取巡检工单列表成功"));
        return resultDTO;
    }
}
