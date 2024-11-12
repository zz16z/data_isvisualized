package com.zkrs.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 电量统计对象 electricity_statistics
 * 
 * @author zkrs
 * @date 2022-07-05
 */
@Data
public class ElectricityStatisticsVerify extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 电量统计id */
    private Long dltjId;

    /** 项目id */
//    @Excel(name = "项目id")
    private String dltjProjectId;

    /** 项目id */
    @Excel(name = "项目名称")
    private String projectName;

    @Excel(name = "序号")
    private String xh;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String dltjRq;

    /** 预计发电量 */
    @Excel(name = "预计发电量")
    private String dltjYjfdl;

    /** 发电量情况 */
    @Excel(name = "发电量情况")
    private String dltjQdlqk;

    /** 上月结算电费应收金额 */
    @Excel(name = "上月结算电费应收金额")
    private String dltjSyjsdfyssj;

    /** 累计未到账电费 */
    @Excel(name = "累计未到账电费")
    private String dltjLjwdzdf;

    /** 贵电工程回款 */
    @Excel(name = "贵电工程回款")
    private String dltjGdgchk;

    /** 电费收益 */
    @Excel(name = "电费收益")
    private String dltjDfsy;

    /** 补贴 */
    @Excel(name = "补贴")
    private String dltjBt;

    /** 本月收入合计 */
    @Excel(name = "本月收入合计")
    private String dltjBysrhj;

    /** 下网电费 */
    @Excel(name = "下网电费")
    private String dltjXwdf;

    /** 人工工资 */
    @Excel(name = "人工工资")
    private String dltjRggz;

    /** 文件办理及其他 */
    @Excel(name = "文件办理及其他")
    private String dltjWjbljqt;

    /** 利息 */
    @Excel(name = "利息")
    private String dltjLx;

    /** 是否删除：未删除 0、  删除 1 */
//    @Excel(name = "是否删除：未删除 0、  删除 1")
    private String isDelete;

    /** 收入合计 */
    @Excel(name = "收入合计")
    private String dltjSyhj;

    /** 支持总计 */
    @Excel(name = "支持总计")
    private String dltZczj;

    /** 累计应收国补 */
    @Excel(name = "累计应收国补")
    private String dltjLjysgb;

    /** 请输入实际结算电量 */
    @Excel(name = "实际结算电量")
    private String dltjSjjsdl;

    /** 电费结算累计实际到账(万元 */
    @Excel(name = "电费结算累计实际到账(万元")
    private String dltjDfjsljsjdz;

    /** 其他 */
    @Excel(name = "其他")
    private String dltjQt;

    /** 本月支持合计  */
    @Excel(name = "本月支持合计 ")
    private String dltjByzchj;

    /** 报销款 */
    @Excel(name = "报销款")
    private String dltjBxk;

    /** 上月结算电费应收 */
    @Excel(name = "上月结算电费应收")
    private String dltjSyjsdfysje;




    /** 累计发电量 */
    @Excel(name = "累计发电量")
    private String dltjLjfdl;
    /** 电费结算累计实际到账 */
    @Excel(name = "电费结算累计预计到账")
    private String dltjDfjsljyjdz;
    /** 预计电费收入 */
    @Excel(name = "预计电费收入")
    private String dltjYjdfsr;
    /** 实际电费收入 */
    @Excel(name = "实际电费收入")
    private String dltjSjdfsr;

    /** 实际电费收入 */
    @Excel(name = "电费结算累计应收金额")
    private String dltjDljsljysje;

}
