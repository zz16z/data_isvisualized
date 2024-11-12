package com.zkrs.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zkrs.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zkrs.system.mapper.ProjectFinancingLoanMapper;
import com.zkrs.system.domain.ProjectFinancingLoan;
import com.zkrs.system.service.IProjectFinancingLoanService;

/**
 * 融资放款Service业务层处理
 * 
 * @author zkrs
 * @date 2022-10-13
 */
@Service
public class ProjectFinancingLoanServiceImpl implements IProjectFinancingLoanService 
{
    @Autowired
    private ProjectFinancingLoanMapper projectFinancingLoanMapper;

    /**
     * 查询融资放款
     * 
     * @param financingId 融资放款主键
     * @return 融资放款
     */
    @Override
    public ProjectFinancingLoan selectProjectFinancingLoanByFinancingId(Long financingId)
    {
        return projectFinancingLoanMapper.selectProjectFinancingLoanByFinancingId(financingId);
    }

    /**
     * 查询融资放款列表
     * 
     * @param projectFinancingLoan 融资放款
     * @return 融资放款
     */
    @Override
    public List<ProjectFinancingLoan> selectProjectFinancingLoanList(ProjectFinancingLoan projectFinancingLoan)
    {
        return projectFinancingLoanMapper.selectProjectFinancingLoanList(projectFinancingLoan);
    }

    /**
     * 新增融资放款
     * 
     * @param projectFinancingLoan 融资放款
     * @return 结果
     */
    @Override
    public int insertProjectFinancingLoan(ProjectFinancingLoan projectFinancingLoan)
    {
        return projectFinancingLoanMapper.insertProjectFinancingLoan(projectFinancingLoan);
    }

    /**
     * 修改融资放款
     * 
     * @param projectFinancingLoan 融资放款
     * @return 结果
     */
    @Override
    public int updateProjectFinancingLoan(ProjectFinancingLoan projectFinancingLoan)
    {
        return projectFinancingLoanMapper.updateProjectFinancingLoan(projectFinancingLoan);
    }

    /**
     * 批量删除融资放款
     * 
     * @param financingIds 需要删除的融资放款主键
     * @return 结果
     */
    @Override
    public int deleteProjectFinancingLoanByFinancingIds(Long[] financingIds)
    {
        return projectFinancingLoanMapper.deleteProjectFinancingLoanByFinancingIds(financingIds);
    }

    /**
     * 删除融资放款信息
     * 
     * @param financingId 融资放款主键
     * @return 结果
     */
    @Override
    public int deleteProjectFinancingLoanByFinancingId(Long financingId)
    {
        return projectFinancingLoanMapper.deleteProjectFinancingLoanByFinancingId(financingId);
    }

    @Override
    public AjaxResult insertFinancingLoanList(List<ProjectFinancingLoan> listData) {
        try{
            //自验证前端传过来集合中有没有重复的月份
            List<String> dateList = new ArrayList<>();
            listData.forEach(item->{
                dateList.add(item.getLoanYear());
            });
            List<String> dateList2 = new ArrayList<>();
            for (String s : dateList) {
                if (dateList2.contains(s)) {
                    return AjaxResult.error("年份"+s+"只能存在一条数据");
                }
                dateList2.add(s);
            }
            for (ProjectFinancingLoan listDatum : listData) {
                if(listDatum.getId()!=null){
                    projectFinancingLoanMapper.updateProjectFinancingLoan(listDatum);
                }else{
                    projectFinancingLoanMapper.insertProjectFinancingLoan(listDatum);
                }
            }

            return AjaxResult.success("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("添加失败");
        }
    }

    @Override
    public int deleteProjectFinancingLoanByIds(Long[] ids)
    {
        return projectFinancingLoanMapper.deleteProjectFinancingLoanByIds(ids);
    }
}
