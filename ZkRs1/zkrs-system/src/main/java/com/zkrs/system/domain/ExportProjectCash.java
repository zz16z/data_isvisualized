package com.zkrs.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 现金流入流出对象 project_cash
 *
 * @author zkrs
 * @date 2022-10-08
 */
@Data
public class ExportProjectCash extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 0.流出 1.流入 */
    private String outIn;

    private String cashId;

    @Excel(name = "项目名称", width = 50)
    private String xmqkXmmc;

    /** epc签约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "epc签约日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String projectEpc;

    /** 年份 */
    @Excel(name = "年份")
    private String projectYear;

    @Excel(name = "一月")
    private BigDecimal jan;
    @Excel(name = "二月")
    private BigDecimal feb;
    @Excel(name = "三月")
    private BigDecimal mar;
    @Excel(name = "四月")
    private BigDecimal apr;
    @Excel(name = "五月")
    private BigDecimal may;
    @Excel(name = "六月")
    private BigDecimal jun;
    @Excel(name = "七月")
    private BigDecimal jul;
    @Excel(name = "八月")
    private BigDecimal aug;
    @Excel(name = "九月")
    private BigDecimal sep;
    @Excel(name = "十月")
    private BigDecimal oct;
    @Excel(name = "十一月")
    private BigDecimal nov;
    @Excel(name = "十二月")
    private BigDecimal decs;
    @Excel(name = "合计")
    private BigDecimal all;
}
