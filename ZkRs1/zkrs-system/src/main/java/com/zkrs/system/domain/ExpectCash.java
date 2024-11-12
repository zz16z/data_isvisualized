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
 * 现金流预计情况对象 expect_cash
 * 
 * @author zkrs
 * @date 2022-07-06
 */
@Data
public class ExpectCash extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 现金流情况id */
    private Long xjlyjqkId;

    /** 预计现金流关联id */
   // @Excel(name = "预计现金流关联id")
    private String xjlsjqkYjxjlId;

    /** 项目id */
   // @Excel(name = "项目id")
    private String xjlyjqkProjectId;

    /** 预计项目总成本 */
    private String xjlyjqkYjxmcb;

    /** 收入合计 */
    private String xjlyjqkSrHjAll;

    /** 保证金 */
    private String xjlyjqkBzj;

    @Excel(name = "项目名称")
    private String xmqkXmmc;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date xjlyjqkRq;

    /** 已经出售总价改为预计出售总价 */
    @Excel(name = "预计出售总价")
    private BigDecimal xjlyjqkYjcszj;



    /** 预计EPC成本 */
    @Excel(name = "预计EPC成本")
    private BigDecimal xjlyjqkYjecpcb;

    /** 预计项目公司成本 */
    @Excel(name = "预计项目公司成本")
    private BigDecimal xjlyjqkYjxmgscb;

    /** 收款额改为预收购款 */
    @Excel(name = "预收购款")
    private BigDecimal xjlyjqkSk;

    /** 融资款 */
    @Excel(name = "融资款")
    private BigDecimal xjlyjqkRzk;

    /** 工程款 */
    @Excel(name = "工程款")
    private BigDecimal xjlyjqkGck;

    /** 电费收款不用了改为资源费 */
    @Excel(name = "资源费")
    private BigDecimal xjlyjqkDfsk;

    /** 预计总收入 */
    @Excel(name = "预计总收入")
    private BigDecimal xjlyjqkSrHj;

    /** 设备款 */
    @Excel(name = "设备款")
    private BigDecimal xjlyjqkSbk;

    /** 建安款 */
    @Excel(name = "建安款")
    private BigDecimal xjlyjqkJak;

    /** 其他款项 */
    @Excel(name = "其他款项")
    private BigDecimal xjlyjqkQtkx;

    /** 预计支持合计 */
    @Excel(name = "预计总支出")
    private BigDecimal xjlyjqkZcHj;

    /** 是否删除：未删除 0、  删除 1 */
    //@Excel(name = "是否删除：未删除 0、  删除 1")
    private String isDelete;


    /** 预计利润 */
    @Excel(name = "预计利润")
    private BigDecimal xjlyjqkYjlr;

    /** 预计EPC结算价 */
    @Excel(name = "预计EPC结算价")
    private BigDecimal xjlyjqkYjepcjsj;

    /** 实际总支出 */
    @Excel(name = "实际月支出(万元)")
    private BigDecimal xjlsjqkZcHj;

    /** 实际总收入 */
    @Excel(name = "实际月收入(万元)")
    private BigDecimal xjlsjqkSrHj;



}
