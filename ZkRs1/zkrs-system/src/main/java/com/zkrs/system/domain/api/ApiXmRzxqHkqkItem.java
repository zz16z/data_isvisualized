package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

import java.io.Serializable;

//融资详情-还款款情况-子项数据
@Data
public class ApiXmRzxqHkqkItem implements Serializable {




    //成本
    @ApiResultsVo(attributeName="成本")
    String financingCb;
    //利息
    @ApiResultsVo(attributeName="利息")
    String financingLx;
    //手续费
    @ApiResultsVo(attributeName="手续费")
    String financingSxf;
    //保证金
    @ApiResultsVo(attributeName="保证金")
    String financingBzj;
    //还款合计
    @ApiResultsVo(attributeName="还款合计")
    String financingBysjhk;

    //还款日期
    String financingRq;
}
