package com.zkrs.system.domain.api;

import com.zkrs.common.core.domain.BaseEntity;
import com.zkrs.system.section.ApiResultsVo;
import com.zkrs.system.section.ApiZd;
import lombok.Data;

//资金预警详情
@Data
public class ApiXmZjyjxq  {

    //项目名称
    @ApiResultsVo(attributeName="项目名称")
    String  xmqkXmmc;
    //实际支出
    @ApiResultsVo(attributeName="支出")
    String  xjlsjqkZcHj;
    //实际收入
    @ApiResultsVo(attributeName="收入")
    String  xjlsjqkSrHj;
    ///项目分类
    @ApiZd(attributeName="xmqkXmlx")
    String  xmqkXmlx;
    //项目现状
    @ApiZd(attributeName="xmqkXmxz")
    String  xmqkXmxz;
    //建设状态
    @ApiZd(attributeName="xmqkJszt")
    String  xmqkJszt;
    //容量(MV)
    @ApiResultsVo(attributeName="容量(MV)")
    Integer  xmqkRl;
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
