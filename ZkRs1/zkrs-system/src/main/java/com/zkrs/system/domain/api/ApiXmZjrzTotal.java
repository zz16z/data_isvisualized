package com.zkrs.system.domain.api;

import lombok.Data;

//项目融资
@Data
public class ApiXmZjrzTotal {
   /* 融资情况 */
    //融资总金额
    String xmrzRzze;
    //已放款
    String xmrzYfkze;

    /* 放款 */
    //成本
    String financingCb;
    //利息
    String financingLx;

    //手续费
    String financingSxf;

    //合计
    String financingBysjhk;

    //预计下比放款
   String xmrzXbfktj;

   //本月放款
   String  byyjfk;

  //本月放款
  String  bysjfk;
}
