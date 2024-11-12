package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//资金统计
@Data
public class ApiXmZjtj {

    //应收余额
    String xjlsjqkYsye;
    //（应付未付）
    String xjlsjqkYfwsk;
    //应付余额
    String xmqkQzzyf;
    //应付履约保证金取
    String xmqkYflybzj;
    //项目公司合同应付余额
    String xmqkQzzyfXmgs;

    //现金流
    String cash;
}
