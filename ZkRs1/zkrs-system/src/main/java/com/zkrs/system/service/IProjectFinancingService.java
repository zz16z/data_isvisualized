package com.zkrs.system.service;

import java.util.List;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.*;
import com.zkrs.system.domain.dto.ProjectFinancingDto;

/**
 * 项目融资Service接口
 *
 * @author zkrs
 * @date 2022-07-01
 */
public interface IProjectFinancingService
{
    /**
     * 查询项目融资
     *
     * @param xmrzId 项目融资主键
     * @return 项目融资
     */
    public ProjectFinancing selectProjectFinancingByXmrzId(Long xmrzId);

    /**
     * 查询项目融资列表
     *
     * @param projectFinancing 项目融资
     * @return 项目融资集合
     */
    public List<ProjectFinancing> selectProjectFinancingList(ProjectFinancing projectFinancing);


    /**
     * 导出项目融资列表
     *
     * @param projectFinancing 项目融资
     * @return 项目融资集合
     */
    public List<ProjectFinancing> selectProjectFinancingExport(ProjectFinancing projectFinancing);




    /**
     * 新增项目融资
     *
     * @param projectFinancing 项目融资
     * @return 结果
     */
    public int insertProjectFinancing(ProjectFinancing projectFinancing);

    /**
     * 修改项目融资
     *
     * @param projectFinancing 项目融资
     * @return 结果
     */
    public int updateProjectFinancing(ProjectFinancing projectFinancing);

    /**
     * 批量删除项目融资
     *
     * @param xmrzIds 需要删除的项目融资主键集合
     * @return 结果
     */
    public int deleteProjectFinancingByXmrzIds(Long[] xmrzIds);

    /**
     * 删除项目融资信息
     *
     * @param xmrzId 项目融资主键
     * @return 结果
     */
    public int deleteProjectFinancingByXmrzId(Long xmrzId);


    /**
     * 新增项目融资批量
     *
     * @param projectFinancingLits 项目融资
     * @return 结果
     */
    public int insertProjectFinancingAll(List<ProjectFinancingDto> projectFinancingLits);

    /**
     * 新增项目融资批量
     *
     * @param listData 电量统计导入
     * @return 结果AjaxResult
     */
    public AjaxResult importBox(List<ProjectFinancingVerify> listData, String xmid);

    /**
     * 导出
     * @param projectInfo
     * @return
     */
    List<ExportFinancingInfo> selectProjectInfoList(ProjectInfo projectInfo);

    /*
      organ
      机构名称验证
     */
    int  organNameChek(ProjectFinancing projectFinancing);
}
