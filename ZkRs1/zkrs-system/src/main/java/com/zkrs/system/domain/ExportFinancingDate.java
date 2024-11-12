package com.zkrs.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExportFinancingDate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Excel(name = "项目名称", width = 50)
    private String xmqkXmmc;

    /** 融资Id */
    @Excel(name = "融资Id", width = 50)
    private String financingId;

    /** 融资机构 */
    @Excel(name = "融资机构", width = 50)
    private String xmqkRzf;

    /** 签约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签约日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String xmrzQyrq;

    /** 融资经理 */
    @Excel(name = "融资经理")
    private String xmqkRzjl;

    /** 融资金额 */
    @Excel(name = "融资金额")
    private BigDecimal xmrzRzze;

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
    private String xmrzRzqk;

    /** 融资情况 */
    @Excel(name = "融资情况")
    private String XmrzRzqkName;

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

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间")
    private String times;

    @Excel(name = "金额")
    private BigDecimal january;

    @Excel(name = "手续费收取方式")
    private String xmrzSxfsqfs;


}
