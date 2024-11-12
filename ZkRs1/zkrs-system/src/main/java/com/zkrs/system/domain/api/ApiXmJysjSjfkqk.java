package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//项目经营数据-实际付款情况
@Data
public class ApiXmJysjSjfkqk {

    //已付设备款
    @ApiResultsVo(attributeName="已付设备款")
    String xjlsjqkSbk;
    //已付建安款
    @ApiResultsVo(attributeName="已付建安款")
    String xjlsjqkJak;
    //已付其他款项
    @ApiResultsVo(attributeName="已付其他款项")
    String xjlsjqkQt;
//    //实际应付未付款项
//    @ApiResultsVo(attributeName="实际应付未付款项")
//    String xjlsjqkYfwsk;
    //已付总金额
    @ApiResultsVo(attributeName="已付总金额")
    String xjlsjqkZcHj;
    //应付账款余额（万元）
    @ApiResultsVo(attributeName="应付账款余额")
    String xjlsjqkYfzkye;
    //实际应付未付款
    @ApiResultsVo(attributeName="实际应付未付款")
    String xjlsjqkYfwsk;
    //已付履约保证金（万元）
    @ApiResultsVo(attributeName="已付履约保证金")
    String xjlsjqkLybzj;

    //应付账款总额（万元）
    @ApiResultsVo(attributeName="应付账款总额")
    String xjlsjqkXmyfzkzje;

}
