package com.zkrs.system.service.api.impl;


import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.common.core.redis.RedisCache;
import com.zkrs.common.utils.DateUtils;
import com.zkrs.common.utils.StringUtils;
import com.zkrs.common.utils.uuid.IdUtils;
import com.zkrs.system.domain.BusinessDictionary;
import com.zkrs.system.domain.ImportError;
import com.zkrs.system.domain.ProjectInfoVerify;
import com.zkrs.system.domain.api.*;
import com.zkrs.system.enumeration.CommonlyCache;
import com.zkrs.system.mapper.*;
import com.zkrs.system.mapper.api.ApiProjectInfoMapper;
import com.zkrs.system.service.IProjectInfoService;
import com.zkrs.system.service.api.IApiProjectInfoService;
import com.zkrs.system.tool.BusinessUtils;
import com.zkrs.system.tool.Dictionaries;
import com.zkrs.system.tool.FastJsonUtils;
import com.zkrs.system.tool.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 项目情况Service业务层处理
 *
 * @author zkrs
 * @date 2022-06-30
 */
@Service
public class ApiProjectInfoServiceImpl implements IApiProjectInfoService {
    @Autowired
    private ApiProjectInfoMapper projectInfoMapper;






    /**
     * 查询项目情况列表
     *
     * @param projectInfo 项目情况
     * @return 项目情况
     */
    @Override
    public List<ApiProjectInfo> selectList(ApiProjectInfo projectInfo) {
        return projectInfoMapper.selectList(projectInfo);
    }

    @Override
    public List<ApiProjectType> selectProjectInfoRlTtal(String corporation) {
        return projectInfoMapper.selectProjectInfoRlTtal(corporation);
    }


    /**
     * 项目建设情况统计
     *
     *
     */
    @Override
    public List<ApiXmJsqkSql> selectProjectJsqkTtal(String corporation,String  xmFag) {
        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return projectInfoMapper.selectProjectJsqkTtal(corporation,xmFag,newOldTime);
    }

    /**
     * 项目现状统计
     *
     *
     */
    @Override
    public List<ApiXmXzTtal> selectProjectXzTtal(String corporation) {
        //设置项目新老时间
        String businessItem=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return projectInfoMapper.selectProjectXzTtal(corporation,businessItem);
    }




    /**
     * 总览
     *
     *
     */
    @Override
    public List<ApiXmZlTtal> selectProjectZl(String  isDq,String corporation) {
        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return projectInfoMapper.selectProjectZl(isDq,corporation,newOldTime);
    }

    /**
     * 项目公司情况(统计当年月份)
     *
     *
     */
    public ApiXmDltj selectProjectDltjl(String  month,String corporation,String  year) {
        return projectInfoMapper.selectProjectDltjl(month,corporation,year);
    }





    /**
     * 项目经营数据
     *
     *
     */

    public ApiXmJysj selectProjectJysj(String  month,String corporation,String beginTime,String endTime) {
        return projectInfoMapper.selectProjectJysj(month,corporation,beginTime,endTime);
    }


    /**
     * 资金预警
     *
     *
     */
    public List<ApiXmZjyj> selectProjectZjyj(String corporation) {
        return projectInfoMapper.selectProjectZjyj(corporation);
    }



    /**
     * 资金预警详情
     *
     *
     */
    public List<ApiXmZjyjxq> selectProjectZjyjxq(String corporation) {
        return projectInfoMapper.selectProjectZjyjxq(corporation );
    }


    /**
     * 工期预警
     *
     *
     */
    public List<ApiXmGqyj> selectProjectGqyjj(String corporation){
        return projectInfoMapper.selectProjectGqyjj( corporation);
    }




    /**
     * 出售预警
     *
     *
     */
    public List<ApiXmCsyj> selectProjectCsyj(String corporation){
        return projectInfoMapper.selectProjectCsyj(corporation );
    }




    /**
     * 融资预警
     *
     *
     */

    public List<ApiXmRzyj> selectProjectRzyj(String corporation){
        return projectInfoMapper.selectProjectRzyj(corporation );
    }





    /**
     * 项目履约分公司
     *
     *
     */
    public List<ApiXmLyfgs> selectProjectlyfgs(){
        return projectInfoMapper.selectProjectlyfgs( );
    }





    /**
     * 现金流情况
     *
     *
     */
    public List<ApiXmXjlqk> selectProjectXjlqk(String year,String  corporationId,String beginTime,String endTime){
        return projectInfoMapper.selectProjectXjlqk(year,corporationId,beginTime,endTime);
    }


    /**
     * 实际收款情况
     *
     *
     */
    public ApiXmJysjSjskqk  selectProjectJysjSjskqk(String yearValue ,String  monthValue,String corporation,String xmqkXmxz, String xmqkJszt,String xmqkXmlx,
                                                    String xmqkQy ,String xmFag,String xmrzRzqk,String xmqkRzf,String beginTime,String endTime){
        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return projectInfoMapper.selectProjectJysjSjskqk(yearValue,monthValue,corporation ,xmqkXmxz,  xmqkJszt, xmqkXmlx, xmqkQy , xmFag, xmrzRzqk, xmqkRzf,newOldTime,beginTime,endTime);

    }


    /**
     * 实际付款情况
     *
     *
     */
    public ApiXmJysjSjfkqk  selectProjectJysjSjfkqk(String yearValue ,String  monthValue,String corporation,String xmqkXmxz, String xmqkJszt,String xmqkXmlx,
    String xmqkQy ,String xmFag,String xmrzRzqk,String xmqkRzf,String beginTime,String endTime){
        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return projectInfoMapper.selectProjectJysjSjfkqk(yearValue,monthValue,corporation,xmqkXmxz,  xmqkJszt, xmqkXmlx , xmqkQy , xmFag, xmrzRzqk, xmqkRzf,newOldTime,beginTime,endTime);

    }






    /**
     * 预计经营情况
     *
     *
     */
    public ApiXmJysjYjjyqk  selectProjectJysjYjjyqk(String yearValue ,String  monthValue,String corporation,String xmqkXmxz, String xmqkJszt,String xmqkXmlx,String xmqkQy ,String xmFag
    ,String xmrzRzqk,String xmqkRzf,String beginTime,String endTime){
        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return projectInfoMapper.selectProjectJysjYjjyqk(yearValue,monthValue,corporation,xmqkXmxz,  xmqkJszt, xmqkXmlx , xmqkQy , xmFag, xmrzRzqk, xmqkRzf,newOldTime,beginTime,endTime);

    }

    /**
     * 预计经营情况数量
     *
     *
     */
    public ApiXmTotal  projectInfoTotal(String yearValue ,String  monthValue,String corporation,String xmqkXmxz, String xmqkJszt,String xmqkXmlx,String xmqkQy ,String xmFag
            ,String xmrzRzqk,String xmqkRzf,String beginTime,String endTime){
        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return projectInfoMapper.projectInfoTotal(yearValue,monthValue,corporation,xmqkXmxz,  xmqkJszt, xmqkXmlx , xmqkQy , xmFag, xmrzRzqk, xmqkRzf,newOldTime,beginTime,endTime);

    }


    /**
     * 项目现状列表
     *
     *
     */
    public List<ApiXmXmXzxq>   selectProjectXmqklb(String yearValue , String  monthValue ,String xmxz ,String corporation,String jszt ,String xmFag,String xmqkJszt,String xmrzRzqk,String beginTime,String endTime){
        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return projectInfoMapper.selectProjectXmqklb(yearValue,monthValue,xmxz,corporation ,jszt,newOldTime,xmFag,xmqkJszt,xmrzRzqk,beginTime,endTime);
    }



    /**
     * 发电量情况列表
     *
     *
     */
    public List<ApiXmDltjlb>   selectProjectXmfdlqklb(String yearValue , String  monthValue ,String xmmc ,String corporation ){


        return projectInfoMapper.selectProjectXmfdlqklb(yearValue,monthValue,xmmc,corporation);

    }

    /**
     * 发电量情况列表
     *
     *
     */
    public ApiXmFdlxq  selectProjectJXmfdlxq(String yearValue ,String  monthValue,String corporation,String xmid,String xmType,String xmFag,String xmqkXmxz,String xmqkLyfgs,String beginTime,String endTime){

//        Calendar calendar = Calendar.getInstance();
//        int month = calendar.get(Calendar.MONTH) + 1;
//        int lastMonth = 0;
//        if(monthValue != null && monthValue != ""){
//            lastMonth = Integer.parseInt(monthValue) -1 ;
//        }else{
//            monthValue = month+"";
//            lastMonth = month -1;
//        }
//        ApiXmFdlxq apiXmFdlxq = projectInfoMapper.selectProjectJXmfdlxq(yearValue, lastMonth + "", corporation, xmid,xmType);
//        ApiXmFdlxq apiXmFdlxq1 = projectInfoMapper.selectProjectJXmfdlxq(yearValue, monthValue, corporation, xmid,xmType);
//        if(apiXmFdlxq != null){
//            apiXmFdlxq1.setDltjSyjsdfysje(apiXmFdlxq.getDltjDljsljysje());
//        }



        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        ApiXmFdlxq apiXmFdlxq1 = projectInfoMapper.selectProjectJXmfdlxq(yearValue, monthValue, corporation, xmid,xmType,newOldTime,xmFag,xmqkXmxz,xmqkLyfgs,beginTime,endTime);
        return apiXmFdlxq1;
    }

    @Override
    public Map<String, String> projectInfoListTotal(String yearValue, String monthValue, String corporation, String xmqkXmxz, String xmqkJszt, String xmqkXmlx, String xmqkQy, String xmFag, String xmrzRzqk, String xmqkRzf, String beginTime, String endTime) {
        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return projectInfoMapper.projectInfoListTotal(yearValue,monthValue,corporation,xmqkXmxz,  xmqkJszt, xmqkXmlx , xmqkQy , xmFag, xmrzRzqk, xmqkRzf,newOldTime,beginTime,endTime);
    }

    @Override
    public List<ApiXmjy> selectProjectInfoList(String yearValue, String monthValue, String corporation, String xmqkXmxz, String xmqkJszt, String xmqkXmlx, String xmqkQy, String xmFag, String xmrzRzqk, String xmqkRzf, String beginTime, String endTime) {
        String newOldTime=  BusinessUtils.getByIdName(Dictionaries.NEW_OLD_TIME_ID);
        return projectInfoMapper.selectProjectInfoList(yearValue,monthValue,corporation,xmqkXmxz,  xmqkJszt, xmqkXmlx , xmqkQy , xmFag, xmrzRzqk, xmqkRzf,newOldTime,beginTime,endTime);
    }


}



