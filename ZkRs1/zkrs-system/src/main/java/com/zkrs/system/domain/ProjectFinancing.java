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
 * 项目融资对象 project_financing
 * 
 * @author zkrs
 * @date 2022-07-01
 */
@Data
public class ProjectFinancing extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long xmrzId;

    /** 未放款 */
    private BigDecimal xmrzWfkze;

    /** 是否满足要求*/
    private String xmrzIsxcfk;

    /** 项目id */
//    @Excel(name = "项目id")
    private String xmrzProjectId;

    @Excel(name = "项目名称")
    private String xmqkXmmc;


    /** 融资机构 */
    @Excel(name = "融资机构")
    private String xmrzRzjg;

    /** 签约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签约日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date xmrzQyrq;

    /** 预计下笔放款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计下笔放款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date xmrzYjxbfksj;


    /** 融资负责人 */
    @Excel(name = "融资负责人")
    private String xmrzRzfzr;

    /** 融资总额 */
    @Excel(name = "融资总额")
    private BigDecimal xmrzRzze;

    /** 已放款总额 */
    @Excel(name = "已放款总额")
    private BigDecimal xmrzYfkze;

    /** 预计下笔放款 */
    @Excel(name = "预计下笔放款")
    private BigDecimal xmrzYjxbfk;

    /** 保证金 */
    @Excel(name = "保证金")
    private BigDecimal xmrzBzj;

    /** 融资利率 */
    @Excel(name = "融资利率")
    private String xmrzRzll;

    /** 手续费收取方式 */
    @Excel(name = "手续费收取方式")
    private String xmrzSxfsqfs;

    /** 首年IRR */
    @Excel(name = "首年IRR")
    private String xmrzSnirr;

    /** 全周期综合irr */
    @Excel(name = "全周期综合irr")
    private String xmrzQzqzhirr;

    /** 融资情况 */
//    @Excel(name = "融资情况")
    private Integer xmrzRzqk;

    /** 下笔放款条件 */
    @Excel(name = "下笔放款条件")
    private String xmrzXbfktj;


    /** 成本 */
    @Excel(name = "成本")
    private BigDecimal xmrzCb;
    /** 利息 */
    @Excel(name = "利息")
    private BigDecimal xmrzLx;
    /** 手续费 */
    @Excel(name = "手续费")
    private BigDecimal xmrzSxf;
    /** 还款保证金 */
    @Excel(name = "还款保证金")
    private BigDecimal xmrzHkbzj;

    @Excel(name = "融资风险提示")
    private String xmrzFxts;


    /** 是否删除：未删除 0、  删除 1 */
   // @Excel(name = "是否删除：未删除 0、  删除 1")
    private String isDelete;


}
