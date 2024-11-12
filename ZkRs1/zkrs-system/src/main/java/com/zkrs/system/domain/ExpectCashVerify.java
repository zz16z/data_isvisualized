package com.zkrs.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 现金流预计情况对象 expect_cash
 * 
 * @author zkrs
 * @date 2022-07-06
 */
@Data
public class ExpectCashVerify extends BaseEntity
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

    @Excel(name = "序号")
    private String xh;

    @Excel(name = "项目名称")
    private String xmqkXmmc;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String xjlyjqkRq;

    /** 已经出售总价 */
    @Excel(name = "预计出售总价")
    private String xjlyjqkYjcszj;

    /** 预计EPC成本 */
    @Excel(name = "预计EPC成本")
    private String xjlyjqkYjecpcb;

    /** 预计项目公司成本 */
    @Excel(name = "预计项目公司成本")
    private String xjlyjqkYjxmgscb;

    /** 收款额 */
    @Excel(name = "预收购款")
    private String xjlyjqkSk;

    /** 融资款 */
    @Excel(name = "融资款")
    private String xjlyjqkRzk;

    /** 工程款 */
    @Excel(name = "工程款")
    private String xjlyjqkGck;

//    /** 电费收款不用了改为资源费 */
    @Excel(name = "资源费")
    private String xjlyjqkDfsk;

    /** 预计收入合计 */
    @Excel(name = "预计总收入")
    private String xjlyjqkSrHj;

    /** 设备款 */
    @Excel(name = "设备款")
    private String xjlyjqkSbk;

    /** 建安款 */
    @Excel(name = "建安款")
    private String xjlyjqkJak;

    /** 其他款项 */
    @Excel(name = "其他款项")
    private String xjlyjqkQtkx;

    /** 预计支持合计 */
    @Excel(name = "预计总支出")
    private String xjlyjqkZcHj;
    /** 预计利润 */
    @Excel(name = "预计利润")
    private String xjlyjqkYjlr;

    /////////////实际类代码////////////////

    /** 现金流情况id */
    private Long xjlsjqkId;

    /** 项目id */
    // @Excel(name = "项目id")
    private String xjlsjqkProjectId;


    /** 收购款 */
    @Excel(name = "实际预收购款")
    private String xjlsjqkSk;

    /** 融资款 */
    @Excel(name = "实际融资款")
    private String xjlsjqkRzk;

//    /** 电费收款 */
//    @Excel(name = "电费收款")
//    private String xjlsjqkDfsk;

    /** 实际收入合计 */
    @Excel(name = "实际收入合计")
    private String xjlsjqkSrHj;

    /** 设备款 */
    @Excel(name = "实际设备款")
    private String xjlsjqkSbk;

    /** 建安款 */
    @Excel(name = "实际建安款")
    private String xjlsjqkJak;

    /** 实际支持合计 */
    @Excel(name = "实际支出合计")
    private String xjlsjqkZcHj;

    /** 应付余额 */
    @Excel(name = "应付余额")
    private String xjlsjqkYfwsk;

    /** 是否删除：未删除 0、  删除 1 */
    // @Excel(name = "是否删除：未删除 0、  删除 1")
    private String isDelete;

    /** 履约保证金 */
    @Excel(name = "履约保证金")
    private String xjlsjqkLybzj;

    /** 其他 */
    @Excel(name = "实际其他款项")
    private String xjlsjqkQt;

    /** 工程款 */
    @Excel(name = "实际工程款")
    private String xjlsjqkGck;

    /** 预计利润 */
    @Excel(name = "预计EPC结算价")
    private BigDecimal xjlyjqkYjepcjsj;
   
}
