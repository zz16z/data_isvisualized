package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//项目经营数据-实际收款情况
@Data
public class ApiXmJysjSjskqk {

    //预收购款
    @ApiResultsVo(attributeName="预收购款")
    String  xjlsjqkSk;
    //融资款
    @ApiResultsVo(attributeName="融资款")
    String  xjlsjqkRzk;
    //工程款
    @ApiResultsVo(attributeName="工程款")
    String  xjlsjqkGck;
    //已收电费
    @ApiResultsVo(attributeName="已收电费")
    String  dltjDfsy;
    //实际收入合计
    @ApiResultsVo(attributeName="实际收入合计")
    String  sjsrhj;

    //应收未收
    @ApiResultsVo(attributeName="应收未收")
    String  xjlsjqkYsye;

    //应收账款总额
    @ApiResultsVo(attributeName="应收账款总额")
    String  xjlsjqkXmyszkze;

}
