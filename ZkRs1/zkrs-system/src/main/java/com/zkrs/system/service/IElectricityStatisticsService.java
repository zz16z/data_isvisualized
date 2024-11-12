package com.zkrs.system.service;

import java.util.List;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.ElectricityStatistics;
import com.zkrs.system.domain.ElectricityStatisticsVerify;
import com.zkrs.system.domain.ExportElectricity;
import com.zkrs.system.domain.ProjectInfo;

/**
 * 电量统计Service接口
 * 
 * @author zkrs
 * @date 2022-07-01
 */
public interface IElectricityStatisticsService 
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
     * 查询电量统计导出列表
     *
     * @param electricityStatistics 电量统计导出列表
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
     * 批量删除电量统计
     * 
     * @param dltjIds 需要删除的电量统计主键集合
     * @return 结果
     */
    public int deleteElectricityStatisticsByDltjIds(Long[] dltjIds);

    /**
     * 删除电量统计信息
     * 
     * @param dltjId 电量统计主键
     * @return 结果
     */
    public int deleteElectricityStatisticsByDltjId(Long dltjId);


    /**
     * 电量统计导入
     *
     * @param listData 电量统计导入
     * @return 结果AjaxResult
     */
    public AjaxResult importBox(List<ElectricityStatisticsVerify> listData, String xmid);


    List<ExportElectricity> selectProjectInfoList(ProjectInfo projectInfo);
}
