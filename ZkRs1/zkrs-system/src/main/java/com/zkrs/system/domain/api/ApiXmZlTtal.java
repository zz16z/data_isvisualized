package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//项目总览统计
@Data
public class ApiXmZlTtal {

    //项目总数
    @ApiResultsVo(attributeName="项目总数")
    String  xmZs;
    //容量总数
    @ApiResultsVo(attributeName="容量总数")
    String  xmRlzs;
    //预计出售总额
    @ApiResultsVo(attributeName="预计出售总额")
    String xmYjcsze;
    //预计EPC结算总价
    @ApiResultsVo(attributeName="预计EPC结算总价")
    String  xmYjepcjszj;



}
