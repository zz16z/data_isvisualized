package com.zkrs.system.service.impl;

import java.util.List;
import com.zkrs.common.utils.DateUtils;
import com.zkrs.system.tool.BusinessUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zkrs.system.mapper.BusinessDictionaryMapper;
import com.zkrs.system.domain.BusinessDictionary;
import com.zkrs.system.service.IBusinessDictionaryService;

import javax.annotation.PostConstruct;

/**
 * 业务字典Service业务层处理
 * 
 * @author zkrs
 * @date 2022-07-04
 */
@Service
public class BusinessDictionaryServiceImpl implements IBusinessDictionaryService 
{
    @Autowired
    private BusinessDictionaryMapper businessDictionaryMapper;




    @PostConstruct
    private void initData(){
        //加载缓存数据
        BusinessUtils.loodData(businessDictionaryMapper.selectBusinessDictionaryList(null));
    }

    /**
     * 查询业务字典
     * 
     * @param id 业务字典主键
     * @return 业务字典
     */
    @Override
    public BusinessDictionary selectBusinessDictionaryById(Long id)
    {
        return businessDictionaryMapper.selectBusinessDictionaryById(id);
    }

    /**
     * 查询业务字典列表
     * 
     * @param businessDictionary 业务字典
     * @return 业务字典
     */
    @Override
    public List<BusinessDictionary> selectBusinessDictionaryList(BusinessDictionary businessDictionary)
    {
        return businessDictionaryMapper.selectBusinessDictionaryList(businessDictionary);
    }

    /**
     * 新增业务字典
     * 
     * @param businessDictionary 业务字典
     * @return 结果
     */
    @Override
    public int insertBusinessDictionary(BusinessDictionary businessDictionary)
    {
        businessDictionary.setCreateTime(DateUtils.getNowDate());
        return businessDictionaryMapper.insertBusinessDictionary(businessDictionary);
    }

    /**
     * 修改业务字典
     * 
     * @param businessDictionary 业务字典
     * @return 结果
     */
    @Override
    public int updateBusinessDictionary(BusinessDictionary businessDictionary)
    {
        businessDictionary.setUpdateTime(DateUtils.getNowDate());
        return businessDictionaryMapper.updateBusinessDictionary(businessDictionary);
    }

    /**
     * 批量删除业务字典
     * 
     * @param ids 需要删除的业务字典主键
     * @return 结果
     */
    @Override
    public int deleteBusinessDictionaryByIds(Long[] ids)
    {
        return businessDictionaryMapper.deleteBusinessDictionaryByIds(ids);
    }

    /**
     * 删除业务字典信息
     * 
     * @param id 业务字典主键
     * @return 结果
     */
    @Override
    public int deleteBusinessDictionaryById(Long id)
    {
        return businessDictionaryMapper.deleteBusinessDictionaryById(id);
    }


    /**
     * 查询业务字典数据列表
     *
     * @param selectBusinessDictionaryListData 业务字典数据
     * @return 业务字典集合
     */
    @Override
    public List<BusinessDictionary> selectBusinessDictionaryListData(BusinessDictionary businessDictionary){

        return businessDictionaryMapper.selectBusinessDictionaryListData(businessDictionary);
    }

    /**
     * 验证子项
     *
     * @param businessDictionary 验证子项
     * @return 业务字典集合
     */
    @Override
    public  int  checkData( Long[] ids){

        return  businessDictionaryMapper.checkData(ids);
    }



    public  Integer  selectBusinessChekData( String pid ,  String name){
        return  businessDictionaryMapper.selectBusinessChekData(  pid ,   name);
    }
}
