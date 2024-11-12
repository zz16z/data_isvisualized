package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiZd;
import lombok.Data;

//出售预警
@Data
public class ApiXmCsyj {

    //并网时间
    String xmqkBwsj;
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
    //应出售日期
    String xmqkYcsrq;
    //预警原因
    String xmqkYjyy;
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
