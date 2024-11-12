package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//预计预计收入
@Data
public class ApiXmYjqkxqyjsr {


    ///////////////预计收入//////////////////////
    //年预计总收入
    String  xjlyjqkSrHj;
    //月预计总收入
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

    //图表数据
    ApiRs  data;





}
