package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import com.zkrs.system.section.ApiZd;
import lombok.Data;

//风、光伏项目
@Data
public class ApiXmFgxm {
   ///预计出售总价
   @ApiResultsVo(attributeName="预计出售总价",filterNull="1")
    String xjlyjqkYjcszj;
    ///预计总成本
    @ApiResultsVo(attributeName="预计总成本",filterNull="1")
    String xjlyjqkYjxmgscb;
    ///预计EPC结算总价
    @ApiResultsVo(attributeName="预计EPC结算总价",filterNull="1")
    String xjlyjqkYjepcjsj;
    ///实际收入总额
    @ApiResultsVo(attributeName="实际收入总额",filterNull="1")
    String xjlsjqkSrHj;
    ///预计EPC总成本
    @ApiResultsVo(attributeName="预计EPC总成本",filterNull="1")
    String xjlyjqkYjecpcb;
    ///实际支付总额
    @ApiResultsVo(attributeName="实际支付总额",filterNull="1")
    String xjlsjqkZcHj;
//    ///预计利润
//    @ApiResultsVo(attributeName="预计利润")
//    String yjlr;
    ///项目应收账款总额
    @ApiResultsVo(attributeName="项目应收账款总额")
    String xjlsjqkXmyszkze;
    ///月实际总收入
    @ApiResultsVo(attributeName="月实际总收入")
    String ysjsr;
    ///年实际总收入
    @ApiResultsVo(attributeName="年实际总收入")
    String nsjsr;

}
