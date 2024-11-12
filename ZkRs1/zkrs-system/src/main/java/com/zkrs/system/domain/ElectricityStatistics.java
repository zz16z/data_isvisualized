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
 * 电量统计对象 electricity_statistics
 * 
 * @author zkrs
 * @date 2022-07-05
 */
@Data
public class ElectricityStatistics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 电量统计id */
    private Long dltjId;

    /** 项目id */
//    @Excel(name = "项目id")
    private String dltjProjectId;

    /** 预计发电量 */
    @Excel(name = "预计发电量")
    private String dltjYjfdl;

    /**实际上网电量（万kWh） */
    private BigDecimal dltjSjfdl;

    /**上网电量偏差（万kWh）*/
    private BigDecimal dltjPc;

    /** 累积上网电量（万kWh）*/
    private BigDecimal dltjLjswdl;
    /** 累计结算电量（万kWh）*/
    private BigDecimal dltjLjjsdl;


    /** 项目id */
    @Excel(name = "项目名称")
    private String projectName;


    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dltjRq;


    /** 发电量情况 */
    @Excel(name = "发电量情况")
    private String dltjQdlqk;

    /** 上月结算电费应收金额 */
    @Excel(name = "上月结算电费应收金额")
    private BigDecimal dltjSyjsdfyssj;

    /** 累计未到账电费 */
    @Excel(name = "累计未到账电费")
    private BigDecimal dltjLjwdzdf;

    /** 贵电工程回款 */
    @Excel(name = "贵电工程回款")
    private BigDecimal dltjGdgchk;

    /** 电费收益 */
    @Excel(name = "电费收益")
    private BigDecimal dltjDfsy;

    /** 补贴 */
    @Excel(name = "补贴")
    private BigDecimal dltjBt;

    /** 本月收入合计 */
    @Excel(name = "本月收入合计")
    private BigDecimal dltjBysrhj;

    /** 下网电费 */
    @Excel(name = "下网电费")
    private BigDecimal dltjXwdf;

    /** 人工工资 */
    @Excel(name = "人工工资")
    private BigDecimal dltjRggz;

    /** 文件办理及其他 */
    @Excel(name = "文件办理及其他")
    private BigDecimal dltjWjbljqt;

    /** 利息 */
    @Excel(name = "利息")
    private BigDecimal dltjLx;

    /** 是否删除：未删除 0、  删除 1 */
//    @Excel(name = "是否删除：未删除 0、  删除 1")
    private String isDelete;

    /** 收入合计 */
    @Excel(name = "收入合计")
    private BigDecimal dltjSyhj;

    /** 支持总计 */
    @Excel(name = "支持总计")
    private BigDecimal dltZczj;

    /** 累计应收国补 */
    @Excel(name = "累计应收国补")
    private BigDecimal dltjLjysgb;

    /** 请输入实际结算电量 */
    @Excel(name = "实际结算电量")
    private BigDecimal dltjSjjsdl;

    /** 电费结算累计实际到账(万元 */
    @Excel(name = "电费结算累计实际到账(万元")
    private BigDecimal dltjDfjsljsjdz;

    /** 其他 */
    @Excel(name = "其他")
    private BigDecimal dltjQt;

    /** 本月支持合计  */
    @Excel(name = "本月支持合计 ")
    private BigDecimal dltjByzchj;

    /** 报销款 */
    @Excel(name = "报销款")
    private BigDecimal dltjBxk;

    /** 上月结算电费应收 */
    @Excel(name = "上月结算电费应收")
    private BigDecimal dltjSyjsdfysje;




    /** 累计发电量 */
    @Excel(name = "累计发电量")
    private BigDecimal dltjLjfdl;
    /** 电费结算累计实际到账 */
    @Excel(name = "电费结算累计预计到账")
    private BigDecimal dltjDfjsljyjdz;
    /** 预计电费收入 */
    @Excel(name = "预计电费收入")
    private BigDecimal dltjYjdfsr;
    /** 实际电费收入 */
    @Excel(name = "实际电费收入")
    private BigDecimal dltjSjdfsr;


    /** 实际电费收入 */
    @Excel(name = "电费结算累计应收金额")
    private BigDecimal dltjDljsljysje;




}
