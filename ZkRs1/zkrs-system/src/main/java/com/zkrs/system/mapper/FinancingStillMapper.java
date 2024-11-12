package com.zkrs.system.mapper;

import java.util.List;

import com.zkrs.system.domain.ExpectedFinancing;
import com.zkrs.system.domain.ExpectedTrueFinancing;
import com.zkrs.system.domain.FinancingDate;
import com.zkrs.system.domain.FinancingStill;
import org.apache.ibatis.annotations.Param;

/**
 * 还款情况Mapper接口
 * 
 * @author zkrs
 * @date 2022-07-23
 */
public interface FinancingStillMapper 
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
     * 删除还款情况
     * 
     * @param id 还款情况主键
     * @return 结果
     */
    public int deleteFinancingStillById(Long id);

    /**
     * 批量删除还款情况
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinancingStillByIds(Long[] ids);



    /**
     * 融资时间导入
     *
     * @param
     * @return 结果
     */
    public int importBox(@Param("listData") List<FinancingStill> listData);

    public int insertUpdateBatch(@Param("listData") List<FinancingStill> listData);

    public int insertUpdateBatchSQl(@Param("sqlv") String  sqlv);

    public  int  insertFinancingDateList(@Param("financingDateList") List<FinancingStill> financingDateList);

    public int updateBatch(@Param("listData") List<FinancingStill> listData);

    void importBoxFinancingInfo(ExpectedFinancing financingInfo);

    void importBoxTrueFinancingInfo(ExpectedTrueFinancing financingInfo);

    void insertFinancingDateList1(@Param("financingDateList")List<FinancingStill> financingStillList);
}
