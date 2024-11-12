package com.zkrs.system.mapper;

import java.util.List;
import com.zkrs.system.domain.ActualCash;
import com.zkrs.system.domain.ExportManagementCash;
import com.zkrs.system.domain.ManagementCash;
import com.zkrs.system.domain.ProjectInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 现金流实际情况Mapper接口
 * 
 * @author zkrs
 * @date 2022-07-01
 */
public interface ActualCashMapper 
{
    /**
     * 查询现金流实际情况
     * 
     * @param xjlsjqkId 现金流实际情况主键
     * @return 现金流实际情况
     */
    public ActualCash selectActualCashByXjlsjqkId(Long xjlsjqkId);


    /**
     * 根据关联id获取现金流实际情况详细信息
     *
     * @param id 根据关联id获取现金流实际情况详细信息
     * @return 根据关联id获取现金流实际情况详细信息
     */

    public ActualCash selectActualCashByxjlsjqkYjxjlId(String id);

    /**
     * 查询现金流实际情况列表
     * 
     * @param actualCash 现金流实际情况
     * @return 现金流实际情况集合
     */
    public List<ActualCash> selectActualCashList(ActualCash actualCash);



    /**
     * 导出现金流实际情况列表
     *
     * @param actualCash 现金流实际情况
     * @return 现金流实际情况集合
     */
    public List<ActualCash> selectActualCashexport(ActualCash actualCash);

    /**
     * 新增现金流实际情况
     * 
     * @param actualCash 现金流实际情况
     * @return 结果
     */
    public int insertActualCash(ActualCash actualCash);

    /**
     * 修改现金流实际情况
     * 
     * @param actualCash 现金流实际情况
     * @return 结果
     */
    public int updateActualCash(ActualCash actualCash);

    /**
     * 删除现金流实际情况
     * 
     * @param xjlsjqkId 现金流实际情况主键
     * @return 结果
     */
    public int deleteActualCashByXjlsjqkId(Long xjlsjqkId);

    /**
     * 批量删除现金流实际情况
     * 
     * @param xjlsjqkIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActualCashByXjlsjqkIds(Long[] xjlsjqkIds);


    /**
     * 现金流实际情况导入
     *
     * @param listData 需要删除的数据主键集合
     * @return 结果
     */
    public int importBox(@Param("listData") List<ActualCash> listData);


    /**
     * 删除现金流实际情况
     *
     * @param xjlsjqkId 现金流实际情况主键
     * @return 结果
     */
    public int deleteActualCashByXjlsjqkIdJl(String  xjlsjqkId);

    /**
     * 批量删除现金流实际情况
     *
     * @param xjlsjqkIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActualCashByXjlsjqkIdsJl(String[] xjlsjqkIds);


    /**
     * 批量删除现金流实际情况
     *
     * @param xjlsjqkIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActualCashByXjlsjqkIdsJll(Long[] xjlsjqkIds);
     void addBatch(@Param("listData")List<ManagementCash> listData);
    void updateBatch(@Param("listData")List<ManagementCash> listData);

    /**
     * 导入
     */
    void importBoxManagement(@Param("managementCash")ManagementCash managementCash);
    void insertUpdateBatch(@Param("managementCash")ManagementCash managementCash);
    /**
     * 导出
     */
    List<ExportManagementCash> selectProjectInfoList(@Param("projectInfo")ProjectInfo projectInfo);

}
