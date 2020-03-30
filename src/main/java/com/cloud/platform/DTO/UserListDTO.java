package com.cloud.platform.DTO;

import lombok.Data;

@Data
public class UserListDTO {
    private int id;
    private String username;
    private String mobile;
    private String email;
    private Long gmtCreate;
    private Long gmtModify;
    private Boolean mgState;
    private String roleName;
}
