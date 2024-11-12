package com.zkrs.system.service;

import java.util.List;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.ProjectFinancingLoan;

/**
 * 融资放款Service接口
 * 
 * @author zkrs
 * @date 2022-10-13
 */
public interface IProjectFinancingLoanService 
{
    /**
     * 查询融资放款
     * 
     * @param financingId 融资放款主键
     * @return 融资放款
     */
    public ProjectFinancingLoan selectProjectFinancingLoanByFinancingId(Long financingId);

    /**
     * 查询融资放款列表
     * 
     * @param projectFinancingLoan 融资放款
     * @return 融资放款集合
     */
    public List<ProjectFinancingLoan> selectProjectFinancingLoanList(ProjectFinancingLoan projectFinancingLoan);

    /**
     * 新增融资放款
     * 
     * @param projectFinancingLoan 融资放款
     * @return 结果
     */
    public int insertProjectFinancingLoan(ProjectFinancingLoan projectFinancingLoan);

    /**
     * 修改融资放款
     * 
     * @param projectFinancingLoan 融资放款
     * @return 结果
     */
    public int updateProjectFinancingLoan(ProjectFinancingLoan projectFinancingLoan);

    /**
     * 批量删除融资放款
     * 
     * @param financingIds 需要删除的融资放款主键集合
     * @return 结果
     */
    public int deleteProjectFinancingLoanByFinancingIds(Long[] financingIds);

    /**
     * 删除融资放款信息
     * 
     * @param financingId 融资放款主键
     * @return 结果
     */
    public int deleteProjectFinancingLoanByFinancingId(Long financingId);

    AjaxResult insertFinancingLoanList(List<ProjectFinancingLoan> listData);

    int deleteProjectFinancingLoanByIds(Long[] ids);
}
