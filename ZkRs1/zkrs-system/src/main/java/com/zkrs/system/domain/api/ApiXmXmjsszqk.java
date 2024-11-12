package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiZd;
import lombok.Data;

//项目建设收支情况
@Data
public class ApiXmXmjsszqk {

    //项目现状
    @ApiZd(attributeName="xmqkXmxz")
    String  xmqkXmxz;
    //项目名称
    String  xmqkXmmc ;
    //预计收入
    String  xjlyjqkSrHj;
    //预计支出
    String  xjlyjqkZcHj;
    //实际收入
    String  xjlsjqkSrHj;
    //实际支出
    String  xjlsjqkZcHj;
    //合同应付余额
    String  xmqkQzyf;

    //分公司名称
    @ApiZd(attributeName="xmqkLyfgs")
    String  xmqkLyfgs;

    //应收未收(应收余额):
    String  xjlsjqkYsye;
    //实际应付未付款项(应付余额):
    String  xjlsjqkYfwsk;
    //项目id
    String  xmqkProjectId;

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
