package com.zkrs.system.mapper;

import java.util.List;

import com.zkrs.system.domain.ExportFinancingStill;
import com.zkrs.system.domain.ExportRepayment;
import com.zkrs.system.domain.ProjectFinancingRepayment;
import com.zkrs.system.domain.ProjectInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 融资还款Mapper接口
 * 
 * @author zkrs
 * @date 2022-10-14
 */
public interface ProjectFinancingRepaymentMapper 
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
     * 删除融资还款
     * 
     * @param financingId 融资还款主键
     * @return 结果
     */
    public int deleteProjectFinancingRepaymentByFinancingId(Long financingId);

    /**
     * 批量删除融资还款
     * 
     * @param financingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectFinancingRepaymentByFinancingIds(Long[] financingIds);

    Integer selectIsExistRepayment(@Param("projectInfo") ProjectFinancingRepayment projectInfo);

    void updateProjectFinancingRepaymentById(ProjectFinancingRepayment listDatum);

    void deleteProjectFinancingRepayment(Long[] xmrzIds);

    int deleteProjectFinancingRepaymentByIds(Long[] ids);

    List<ExportFinancingStill> selectProjectInfoList(@Param("projectInfo") ProjectInfo projectInfo);
}
