package com.cloud.platform.service;

import com.cloud.platform.DTO.MetaDTO;
import com.cloud.platform.DTO.PaginationDTO;
import com.cloud.platform.DTO.ResultDTO;
import com.cloud.platform.DTO.ServerDTO;
import com.cloud.platform.Enum.ResponseType;
import com.cloud.platform.mapper.ServerInfoMapper;
import com.cloud.platform.model.ServerInfo;
import com.cloud.platform.model.ServerInfoExample;
import com.cloud.platform.utils.DateUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServersService {

    @Autowired
    private ServerInfoMapper serverInfoMapper;

    public ResultDTO<PaginationDTO> getServersPagination(Integer page, Integer size,String query,String type) {
        ServerInfoExample example = new ServerInfoExample();
        if(type!=null&&!"".equals(type))
            example.createCriteria().andServerNameLike("%"+query+"%").andTypeEqualTo(type);
        else
            example.createCriteria().andServerNameLike("%"+query+"%");
        long total = serverInfoMapper.countByExample(example);
        List<ServerInfo> serverInfos = serverInfoMapper.selectByExampleWithRowbounds(example,new RowBounds((page-1)*size,size));
        List<ServerDTO> serverDTOS = new ArrayList<>();
        serverInfos.stream().forEach(server->{
            ServerDTO target = new ServerDTO();
            BeanUtils.copyProperties(server, target);
            serverDTOS.add(target);
        });
        ResultDTO<PaginationDTO> resultDTO = new ResultDTO<>();
        PaginationDTO<List<ServerDTO>> paginationDTO = new PaginationDTO<>();
        paginationDTO.setPagenum((int) (total/size+1));
        paginationDTO.setTotal((int) total);
        paginationDTO.setData(serverDTOS);
        resultDTO.setData(paginationDTO);
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"获取服务商信息列表成功"));
        return resultDTO;
    }
}
