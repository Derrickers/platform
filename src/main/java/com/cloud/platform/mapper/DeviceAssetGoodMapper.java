package com.cloud.platform.mapper;

import com.cloud.platform.model.DeviceAssetGood;
import com.cloud.platform.model.DeviceAssetGoodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DeviceAssetGoodMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_asset_goods
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    long countByExample(DeviceAssetGoodExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_asset_goods
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    int deleteByExample(DeviceAssetGoodExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_asset_goods
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_asset_goods
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    int insert(DeviceAssetGood record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_asset_goods
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    int insertSelective(DeviceAssetGood record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_asset_goods
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    List<DeviceAssetGood> selectByExampleWithRowbounds(DeviceAssetGoodExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_asset_goods
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    List<DeviceAssetGood> selectByExample(DeviceAssetGoodExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_asset_goods
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    DeviceAssetGood selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_asset_goods
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    int updateByExampleSelective(@Param("record") DeviceAssetGood record, @Param("example") DeviceAssetGoodExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_asset_goods
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    int updateByExample(@Param("record") DeviceAssetGood record, @Param("example") DeviceAssetGoodExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_asset_goods
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    int updateByPrimaryKeySelective(DeviceAssetGood record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_asset_goods
     *
     * @mbg.generated Sat Apr 25 17:58:35 CST 2020
     */
    int updateByPrimaryKey(DeviceAssetGood record);
}