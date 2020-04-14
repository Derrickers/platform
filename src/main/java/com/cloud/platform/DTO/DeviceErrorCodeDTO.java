package com.cloud.platform.DTO;

import lombok.Data;

@Data
public class DeviceErrorCodeDTO {
    private Integer id;
    private String classification;
    private String errorCode;
    private String description;
    private String createTime;
}
