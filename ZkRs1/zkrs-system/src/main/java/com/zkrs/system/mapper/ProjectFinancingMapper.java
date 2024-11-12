package com.zkrs.system.mapper;

import java.util.List;

import com.zkrs.system.domain.*;
import com.zkrs.system.domain.dto.ProjectFinancingDto;
import org.apache.ibatis.annotations.Param;

/**
 * 项目融资Mapper接口
 * 
 * @author zkrs
 * @date 2022-07-01
 */
public interface ProjectFinancingMapper 
{
    /**
     * 查询项目融资
     * 
     * @param xmrzId 项目融资主键
     * @return 项目融资
     */
    public ProjectFinancing selectProjectFinancingByXmrzId(Long xmrzId);

    /**
     * 查询项目融资列表
     * 
     * @param projectFinancing 项目融资
     * @return 项目融资集合
     */
    public List<ProjectFinancing> selectProjectFinancingList(ProjectFinancing projectFinancing);


    /**
     * 导出项目融资列表
     *
     * @param projectFinancing 项目融资
     * @return 项目融资集合
     */
    public List<ProjectFinancing> selectProjectFinancingExport(ProjectFinancing projectFinancing);

    /**
     * 新增项目融资
     * 
     * @param projectFinancing 项目融资
     * @return 结果
     *///@Param("projectFinancing")
    public int insertProjectFinancing( ProjectFinancing projectFinancing);

    /**
     * 修改项目融资
     * 
     * @param projectFinancing 项目融资
     * @return 结果
     */
    public int updateProjectFinancing(ProjectFinancing projectFinancing);

    /**
     * 删除项目融资
     * 
     * @param xmrzId 项目融资主键
     * @return 结果
     */
    public int deleteProjectFinancingByXmrzId(Long xmrzId);

    /**
     * 批量删除项目融资
     * 
     * @param xmrzIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectFinancingByXmrzIds(Long[] xmrzIds);



    /**
     * 新增项目融资批量
     *
     * @param projectFinancingLits 项目融资
     * @return 结果
     */
    public int insertProjectFinancingAll(List<ProjectFinancingDto>  list);


    /**
     * 新增项目融资批量
     *
     * @param ProjectFinancing 项目融资
     * @return 结果
     */
    public int importBox(@Param("listData") List<ProjectFinancing>  listData);


    /**
     * 删除项目融资
     *
     * @param 项目id 项目融资主键
     * @return 结果
     */
    public int deleteProjectFinancingByXmrzIdJl(String xmrzId);

    /**
     * 批量删除项目融资
     *
     * @param 项目id 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectFinancingByXmrzIdsJl(String[] xmrzIds);

    /**
     * 批量删除项目融资
     *
     * @param 项目id 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectFinancingByXmrzIdJlL(String[] xmrzIds);


    /**
     * 批量删除项目融资
     *
     * @param 项目id 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectFinancingByStil(String[] xmrzIds);

    /**
     * 导入
     */
    void importBoxFinancingInfo(FinancingInfo financingInfo);

    /**
     * 根据项目ID查融资ID
     */
    Long selectByProjectId(@Param("xmrzProjectId") String xmrzProjectId);

    /**
     * 导出
     * @param projectInfo
     * @return
     */
    List<ExportFinancingDate> selectProjectInfoList(@Param("projectInfo") ProjectInfo projectInfo);

    Integer isExistProject(FinancingInfo financingInfo);

    void updateProjectFinancingInfo(FinancingInfo financingInfo);

    Integer selectProjectFinancingByProjectIdAndRzjg(@Param("projectId") String projectId, @Param("rzjg")String rzjg);


    int  organNameChek(@Param("projectFinancing") ProjectFinancing projectFinancing);
}
