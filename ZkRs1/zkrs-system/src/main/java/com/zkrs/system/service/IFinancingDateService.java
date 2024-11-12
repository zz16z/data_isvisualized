package com.zkrs.system.service;

import java.util.List;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.*;

/**
 * 融资时间Service接口
 * 
 * @author zkrs
 * @date 2022-07-07
 */
public interface IFinancingDateService {
    /**
     * 查询融资时间
     *
     * @param id 融资时间主键
     * @return 融资时间
     */
    public FinancingDate selectFinancingDateById(Long id);

    /**
     * 查询融资时间列表
     *
     * @param financingDate 融资时间
     * @return 融资时间集合
     */
    public List<FinancingDate> selectFinancingDateList(FinancingDate financingDate);

    /**
     * 导出融资时间列表
     *
     * @param financingDate 融资时间
     * @return 融资时间集合
     */
    public List<FinancingDate> selectFinancingDateExport(FinancingDate financingDate);



    /**
     * 新增融资时间
     *
     * @param financingDate 融资时间
     * @return 结果
     */
    public int insertFinancingDate(FinancingDate financingDate);

    /**
     * 新增融资时间
     *
     * @param financingDate 更新添加
     * @return 结果
     */
    public AjaxResult insertFinancingDateList(List<FinancingDate> financingDate) throws Exception;


    /**
     * 修改融资时间
     *
     * @param financingDate 融资时间
     * @return 结果
     */
    public int updateFinancingDate(FinancingDate financingDate);

    /**
     * 批量删除融资时间
     *
     * @param ids 需要删除的融资时间主键集合
     * @return 结果
     */
    public int deleteFinancingDateByIds(Long[] ids);

    /**
     * 删除融资时间信息
     *
     * @param id 融资时间主键
     * @return 结果
     */
    public int deleteFinancingDateById(Long id);


    /**
     * 融资时间导入
     *
     * @param listData 融资时间导入
     * @return 结果AjaxResult
     */
    public AjaxResult importBox(List<FinancingDateVerify> listData, String xmid,String msg_id,String  isYjSj);

    /**
     * 导出
     * @param projectInfo
     * @return
     */
    List<ExportExpectedFinancing> selectProjectInfoList(ProjectInfo projectInfo);
    /**
     * 导出
     * @param projectInfo
     * @return
     */
    List<ExportExpectedTrueFinancing> selectProjectInfoListTrue(ProjectInfo projectInfo);
}