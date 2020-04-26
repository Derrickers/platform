package com.cloud.platform.mapper;

import com.cloud.platform.model.AccessoryDevice;
import com.cloud.platform.model.AccessoryDeviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AccessoryDeviceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accessory_device
     *
     * @mbg.generated Sun Apr 26 13:17:26 CST 2020
     */
    long countByExample(AccessoryDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accessory_device
     *
     * @mbg.generated Sun Apr 26 13:17:26 CST 2020
     */
    int deleteByExample(AccessoryDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accessory_device
     *
     * @mbg.generated Sun Apr 26 13:17:26 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accessory_device
     *
     * @mbg.generated Sun Apr 26 13:17:26 CST 2020
     */
    int insert(AccessoryDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accessory_device
     *
     * @mbg.generated Sun Apr 26 13:17:26 CST 2020
     */
    int insertSelective(AccessoryDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accessory_device
     *
     * @mbg.generated Sun Apr 26 13:17:26 CST 2020
     */
    List<AccessoryDevice> selectByExampleWithRowbounds(AccessoryDeviceExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accessory_device
     *
     * @mbg.generated Sun Apr 26 13:17:26 CST 2020
     */
    List<AccessoryDevice> selectByExample(AccessoryDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accessory_device
     *
     * @mbg.generated Sun Apr 26 13:17:26 CST 2020
     */
    AccessoryDevice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accessory_device
     *
     * @mbg.generated Sun Apr 26 13:17:26 CST 2020
     */
    int updateByExampleSelective(@Param("record") AccessoryDevice record, @Param("example") AccessoryDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accessory_device
     *
     * @mbg.generated Sun Apr 26 13:17:26 CST 2020
     */
    int updateByExample(@Param("record") AccessoryDevice record, @Param("example") AccessoryDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accessory_device
     *
     * @mbg.generated Sun Apr 26 13:17:26 CST 2020
     */
    int updateByPrimaryKeySelective(AccessoryDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accessory_device
     *
     * @mbg.generated Sun Apr 26 13:17:26 CST 2020
     */
    int updateByPrimaryKey(AccessoryDevice record);
}