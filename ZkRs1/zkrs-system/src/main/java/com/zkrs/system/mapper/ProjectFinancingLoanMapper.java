package com.zkrs.system.mapper;

import java.util.List;

import com.zkrs.system.domain.FinancingInfo;
import com.zkrs.system.domain.ProjectFinancingLoan;
import org.apache.ibatis.annotations.Param;

/**
 * 融资放款Mapper接口
 * 
 * @author zkrs
 * @date 2022-10-13
 */
public interface ProjectFinancingLoanMapper 
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
     * 删除融资放款
     * 
     * @param financingId 融资放款主键
     * @return 结果
     */
    public int deleteProjectFinancingLoanByFinancingId(Long financingId);

    /**
     * 批量删除融资放款
     * 
     * @param financingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectFinancingLoanByFinancingIds(Long[] id);

    void importBoxProjectFinancingLoan(FinancingInfo financingInfo);

    Integer isExistProject(@Param("financingInfo") FinancingInfo financingInfo);

    void updateProjectFinancingLoanInfo(FinancingInfo financingInfo);

    void insertFinancingLoanList(@Param("listData") List<ProjectFinancingLoan> listData);

    int deleteProjectFinancingLoanByIds(Long[] ids);

    void deleteProjectFinancingLoan(Long[] xmrzIds);
}
