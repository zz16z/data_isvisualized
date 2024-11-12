package com.zkrs.system.service.impl;

import java.util.List;

import com.zkrs.system.domain.ExportProjectCash;
import com.zkrs.system.domain.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zkrs.system.mapper.ProjectCashMapper;
import com.zkrs.system.domain.ProjectCash;
import com.zkrs.system.service.IProjectCashService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 现金流入流出Service业务层处理
 * 
 * @author zkrs
 * @date 2022-10-08
 */
@Service
public class ProjectCashServiceImpl implements IProjectCashService 
{
    @Autowired
    private ProjectCashMapper projectCashMapper;

    /**
     * 查询现金流入流出
     * 
     * @param id 现金流入流出主键
     * @return 现金流入流出
     */
    @Override
    public ProjectCash selectProjectCashById(Long id)
    {
        return projectCashMapper.selectProjectCashById(id);
    }

    /**
     * 查询现金流入流出列表
     * 
     * @param projectCash 现金流入流出
     * @return 现金流入流出
     */
    @Override
    public List<ProjectCash> selectProjectCashList(ProjectCash projectCash)
    {
        return projectCashMapper.selectProjectCashList(projectCash);
    }

    /**
     * 新增现金流入流出
     * 
     * @param projectCash 现金流入流出
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertProjectCash(ProjectCash projectCash)
    {
        int i = projectCashMapper.insertProjectCash(projectCash);
        int i1 = projectCashMapper.insertProjectCashInfo(projectCash);
        if(i>0 && i1>0){
            return 1;
        }
        return -1;
    }

    /**
     * 修改现金流入流出
     * 
     * @param projectCash 现金流入流出
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateProjectCash(ProjectCash projectCash)
    {
        int i = projectCashMapper.updateProjectCash(projectCash);
        int i1 = projectCashMapper.updateProjectCashInfo(projectCash);
        if(i>0 && i1>0){
            return 1;
        }
        return -1;
    }

    /**
     * 批量删除现金流入流出
     * 
     * @param ids 需要删除的现金流入流出主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteProjectCashByIds(Long[] ids)
    {
        int i= projectCashMapper.deleteProjectCashByIds(ids);
        int i1 = projectCashMapper.deleteProjectCashInfoByIds(ids);
        if(i>0 && i1>0){
            return 1;
        }
        return -1;
    }

    /**
     * 删除现金流入流出信息
     * 
     * @param id 现金流入流出主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteProjectCashById(Long id)
    {
        int i = projectCashMapper.deleteProjectCashById(id);
        int i1 = projectCashMapper.deleteProjectCashInfoById(id);
        if(i>0 && i1>0){
            return 1;
        }
        return -1;
    }

    @Override
    public List<ExportProjectCash> selectProjectInfoList(ProjectInfo projectInfo) {
        return  projectCashMapper.selectProjectInfoList(projectInfo);
    }

    @Override
    public List<ExportProjectCash> selectProjectInfoListCashIn(ProjectInfo projectInfo) {
        return projectCashMapper.selectProjectInfoListCashIn(projectInfo);
    }
}
