package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//实际情况详情
@Data
public class ApiXmSjqkxq {
    //////////////预计情况///////////////////////////////////
//    //预计利润
//    @ApiResultsVo(attributeName="预计利润")
//    String  xjlyjqkYjlr;
//    //预计EPC结算价
//    @ApiResultsVo(attributeName="预计EPC结算价")
//    String  xjlyjqkYjepcjsj;
//    //预计出售总价
//    @ApiResultsVo(attributeName="预计出售总价")
//    String  xjlyjqkYjcszj;
//    //预计EPC成本
//    @ApiResultsVo(attributeName="预计EPC成本")
//    String  xjlyjqkYjecpcb;
//
//    //预计项目公司成本
//    @ApiResultsVo(attributeName="预计项目公司成本")
//    String  xjlyjqkYjxmgscb;
//    ///////////////预计收入//////////////////////
//    //年预计总收入
//    @ApiResultsVo(attributeName="年预计总收入")
//    String  xjlyjqkSrHj;
//    //月预计总收入
//    @ApiResultsVo(attributeName="月预计总收入")
//    String  yXjlyjqkSrHj;
//    //预收购款
//    @ApiResultsVo(attributeName="预收购款")
//    String  xjlyjqkSk;
//    //融资款
//    @ApiResultsVo(attributeName="融资款")
//    String  xjlyjqkRzk;
//    //工程款
//    @ApiResultsVo(attributeName="工程款")
//    String  xjlyjqkGck;
//
//
//    ///////////////预计支出//////////////////////
//    //年预计总支出
//    @ApiResultsVo(attributeName="年预计总支出")
//    String  xjlyjqkZcHj;
//    //月预计总支出
//    @ApiResultsVo(attributeName="月预计总支出")
//    String  yXjlyjqkZcHj;
//    //设备款
//    @ApiResultsVo(attributeName="设备款")
//    String  xjlyjqkSbk;
//    //建安款
//    @ApiResultsVo(attributeName="建安款")
//    String  xjlyjqkJak;
//    //其他款项
//    @ApiResultsVo(attributeName="其他款项")
//    String  xjlyjqkQtkx;
//


    //////实际收入：

    //项目应收帐款总额(应付总额)
    @ApiResultsVo(attributeName="项目应收帐款总额")
    String  xjlsjqkXmyszkze;
    // //实际应付未付款
    @ApiResultsVo(attributeName="应付余额")
    String  xjlsjqkYfwsk;


    //应收余额
    @ApiResultsVo(attributeName="应收余额")
    String  xjlsjqkYsye;

    //年实际总收入
    @ApiResultsVo(attributeName="年实际总收入")
    String  xjlsjqkSrHj;
    //月实际总收入
    @ApiResultsVo(attributeName="月实际总收入")
    String  yXjlsjqkSrHj;
    //预收购款
    @ApiResultsVo(attributeName="预收购款")
    String  xjlsjqkSk;
    //融资款
    @ApiResultsVo(attributeName="融资款")
    String  xjlsjqkRzk;
    //工程款
    @ApiResultsVo(attributeName="工程款")
    String  xjlsjqkGck;

    ///   实际支出合计
    //年实际总支出
    @ApiResultsVo(attributeName="年实际总支出")
    String  xjlsjqkZcHj;
    //月实际总支出
    @ApiResultsVo(attributeName="月实际总支出")
    String  yXjlsjqkZcHj;
    //设备款
    @ApiResultsVo(attributeName="设备款")
    String  xjlsjqkSbk;
    //建安款
    @ApiResultsVo(attributeName="建安款")
    String  xjlsjqkJak;
    //其他款项
    @ApiResultsVo(attributeName="其他款项")
    String  xjlsjqkQt;
    //履约保证金
    @ApiResultsVo(attributeName="履约保证金")
    String  xjlsjqkLybzj;


}
