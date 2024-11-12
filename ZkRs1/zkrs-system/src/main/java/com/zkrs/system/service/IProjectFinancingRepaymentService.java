package com.zkrs.system.service;

import java.util.List;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.ExportFinancingStill;
import com.zkrs.system.domain.ExportRepayment;
import com.zkrs.system.domain.ProjectFinancingRepayment;
import com.zkrs.system.domain.ProjectInfo;

/**
 * 融资还款Service接口
 * 
 * @author zkrs
 * @date 2022-10-14
 */
public interface IProjectFinancingRepaymentService 
{
    /**
     * 查询融资还款
     * 
     * @param financingId 融资还款主键
     * @return 融资还款
     */
    public ProjectFinancingRepayment selectProjectFinancingRepaymentByFinancingId(Long financingId);

    /**
     * 查询融资还款列表
     * 
     * @param projectFinancingRepayment 融资还款
     * @return 融资还款集合
     */
    public List<ProjectFinancingRepayment> selectProjectFinancingRepaymentList(ProjectFinancingRepayment projectFinancingRepayment);

    /**
     * 新增融资还款
     * 
     * @param projectFinancingRepayment 融资还款
     * @return 结果
     */
    public int insertProjectFinancingRepayment(ProjectFinancingRepayment projectFinancingRepayment);

    /**
     * 修改融资还款
     * 
     * @param projectFinancingRepayment 融资还款
     * @return 结果
     */
    public int updateProjectFinancingRepayment(ProjectFinancingRepayment projectFinancingRepayment);

    /**
     * 批量删除融资还款
     * 
     * @param financingIds 需要删除的融资还款主键集合
     * @return 结果
     */
    public int deleteProjectFinancingRepaymentByFinancingIds(Long[] financingIds);

    /**
     * 删除融资还款信息
     * 
     * @param financingId 融资还款主键
     * @return 结果
     */
    public int deleteProjectFinancingRepaymentByFinancingId(Long financingId);

    AjaxResult insertFinancingRepaymentList(List<ProjectFinancingRepayment> listData);

    int deleteProjectFinancingRepaymentByIds(Long[] ids);

    List<ExportRepayment> selectProjectInfoList(ProjectInfo projectInfo);
}
