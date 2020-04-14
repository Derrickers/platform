package com.cloud.platform.mapper;

import com.cloud.platform.model.RepairOrder;
import com.cloud.platform.model.RepairOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RepairOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repair_order
     *
     * @mbg.generated Tue Apr 14 13:57:25 CST 2020
     */
    long countByExample(RepairOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repair_order
     *
     * @mbg.generated Tue Apr 14 13:57:25 CST 2020
     */
    int deleteByExample(RepairOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repair_order
     *
     * @mbg.generated Tue Apr 14 13:57:25 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repair_order
     *
     * @mbg.generated Tue Apr 14 13:57:25 CST 2020
     */
    int insert(RepairOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repair_order
     *
     * @mbg.generated Tue Apr 14 13:57:25 CST 2020
     */
    int insertSelective(RepairOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repair_order
     *
     * @mbg.generated Tue Apr 14 13:57:25 CST 2020
     */
    List<RepairOrder> selectByExampleWithRowbounds(RepairOrderExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repair_order
     *
     * @mbg.generated Tue Apr 14 13:57:25 CST 2020
     */
    List<RepairOrder> selectByExample(RepairOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repair_order
     *
     * @mbg.generated Tue Apr 14 13:57:25 CST 2020
     */
    RepairOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repair_order
     *
     * @mbg.generated Tue Apr 14 13:57:25 CST 2020
     */
    int updateByExampleSelective(@Param("record") RepairOrder record, @Param("example") RepairOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repair_order
     *
     * @mbg.generated Tue Apr 14 13:57:25 CST 2020
     */
    int updateByExample(@Param("record") RepairOrder record, @Param("example") RepairOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repair_order
     *
     * @mbg.generated Tue Apr 14 13:57:25 CST 2020
     */
    int updateByPrimaryKeySelective(RepairOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repair_order
     *
     * @mbg.generated Tue Apr 14 13:57:25 CST 2020
     */
    int updateByPrimaryKey(RepairOrder record);
}