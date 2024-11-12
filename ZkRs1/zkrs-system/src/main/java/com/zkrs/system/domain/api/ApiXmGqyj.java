package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import com.zkrs.system.section.ApiZd;
import lombok.Data;

//工期预警
@Data
public class ApiXmGqyj {

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
    //施工进度
    String xmqkSgjd;
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
