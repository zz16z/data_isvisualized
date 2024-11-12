package com.zkrs.system.mapper;

import java.util.List;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.ElectricityCash;
import com.zkrs.system.domain.ElectricityStatistics;
import com.zkrs.system.domain.ExportElectricity;
import com.zkrs.system.domain.ProjectInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 电量统计Mapper接口
 * 
 * @author zkrs
 * @date 2022-07-01
 */
public interface ElectricityStatisticsMapper 
{
    /**
     * 查询电量统计
     * 
     * @param dltjId 电量统计主键
     * @return 电量统计
     */
    public ElectricityStatistics selectElectricityStatisticsByDltjId(Long dltjId);

    /**
     * 查询电量统计列表
     * 
     * @param electricityStatistics 电量统计
     * @return 电量统计集合
     */
    public List<ElectricityStatistics> selectElectricityStatisticsList(ElectricityStatistics electricityStatistics);


    /**
     * 查询电量统计列表
     *
     * @param electricityStatistics 电量统计导出
     * @return 电量统计集合
     */
    public List<ElectricityStatistics> selectElectricityStatisticsExport(ElectricityStatistics electricityStatistics);



    /**
     * 新增电量统计
     * 
     * @param electricityStatistics 电量统计
     * @return 结果
     */
    public int insertElectricityStatistics(ElectricityStatistics electricityStatistics);

    /**
     * 修改电量统计
     * 
     * @param electricityStatistics 电量统计
     * @return 结果
     */
    public int updateElectricityStatistics(ElectricityStatistics electricityStatistics);

    /**
     * 删除电量统计
     * 
     * @param dltjId 电量统计主键
     * @return 结果
     */
    public int deleteElectricityStatisticsByDltjId(Long dltjId);

    /**
     * 批量删除电量统计
     * 
     * @param dltjIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteElectricityStatisticsByDltjIds(Long[] dltjIds);



    /**
     * 删除电量统计
     *
     * @param 项目id 电量统计主键
     * @return 结果
     */
    public int deleteElectricityStatisticsByDltjIdJl(String dltjId);

    /**
     * 批量删除电量统计
     *
     * @param 项目id 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteElectricityStatisticsByDltjIdsJl(String[] dltjIds);


    /**
     * 电量统计导入
     *
     * @param listData 电量统计导入
     * @return 结果AjaxResult
     */
    public int importBox(@Param("listData") List<ElectricityStatistics> listData);

    /**
     * 电量统计导入
     */
    void importBoxElectricityCash(@Param("electricityCash") ElectricityCash electricityCash);
    void insertUpdateBatch0(@Param("listData") List<ElectricityCash> listData);
    void insertUpdateBatch(@Param("electricityCash") ElectricityCash electricityCash);

    Integer selectProjectInfoChek(@Param("electricityCash") ElectricityCash electricityCash);

    Integer updateElectricity(@Param("electricityCash") ElectricityCash electricityCash);


    List<ExportElectricity> selectProjectInfoList(@Param("projectInfo")ProjectInfo projectInfo);



}
