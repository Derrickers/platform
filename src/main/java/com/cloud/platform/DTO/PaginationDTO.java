package com.cloud.platform.DTO;

import lombok.Data;

@Data
public class PaginationDTO<T> {
    private int pagenum;
    private int total;
    private T data;
}
