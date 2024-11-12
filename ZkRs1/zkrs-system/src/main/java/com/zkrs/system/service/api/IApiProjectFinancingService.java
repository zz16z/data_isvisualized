package com.zkrs.system.service.api;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.ProjectFinancingVerify;
import com.zkrs.system.domain.api.*;
import com.zkrs.system.domain.dto.ProjectFinancingDto;

import java.util.List;

/**
 * 项目管理Service接口
 *
 * @author zkrs
 * @date 2022-07-01
 */
public interface IApiProjectFinancingService
{
  


    /**
     * 项目管理列表
     *
     * @param ApiXmXmgl 项目管理列表
     * @return 项目管理列表
     */
    public List<ApiXmXmgl> selectgetProjectXmgl(ApiXmXmgl apiXmXmgl);

    /**
     * 预计情况

     */
    public ApiXmYjqkxq selectProjectXmyjqkxq(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime);



    /**
     * 实际情况

     */
    public ApiXmSjqkxq selectProjectXmsjqkxq(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime);

    /**
     * 电量统计

     */
    public ApiXmDltjqk selectProjectXmDltj(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime);



    /**
     * 施工情况-风电

     */
    public ApiXmSgqk selectProjectXmGsqk(String yearValue , String  monthValue, String corporation, String xmid,String xmTypeId);


    /**
     * 施工情况-光伏

     */
    public ApiXmSgqkGf selectProjectXmGsqkGh(String yearValue , String  monthValue, String corporation, String xmid,String xmTypeId);

    /**
     * 项目融资(安装当年统计)

     */
    public List<ApiXmRztj>  selectprojectXmRztjik(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime);


    /**
     * 项目现金流

     */
    public ApiXmXjl  selectprojectXmXjl(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime);

    /**
     * 项目现金流(年统计-总计)

     */
    public List<ApiXmXjlNf>  selectprojectXmNf(String yearValue , String  monthValue, String corporation, String xmid);

    /**
     * 项目现金流(月统计)

     */
    public List<ApiXmXjlNf>  selectprojectXmYf(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime);

    /**
     * 融资详情(标题数据)

     */
    public ApiXmRzxq  selectprojectXmRzxq(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime);

    /**
     * 融资详情(放款情况)

     */
    public ApiXmRzxqFkqk  selectprojectXmRzxqFkqk(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime);

    /**
     * 融资详情(资金还款情况)

     */
    public ApiXmRzxqHkqkItem  selectprojectXmRzxqHkqk(String yearValue , String  monthValue, String corporation, String xmid,String beginTime,String endTime);

    /**
     * 项目管理标题数据

     */
    public ApiXmrzbtsj  selectprojectXmglbtsj(String yearValue , String  monthValue, String corporation, String xmid);

}
