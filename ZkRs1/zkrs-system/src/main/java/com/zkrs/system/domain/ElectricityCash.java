package com.zkrs.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ElectricityCash extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String dltjId;
    /** 项目id */
    private String xmqkProjectId;

    @Excel(name = "序号")
    private String xh;

    @Excel(name = "项目名称")
    private String xmqkXmmc;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String dltjRq;

    @Excel(name = "预计发电量（万kWh）")
    private BigDecimal dltjYjfdl;

    @Excel(name = "本月上网电量（万kWh）")
    private BigDecimal dltjSjfdl;

    @Excel(name = "上网电量偏差（万kWh）")
    private BigDecimal dltjPc;

    @Excel(name = "本月结算电量（万kWh）")
    private BigDecimal dltjSjjsdl;

    @Excel(name = "本月结算电费（万元）")
    private BigDecimal dltjSjdfsr;

    @Excel(name = "累积上网电量（万kWh）")
    private BigDecimal dltjLjswdl;

    @Excel(name = "累积结算电量（万kWh）")
    private BigDecimal dltjLjjsdl;

    @Excel(name = "结算累积应收（万元）")
    private BigDecimal dltjLjfdl;

    @Excel(name = "结算累积到账（万元）")
    private BigDecimal dltjDfjsljsjdz;

    @Excel(name = "结算累积未到账（万元）")
    private BigDecimal dltjLjwdzdf;

    @Excel(name = "电费收益")
    private BigDecimal dltjDfsy;

    @Excel(name = "补贴")
    private BigDecimal dltjBt;

    @Excel(name = "其他")
    private BigDecimal dltjQt;

    @Excel(name = "本月收入合计（万元）")
    private BigDecimal dltjBysrhj;

    @Excel(name = "融资利息")
    private BigDecimal dltjLx;

    @Excel(name = "下网电费")
    private BigDecimal dltjXwdf;

    @Excel(name = "员工工资")
    private BigDecimal dltjRggz;

    @Excel(name = "文件办理及其他")
    private BigDecimal dltjWjbljqt;

    @Excel(name = "支出总计")
    private BigDecimal dltjByzchj;
}
