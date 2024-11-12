package com.zkrs.system.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;

/**
 * 融资还款对象 project_financing_repayment
 *
 * @author zkrs
 * @date 2022-10-14
 */
@Data
public class ExportRepayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "项目名称")
    private String xmqkXmmc;

    @Excel(name = "融资机构")
    private String rzjg;

    /** 开始还本时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始还本时间",  dateFormat = "yyyy-MM-dd")
    private String beginPay;

    /** 年份 */
    @Excel(name = "年份")
    private String repaymentYear;

    /** 一月本金 */
    @Excel(name = "一月本金")
    private BigDecimal janPrincipal;

    /** 一月利息 */
    @Excel(name = "一月利息")
    private BigDecimal janInterest;

    /** 一月手续费 */
    @Excel(name = "一月手续费")
    private BigDecimal janService;

    /** 二月本金 */
    @Excel(name = "二月本金")
    private BigDecimal febPrincipal;

    /** 二月利息 */
    @Excel(name = "二月利息")
    private BigDecimal febInterest;

    /** 二月手续费 */
    @Excel(name = "二月手续费")
    private BigDecimal febService;

    /** 三月本金 */
    @Excel(name = "三月本金")
    private BigDecimal marPrincipal;

    /** 三月利息 */
    @Excel(name = "三月利息")
    private BigDecimal marInterest;

    /** 三月手续费 */
    @Excel(name = "三月手续费")
    private BigDecimal marService;

    /** 四月本金 */
    @Excel(name = "四月本金")
    private BigDecimal aprPrincipal;

    /** 四月利息 */
    @Excel(name = "四月利息")
    private BigDecimal aprInterest;

    /** 四月手续费 */
    @Excel(name = "四月手续费")
    private BigDecimal aprService;

    /** 五月本金 */
    @Excel(name = "五月本金")
    private BigDecimal mayPrincipal;

    /** 五月利息 */
    @Excel(name = "五月利息")
    private BigDecimal mayInterest;

    /** 五月手续费 */
    @Excel(name = "五月手续费")
    private BigDecimal mayService;

    /** 六月本金 */
    @Excel(name = "六月本金")
    private BigDecimal junPrincipal;

    /** 六月利息 */
    @Excel(name = "六月利息")
    private BigDecimal junInterest;

    /** 六月手续费 */
    @Excel(name = "六月手续费")
    private BigDecimal junService;

    /** 七月本金 */
    @Excel(name = "七月本金")
    private BigDecimal julPrincipal;

    /** 七月利息 */
    @Excel(name = "七月利息")
    private BigDecimal julInterest;

    /** 七月手续费 */
    @Excel(name = "七月手续费")
    private BigDecimal julService;

    /** 八月本金 */
    @Excel(name = "八月本金")
    private BigDecimal augPrincipal;

    /** 八月利息 */
    @Excel(name = "八月利息")
    private BigDecimal augInterest;

    /** 八月手续费 */
    @Excel(name = "八月手续费")
    private BigDecimal augService;

    /** 九月本金 */
    @Excel(name = "九月本金")
    private BigDecimal sepPrincipal;

    /** 九月利息 */
    @Excel(name = "九月利息")
    private BigDecimal sepInterest;

    /** 九月手续费 */
    @Excel(name = "九月手续费")
    private BigDecimal sepService;

    /** 十月本金 */
    @Excel(name = "十月本金")
    private BigDecimal octPrincipal;

    /** 十月利息 */
    @Excel(name = "十月利息")
    private BigDecimal octInterest;

    /** 十月手续费 */
    @Excel(name = "十月手续费")
    private BigDecimal octService;

    /** 十一月本金 */
    @Excel(name = "十一月本金")
    private BigDecimal novPrincipal;

    /** 十一月利息 */
    @Excel(name = "十一月利息")
    private BigDecimal novInterest;

    /** 十一月手续费 */
    @Excel(name = "十一月手续费")
    private BigDecimal novService;

    /** 十二月本金 */
    @Excel(name = "十二月本金")
    private BigDecimal decPrincipal;

    /** 十二月利息 */
    @Excel(name = "十二月利息")
    private BigDecimal decInterest;

    /** 十二月手续费 */
    @Excel(name = "十二月手续费")
    private BigDecimal decService;
}
