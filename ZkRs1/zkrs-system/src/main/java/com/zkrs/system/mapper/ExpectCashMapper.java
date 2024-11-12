package com.zkrs.system.mapper;

import java.util.List;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.ExpectCasOrActualCash;
import com.zkrs.system.domain.ExpectCash;
import com.zkrs.system.domain.ManagementCash;
import com.zkrs.system.domain.dto.ExpectCashDto;
import org.apache.ibatis.annotations.Param;

/**
 * 现金流预计情况Mapper接口
 * 
 * @author zkrs
 * @date 2022-07-01
 */
public interface ExpectCashMapper 
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
     * 导出金流预计情况列表
     *
     * @param expectCash 现金流预计情况
     * @return 现金流预计情况集合
     */
    public List<ExpectCash> selectExpectCashExport(ExpectCash expectCash);


    /**
     * 查询现金流预计情况列表合并
     *
     * @param expectCash 现金流预计情况合并
     * @return 现金流预计情况合并
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
     * 删除现金流预计情况
     * 
     * @param xjlyjqkId 现金流预计情况主键
     * @return 结果
     */
    public int deleteExpectCashByXjlyjqkId(Long xjlyjqkId);

    /**
     * 批量删除现金流预计情况
     * 
     * @param xjlyjqkIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExpectCashByXjlyjqkIds(Long[] xjlyjqkIds);


    /**
     * 批量导入现金流预计情况
     *
     * @param listData 批量导入现金流预计情况
     * @return 结果
     */
    public int importBox(@Param("listData") List<ExpectCash> listData);


    /**
     * 删除现金流预计情况
     *
     * @param 项目id 现金流预计情况主键
     * @return 结果
     */
    public int deleteExpectCashByXjlyjqkIdJl(String  xjlyjqkId);

    /**
     * 批量删除现金流预计情况
     *
     * @param 项目id 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExpectCashByXjlyjqkIdsJl(String[] xjlyjqkIds);

    /**
     * 导入
     */
    void importBoxManagement( @Param("managementCash") ManagementCash managementCash);
    void addBatch( @Param("listData") List<ManagementCash> listData);
    void updateBatch( @Param("listData") List<ManagementCash> listData);
   void insertUpdateBatch( @Param("managementCash") ManagementCash managementCash);
    void insertUpdateBatch0( @Param("listData") List<ManagementCash> listData);


    List<ExpectCasOrActualCash>  selectCkekLits(@Param("listData") List<String> listData);


}
