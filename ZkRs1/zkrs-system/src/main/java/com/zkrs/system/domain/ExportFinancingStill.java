package com.zkrs.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExportFinancingStill extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Excel(name = "项目名称")
    private String xmqkXmmc;

    /** 融资Id */
    @Excel(name = "融资Id", width = 50)
    private String financingId;

    @Excel(name = "融资机构")
    private String xmrzRzjg;

    @Excel(name = "开始支付时间")
    private String beginPay;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际还款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String financingRq;

    @Excel(name = "实际还款本金")
    private BigDecimal financingCb;

    @Excel(name = "实际还款利息")
    private BigDecimal financingLx;

    @Excel(name = "实际还款手续费")
    private BigDecimal financingSxf;
}
