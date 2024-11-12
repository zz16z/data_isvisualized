package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiZd;
import lombok.Data;

//项目现状详情
@Data
public class ApiXmXmXzxq {


    //项目名称
    String xmqkXmmc;
    //容量
    String xmqkRl;
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

}
