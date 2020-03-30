package com.cloud.platform.DTO;

import com.cloud.platform.Enum.ResponseType;
import lombok.Data;

/**
 * 存储请求状态信息
 */
@Data
public class MetaDTO {
    private Integer code;
    private String message;

    public static MetaDTO okOf(String message) {
        MetaDTO metaDTO = new MetaDTO();
        metaDTO.setCode(ResponseType.SUCCESS.getValue());
        metaDTO.setMessage(message);
        return metaDTO;
    }
    public static MetaDTO errorOf(int code,String message){
        MetaDTO metaDTO = new MetaDTO();
        metaDTO.setCode(code);
        metaDTO.setMessage(message);
        return metaDTO;
    }
}
