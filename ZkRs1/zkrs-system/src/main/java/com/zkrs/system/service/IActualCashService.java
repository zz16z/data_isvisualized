package com.zkrs.system.service;

import java.util.List;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.*;

/**
 * 现金流实际情况Service接口
 * 
 * @author zkrs
 * @date 2022-07-01
 */
public interface IActualCashService 
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
     * 查询现金流实际情况列表id
     * 
     * @param actualCash 现金流实际情况
     * @return 现金流实际情况集合
     */
    public List<ActualCash> selectActualCashList(ActualCash actualCash);

    /**
     * 导出现金流实际情况列表id
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
     * 批量删除现金流实际情况
     * 
     * @param xjlsjqkIds 需要删除的现金流实际情况主键集合
     * @return 结果
     */
    public int deleteActualCashByXjlsjqkIds(Long[] xjlsjqkIds);

    /**
     * 删除现金流实际情况信息
     * 
     * @param xjlsjqkId 现金流实际情况主键
     * @return 结果
     */
    public int deleteActualCashByXjlsjqkId(Long xjlsjqkId);


    /**
     * 金流实际情况信息导入
     *
     * @param listData 金流实际情况信息计导入
     * @return 结果AjaxResult
     */
    public AjaxResult importBox(List<ActualCashVerify> listData, String xmid, String  xjlsjqkYjxjlId);

    /**
     * 导出查询
     *
     * @param listData 金流实际情况信息计导入
     * @return 结果AjaxResult
     */
    List<ExportManagementCash> selectProjectInfoList(ProjectInfo projectInfo);
}
