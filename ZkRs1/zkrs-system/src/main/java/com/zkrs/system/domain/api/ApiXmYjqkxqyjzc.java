package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//预计支出
@Data
public class ApiXmYjqkxqyjzc {


    ///////////////预计支出//////////////////////
    //年预计总支出
    String  xjlyjqkZcHj;
    //月预计总支出
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


   //图表数据
    ApiRs  data;


}
