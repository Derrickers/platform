package com.cloud.platform.mapper;

import com.cloud.platform.model.UnAssetType;
import com.cloud.platform.model.UnAssetTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UnAssetTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table un_asset_type
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    long countByExample(UnAssetTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table un_asset_type
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    int deleteByExample(UnAssetTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table un_asset_type
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table un_asset_type
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    int insert(UnAssetType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table un_asset_type
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    int insertSelective(UnAssetType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table un_asset_type
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    List<UnAssetType> selectByExampleWithRowbounds(UnAssetTypeExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table un_asset_type
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    List<UnAssetType> selectByExample(UnAssetTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table un_asset_type
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    UnAssetType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table un_asset_type
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    int updateByExampleSelective(@Param("record") UnAssetType record, @Param("example") UnAssetTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table un_asset_type
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    int updateByExample(@Param("record") UnAssetType record, @Param("example") UnAssetTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table un_asset_type
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    int updateByPrimaryKeySelective(UnAssetType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table un_asset_type
     *
     * @mbg.generated Mon Apr 27 21:47:54 CST 2020
     */
    int updateByPrimaryKey(UnAssetType record);
}