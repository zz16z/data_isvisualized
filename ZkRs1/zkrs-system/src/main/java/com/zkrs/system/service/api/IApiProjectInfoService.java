package com.zkrs.system.service.api;



import com.zkrs.system.domain.api.*;

import java.util.List;
import java.util.Map;

/**
 * 项目情况Service接口
 * 
 * @author zkrs
 * @date 2022-06-30
 */
public interface IApiProjectInfoService {


    /**
     * 查询项目情况列表
     *
     * @param projectInfo 项目情况
     * @return 项目情况集合
     */
    public List<ApiProjectInfo> selectList(ApiProjectInfo projectInfo);

    /**
     * 项目分类统计电量
     *
     *
     */
    public List<ApiProjectType> selectProjectInfoRlTtal(String corporation);


    /**
     * 项目建设情况统计
     *
     *
     */
    public List<ApiXmJsqkSql> selectProjectJsqkTtal(String corporation,String xmFag);



    /**
     * 项目现状统计
     *
     *
     */
    public List<ApiXmXzTtal> selectProjectXzTtal(String corporation);


    /**
     * 总览
     *
     *
     */
    public List<ApiXmZlTtal> selectProjectZl(String  isDq,String corporation);


    /**
     * 项目公司情况(统计当年月份)
     *
     *
     */
    public ApiXmDltj selectProjectDltjl(String  month,String corporation,String  year);




    /**
     * 项目经营数据
     *
     *
     */
    public ApiXmJysj selectProjectJysj(String  month,String corporation,String beginTime,String endTime);


    /**
     * 资金预警
     *
     *
     */
    public List<ApiXmZjyj> selectProjectZjyj(String corporation);



    /**
     * 资金预警详情
     *
     *
     */
    public List<ApiXmZjyjxq> selectProjectZjyjxq(String corporation);


    /**
     * 工期预警
     *
     *
     */
    public List<ApiXmGqyj> selectProjectGqyjj(String corporation);


    /**
     * 出售预警
     *
     *
     */
    public List<ApiXmCsyj> selectProjectCsyj(String corporation);


    /**
     * 融资预警
     *
     *
     */
    public List<ApiXmRzyj> selectProjectRzyj(String corporation);




    /**
     * 项目履约分公司
     *
     *
     */
    public List<ApiXmLyfgs> selectProjectlyfgs();


    /**
     * 现金流情况
     *
     *
     */
    public List<ApiXmXjlqk> selectProjectXjlqk(String year,String  corporationId,String beginTime,String endTime);

    /**
     * 实际收款情况
     *
     *
     */
    public ApiXmJysjSjskqk  selectProjectJysjSjskqk(String yearValue ,String  monthValue,String corporation,String xmqkXmxz, String xmqkJszt,String xmqkXmlx,String xmqkQy ,String xmFag
    ,String xmrzRzqk,String xmqkRzf,String beginTime,String endTime);


    /**
     * 实际付款情况
     *
     *
     */
    public ApiXmJysjSjfkqk  selectProjectJysjSjfkqk(String yearValue ,String  monthValue,String corporation,String xmqkXmxz, String xmqkJszt,String xmqkXmlx,String xmqkQy ,String xmFag,String xmrzRzqk,String xmqkRzf,String beginTime,String endTime);

    /**
     * 预计经营情况
     *
     *
     */
    public ApiXmJysjYjjyqk  selectProjectJysjYjjyqk(String yearValue ,String  monthValue,String corporation,String xmqkXmxz, String xmqkJszt,String xmqkXmlx,String xmqkQy ,String xmFag,
                                                    String xmrzRzqk,String xmqkRzf,String beginTime,String endTime);

    /**
     * 预计经营情况数量
     *
     *
     */
    public ApiXmTotal  projectInfoTotal(String yearValue ,String  monthValue,String corporation,String xmqkXmxz, String xmqkJszt,String xmqkXmlx,String xmqkQy ,String xmFag,
                                                    String xmrzRzqk,String xmqkRzf,String beginTime,String endTime);



    /**
     * 项目现状列表
     *
     *
     */
    public List<ApiXmXmXzxq>   selectProjectXmqklb(String yearValue , String  monthValue ,String xmxz ,String corporation,String jszt,String xmFag,String xmqkJszt,String xmrzRzqk,String beginTime,String endTime );



    /**
     * 发电量情况列表
     *
     *
     */
    public List<ApiXmDltjlb>   selectProjectXmfdlqklb(String yearValue , String  monthValue ,String xmmc ,String corporation );



    /**
     * 发电量详情
     *
     *
     */
    public ApiXmFdlxq  selectProjectJXmfdlxq(String yearValue ,String  monthValue,String corporation,String xmid,String xmType,String xmFag,String xmqkXmxz,String xmqkLyfgs,String beginTime,String endTime);

    /**
     * 经营数据项目清单总计
     */
    Map<String, String> projectInfoListTotal(String yearValue, String monthValue, String corporation, String xmqkXmxz, String xmqkJszt, String xmqkXmlx, String xmqkQy, String xmFag, String xmrzRzqk, String xmqkRzf, String beginTime, String endTime);

    /**
     * 经营数据项目清单
     */
    List<ApiXmjy> selectProjectInfoList(String yearValue, String monthValue, String corporation, String xmqkXmxz, String xmqkJszt, String xmqkXmlx, String xmqkQy, String xmFag, String xmrzRzqk, String xmqkRzf, String beginTime, String endTime);
}


