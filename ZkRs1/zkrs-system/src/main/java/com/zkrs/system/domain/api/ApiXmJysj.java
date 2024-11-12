package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//项目经营数据
@Data
public class ApiXmJysj {

    //预计出售总价
    @ApiResultsVo(attributeName="预计出售总价",filterNull="1")
    String xjlyjqkYjcszj;
    @ApiResultsVo(attributeName="预计总成本",filterNull="1")
    String xjlyjqkYjxmgscb;
    //预计EPC结算总价
    @ApiResultsVo(attributeName="预计EPC结算总价",filterNull="1")
    String xjlyjqkYjepcjsj;
    //预计EPC成本总额
    @ApiResultsVo(attributeName="预计EPC成本总额",filterNull="1")
    String xjlyjqkYjecpcb;
    //实际收入总额
    @ApiResultsVo(attributeName="实际收入总额",filterNull="1")
    String xjlsjqkSrHj;
    //实际支付总额
    @ApiResultsVo(attributeName="实际支付总额",filterNull="1")
    String xjlsjqkZcHj;
    //已付履约保证金
    @ApiResultsVo(attributeName="已付履约保证金")
    String xjlsjqkLybzj;
    //项目应付账款总额
    @ApiResultsVo(attributeName="项目应付账款总额")
    String xjlsjqkXmyfzkzje;
    //预计利润
    @ApiResultsVo(attributeName="预计利润")
    String yjlr;
    //应收余额
    @ApiResultsVo(attributeName="应收余额")
    String ysye;
    //合同余额
    @ApiResultsVo(attributeName="合同余额")
    String htye;



}
