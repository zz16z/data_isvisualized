package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiZd;
import lombok.Data;

//项目融资
@Data
public class ApiXmZjrz {

    //融资机构
    String xmrzRzjg;
    //项目名称
    String xmqkXmmc;
    //放款年份
    String fknf;
    //放款月份
    String fkyf;
    //融资总金额
    String xmrzRzze;
    //已放款
    String xmrzYfkze;
    //下笔放款条件
    String xmrzXbfktj;
    //本月预计放款
    String byyjfk;
    //本月实际放款
    String bysjfk;
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


     //首年IRR
    String xmrzSnirr  ;
   //全周期综合IRR
   String xmrzQzqzhirr ;

}
