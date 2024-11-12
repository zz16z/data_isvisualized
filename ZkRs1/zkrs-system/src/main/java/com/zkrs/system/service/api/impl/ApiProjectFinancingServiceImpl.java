package com.zkrs.system.service.api.impl;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.common.core.redis.RedisCache;
import com.zkrs.common.utils.DateUtils;
import com.zkrs.system.domain.ImportError;
import com.zkrs.system.domain.ProjectFinancingVerify;
import com.zkrs.system.domain.api.*;
import com.zkrs.system.domain.dto.ProjectFinancingDto;
import com.zkrs.system.mapper.*;
import com.zkrs.system.mapper.api.ApiProjectFinancingMapper;
import com.zkrs.system.service.IProjectFinancingService;
import com.zkrs.system.service.api.IApiProjectFinancingService;
import com.zkrs.system.tool.BusinessUtils;
import com.zkrs.system.tool.Dictionaries;
import com.zkrs.system.tool.FastJsonUtils;
import com.zkrs.system.tool.Valid;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目管理Service业务层处理
 *
 * @author zkrs
 * @date 2022-07-01
 */
@Service
public class ApiProjectFinancingServiceImpl implements IApiProjectFinancingService
{
    @Autowired
    private ApiProjectFinancingMapper projectFinancingMapper;





    /**
     * 项目管理列表
     *
     * @param ApiXmXmgl 项目管理列表
     * @return 项目管理列表
     */
    public List<ApiXmXmgl> selectgetProjectXmgl(ApiXmXmgl apiXmXmgl) {

        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        apiXmXmgl.setNewOldTime(newOldTime);
        return projectFinancingMapper.selectgetProjectXmgl(apiXmXmgl);

    }



    /**
     * 预计情况

     */
    public ApiXmYjqkxq selectProjectXmyjqkxq(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime){
        return projectFinancingMapper.selectProjectXmyjqkxq(yearValue,monthValue,corporation,xmid,beginTime,endTime);
    }


    /**
     * 实际情况

     */
    public ApiXmSjqkxq selectProjectXmsjqkxq(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime){

        return projectFinancingMapper.selectProjectXmsjqkxq(yearValue,monthValue,corporation,xmid,beginTime,endTime);

    }


    /**
     * 电量统计

     */
    public ApiXmDltjqk selectProjectXmDltj(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime){
        return projectFinancingMapper.selectProjectXmDltj(yearValue,monthValue,corporation,xmid,beginTime,endTime);

    }

    /**
     * 施工情况

     */
    public ApiXmSgqk selectProjectXmGsqk(String yearValue , String  monthValue, String corporation, String xmid,String xmTypeId){
        return projectFinancingMapper.selectProjectXmGsqk(yearValue,monthValue,corporation,xmid,xmTypeId);
    }
    /**
     * 施工情况-光伏

     */
    public ApiXmSgqkGf selectProjectXmGsqkGh(String yearValue , String  monthValue, String corporation, String xmid,String xmTypeId){
        return projectFinancingMapper.selectProjectXmGsqkGh(yearValue,monthValue,corporation,xmid,xmTypeId);
    }

    /**
     * 项目融资(安装当年统计)

     */
    public List<ApiXmRztj>  selectprojectXmRztjik(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime){
        return projectFinancingMapper.selectprojectXmRztjik(yearValue,monthValue,corporation,xmid,beginTime,endTime);

    }


    /**
     * 项目现金流

     */
    public ApiXmXjl  selectprojectXmXjl(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime){

        return projectFinancingMapper.selectprojectXmXjl(yearValue,monthValue,corporation,xmid,beginTime,endTime);

    }


    /**
     * 项目现金流(年统计-总计)

     */
    public List<ApiXmXjlNf>  selectprojectXmNf(String yearValue , String  monthValue, String corporation, String xmid){
        return projectFinancingMapper.selectprojectXmNf(yearValue,monthValue,corporation,xmid);
    }

    /**
     * 项目现金流(月统计)

     */
    public List<ApiXmXjlNf>  selectprojectXmYf(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime){

        return projectFinancingMapper.selectprojectXmYf(yearValue,monthValue,corporation,xmid,beginTime,endTime);
    }


    /**
     * 融资详情(标题数据)

     */
    public ApiXmRzxq  selectprojectXmRzxq(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime){

        return projectFinancingMapper.selectprojectXmRzxq(yearValue,monthValue,corporation,xmid,beginTime,endTime);
    }


    /**
     * 融资详情(放款情况)

     */
    public ApiXmRzxqFkqk  selectprojectXmRzxqFkqk(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime){
        ApiXmRzxqFkqk rs= projectFinancingMapper.selectprojectXmRzxqFkqk(yearValue,monthValue,corporation,xmid,beginTime,endTime);
        if(rs!=null){
            List<ApiXmRzxqFkqkItem> item=    projectFinancingMapper.selectprojectXmRzxqFkqkItem(yearValue,monthValue,corporation,xmid,beginTime,endTime);
            rs.setListData(item);
        }

        return rs ;
    }


    /**
     * 融资详情(资金还款情况)

     */
    public ApiXmRzxqHkqkItem  selectprojectXmRzxqHkqk(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime){

        return projectFinancingMapper.selectprojectXmRzxqHkqk(yearValue,monthValue,corporation,xmid,beginTime,endTime);
    }


    /**
     * 项目管理标题数据

     */
    public ApiXmrzbtsj  selectprojectXmglbtsj(String yearValue , String  monthValue, String corporation, String xmid){
        return projectFinancingMapper.selectprojectXmglbtsj(yearValue,monthValue,corporation,xmid);
    }
}
