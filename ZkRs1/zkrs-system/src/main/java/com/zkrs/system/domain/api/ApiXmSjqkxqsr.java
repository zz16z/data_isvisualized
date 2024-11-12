package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//实际情况详情-实际收入
@Data
public class ApiXmSjqkxqsr {


    //项目应收帐款总额
    String  xjlsjqkXmyszkze;
    //实际应付未付款
    String  xjlsjqkYfwsk;
    //应收余额
    String  xjlsjqkYsye;

    //年实际总收入
    String  xjlsjqkSrHj;
    //月实际总收入
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

    //展示数据
    ApiRs data;


}
