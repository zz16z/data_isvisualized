package com.zkrs.system.service.api;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.ElectricityStatisticsVerify;
import com.zkrs.system.domain.api.*;

import java.util.List;

/**
 * 电量统计Service接口
 * 
 * @author zkrs
 * @date 2022-07-01
 */
public interface IApiElectricityStatisticsService
{

    /**
     * 项目建设收支持情况
     *

     */

    public List<ApiXmFgsskqk> selectProjectJsqk();





    public ApiXmFgxm selectProjectFgxm(String xmTYpe , String yearValue , String  monthValue,String beginTime,String endTime );




    /**
     * 分公司收款情况
     *

     */
    public  List<ApiXmFgsskqk>  selectprojectFgsskqk(String yearValue, String  monthValue ,String beginTime,String endTime);



    /**
     * 现金流情况合计
     *

     */
    public  List<ApiXmXjlqkHj>  selectprojectXjlqkHj(String yearValue, String  monthValue ,String  projectType ,String beginTime,String endTime);



    /**
     * 现金流情况
     *

     */
    public  List<ApiXmXjlqk>  selectProjectZjXjlqk(String yearValue, String  monthValue ,String  projectType );


    /**
     * 资金统计
     *

     */
    public  ApiXmZjtj selectProjectZjTj( );



    /**
     * 融资情况
     *

     */

    public ApiXmXjrzqk selectProjectZjRzqk(String yearValue, String  monthValue ,String  projectType,String beginTime,String endTime);


    /**
     * 项目融资
     *

     */

    public ApiXmZjrzTotal selectProjectZjXmrzTotal(String xllx , String yearValue , String  monthValue ,String  xmqkXmmc ,String financeManager,String financingInstitutions ,String financingSituation,String xmFag,String xmqkXmxz,String xmqkJszt,String xmrzQyrq,String beginTime,String endTime );

    public List<ApiXmElectricityList>  selectProjectElectricityList(String xllx,String yearValue,String monthValue,String xmqkXmmc,String xmqkLyfgs, String xmqkQy ,String xmqkXmlx,String xmFag,String xmqkXmxz ,String beginTime,String endTime );

    public ApiXmElectricityTotal  selectProjectElectricityTotal(String xllx,String yearValue,String monthValue,String xmqkXmmc,String xmqkLyfgs, String xmqkQy ,String xmqkXmlx,String xmFag,String xmqkXmxz ,String beginTime,String endTime);


    /**
     * 资金还款
     *

     */

    public List<ApiXmZjhk>  selectProjectZjHk(String xllx , String yearValue , String  monthValue,String xmqkXmmc ,String  financeManager ,String financingInstitutions,String financingSituation );

    /**
     * 资金还款
     *

     */

    public ApiXmZjrzTotal  selectProjectZjHkTotal(String xllx , String yearValue , String  monthValue,String xmqkXmmc ,String  financeManager ,String financingInstitutions,String financingSituation );




}
