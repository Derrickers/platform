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
}
