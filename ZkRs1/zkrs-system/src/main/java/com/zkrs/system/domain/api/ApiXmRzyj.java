package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import com.zkrs.system.section.ApiZd;
import lombok.Data;

//融资预警
@Data
public class ApiXmRzyj {

    //项目名称
//    @ApiResultsVo(attributeName="项目名称")



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
    //还款日期
    String  financingRq;
    //还款金额
    String financingJe;

    //项目id
    String  xmid;


}
