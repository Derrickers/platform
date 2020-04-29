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
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private RepairOrderMapper repairOrderMapper;
    @Autowired
    private DetectOrderMapper detectOrderMapper;

    public ResultDTO<PaginationDTO> getRepairOrderList(int page, int size, String query, String type, String affiliation, String status) {
        RepairOrderExample repairOrderExample = new RepairOrderExample();
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
        long total = repairOrderMapper.countByExample(repairOrderExample);
        paginationDTO.setPagenum((int) (total / size));
        paginationDTO.setTotal((int) total);
        paginationDTO.setData(repairOrders);
        resultDTO.setData(paginationDTO);
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(), "获取维修工单列表成功"));
        return resultDTO;
    }

    public ResultDTO<PaginationDTO> getDetectOrder(int page, int size, String query, String date, String affiliation, String server) {
        DetectOrderExample detectOrderExample = new DetectOrderExample();
        DetectOrderExample.Criteria detectOrderExampleCriteria = detectOrderExample.createCriteria();
        detectOrderExampleCriteria.andOrderIndexLike("%" + query + "%");
        if (date != null && !"".equals(date)) {
            detectOrderExampleCriteria.andDetectDateEqualTo(date);
        }
        if (affiliation != null && !"".equals(affiliation)) {
            detectOrderExampleCriteria.andAffiliationEqualTo(affiliation);
        }
        if (server != null && !"".equals(server)) {
            detectOrderExampleCriteria.andServerLike("%" + server + "%");
        }
        long total = detectOrderMapper.countByExample(detectOrderExample);
        List<DetectOrder> detectOrders = detectOrderMapper.selectByExampleWithRowbounds(detectOrderExample, new RowBounds((page - 1) * size, size));
        List<DetectOrderDTO> detectOrderDTOS = new ArrayList<>();
        detectOrders.forEach(item->{
            DetectOrderDTO target = new DetectOrderDTO();
            BeanUtils.copyProperties(item, target);
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
