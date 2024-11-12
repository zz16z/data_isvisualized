package com.zkrs.system.mapper;

import java.util.List;

import com.zkrs.system.domain.*;
import org.apache.ibatis.annotations.Param;

/**
 * 融资时间Mapper接口
 * 
 * @author zkrs
 * @date 2022-07-07
 */
public interface FinancingDateMapper 
{
    /**
     * 查询融资时间
     * 
     * @param id 融资时间主键
     * @return 融资时间
     */
    public FinancingDate selectFinancingDateById(Long id);

    /**
     * 查询融资时间列表
     * 
     * @param financingDate 融资时间
     * @return 融资时间集合
     */
    public List<FinancingDate> selectFinancingDateList(FinancingDate financingDate);

    /**
     * 查询融资时间列表
     *
     * @param financingDate 融资时间
     * @return 融资时间集合
     */
    public List<FinancingDate> selectFinancingDateExport(FinancingDate financingDate);



    /**
     * 新增融资时间
     * 
     * @param financingDate 融资时间
     * @return 结果
     */
    public int insertFinancingDate(FinancingDate financingDate);


    /**
     * 新增融资时间
     *
     * @param financingDateList 更新添加
     * @return 结果
     */

    int  insertFinancingDateList(@Param("financingDateList") List<FinancingDate> financingDateList);

    /**
     * 修改融资时间
     * 
     * @param financingDate 融资时间
     * @return 结果
     */
    public int updateFinancingDate(FinancingDate financingDate);

    /**
     * 删除融资时间
     * 
     * @param id 融资时间主键
     * @return 结果
     */
    public int deleteFinancingDateById(Long id);

    /**
     * 批量删除融资时间
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinancingDateByIds(Long[] ids);


    /**
     * 融资时间导入
     *
     * @param  融资时间导入
     * @return 结果
     */
    public int importBox(@Param("listData") List<FinancingDate> listData);


    public int updateBatch(@Param("listData") List<FinancingDate> listData);


    public int insertUpdateBatch(@Param("listData") List<FinancingDate> listData);

    public int insertUpdateBatchSql(@Param("insertSql") String insertSql);

    /**
     * 删除融资时间
     *
     * @param id 融资表id
     * @return 结果
     */
    public int deleteFinancingDateByIdJl(Long id);

    /**
     * 批量删除融资时间
     *
     * @param ids 融资表id
     * @return 结果
     */
    public int deleteFinancingDateByIdsJl(Long[] ids);


    /**
     * 删除还款
     *
     * @param id 融资表id
     * @return 结果
     */
    public int deleteFinancingDateByIdJlHk(Long id);

    /**
     * 批量删除融资 删除还款
     *
     * @param ids 融资表id
     * @return 结果
     */
    public int deleteFinancingDateByIdsJlHK(Long[] ids);


    void importBoxFinancingInfo(ExpectedFinancing financingInfo);

    void importBoxTrueFinancingInfo(ExpectedTrueFinancing financingInfo);

    List<ExportExpectedFinancing> selectProjectInfoList(@Param("projectInfo")ProjectInfo projectInfo);

    List<ExportExpectedTrueFinancing> selectProjectInfoListTrue(@Param("projectInfo")ProjectInfo projectInfo);
}
