package com.zkrs.system.mapper.api;


import com.zkrs.system.domain.api.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 项目情况Mapper接口
 * 
 * @author zkrs
 * @date 2022-06-30
 */
public interface ApiProjectInfoMapper
{


    /**
     * 查询项目情况列表
     * 
     * @param projectInfo 项目情况
     * @return 项目情况集合
     */
    public List<ApiProjectInfo> selectList(ApiProjectInfo projectInfo);


    /**
     * 查询项目情况列表
     *
     * @param  分类统计容量
     * @return 项目情况集合
     */
    public List<ApiProjectType> selectProjectInfoRlTtal(@Param("corporation") String corporation);




    /**
     * 项目建设情况统计
     *
     *
     */
    public List<ApiXmJsqkSql> selectProjectJsqkTtal(@Param("corporation") String corporation,@Param("xmFag") String xmFag,@Param("newOldTime") String newOldTime);


    /**
     * 项目现状统计
     *
     *
     */
    public List<ApiXmXzTtal> selectProjectXzTtal(@Param("corporation") String corporation,@Param("newOldTime") String newOldTime);


    /**
     * 总览
     *
     *
     */
    public List<ApiXmZlTtal> selectProjectZl(@Param("isDq") String isDq,@Param("corporation") String corporation,@Param("newOldTime")  String newOldTime);




    /**
     * 项目公司情况(统计当年月份)
     *
     *
     */
    public ApiXmDltj selectProjectDltjl(@Param("month")  String  month,@Param("corporation")  String corporation,@Param("year") String  year);



    /**
     * 项目经营数据
     *
     *
     */
    public ApiXmJysj selectProjectJysj(@Param("month") String  month,@Param("corporation") String corporation,@Param("beginTime")String beginTime,@Param("endTime")String endTime);




    /**
     * 资金预警
     *
     *
     */

    public List<ApiXmZjyj> selectProjectZjyj(@Param("corporation") String corporation) ;




    /**
     * 资金预警详情
     *
     *
     */
    public List<ApiXmZjyjxq> selectProjectZjyjxq(@Param("corporation") String corporation);



    /**
     * 工期预警
     *
     *
     */
    public List<ApiXmGqyj> selectProjectGqyjj(@Param("corporation") String corporation);


    /**
     * 出售预警
     *
     *
     */
    public List<ApiXmCsyj> selectProjectCsyj(@Param("corporation") String corporation);






    /**
     * 融资预警
     *
     *
     */

    public List<ApiXmRzyj> selectProjectRzyj(@Param("corporation") String corporation);



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
    public List<ApiXmXjlqk> selectProjectXjlqk(@Param("year") String year,@Param("corporationId") String  corporationId,@Param("beginTime")String beginTime,@Param("endTime")String endTime);


    /**
     * 实际收款情况
     *
     *
     */
    public ApiXmJysjSjskqk  selectProjectJysjSjskqk(@Param("yearValue")String yearValue ,@Param("monthValue")String  monthValue,@Param("corporation") String corporation
            ,@Param("xmqkXmxz") String xmqkXmxz, @Param("xmqkJszt")String xmqkJszt,@Param("xmqkXmlx")String xmqkXmlx,@Param("xmqkQy")String xmqkQy ,@Param("xmFag")String xmFag
    ,@Param("xmrzRzqk") String xmrzRzqk,@Param("xmqkRzf") String xmqkRzf,@Param("newOldTime") String newOldTime,@Param("beginTime")String beginTime,@Param("endTime")String endTime);



    /**
     * 实际付款情况
     *
     *
     */

    public ApiXmJysjSjfkqk  selectProjectJysjSjfkqk(@Param("yearValue")String yearValue ,@Param("monthValue")String  monthValue,@Param("corporation") String corporation
    ,@Param("xmqkXmxz") String xmqkXmxz, @Param("xmqkJszt")String xmqkJszt,@Param("xmqkXmlx")String xmqkXmlx,@Param("xmqkQy")String xmqkQy ,@Param("xmFag")String xmFag,
                                                    @Param("xmrzRzqk") String xmrzRzqk,@Param("xmqkRzf") String xmqkRzf,@Param("newOldTime") String newOldTime,@Param("beginTime")String beginTime,@Param("endTime")String endTime
    );


    /**
     * 预计经营情况
     *
     *
     */
    public ApiXmJysjYjjyqk  selectProjectJysjYjjyqk(@Param("yearValue")String yearValue ,@Param("monthValue")String  monthValue,@Param("corporation") String corporation,
            @Param("xmqkXmxz") String xmqkXmxz, @Param("xmqkJszt")String xmqkJszt,@Param("xmqkXmlx")String xmqkXmlx,@Param("xmqkQy")String xmqkQy ,@Param("xmFag")String xmFag,
                                                    @Param("xmrzRzqk")String xmrzRzqk,@Param("xmqkRzf")String xmqkRzf,@Param("newOldTime") String newOldTime,@Param("beginTime") String beginTime,@Param("endTime") String endTime);


    /**
     * 预计经营情况数量
     *
     *
     */
    public ApiXmTotal  projectInfoTotal(@Param("yearValue")String yearValue ,@Param("monthValue")String  monthValue,@Param("corporation") String corporation,
                                                    @Param("xmqkXmxz") String xmqkXmxz, @Param("xmqkJszt")String xmqkJszt,@Param("xmqkXmlx")String xmqkXmlx,@Param("xmqkQy")String xmqkQy ,@Param("xmFag")String xmFag,
                                                    @Param("xmrzRzqk")String xmrzRzqk,@Param("xmqkRzf")String xmqkRzf,@Param("newOldTime") String newOldTime,@Param("beginTime")String beginTime,@Param("endTime")String endTime);

    /**
     * 项目现状列表
     *
     *
     */
    public List<ApiXmXmXzxq>   selectProjectXmqklb(@Param("yearValue")String yearValue , @Param("monthValue")String  monthValue
            ,@Param("xmxz")String xmxz ,@Param("corporation")String corporation ,@Param("jszt") String jszt,@Param("newOldTime") String newOldTime,
                                                   @Param("xmFag") String xmFag ,@Param("xmqkJszt")String xmqkJszt,@Param("xmrzRzqk")String xmrzRzqk,@Param("beginTime")String beginTime,@Param("endTime")String endTime);



    /**
     * 发电量情况列表
     *
     *
     */
    public List<ApiXmDltjlb>   selectProjectXmfdlqklb(@Param("yearValue")String yearValue ,@Param("monthValue") String  monthValue , @Param("xmmc")String xmmc , @Param("corporation")String corporation );



    public ApiXmFdlxq  selectProjectJXmfdlxq(@Param("yearValue")String yearValue ,@Param("monthValue")String  monthValue,@Param("corporation")String corporation,@Param("xmid")String xmid,@Param("xmType")String xmType,
                                             @Param("newOldTime")String newOldTime,@Param("xmFag")String xmFag,@Param("xmqkXmxz")String xmqkXmxz,@Param("xmqkLyfgs") String xmqkLyfgs,@Param("beginTime")String beginTime,@Param("endTime")String endTime);


    Map<String, String> projectInfoListTotal(@Param("yearValue")String yearValue ,@Param("monthValue")String  monthValue,@Param("corporation") String corporation,
                                             @Param("xmqkXmxz") String xmqkXmxz, @Param("xmqkJszt")String xmqkJszt,@Param("xmqkXmlx")String xmqkXmlx,@Param("xmqkQy")String xmqkQy ,@Param("xmFag")String xmFag,
                                             @Param("xmrzRzqk")String xmrzRzqk,@Param("xmqkRzf")String xmqkRzf,@Param("newOldTime") String newOldTime,@Param("beginTime")String beginTime,@Param("endTime")String endTime);

    List<ApiXmjy> selectProjectInfoList(@Param("yearValue")String yearValue ,@Param("monthValue")String  monthValue,@Param("corporation") String corporation,
                                        @Param("xmqkXmxz") String xmqkXmxz, @Param("xmqkJszt")String xmqkJszt,@Param("xmqkXmlx")String xmqkXmlx,@Param("xmqkQy")String xmqkQy ,@Param("xmFag")String xmFag,
                                        @Param("xmrzRzqk")String xmrzRzqk,@Param("xmqkRzf")String xmqkRzf,@Param("newOldTime") String newOldTime,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
}
