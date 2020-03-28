package com.cloud.platform.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private int rid;
    private String username;
    private String mobile;
    private String email;
    private String token;
}
