package com.cloud.platform.model;

public class AccessoryType {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accessory_type.id
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accessory_type.device_type
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    private String deviceType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accessory_type.accessory_type_name
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    private String accessoryTypeName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accessory_type.accessory_type_index
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    private String accessoryTypeIndex;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accessory_type.id
     *
     * @return the value of accessory_type.id
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accessory_type.id
     *
     * @param id the value for accessory_type.id
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accessory_type.device_type
     *
     * @return the value of accessory_type.device_type
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accessory_type.device_type
     *
     * @param deviceType the value for accessory_type.device_type
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accessory_type.accessory_type_name
     *
     * @return the value of accessory_type.accessory_type_name
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    public String getAccessoryTypeName() {
        return accessoryTypeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accessory_type.accessory_type_name
     *
     * @param accessoryTypeName the value for accessory_type.accessory_type_name
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    public void setAccessoryTypeName(String accessoryTypeName) {
        this.accessoryTypeName = accessoryTypeName == null ? null : accessoryTypeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accessory_type.accessory_type_index
     *
     * @return the value of accessory_type.accessory_type_index
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    public String getAccessoryTypeIndex() {
        return accessoryTypeIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accessory_type.accessory_type_index
     *
     * @param accessoryTypeIndex the value for accessory_type.accessory_type_index
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    public void setAccessoryTypeIndex(String accessoryTypeIndex) {
        this.accessoryTypeIndex = accessoryTypeIndex == null ? null : accessoryTypeIndex.trim();
    }
}