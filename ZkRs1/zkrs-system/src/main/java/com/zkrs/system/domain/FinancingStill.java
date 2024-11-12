package com.zkrs.system.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;

/**
 * 还款情况对象 financing_still
 * 
 * @author zkrs
 * @date 2022-07-23
 */
@Data
public class FinancingStill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @Excel(name = "项目名称")
    private String xmqkXmmc;

    /** 融资表关联id */
   // @Excel(name = "融资表关联id")
    private Long financingId;

    /** 成本 */
    @Excel(name = "成本")
    private BigDecimal financingCb;


    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date  financingRq;



    /** 利息 */
    @Excel(name = "利息")
    private BigDecimal financingLx;

    /** 手续费 */
    @Excel(name = "手续费")
    private BigDecimal financingSxf;

    /** 还款保证金 */
    @Excel(name = "还款保证金")
    private BigDecimal financingBzj;

    /** 是否删除：未删除 0、  删除 1 */
    private String isDelete;
    // 判断实际和预计：0预计、1实际
    private  String isYjSj;

    @Excel(name = "还款合计")
    private BigDecimal financingBysjhk;


    @Excel(name = "开始还本时间",  dateFormat = "yyyy-MM-dd")
    private String beginPay;

    private String quDate;

}
