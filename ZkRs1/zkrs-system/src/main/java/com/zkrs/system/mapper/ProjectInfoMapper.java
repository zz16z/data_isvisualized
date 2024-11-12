package com.zkrs.system.mapper;

import java.util.List;
import java.util.Map;

import com.zkrs.system.domain.ElectricityStatistics;
import com.zkrs.system.domain.ProjectInfo;
import com.zkrs.system.domain.ProjectResult;
import org.apache.ibatis.annotations.Param;

/**
 * 项目情况Mapper接口
 * 
 * @author zkrs
 * @date 2022-06-30
 */
public interface ProjectInfoMapper 
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
     * 删除项目情况
     * 
     * @param xmqkProjectId 项目情况主键
     * @return 结果
     */
    public int deleteProjectInfoByXmqkProjectId(String xmqkProjectId);

    /**
     * 批量删除项目情况
     * 
     * @param xmqkProjectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectInfoByXmqkProjectIds(String[] xmqkProjectIds);



    /**
     * 查询项目情况列表缓存
     *
     * @param projectInfo 项目情况缓存
     * @return 项目情况缓存集合
     */
    public List<ProjectInfo> selectProjectInfoCache(ProjectInfo projectInfo);

    /**
     * 项目情况导入
     *
     * @param listData 电项目情况导入
     * @return 结果AjaxResult
     */
    public int importBox(@Param("listData") List<ProjectInfo> listData);

    /**
     * 项目名称重复验证
     *
     * @param listData 电项目情况导入
     * @return 结果AjaxResult
     */
    public  List<ProjectInfo> checkByname(@Param("listData") List<ProjectInfo> listData);

    /**
     * 查项目Id和名称
     *
     * @return 结果Map
     */
    public List<ProjectResult> getAllNameAndId();

    void importBoxConstruction(@Param("projectInfo")ProjectInfo projectInfo);

    int selectProjectIsExist(@Param("xmqkXmmc")String xmqkXmmc);

    int updateProjectInfoByXmmc(ProjectInfo projectInfo);
}
