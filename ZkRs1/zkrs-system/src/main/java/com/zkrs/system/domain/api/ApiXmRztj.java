package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//项目融资统计
@Data
public class ApiXmRztj {

    //融资总金额
    String  xmrzRzze="0";
    //已放款
    String  xmrzYfkze="0";;
    //预计下次放款数
    String  xmrzYjxbfk="0";
    //预计下次放款时间
    String  xmrzYjxbfksj="0";
    //已还款
    String    financingBysjhk;
    //已放款
    String january ;
    //数据标记 总计 0 、 放款 1  、 还款  2
    String  isbj ;
    //还款月份
    String  hkyf;
    //放款月份
    String  fkyf;
}
