package com.zkrs.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ManagementCash extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 实际主键回填
     */
    private String xjlsjqkId;

    /**
     * 预计主键回填
     */
    private String xjlyjqkId;

    /** 项目id */
    private String xmqkProjectId;

    /** 预计现金流ID */
    private String xjlsjqkYjxjlId;

    @Excel(name = "序号")
    private String xh;

    @Excel(name = "项目名称")
    private String xmqkXmmc;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String xjlyjqkRq;

    @Excel(name = "预计出售总价")
    private BigDecimal xjlyjqkYjcszj;

    @Excel(name = "EPC结算价")
    private BigDecimal xjlyjqkYjepcjsj;

    @Excel(name = "预计收入合计")
    private BigDecimal xjlyjqkSrHjAll;

    @Excel(name = "项目公司总成本（万元）")
    private BigDecimal xjlyjqkYjxmgscb;

    @Excel(name = "项目EPC总成本（万元）")
    private BigDecimal xjlyjqkYjecpcb;

    @Excel(name = "项目总成本（万元）")
    private BigDecimal xjlyjqkYjxmcb;

    @Excel(name = "EPC利润")
    private BigDecimal xjlyjqkYjlr;


    /*实际收入*/

    @Excel(name = "应收账款总额")
    private BigDecimal xjlsjqkXmyszkze;

    @Excel(name = "已收预收购款")
    private BigDecimal xjlsjqkSk;

    @Excel(name = "已收融资款")
    private BigDecimal xjlsjqkRzk;

    @Excel(name = "已收工程款（业主回款）")
    private BigDecimal xjlsjqkGck;

    @Excel(name = "已收电费")
    private BigDecimal xjlsjqkDfsk;

    @Excel(name = "实际收款总额")
    private BigDecimal xjlsjqkSrHj;

//    @Excel(name = "财务收款合计")
//    private BigDecimal xjlsjqkCwskhj;

    @Excel(name = "应收账款余额")
    private BigDecimal xjlsjqkYsye;

    @Excel(name = "应付账款总额")
    private BigDecimal xjlsjqkXmyfzkzje;

    @Excel(name = "已付设备款（万元）")
    private BigDecimal xjlsjqkSbk;

    @Excel(name = "已付建安款（万元）")
    private BigDecimal xjlsjqkJak;

    @Excel(name = "已付其他款项（万元）")
    private BigDecimal xjlsjqkQt;

    @Excel(name = "实际付款总额（万元）")
    private BigDecimal xjlsjqkZcHj;

//    @Excel(name = "财务已付")
//    private BigDecimal xjlsjqkCwyf;

    @Excel(name = "实际应付未付款（万元）")
    private BigDecimal xjlsjqkYfwsk;

    @Excel(name = "应付账款余额（万元）")
    private BigDecimal xjlsjqkYfzkye;

    @Excel(name = "已付履约保证金（万元）")
    private BigDecimal xjlsjqkLybzj;

    @Excel(name = "已退履约保证金（万元）")
    private BigDecimal xjlsjqkLybzjBack;

    /*预计收入*/

    @Excel(name = "融资收款")
    private BigDecimal xjlyjqkRzk;

    @Excel(name = "工程款")
    private BigDecimal xjlyjqkGck;

    @Excel(name = "其他（收购款）")
    private BigDecimal xjlyjqkSk;

    @Excel(name = "收入合计")
    private BigDecimal xjlyjqkSrHj;


    /*预计支出*/

    @Excel(name = "设备费")
    private BigDecimal xjlyjqkSbk;

    @Excel(name = "建安费")
    private BigDecimal xjlyjqkJak;

    @Excel(name = "经营管理支出（含手续利息费、税金、保险费）")
    private BigDecimal xjlyjqkQtkx;

    @Excel(name = "履约保证金")
    private BigDecimal xjlyjqkBzj;

    @Excel(name = "支出总计")
    private BigDecimal xjlyjqkZcHj;
}
