package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//融资情况
@Data

public class ApiXmXjrzqk {

    //本月预计放款
    @ApiResultsVo(attributeName="预计放款")
     String byyjfk;
    //本月实际放款
    @ApiResultsVo(attributeName="实际放款")
    String bysjfk;
    //本月预计还款(不要)
//    @ApiResultsVo(attributeName="本月预计还款")
//    String byyjhk;
    //本月实际还款
    @ApiResultsVo(attributeName="实际还款")
    String bysjhk;
    //签约日期
    @ApiResultsVo(attributeName="融资总额")
    String xmrzRzze;



}
