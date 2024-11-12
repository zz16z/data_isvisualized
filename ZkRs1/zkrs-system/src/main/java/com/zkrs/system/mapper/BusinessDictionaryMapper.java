package com.zkrs.system.mapper;

import java.util.List;
import com.zkrs.system.domain.BusinessDictionary;
import com.zkrs.system.domain.api.ApiDictionary;
import org.apache.ibatis.annotations.Param;

/**
 * 业务字典Mapper接口
 * 
 * @author zkrs
 * @date 2022-07-04
 */
public interface BusinessDictionaryMapper 
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
     * 查询业务字典列表
     *
     * @param businessDictionary 业务字典API用
     * @return 业务字典集合
     */
    public List<ApiDictionary> selectApiList(@Param("id") String id);

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
     * 删除业务字典
     * 
     * @param id 业务字典主键
     * @return 结果
     */
    public int deleteBusinessDictionaryById(Long id);

    /**
     * 批量删除业务字典
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusinessDictionaryByIds(Long[] ids);


    /**
     * 查询业务字典数据列表
     *
     * @param selectBusinessDictionaryListData 业务字典数据
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

    /**
     * 验证目录重复
     *
     * @param businessDictionary 验证子项
     * @return 业务字典集合
     */
    public  Integer selectBusinessChekData(@Param("pid") String pid , @Param("name") String name);

}
