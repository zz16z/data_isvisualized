package com.zkrs.system.domain;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import org.apache.poi.hpsf.Decimal;

/**
 * 现金流入流出对象 project_cash
 * 
 * @author zkrs
 * @date 2022-10-08
 */
@Data
public class ProjectCash extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    private String xmqkXmmc;

    /** 项目ID */
    @Excel(name = "项目ID")
    private String projectId;

    /** epc签约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "epc签约日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String projectEpc;

    /** 年份 */
    @Excel(name = "年份")
    private String projectYear;

    /** 0.流出 1.流入 */
    @Excel(name = "0.流出 1.流入")
    private String outIn;

    private String cashId;

    private BigDecimal jan;
    private BigDecimal feb;
    private BigDecimal mar;
    private BigDecimal apr;
    private BigDecimal may;
    private BigDecimal jun;
    private BigDecimal jul;
    private BigDecimal aug;
    private BigDecimal sep;
    private BigDecimal oct;
    private BigDecimal nov;
    private BigDecimal decs;
    private BigDecimal all;
}
