package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

import java.io.Serializable;

//融资详情-标题
@Data
public class ApiXmRzxq implements Serializable {



    //项目名称
    String xmqkXmmc;
    //融资机构
    String xmrzRzjg;
    //签约日期
    String xmrzQyrq;
    //融资总额
    String xmrzRzze;
    //已放款总额
    String xmrzYfkze;
    //预计下笔放款
    String xmrzYjxbfk;
    //预计下次放款时间
    String xmrzYjxbfksj;
    //融资利率
    String xmrzRzll;
    //保证金
    String xmrzBzj;
    //首年IRR
    String xmrzSnirr;
    //全周期综合IRR
    String xmrzQzqzhirr;
    //手续费收取方式
    String xmrzSxfsqfs;
    //下笔放款条件
    String xmrzXbfktj;

}
