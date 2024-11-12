package com.zkrs.system.service;

import java.util.List;
import com.zkrs.system.domain.BusinessDictionary;
import org.apache.ibatis.annotations.Param;

/**
 * 业务字典Service接口
 * 
 * @author zkrs
 * @date 2022-07-04
 */
public interface IBusinessDictionaryService 
{
    /**
     * 查询业务字典
     * 
     * @param id 业务字典主键
     * @return 业务字典
     */
    public BusinessDictionary selectBusinessDictionaryById(Long id);

    /**
     * 查询业务字典列表
     * 
     * @param businessDictionary 业务字典
     * @return 业务字典集合
     */
    public List<BusinessDictionary> selectBusinessDictionaryList(BusinessDictionary businessDictionary);

    /**
     * 新增业务字典
     * 
     * @param businessDictionary 业务字典
     * @return 结果
     */
    public int insertBusinessDictionary(BusinessDictionary businessDictionary);

    /**
     * 修改业务字典
     * 
     * @param businessDictionary 业务字典
     * @return 结果
     */
    public int updateBusinessDictionary(BusinessDictionary businessDictionary);

    /**
     * 批量删除业务字典
     * 
     * @param ids 需要删除的业务字典主键集合
     * @return 结果
     */
    public int deleteBusinessDictionaryByIds(Long[] ids);

    /**
     * 删除业务字典信息
     * 
     * @param id 业务字典主键
     * @return 结果
     */
    public int deleteBusinessDictionaryById(Long id);


    /**
     * 查询业务字典数据列表
     *
     * @param businessDictionary 业务字典
     * @return 业务字典集合
     */
    public List<BusinessDictionary> selectBusinessDictionaryListData(BusinessDictionary businessDictionary);

    /**
     * 验证子项
     *
     * @param businessDictionary 验证子项
     * @return 业务字典集合
     */
    public  int  checkData( Long[] ids);


    public  Integer   selectBusinessChekData( String pid ,  String name);

}
