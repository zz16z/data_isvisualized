package com.zkrs.system.mapper;

import java.util.List;

import com.zkrs.system.domain.ExportProjectCash;
import com.zkrs.system.domain.ImportProjectCash;
import com.zkrs.system.domain.ProjectCash;
import com.zkrs.system.domain.ProjectInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 现金流入流出Mapper接口
 * 
 * @author zkrs
 * @date 2022-10-08
 */
public interface ProjectCashMapper 
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
    public int updateProjectCashTo(ImportProjectCash projectCash);

    /**
     * 删除现金流入流出
     * 
     * @param id 现金流入流出主键
     * @return 结果
     */
    public int deleteProjectCashById(Long id);

    public int deleteProjectCashInfoById(Long id);

    /**
     * 批量删除现金流入流出
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectCashByIds(Long[] ids);

    public int insertProjectCashInfo(ProjectCash projectCash);

    public int updateProjectCashInfo(ProjectCash projectCash);

    public int updateProjectCashInfoTo(ImportProjectCash projectCash);
    public int deleteProjectCashInfoByIds(Long[] ids);

    List<ExportProjectCash> selectProjectInfoList(@Param("projectInfo") ProjectInfo projectInfo);

    void insertProjectCashImport(ImportProjectCash projectCash);
    void insertUpdateBatch(@Param("projectCash") ImportProjectCash projectCash);

    Integer selectProjectInfoChekCashIn(@Param("projectCash") ImportProjectCash projectCash);



    void insertProjectCashInfoImport(ImportProjectCash projectCash);
    void insertUpdateBatchCashInfo(@Param("projectCash") ImportProjectCash projectCash);

    List<ExportProjectCash> selectProjectInfoListCashIn(@Param("projectInfo")ProjectInfo projectInfo);
}
