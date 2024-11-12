package com.zkrs.system.service.api.impl;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.common.core.redis.RedisCache;

import com.zkrs.system.domain.api.*;
import com.zkrs.system.mapper.ElectricityStatisticsMapper;
import com.zkrs.system.mapper.api.ApiElectricityStatisticsMapper;
import com.zkrs.system.service.IElectricityStatisticsService;
import com.zkrs.system.service.api.IApiElectricityStatisticsService;
import com.zkrs.system.tool.BusinessUtils;
import com.zkrs.system.tool.Dictionaries;
import com.zkrs.system.tool.FastJsonUtils;
import com.zkrs.system.tool.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 项目资金管理ervice业务层处理
 *
 * @author zkrs
 * @date 2022-07-01
 */
@Service
public class ApiElectricityStatisticsServiceImpl implements IApiElectricityStatisticsService
{
    @Autowired
    private ApiElectricityStatisticsMapper electricityStatisticsMapper;




    /**
     * 项目建设收支持情况
     *

     */

    public List<ApiXmFgsskqk> selectProjectJsqk()
    {
        return electricityStatisticsMapper.selectProjectJsqk();
    }


    /**
     * 风、光伏项目
     *

     */

    public ApiXmFgxm selectProjectFgxm(String xmTYpe , String yearValue , String  monthValue ,String beginTime,String endTime)
    {
        return electricityStatisticsMapper.selectProjectFgxm(xmTYpe,yearValue,monthValue,beginTime,endTime);
    }



    /**
     * 分公司收款情况
     *

     */
    public List<ApiXmFgsskqk> selectprojectFgsskqk(String yearValue, String  monthValue,String beginTime,String endTime ){

        return electricityStatisticsMapper.selectprojectFgsskqk(yearValue,monthValue,beginTime,endTime);


    }



    /**
     * 现金流情况合计
     *

     */
    public  List<ApiXmXjlqkHj>  selectprojectXjlqkHj(String yearValue, String  monthValue , String  projectType ,String beginTime,String endTime){
        String allWhere=" 1=1 ";
        if(monthValue!=null&&!"".equals(monthValue)){
            allWhere="1=2";

        }else {
            monthValue="-100";
        }

        return electricityStatisticsMapper.selectprojectXjlqkHj(yearValue,monthValue,projectType,allWhere,beginTime,endTime);

    }


    /**
     * 现金流情况
     *

     */
    public  List<ApiXmXjlqk>  selectProjectZjXjlqk(String yearValue, String  monthValue ,String  projectType ){
        String allWhere=" 1=1 ";
        if(monthValue!=null&&!"".equals(monthValue)){
            allWhere="1=2";

        }else {
            monthValue="-100";
        }

        return electricityStatisticsMapper.selectProjectZjXjlqk(yearValue,monthValue,projectType,allWhere);
    }





    /**
     * 资金统计
     *

     */
    public  ApiXmZjtj selectProjectZjTj( ){
        return electricityStatisticsMapper.selectProjectZjTj();
    }



    /**
     * 融资情况
     *

     */

    public ApiXmXjrzqk selectProjectZjRzqk(String yearValue, String  monthValue ,String  projectType,String beginTime,String endTime){

        return electricityStatisticsMapper.selectProjectZjRzqk(yearValue,monthValue,projectType,beginTime,endTime);

    }


    /**
     * 建设项目收支情况
     *

     */

    public List<ApiXmXmjsszqk>  selectProjectJsxmszqk(String corporation  , String xmmc , String yearValue , String  monthValue,String projectState , String  xmqkJszt,String  xmqkXmlx,String  xmFag,String xmrzRzqk,String xmqkHzf,String xmqkSgf,String xmqkRzf,String beginTime,String endTime){
        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return electricityStatisticsMapper.selectProjectJsxmszqk(corporation,xmmc,yearValue,monthValue, projectState
        ,   xmqkJszt,  xmqkXmlx,  xmFag,newOldTime,xmrzRzqk,xmqkHzf,xmqkSgf,xmqkRzf,beginTime,endTime);

    }
    /*
    查询总和
     */
    public ApiXmXmjsszqkTotal selectProjectJsxmszqkTotal(String corporation  , String xmmc , String yearValue , String  monthValue,String projectState,String  xmqkJszt,String  xmqkXmlx,String  xmFag,String xmrzRzqk,String xmqkHzf,String xmqkSgf,String xmqkRzf,String beginTime,String endTime ){
        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return electricityStatisticsMapper.selectProjectJsxmszqkTotal(corporation,xmmc,yearValue,monthValue, projectState,  xmqkJszt,  xmqkXmlx,  xmFag,newOldTime,xmrzRzqk,xmqkHzf,xmqkSgf,xmqkRzf,beginTime,endTime);

    }





    /**
     * 项目融资
     *

     */

    public List<ApiXmZjrz>  selectProjectZjXmrz(String xllx , String yearValue , String  monthValue,String  xmqkXmmc ,String financeManager,String financingInstitutions ,String financingSituation,String xmFag,String xmqkXmxz,String xmqkJszt,String xmrzQyrq,String beginTime,String endTime ){
        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return electricityStatisticsMapper.selectProjectZjXmrz(xllx,yearValue,monthValue,  xmqkXmmc, financeManager, financingInstitutions , financingSituation,xmFag,xmqkXmxz,xmqkJszt,xmrzQyrq,newOldTime,beginTime,endTime );

    }


    /**
     * 电量
     *

     */

    public List<ApiXmElectricityList>  selectProjectElectricityList(String xllx,String yearValue,String monthValue,String xmqkXmmc,String xmqkLyfgs, String xmqkQy ,String xmqkXmlx,String xmFag,String xmqkXmxz,String beginTime,String endTime ){

        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return electricityStatisticsMapper.selectProjectElectricityList(xllx,yearValue,monthValue,  xmqkXmmc, xmqkLyfgs, xmqkQy , xmqkXmlx,xmFag, xmqkXmxz,newOldTime ,beginTime,endTime);

    }
    public  ApiXmElectricityTotal  selectProjectElectricityTotal(String xllx,String yearValue,String monthValue,String xmqkXmmc,String xmqkLyfgs, String xmqkQy ,String xmqkXmlx ,String xmFag,String xmqkXmxz,String beginTime,String endTime){


        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return electricityStatisticsMapper.selectProjectElectricityTotal(xllx,yearValue,monthValue,  xmqkXmmc, xmqkLyfgs, xmqkQy , xmqkXmlx ,xmFag,xmqkXmxz,newOldTime,beginTime,endTime );

    }
//    @Override
//    public List<ApiXmElectricityList> selectProjectElectricityList(String xllx, String yearValue, String monthValue, String xmqkXmmc, String xmqkLyfgs, String xmqkQy, String xmqkXmlx,String xmFag,String xmqkXmxz) {
//        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
//        return electricityStatisticsMapper.selectProjectElectricityList(xllx,yearValue,monthValue,  xmqkXmmc, xmqkLyfgs, xmqkQy , xmqkXmlx,xmFag, xmqkXmxz,newOldTime );
//    }
//
//    @Override
//    public ApiXmElectricityTotal selectProjectElectricityTotal(String xllx, String yearValue, String monthValue, String xmqkXmmc, String xmqkLyfgs, String xmqkQy, String xmqkXmlx,String xmFag,String xmqkXmxz) {
//        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
//        return electricityStatisticsMapper.selectProjectElectricityTotal(xllx,yearValue,monthValue,  xmqkXmmc, xmqkLyfgs, xmqkQy , xmqkXmlx ,xmFag,xmqkXmxz,newOldTime );
//    }


    public ApiXmZjrzTotal selectProjectZjXmrzTotal(String xllx , String yearValue , String  monthValue,String  xmqkXmmc ,String financeManager,String financingInstitutions ,String financingSituation,String xmFag,String xmqkXmxz,String xmqkJszt,String xmrzQyrq,String beginTime,String endTime ){

        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return electricityStatisticsMapper.selectProjectZjXmrzTotal(xllx,yearValue,monthValue,  xmqkXmmc, financeManager, financingInstitutions , financingSituation,xmFag,xmqkXmxz,xmqkJszt,xmrzQyrq,newOldTime,beginTime,endTime );

    }



    /**
     * 资金还款
     *

     */

    public List<ApiXmZjhk>  selectProjectZjHk(String xllx , String yearValue , String  monthValue,String xmqkXmmc,String  financeManager ,String financingInstitutions,String financingSituation ){
        return electricityStatisticsMapper.selectProjectZjHk(xllx,yearValue,monthValue,xmqkXmmc,  financeManager , financingInstitutions, financingSituation);

    }
    /**
     * 资金还款
     *

     */

    public ApiXmZjrzTotal selectProjectZjHkTotal(String xllx , String yearValue , String  monthValue,String xmqkXmmc,String  financeManager ,String financingInstitutions,String financingSituation ){
        return electricityStatisticsMapper.selectProjectZjHkTotal(xllx,yearValue,monthValue,xmqkXmmc,  financeManager , financingInstitutions, financingSituation);

    }

    public String selectLybzjBack() {
        return electricityStatisticsMapper.selectLybzjBack();
    }

    public String selectCash() {
        return electricityStatisticsMapper.selectCash();
    }
}
