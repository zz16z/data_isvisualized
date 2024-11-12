package com.zkrs.system.service;

import java.util.List;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.FinancingStill;
import com.zkrs.system.domain.FinancingStillVerify;

/**
 * 还款情况Service接口
 * 
 * @author zkrs
 * @date 2022-07-23
 */
public interface IFinancingStillService 
{
    /**
     * 查询还款情况
     * 
     * @param id 还款情况主键
     * @return 还款情况
     */
    public FinancingStill selectFinancingStillById(Long id);

    /**
     * 查询还款情况列表
     * 
     * @param financingStill 还款情况
     * @return 还款情况集合
     */
    public List<FinancingStill> selectFinancingStillList(FinancingStill financingStill);

    /**
     * 新增还款情况
     * 
     * @param financingStill 还款情况
     * @return 结果
     */
    public int insertFinancingStill(FinancingStill financingStill);

    /**
     * 修改还款情况
     * 
     * @param financingStill 还款情况
     * @return 结果
     */
    public int updateFinancingStill(FinancingStill financingStill);

    /**
     * 批量删除还款情况
     * 
     * @param ids 需要删除的还款情况主键集合
     * @return 结果
     */
    public int deleteFinancingStillByIds(Long[] ids);

    /**
     * 删除还款情况信息
     * 
     * @param id 还款情况主键
     * @return 结果
     */
    public int deleteFinancingStillById(Long id);


    /**
     * 融资时间导入
     *
     * @param listData 融资时间导入
     * @return 结果AjaxResult
     */
    public AjaxResult importBox(List<FinancingStillVerify> listData, String xmid, String msg_id,String isYjSj);

    /*
      * 新增融资时间
     *
             * @param financingDate 更新添加
     * @return 结果
     */
    public AjaxResult insertFinancingDateList(List<FinancingStill> financingStill) throws Exception;


}
