package com.zkrs.system.mapper.api;


import com.zkrs.system.domain.api.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目资金管理Mapper接口
 * 
 * @author zkrs
 * @date 2022-07-01
 */
public interface ApiElectricityStatisticsMapper
{


    /**
     * 项目建设收支持情况
     *

     */

    public List<ApiXmFgsskqk> selectProjectJsqk();




    /**
     * 风、光伏项目
     *

     */

    public ApiXmFgxm selectProjectFgxm(@Param("xmTYpe") String xmTYpe , @Param("yearValue")String yearValue , @Param("monthValue")String  monthValue ,@Param("beginTime")String beginTime,@Param("endTime")String endTime);





    /**
     * 分公司收款情况
     *

     */
    public List<ApiXmFgsskqk> selectprojectFgsskqk(@Param("yearValue") String yearValue, @Param("monthValue") String  monthValue ,@Param("beginTime")String beginTime,@Param("endTime")String endTime);



    /**
     * 现金流情况合计
     *

     */
    public  List<ApiXmXjlqkHj>  selectprojectXjlqkHj(@Param("yearValue") String yearValue,@Param("monthValue")  String  monthValue ,@Param("projectType")  String  projectType ,@Param("allWhere") String  allWhere,@Param("beginTime")String beginTime,@Param("endTime")String endTime);





    /**
     * 现金流情况
     *

     */

    public  List<ApiXmXjlqk>  selectProjectZjXjlqk(@Param("yearValue") String yearValue,@Param("monthValue")  String  monthValue ,@Param("projectType")  String  projectType,@Param("allWhere") String  allWhere);




    /**
     * 资金统计
     *

     */
    public  ApiXmZjtj selectProjectZjTj( );



    /**
     * 融资情况
     *

     */

    public ApiXmXjrzqk selectProjectZjRzqk(@Param("yearValue") String yearValue, @Param("monthValue") String  monthValue ,@Param("projectType") String  projectType,@Param("beginTime")String beginTime,@Param("endTime")String endTime);



    /**
     * 建设项目收支情况
     *

     */

    public List<ApiXmXmjsszqk>  selectProjectJsxmszqk(@Param("corporation")String corporation  , @Param("xmmc") String xmmc , @Param("yearValue") String yearValue , @Param("monthValue") String  monthValue ,@Param("projectState") String projectState ,
                                                      @Param("xmqkJszt")String  xmqkJszt,@Param("xmqkXmlx")String  xmqkXmlx,@Param("xmFag")String  xmFag,@Param("newOldTime") String newOldTime,@Param("xmrzRzqk")String xmrzRzqk,@Param("xmqkHzf")String xmqkHzf,@Param("xmqkSgf")String xmqkSgf,@Param("xmqkRzf")String xmqkRzf,@Param("beginTime")String beginTime,@Param("endTime")String endTime);



    /**
     * 查询总和
     *

     */

    public ApiXmXmjsszqkTotal selectProjectJsxmszqkTotal(@Param("corporation")String corporation  , @Param("xmmc") String xmmc , @Param("yearValue") String yearValue , @Param("monthValue") String  monthValue ,@Param("projectState") String projectState,
                                                         @Param("xmqkJszt") String  xmqkJszt,@Param("xmqkXmlx")String  xmqkXmlx,@Param("xmFag")String  xmFag,@Param("newOldTime") String newOldTime,@Param("xmrzRzqk")String xmrzRzqk,@Param("xmqkHzf")String xmqkHzf,@Param("xmqkSgf")String xmqkSgf,@Param("xmqkRzf")String xmqkRzf,@Param("beginTime")String beginTime,@Param("endTime")String endTime);




    /**
     * 项目融资
     *

     */

    public List<ApiXmZjrz>  selectProjectZjXmrz(@Param("xllx")String xllx , @Param("yearValue")String yearValue , @Param("monthValue")String  monthValue ,@Param("xmqkXmmc")String  xmqkXmmc,
                                                @Param("financeManager")String financeManager,@Param("financingInstitutions")String financingInstitutions ,@Param("financingSituation")String financingSituation,@Param("xmFag")String xmFag,@Param("xmqkXmxz")String xmqkXmxz,@Param("xmqkJszt")String xmqkJszt,@Param("xmrzQyrq")String xmrzQyrq,@Param("newOldTime")String newOldTime ,@Param("beginTime")String beginTime,@Param("endTime")String endTime);



    /**
     * 项目融资
     *

     */

    public List<ApiXmElectricityList>  selectProjectElectricityList(@Param("xllx")String xllx , @Param("yearValue")String yearValue , @Param("monthValue")String  monthValue ,@Param("xmqkXmmc")String  xmqkXmmc,
                                                @Param("xmqkLyfgs")String xmqkLyfgs, @Param("xmqkQy")String xmqkQy , @Param("xmqkXmlx")String xmqkXmlx ,@Param("xmFag")String xmFag, @Param("xmqkXmxz")String xmqkXmxz,@Param("newOldTime")String newOldTime,@Param("beginTime")String beginTime,@Param("endTime")String endTime  );


    public ApiXmElectricityTotal  selectProjectElectricityTotal(@Param("xllx")String xllx , @Param("yearValue")String yearValue , @Param("monthValue")String  monthValue ,@Param("xmqkXmmc")String  xmqkXmmc,
                                                                    @Param("xmqkLyfgs")String xmqkLyfgs, @Param("xmqkQy")String xmqkQy , @Param("xmqkXmlx")String xmqkXmlx ,@Param("xmFag") String xmFag,@Param("xmqkXmxz") String xmqkXmxz,@Param("newOldTime") String newOldTime ,@Param("beginTime")String beginTime,@Param("endTime")String endTime);


    public ApiXmZjrzTotal  selectProjectZjXmrzTotal(@Param("xllx")String xllx , @Param("yearValue")String yearValue , @Param("monthValue")String  monthValue ,@Param("xmqkXmmc")String  xmqkXmmc,
                                                @Param("financeManager")String financeManager,@Param("financingInstitutions")String financingInstitutions ,@Param("financingSituation")String financingSituation,@Param("xmFag")String xmFag,@Param("xmqkXmxz")String xmqkXmxz,@Param("xmqkJszt")String xmqkJszt,@Param("xmrzQyrq")String xmrzQyrq,@Param("newOldTime")String newOldTime,@Param("beginTime")String beginTime,@Param("endTime")String endTime );



    /**
     * 资金还款
     *

     */

    public List<ApiXmZjhk>  selectProjectZjHk(@Param("xllx")String xllx , @Param("yearValue")String yearValue , @Param("monthValue")String  monthValue,@Param("xmqkXmmc") String xmqkXmmc ,
                                              @Param("financeManager") String  financeManager ,@Param("financingInstitutions") String financingInstitutions,@Param("financingSituation") String financingSituation);


    public ApiXmZjrzTotal selectProjectZjHkTotal(@Param("xllx")String xllx , @Param("yearValue")String yearValue , @Param("monthValue")String  monthValue,@Param("xmqkXmmc") String xmqkXmmc ,
                                              @Param("financeManager") String  financeManager ,@Param("financingInstitutions") String financingInstitutions,@Param("financingSituation") String financingSituation);


    String selectLybzjBack();

    String selectCash();

}
