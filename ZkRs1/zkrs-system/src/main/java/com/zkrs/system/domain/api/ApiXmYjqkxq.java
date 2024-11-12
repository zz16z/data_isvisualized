package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import com.zkrs.system.section.ApiZd;
import lombok.Data;

//预计情况详情
@Data
public class ApiXmYjqkxq {
    //////////////预计情况///////////////////////////////////
    //预计利润
    @ApiResultsVo(attributeName="预计利润")
    String  xjlyjqkYjlr;
    //预计EPC结算价
    @ApiResultsVo(attributeName="预计EPC结算价")
    String  xjlyjqkYjepcjsj;
    //预计出售总价
    @ApiResultsVo(attributeName="预计出售总价")
    String  xjlyjqkYjcszj;
    //预计EPC成本
    @ApiResultsVo(attributeName="预计EPC成本")
    String  xjlyjqkYjecpcb;
    //预计总成本
    String  xjlyjqkYjzcb;


    //预计项目公司成本（项目公司成本+项目EPC成本）
    @ApiResultsVo(attributeName="预计项目公司成本")
    String  xjlyjqkYjxmgscb;
    ///////////////预计收入//////////////////////
    //年预计总收入
    @ApiResultsVo(attributeName="年预计总收入")
    String  xjlyjqkSrHj;
    //月预计总收入
    @ApiResultsVo(attributeName="月预计总收入")
    String  yXjlyjqkSrHj;
    //预收购款
    @ApiResultsVo(attributeName="预收购款")
    String  xjlyjqkSk;
    //融资款
    @ApiResultsVo(attributeName="融资款")
    String  xjlyjqkRzk;
    //工程款
    @ApiResultsVo(attributeName="工程款")
    String  xjlyjqkGck;


    ///////////////预计支出//////////////////////
    //年预计总支出
    @ApiResultsVo(attributeName="年预计总支出")
    String  xjlyjqkZcHj;
    //月预计总支出
    @ApiResultsVo(attributeName="月预计总支出")
    String  yXjlyjqkZcHj;
    //设备款
    @ApiResultsVo(attributeName="设备款")
    String  xjlyjqkSbk;
    //建安款
    @ApiResultsVo(attributeName="建安款")
    String  xjlyjqkJak;
    //其他款项
    @ApiResultsVo(attributeName="其他款项")
    String  xjlyjqkQtkx;






}
