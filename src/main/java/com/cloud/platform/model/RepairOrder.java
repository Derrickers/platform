package com.cloud.platform.model;

public class RepairOrder {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repair_order.id
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repair_order.affiliation
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    private String affiliation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repair_order.order_index
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    private String orderIndex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repair_order.type
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repair_order.device_name
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    private String deviceName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repair_order.description
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repair_order.server
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    private String server;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repair_order.status
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repair_order.repair_member
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    private String repairMember;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repair_order.value_added
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    private Integer valueAdded;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repair_order.id
     *
     * @return the value of repair_order.id
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repair_order.id
     *
     * @param id the value for repair_order.id
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repair_order.affiliation
     *
     * @return the value of repair_order.affiliation
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public String getAffiliation() {
        return affiliation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repair_order.affiliation
     *
     * @param affiliation the value for repair_order.affiliation
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation == null ? null : affiliation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repair_order.order_index
     *
     * @return the value of repair_order.order_index
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public String getOrderIndex() {
        return orderIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repair_order.order_index
     *
     * @param orderIndex the value for repair_order.order_index
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public void setOrderIndex(String orderIndex) {
        this.orderIndex = orderIndex == null ? null : orderIndex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repair_order.type
     *
     * @return the value of repair_order.type
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repair_order.type
     *
     * @param type the value for repair_order.type
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repair_order.device_name
     *
     * @return the value of repair_order.device_name
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repair_order.device_name
     *
     * @param deviceName the value for repair_order.device_name
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repair_order.description
     *
     * @return the value of repair_order.description
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repair_order.description
     *
     * @param description the value for repair_order.description
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repair_order.server
     *
     * @return the value of repair_order.server
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public String getServer() {
        return server;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repair_order.server
     *
     * @param server the value for repair_order.server
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public void setServer(String server) {
        this.server = server == null ? null : server.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repair_order.status
     *
     * @return the value of repair_order.status
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repair_order.status
     *
     * @param status the value for repair_order.status
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repair_order.repair_member
     *
     * @return the value of repair_order.repair_member
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public String getRepairMember() {
        return repairMember;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repair_order.repair_member
     *
     * @param repairMember the value for repair_order.repair_member
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public void setRepairMember(String repairMember) {
        this.repairMember = repairMember == null ? null : repairMember.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repair_order.value_added
     *
     * @return the value of repair_order.value_added
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public Integer getValueAdded() {
        return valueAdded;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repair_order.value_added
     *
     * @param valueAdded the value for repair_order.value_added
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    public void setValueAdded(Integer valueAdded) {
        this.valueAdded = valueAdded;
    }
}