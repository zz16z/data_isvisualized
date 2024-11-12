package com.zkrs.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProjectImport {

  /*
  项目信息字段
   */
    /** $column.columnComment */
    @Excel(name = "项目名称")
    //@Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String xmqkXmmc;
    /** 容量(KW) */
    @Excel(name = "容量(KW)")
    private String xmqkRl;
    /** 项目现状 */
    @Excel(name = "项目现状")
    private Long xmqkXmxz;
    /** 建设状态 */
    @Excel(name = "建设状态")
    private Long xmqkJszt;

    /** 履约分公司 */
    @Excel(name = "履约分公司")
    private Long xmqkLyfgs;
    /** 活动时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date xmqkHtsj;

    /** 并网时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "并网时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date xmqkBwsj;

    /** 融资方 */
    @Excel(name = "融资方")
    private String xmqkRzf;

    /** 收购方 */
    @Excel(name = "收购方")
    private String xmqkSgf;

    /** 融资经理 */
    @Excel(name = "融资经理")
    private String xmqkRzjl;


      /*
     预计表
   */

    /** 已经出售总价 */
    @Excel(name = "预计出售总价")
    private BigDecimal xjlyjqkYjcszj;



    /** 预计收入合计 */
    @Excel(name = "预计收入合计")
    private BigDecimal xjlyjqkSrHj;

    /** 预计项目公司成本 */
    @Excel(name = "项目公司成本（万元）")
    private BigDecimal xjlyjqkYjxmgscb;


    /** 预计EPC成本 */
    @Excel(name = "项目EPC成本（万元）")
    private BigDecimal xjlyjqkYjecpcb;

    /*
    实际收款情况
     */

    /** 收款额 */
    @Excel(name = "已收预收购款")
    private BigDecimal xjlsjqkSk;

    /** 融资款 */
    @Excel(name = "已收融资款")
    private BigDecimal xjlsjqkRzk;

    /** 工程款 */
    @Excel(name = "已收工程款（业主回款）")
    private BigDecimal xjlsjqkGck;

    /** 电费收款 */
    @Excel(name = "已收电费")
    private BigDecimal xjlsjqkDfsk;
}
