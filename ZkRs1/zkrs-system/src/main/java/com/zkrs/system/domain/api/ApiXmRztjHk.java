package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//项目融资统计-还款
@Data
public class ApiXmRztjHk {


    //已还款
    @ApiResultsVo(attributeName="key_v")
    String    financingBysjhk;
    //月份
    @ApiResultsVo(attributeName="value_v")
    String  hkyf;

}
