package com.zkrs.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 现金流预计情况对象 expect_cash
 * 
 * @author zkrs
 * @date 2022-07-06
 */
public class ExpectCashDto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 现金流情况id */
    private Long xjlyjqkId;

    /** 预计现金流关联id */
 //   @Excel(name = "预计现金流关联id")
    private String xjlsjqkYjxjlId;

    /** 项目id */
   // @Excel(name = "项目id")
    private String xjlyjqkProjectId;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date xjlyjqkRq;



    /** 预计收入合计 */
    @Excel(name = "预计收入合计")
    private BigDecimal xjlyjqkSrHj;



    /** 预计支持合计 */
    @Excel(name = "预计支持合计")
    private BigDecimal xjlyjqkZcHj;

    /** 实际支持合计 */
    @Excel(name = "实际支持合计")
    private BigDecimal xjlsjqkSrHj;
    /** 实际支持合计 */
    @Excel(name = "实际支持合计")
    private BigDecimal xjlsjqkZcHj;

    public Long getXjlyjqkId() {
        return xjlyjqkId;
    }

    public void setXjlyjqkId(Long xjlyjqkId) {
        this.xjlyjqkId = xjlyjqkId;
    }

    public String getXjlsjqkYjxjlId() {
        return xjlsjqkYjxjlId;
    }

    public void setXjlsjqkYjxjlId(String xjlsjqkYjxjlId) {
        this.xjlsjqkYjxjlId = xjlsjqkYjxjlId;
    }

    public String getXjlyjqkProjectId() {
        return xjlyjqkProjectId;
    }

    public void setXjlyjqkProjectId(String xjlyjqkProjectId) {
        this.xjlyjqkProjectId = xjlyjqkProjectId;
    }

    public Date getXjlyjqkRq() {
        return xjlyjqkRq;
    }

    public void setXjlyjqkRq(Date xjlyjqkRq) {
        this.xjlyjqkRq = xjlyjqkRq;
    }

    public BigDecimal getXjlyjqkSrHj() {
        return xjlyjqkSrHj;
    }

    public void setXjlyjqkSrHj(BigDecimal xjlyjqkSrHj) {
        this.xjlyjqkSrHj = xjlyjqkSrHj;
    }

    public BigDecimal getXjlyjqkZcHj() {
        return xjlyjqkZcHj;
    }

    public void setXjlyjqkZcHj(BigDecimal xjlyjqkZcHj) {
        this.xjlyjqkZcHj = xjlyjqkZcHj;
    }

    public BigDecimal getXjlsjqkSrHj() {
        return xjlsjqkSrHj;
    }

    public void setXjlsjqkSrHj(BigDecimal xjlsjqkSrHj) {
        this.xjlsjqkSrHj = xjlsjqkSrHj;
    }

    public BigDecimal getXjlsjqkZcHj() {
        return xjlsjqkZcHj;
    }

    public void setXjlsjqkZcHj(BigDecimal xjlsjqkZcHj) {
        this.xjlsjqkZcHj = xjlsjqkZcHj;
    }
}
