package com.cloud.platform.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class DetectOrderDTO {
    private Integer id;
    private String affiliation;
    private String orderIndex;
    private String detectDate;
    private String server;
    private String detectMember;
    private String relateOrder;
    private String remark;
}
