package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//项目经营数据-预计经营情况
@Data
public class ApiXmJysjYjjyqk {

    //预计出售总价
    @ApiResultsVo(attributeName="预计出售总价")
    String xjlyjqkYjcszj;
    //预计epc结算价
    @ApiResultsVo(attributeName="预计EPC结算价")
    String xjlyjqkYjepcjsj;
    //项目公司成本
    @ApiResultsVo(attributeName="项目公司成本")
    String xjlyjqkYjxmgscb;
    //资源费
//    @ApiResultsVo(attributeName="资源费")
//    String xjlyjqkDfsk;
    //项目EPC成本
    @ApiResultsVo(attributeName="项目EPC成本")
    String xjlyjqkYjecpcb;
    //成本总价
    @ApiResultsVo(attributeName="预计总成本")
    String cbzj;

    //预计EPC利润
    @ApiResultsVo(attributeName="预计EPC利润")
    String xjlyjqkYjlr;


}
