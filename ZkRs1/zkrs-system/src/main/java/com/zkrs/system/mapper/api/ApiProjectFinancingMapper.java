package com.zkrs.system.mapper.api;
import com.zkrs.system.domain.api.*;
import com.zkrs.system.domain.dto.ProjectFinancingDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目管理Mapper接口
 * 
 * @author zkrs
 * @date 2022-07-01
 */
public interface ApiProjectFinancingMapper
{


    /**
     * 项目管理列表

     */
    public List<ApiXmXmgl> selectgetProjectXmgl(@Param("apiXmXmgl") ApiXmXmgl apiXmXmgl);




    /**
     * 预计情况

     */
    public ApiXmYjqkxq selectProjectXmyjqkxq(@Param("yearValue") String yearValue , @Param("monthValue")String  monthValue,@Param("corporation") String corporation, @Param("xmid")String xmid, @Param("beginTime")String beginTime, @Param("endTime")String endTime);



    /**
     * 实际情况

     */
    public ApiXmSjqkxq selectProjectXmsjqkxq(@Param("yearValue") String yearValue , @Param("monthValue")String  monthValue,@Param("corporation") String corporation, @Param("xmid")String xmid,@Param("beginTime")String beginTime,@Param("endTime")String endTime);


    /**
     * 电量统计

     */
    public ApiXmDltjqk selectProjectXmDltj(@Param("yearValue") String yearValue , @Param("monthValue")String  monthValue,@Param("corporation") String corporation, @Param("xmid")String xmid,@Param("beginTime")String beginTime,@Param("endTime")String endTime);


    /**
     * 施工情况

     */

    public ApiXmSgqk selectProjectXmGsqk(@Param("yearValue") String yearValue , @Param("monthValue")String  monthValue,@Param("corporation") String corporation, @Param("xmid")String xmid,@Param("xmTypeId")String xmTypeId);

    /**
     * 施工情况-光伏

     */
    public ApiXmSgqkGf selectProjectXmGsqkGh(@Param("yearValue") String yearValue , @Param("monthValue")String  monthValue,@Param("corporation") String corporation, @Param("xmid")String xmid,@Param("xmTypeId")String xmTypeId);


    /**
     * 项目融资(安装当年统计)

     */
    public List<ApiXmRztj>  selectprojectXmRztjik(@Param("yearValue") String yearValue , @Param("monthValue")String  monthValue,@Param("corporation") String corporation, @Param("xmid")String xmid, @Param("beginTime")String beginTime, @Param("endTime")String endTime);



    /**
     * 项目现金流

     */
    public ApiXmXjl  selectprojectXmXjl(@Param("yearValue") String yearValue , @Param("monthValue")String  monthValue,@Param("corporation") String corporation, @Param("xmid")String xmid,@Param("beginTime")String beginTime,@Param("endTime")String endTime);


     /**
     * 项目现金流(年统计-总计)

     */
    public List<ApiXmXjlNf>  selectprojectXmNf(@Param("yearValue") String yearValue , @Param("monthValue")String  monthValue,@Param("corporation") String corporation, @Param("xmid")String xmid);


    /**
     * 项目现金流(月统计)

     */
    public List<ApiXmXjlNf>  selectprojectXmYf(@Param("yearValue") String yearValue , @Param("monthValue")String  monthValue,@Param("corporation") String corporation, @Param("xmid")String xmid,@Param("beginTime")String beginTime,@Param("endTime")String endTime);

    /**
     * 融资详情(标题数据)

     */
    public ApiXmRzxq  selectprojectXmRzxq(@Param("yearValue") String yearValue , @Param("monthValue")String  monthValue,@Param("corporation") String corporation, @Param("xmid")String xmid, @Param("beginTime")String beginTime, @Param("endTime")String endTime);


    /**
     * 融资详情(放款情况)

     */
    public ApiXmRzxqFkqk  selectprojectXmRzxqFkqk(@Param("yearValue") String yearValue , @Param("monthValue")String  monthValue,@Param("corporation") String corporation, @Param("xmid")String xmid,@Param("beginTime")String beginTime,@Param("endTime")String endTime);


    /**
     * 融资详情(放款情况-子项数据)

     */
    public List<ApiXmRzxqFkqkItem>  selectprojectXmRzxqFkqkItem(@Param("yearValue") String yearValue , @Param("monthValue")String  monthValue,@Param("corporation") String corporation, @Param("xmid")String xmid,@Param("beginTime")String beginTime,@Param("endTime")String endTime);


    /**
     * 融资详情(资金还款情况)

     */
    public ApiXmRzxqHkqkItem  selectprojectXmRzxqHkqk(@Param("yearValue") String yearValue , @Param("monthValue")String  monthValue,@Param("corporation") String corporation, @Param("xmid")String xmid, @Param("beginTime")String beginTime, @Param("endTime")String endTime);


    /**
     * 项目管理标题数据

     */
    public ApiXmrzbtsj  selectprojectXmglbtsj(@Param("yearValue") String yearValue , @Param("monthValue")String  monthValue,@Param("corporation") String corporation, @Param("xmid")String xmid);

}
