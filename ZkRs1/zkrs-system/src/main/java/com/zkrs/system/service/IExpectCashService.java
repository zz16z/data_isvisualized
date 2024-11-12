package com.zkrs.system.service;

import java.util.List;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.ExpectCash;
import com.zkrs.system.domain.ExpectCashVerify;
import com.zkrs.system.domain.dto.ExpectCashDto;

/**
 * 现金流预计情况Service接口
 * 
 * @author zkrs
 * @date 2022-07-01
 */
public interface IExpectCashService 
{
    /**
     * 查询现金流预计情况
     * 
     * @param xjlyjqkId 现金流预计情况主键
     * @return 现金流预计情况
     */
    public ExpectCash selectExpectCashByXjlyjqkId(Long xjlyjqkId);

    /**
     * 查询现金流预计情况列表
     * 
     * @param expectCash 现金流预计情况
     * @return 现金流预计情况集合
     */
    public List<ExpectCash> selectExpectCashList(ExpectCash expectCash);

    /**
     * 导出现金流预计情况列表
     *
     * @param expectCash 现金流预计情况
     * @return 现金流预计情况集合
     */
    public List<ExpectCash> selectExpectCashExport(ExpectCash expectCash);

    /**
     * 查询现金流预计情况列表合并
     *
     * @param expectCash 现金流预计情况合并
     * @return 现金流预计情况集合合并
     */
    public List<ExpectCashDto> selectExpectCashHbList(ExpectCashDto expectCash);

    /**
     * 新增现金流预计情况
     * 
     * @param expectCash 现金流预计情况
     * @return 结果
     */
    public int insertExpectCash(ExpectCash expectCash);

    /**
     * 修改现金流预计情况
     * 
     * @param expectCash 现金流预计情况
     * @return 结果
     */
    public int updateExpectCash(ExpectCash expectCash);

    /**
     * 批量删除现金流预计情况
     * 
     * @param xjlyjqkIds 需要删除的现金流预计情况主键集合
     * @return 结果
     */
    public int deleteExpectCashByXjlyjqkIds(Long[] xjlyjqkIds);

    /**
     * 删除现金流预计情况信息
     * 
     * @param xjlyjqkId 现金流预计情况主键
     * @return 结果
     */
    public int deleteExpectCashByXjlyjqkId(Long xjlyjqkId);


    /**
     * 现金流预计情况导入
     *
     * @param listData 现金流预计情况导入
     * @return 结果AjaxResult
     */
    public AjaxResult importBox(List<ExpectCashVerify> listData, String xmid);
}
