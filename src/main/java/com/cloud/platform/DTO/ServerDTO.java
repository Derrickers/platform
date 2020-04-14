package com.cloud.platform.DTO;

import lombok.Data;

@Data
public class ServerDTO {
    private Long id;
    private String identifer;
    private String serverName;
    private String abbreviation;
    private String type;
    private Integer creditCode;
    private String location;
    private String present;
    private Integer registeredCapital;
    private String createTime;
    private String manageRange;
    private String  manageBegintime;
    private String manageEndtime;
    private String registrar;
    private String issueTime;
    private String principal;
    private String principalContactInfo;
    private String leader;
    private String leaderContactInfo;
    private String secureLeader;
    private String secureLeaderContactInfo;
    private String secureCode;
    private String  secureBegintime;
    private String  secureEndtime;
    private String remarks;
    private Long gmtCreate;
    private Long gmtModify;
}
