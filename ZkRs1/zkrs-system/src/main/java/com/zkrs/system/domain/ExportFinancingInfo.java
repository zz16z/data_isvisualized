package com.zkrs.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExportFinancingInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Excel(name = "项目名称", width = 50)
    private String xmqkXmmc;

    /** 融资机构 */
    @Excel(name = "融资机构", width = 50)
    private String xmrzRzjg;

    /** 签约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签约日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String xmrzQyrq;

    /** 融资经理 */
    @Excel(name = "融资经理")
    private String xmrzRzfzr;

    /** 融资金额 */
    @Excel(name = "融资金额")
    private BigDecimal xmrzRzze;

    @Excel(name = "手续费收取方式")
    private String xmrzSxfsqfs;

    /** 首年IRR */
    @Excel(name = "首年IRR")
    private String xmrzSnirr;

    /** 全周期综合IRR */
    @Excel(name = "全周期综合IRR")
    private String xmrzQzqzhirr;

    /** 已放款 */
    @Excel(name = "已放款")
    private BigDecimal xmrzYfkze;

    /** 未放款 */
    @Excel(name = "未放款")
    private BigDecimal xmrzWfkze;

    /** 融资情况 */
    @Excel(name = "融资情况")
    private String xmrzRzqk;

    /** 预计下笔放款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计下笔放款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String xmrzYjxbfksj;

    /** 下笔放款金额 */
    @Excel(name = "下笔放款金额")
    private BigDecimal xmrzYjxbfk;

    /** 下笔放款要求，是否满足？ */
    @Excel(name = "下笔放款要求，是否满足？")
    private String xmrzIsxcfk;

    @Excel(name = "融资风险提示")
    private String xmrzFxts;

    /** 年份 */
    @Excel(name = "年份")
    private String loanYear;

    /** 一月 */
    @Excel(name = "一月")
    private BigDecimal jan;

    /** 二月 */
    @Excel(name = "二月")
    private BigDecimal feb;

    /** 三月 */
    @Excel(name = "三月")
    private BigDecimal mar;

    /** 四月 */
    @Excel(name = "四月")
    private BigDecimal apr;

    /** 五月 */
    @Excel(name = "五月")
    private BigDecimal may;

    /** 六月 */
    @Excel(name = "六月")
    private BigDecimal jun;

    /** 七月 */
    @Excel(name = "七月")
    private BigDecimal jul;

    /** 八月 */
    @Excel(name = "八月")
    private BigDecimal aug;

    /** 九月 */
    @Excel(name = "九月")
    private BigDecimal sep;

    /** 十月 */
    @Excel(name = "十月")
    private BigDecimal oct;

    /** 十一月 */
    @Excel(name = "十一月")
    private BigDecimal nov;

    /** 十二月 */
    @Excel(name = "十二月")
    private BigDecimal dec;

//    /** 累计提款 */
//    @Excel(name = "累计提款")
//    private BigDecimal all;
}
