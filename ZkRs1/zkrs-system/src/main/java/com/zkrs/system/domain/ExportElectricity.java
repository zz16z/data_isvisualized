package com.zkrs.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExportElectricity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Excel(name = "项目名称", width = 50)
    private String xmqkXmmc;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String dltjRq;

    @Excel(name = "预计发电量（万kWh）", width = 30)
    private BigDecimal dltjYjfdl;

    @Excel(name = "实际上网电量（万kWh）", width = 30)
    private BigDecimal dltjSjfdl;

    @Excel(name = "上网电量偏差（万kWh）", width = 30)
    private BigDecimal dltjPc;

    @Excel(name = "结算电量（万kWh）", width = 30)
    private BigDecimal dltjSjjsdl;

    @Excel(name = "结算电费（万元）", width = 30)
    private BigDecimal dltjSjdfsr;

    @Excel(name = "累积上网电量（万kWh）", width = 30)
    private BigDecimal dltjLjswdl;

    @Excel(name = "累计结算电量（万kWh）", width = 30)
    private BigDecimal dltjLjjsdl;

    @Excel(name = "电费结算累积应收金额（万元）", width = 30)
    private BigDecimal dltjLjfdl;

    @Excel(name = "电费结算累积实际到账（万元）", width = 30)
    private BigDecimal dltjDfjsljsjdz;

    @Excel(name = "累积未到账电费金额（万元）", width = 30)
    private BigDecimal dltjLjwdzdf;

    @Excel(name = "电费收益", width = 30)
    private BigDecimal dltjDfsy;

    @Excel(name = "补贴", width = 30)
    private BigDecimal dltjBt;

    @Excel(name = "其他", width = 30)
    private BigDecimal dltjQt;

    @Excel(name = "本月收入合计（万元）", width = 30)
    private BigDecimal dltjBysrhj;

    @Excel(name = "融资利息", width = 30)
    private BigDecimal dltjLx;

    @Excel(name = "下网电费", width = 30)
    private BigDecimal dltjXwdf;

    @Excel(name = "员工工资", width = 30)
    private BigDecimal dltjRggz;

    @Excel(name = "文件办理及其他", width = 30)
    private BigDecimal dltjWjbljqt;

    @Excel(name = "支出总计", width = 30)
    private BigDecimal dltjByzchj;
}
