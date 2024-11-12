package com.zkrs.system.domain.api;

import lombok.Data;

//资金还款
@Data
public class ApiXmZjhk {

    //项目名称
    String xmakXmmc;
    //成本
    String financingCb;
    //放款年份
    String fknf;
    //放款月份
    String fkyf;
    //利息
    String financingLx;
    //手续费
    String financingSxf;
    //合计
    String financingBysjhk;
    //保证金
    String financingBzj;
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
