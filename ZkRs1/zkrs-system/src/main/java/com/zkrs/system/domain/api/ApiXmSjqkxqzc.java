package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//实际情况详情-支出
@Data
public class ApiXmSjqkxqzc {


    //应收余额
    String  xjlsjqkYsye;
    //项目应收帐款总额
    String  xjlsjqkXmyszkze;
    //实际应付未付款
    String  xjlsjqkYfwsk;
    //年实际总支出
    String  xjlsjqkZcHj;
    //月实际总支出
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
    //展示数据
    ApiRs data;
}
