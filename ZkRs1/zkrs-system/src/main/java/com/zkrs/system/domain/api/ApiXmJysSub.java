package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//项目经营计算
@Data
public class ApiXmJysSub {

//    //预计出售总价
//    @ApiResultsVo(attributeName="预计出售总价")
//    String xjlyjqkYjcszj;
//    @ApiResultsVo(attributeName="预计总成本")
//    String xjlyjqkYjxmgscb;

    //xjlyjqkYjcszj-xjlyjqkYjxmgscb
    @ApiResultsVo(attributeName="预计差额")
    String yjlrSub;


//    //预计EPC结算总价
//    @ApiResultsVo(attributeName="预计EPC结算总价")
//    String xjlyjqkYjepcjsj;
//    //预计EPC成本总额
//    @ApiResultsVo(attributeName="预计EPC成本总额")
//    String xjlyjqkYjecpcb;

    //xjlyjqkYjepcjsj-xjlyjqkYjecpcb
    @ApiResultsVo(attributeName="预计EPC差额")
    String yjepcSub;




//    //实际收入总额
//    @ApiResultsVo(attributeName="实际收入总额")
//    String xjlsjqkSrHj;
//    //实际支付总额
//    @ApiResultsVo(attributeName="实际支付总额")
//    String xjlsjqkZcHj;

    //xjlsjqkSrHj-xjlsjqkZcHj
    @ApiResultsVo(attributeName="实际差额")
    String sjSub;




}
