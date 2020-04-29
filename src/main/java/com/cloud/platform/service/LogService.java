package com.cloud.platform.service;

import com.cloud.platform.DTO.MetaDTO;
import com.cloud.platform.DTO.PaginationDTO;
import com.cloud.platform.DTO.ResultDTO;
import com.cloud.platform.Enum.ResponseType;
import com.cloud.platform.mapper.LogMapper;
import com.cloud.platform.model.Log;
import com.cloud.platform.model.LogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;


    public ResultDTO getLogList(int page, int size, String query, int type) {
        PaginationDTO<List<Log>> paginationDTO = new PaginationDTO<>();
        LogExample logExample = new LogExample();
        logExample.setOrderByClause("id desc");
        LogExample.Criteria logExampleCriteria = logExample.createCriteria();
        if(query!=null&&!"".equals(query))
            logExampleCriteria.andUsernameLike("%"+query+"%");
        if(type<=0||type>5)
            return ResultDTO.errorOf(ResponseType.FAIL.getValue(),"请求参数有误，日志类型有误");
        logExampleCriteria.andTypeEqualTo(type);
        List<Log> logs = logMapper.selectByExample(logExample);
        int total = logs.size();
        paginationDTO.setTotal(total);
        paginationDTO.setPagenum(total/size);
        paginationDTO.setData(logs);
        ResultDTO<PaginationDTO> resultDTO = new ResultDTO<>();
        resultDTO.setData(paginationDTO);
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"获取日志列表成功"));
        return resultDTO;
    }

    public void addLog(String account, String username, String description, String ip, String gmtCreate,int type) {
        Log log = new Log();
        log.setAccount(account);
        log.setDescription(description);
        log.setGmtCreate(gmtCreate);
        log.setType(type);
        log.setUsername(username);
        log.setIp(ip);
        logMapper.insert(log);
    }
}
