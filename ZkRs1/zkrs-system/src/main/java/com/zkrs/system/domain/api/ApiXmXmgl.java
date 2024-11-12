package com.zkrs.system.domain.api;


import com.zkrs.system.section.ApiZd;
import lombok.Data;

//项目管理首页
@Data
public class ApiXmXmgl {

    //项目名称
    String xmqkXmmc;
    //容量
    Integer xmqkRl;
    //项目分类
    @ApiZd(attributeName="xmqkXmlx")
    String xmqkXmlx;
    //项目现状
    @ApiZd(attributeName="xmqkXmxz")
    String xmqkXmxz;
    //建设状态
    @ApiZd(attributeName="xmqkJszt")
    String xmqkJszt;
    //履约分公司
    @ApiZd(attributeName="xmqkLyfgs")
    String xmqkLyfgs;
    //动工时间
    String xmqkHtsj;
    //合作方
    String xmqkHzf;
    //收购方
    String xmqkSgf;
    //融资方
    String xmqkRzf;
    //页数
    Integer   pageNum ;
    //分页数据大小
    Integer pageSize ;
    //0表示查询全部，1表示分页查询(需要分页参数)
    Integer isFull=1;
   //聚合查询参数
   String  aggregation;
    //项目id
    String  xmid;


    //项目分类id
    String xmqkXmlxId;
    //项目现状id
    String xmqkXmxzId;
    //建设状态id
    String xmqkJsztId;
    //履约分公司id
    String xmqkLyfgsId;
    //新老项目
    String newOldTime;
    String xmFag;

    //区域
    String  xmqkQy;
    //动工时间年
    String xmqkHtsjYear;
    //动工时间月
    String xmqkHtsjMonth;

    //经理查询参数
    String  manage;

    //开始时间
    String  beginTime;

    //结束时间
    String  endTime;
}
