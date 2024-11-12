package com.zkrs.system.service;

import java.util.List;
import java.util.Map;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.*;
import org.apache.ibatis.annotations.Param;

/**
 * 项目情况Service接口
 * 
 * @author zkrs
 * @date 2022-06-30
 */
public interface IProjectInfoService 
{
    /**
     * 查询项目情况
     * 
     * @param xmqkProjectId 项目情况主键
     * @return 项目情况
     */
    public ProjectInfo selectProjectInfoByXmqkProjectId(String xmqkProjectId);

    /**
     * 查询项目情况列表
     * 
     * @param projectInfo 项目情况
     * @return 项目情况集合
     */
    public List<ProjectInfo> selectProjectInfoList(ProjectInfo projectInfo);



    /**
     * 新增项目情况
     * 
     * @param projectInfo 项目情况
     * @return 结果
     */
    public int insertProjectInfo(ProjectInfo projectInfo);

    /**
     * 修改项目情况
     * 
     * @param projectInfo 项目情况
     * @return 结果
     */
    public int updateProjectInfo(ProjectInfo projectInfo);

    /**
     * 批量删除项目情况
     * 
     * @param xmqkProjectIds 需要删除的项目情况主键集合
     * @return 结果
     */
    public int deleteProjectInfoByXmqkProjectIds(String[] xmqkProjectIds);

    /**
     * 删除项目情况信息
     * 
     * @param xmqkProjectId 项目情况主键
     * @return 结果
     */
    public int deleteProjectInfoByXmqkProjectId(String xmqkProjectId);


    /**
     * 查询项目情况列表缓存
     *
     * @param projectInfo 项目情况缓存
     * @return 项目情况缓存集合
     */
    public List<ProjectInfo>  selectProjectInfoCache(ProjectInfo projectInfo);

    /**
     * 验证醒目名称
     */
    public List<ProjectInfo>  checkByname(List<ProjectInfo> listdata);




    /**
     * 项目情况统计导入
     *
     * @param listData 项目情况统计导入
     * @return 结果AjaxResult
     */
    public AjaxResult importBox(List<ProjectInfoVerify> listData, String xmid);


    /**
     *  施工情况统计导入
     *
     * @param listData 项目情况统计导入
     * @return 结果AjaxResult
     */
    AjaxResult importBoxConstruction(List<ProjectInfoVerify> listData);

    /**
     *  经营情况统计导入
     *
     * @param listData 项目情况统计导入
     * @return 结果AjaxResult
     */
    AjaxResult importBoxManagement(List<ManagementCash> listData);

    /**
     *  电量统计导入
     *
     * @param listData 项目情况统计导入
     * @return 结果AjaxResult
     */
    AjaxResult importBoxElectricity(List<ElectricityCash> listData);

    /**
     *  项目融资导入
     * @param listData 项目情况统计导入
     * @return 结果AjaxResult
     */
    AjaxResult importBoxFinancing(List<FinancingInfo> listData);

    /**
     *  项目预计融资导入
     * @param listData 项目情况统计导入
     * @return 结果AjaxResult
     */
    AjaxResult importBoxExpectedFinancing(List<ExpectedFinancing> listData);

    /**
     *  项目实际融资导入
     * @param listData 项目情况统计导入
     * @return 结果AjaxResult
     */
    AjaxResult importBoxExpectedTrueFinancing(List<ExpectedTrueFinancing> listData);

    AjaxResult importBoxProjectCash(List<ImportProjectCash> listData);

    AjaxResult importBoxProjectCashIn(List<ImportProjectCash> listData);

    AjaxResult importBoxRepayment(List<ProjectFinancingRepayment> listData);


}
