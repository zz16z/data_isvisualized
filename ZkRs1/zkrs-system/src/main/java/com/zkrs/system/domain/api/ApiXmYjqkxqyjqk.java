package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//预计情况详情
@Data
public class ApiXmYjqkxqyjqk {
    //////////////预计情况///////////////////////////////////
    //预计利润
    String  xjlyjqkYjlr;
    //预计EPC结算价
    String  xjlyjqkYjepcjsj;

    //预计总成本
    String  xjlyjqkYjzcb;

    //预计出售总价
    @ApiResultsVo(attributeName="预计出售总价")
    String  xjlyjqkYjcszj;
    //预计EPC成本
    @ApiResultsVo(attributeName="预计EPC成本")
    String  xjlyjqkYjecpcb;

    //预计项目公司成本
    @ApiResultsVo(attributeName="预计项目公司成本")
    String  xjlyjqkYjxmgscb;

    //图表数据
    ApiRs  data;






}
