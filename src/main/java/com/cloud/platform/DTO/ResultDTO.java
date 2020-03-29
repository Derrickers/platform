package com.cloud.platform.DTO;

import lombok.Data;

@Data
public class ResultDTO<T> {
    private T data;
    private MetaDTO meta;

    public ResultDTO<T> okOf(T data,MetaDTO meta){
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setData(data);
        resultDTO.setMeta(meta);
        return resultDTO;
    }

    public static ResultDTO errorOf(int code,String message){
        ResultDTO resultDTO = new ResultDTO();
        MetaDTO meta = new MetaDTO();
        meta.setCode(code);
        meta.setMessage(message);
        resultDTO.setMeta(meta);
        return resultDTO;
    }
}
