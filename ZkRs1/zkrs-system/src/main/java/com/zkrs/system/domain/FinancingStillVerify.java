package com.zkrs.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

/**
 * 还款情况对象 financing_still
 * 
 * @author zkrs
 * @date 2022-07-23
 */
@Data
public class FinancingStillVerify extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "序号")
    private String xh;



    private String financingId;

    /** 成本 */
    @Excel(name = "成本")
    private String financingCb;


    /** 日期 */
    @Excel(name = "日期")
    //@JsonFormat(pattern = "yyyy-MM-dd")
    @ApiResultsVo(attributeName = "date")
    private String  financingRq;



    /** 利息 */
    @Excel(name = "利息")
    private String financingLx;

    /** 手续费 */
    @Excel(name = "手续费")
    private String financingSxf;

    /** 还款保证金 */
    @Excel(name = "还款保证金")
    private String financingBzj;

    // 判断实际和预计：0预计、1实际
    private  String isYjSj;

    @Excel(name = "还款合计")
    private String financingBysjhk;

}
