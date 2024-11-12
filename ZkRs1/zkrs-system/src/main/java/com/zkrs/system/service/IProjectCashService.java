package com.zkrs.system.service;

import java.util.List;

import com.zkrs.system.domain.ExportProjectCash;
import com.zkrs.system.domain.ProjectCash;
import com.zkrs.system.domain.ProjectInfo;

/**
 * 现金流入流出Service接口
 * 
 * @author zkrs
 * @date 2022-10-08
 */
public interface IProjectCashService 
{
    /**
     * 查询现金流入流出
     * 
     * @param id 现金流入流出主键
     * @return 现金流入流出
     */
    public ProjectCash selectProjectCashById(Long id);

    /**
     * 查询现金流入流出列表
     * 
     * @param projectCash 现金流入流出
     * @return 现金流入流出集合
     */
    public List<ProjectCash> selectProjectCashList(ProjectCash projectCash);

    /**
     * 新增现金流入流出
     * 
     * @param projectCash 现金流入流出
     * @return 结果
     */
    public int insertProjectCash(ProjectCash projectCash);

    /**
     * 修改现金流入流出
     * 
     * @param projectCash 现金流入流出
     * @return 结果
     */
    public int updateProjectCash(ProjectCash projectCash);

    /**
     * 批量删除现金流入流出
     * 
     * @param ids 需要删除的现金流入流出主键集合
     * @return 结果
     */
    public int deleteProjectCashByIds(Long[] ids);

    /**
     * 删除现金流入流出信息
     * 
     * @param id 现金流入流出主键
     * @return 结果
     */
    public int deleteProjectCashById(Long id);

    //导出
    List<ExportProjectCash> selectProjectInfoList(ProjectInfo projectInfo);

    List<ExportProjectCash> selectProjectInfoListCashIn(ProjectInfo projectInfo);
}
