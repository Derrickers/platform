package com.cloud.platform.mapper;

import com.cloud.platform.model.DeviceClassification;
import com.cloud.platform.model.DeviceClassificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DeviceClassificationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_classification
     *
     * @mbg.generated Sat Apr 25 17:58:34 CST 2020
     */
    long countByExample(DeviceClassificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_classification
     *
     * @mbg.generated Sat Apr 25 17:58:34 CST 2020
     */
    int deleteByExample(DeviceClassificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_classification
     *
     * @mbg.generated Sat Apr 25 17:58:34 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_classification
     *
     * @mbg.generated Sat Apr 25 17:58:34 CST 2020
     */
    int insert(DeviceClassification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_classification
     *
     * @mbg.generated Sat Apr 25 17:58:34 CST 2020
     */
    int insertSelective(DeviceClassification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_classification
     *
     * @mbg.generated Sat Apr 25 17:58:34 CST 2020
     */
    List<DeviceClassification> selectByExampleWithRowbounds(DeviceClassificationExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_classification
     *
     * @mbg.generated Sat Apr 25 17:58:34 CST 2020
     */
    List<DeviceClassification> selectByExample(DeviceClassificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_classification
     *
     * @mbg.generated Sat Apr 25 17:58:34 CST 2020
     */
    DeviceClassification selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_classification
     *
     * @mbg.generated Sat Apr 25 17:58:34 CST 2020
     */
    int updateByExampleSelective(@Param("record") DeviceClassification record, @Param("example") DeviceClassificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_classification
     *
     * @mbg.generated Sat Apr 25 17:58:34 CST 2020
     */
    int updateByExample(@Param("record") DeviceClassification record, @Param("example") DeviceClassificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_classification
     *
     * @mbg.generated Sat Apr 25 17:58:34 CST 2020
     */
    int updateByPrimaryKeySelective(DeviceClassification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_classification
     *
     * @mbg.generated Sat Apr 25 17:58:34 CST 2020
     */
    int updateByPrimaryKey(DeviceClassification record);
}