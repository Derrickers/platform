package com.cloud.platform.DTO;

import lombok.Data;

@Data
public class DeviceAssetGoodDTO {
    private int id;
    private String affiliation;
    private String deviceName;
    private String assetCode;
    private String classification;
    private Integer count;
    private String unit;
    private String manufacture;
    private String specification;
    private String productDate;
    private String useDate;
    private int serviceLife;
    private String warrantyDate;
    private String storageLocation;
    private String storageCharge;
    private String status;
}
