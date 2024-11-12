package com.zkrs.system.domain;

import java.math.BigDecimal;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

/**
 * 现金流实际情况对象 actual_cash
 * 
 * @author zkrs
 * @date 2022-07-06
 */
@Data
public class ActualCash extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    /** 现金流情况id */
    private Long xjlsjqkId;

    /** 应付账款余额（万元） */
    private BigDecimal xjlsjqkYfzkye;

    /** 已付履约保证金（万元 */
    private BigDecimal xjlsjqkLybzjBack;

    /** 预计现金流关联id */
  //  @Excel(name = "预计现金流关联id")
    private String xjlsjqkYjxjlId;

    /** 项目id */
   // @Excel(name = "项目id")
    private String xjlsjqkProjectId;

    @Excel(name = "项目名称")
    private String xmqkXmmc;

    /** 预收购款 */
    @Excel(name = "预收购款")
    private BigDecimal xjlsjqkSk;

    /** 融资款 */
    @Excel(name = "融资款")
    private BigDecimal xjlsjqkRzk;

    /** 电费收款 */
//    @Excel(name = "电费收款")
    private BigDecimal xjlsjqkDfsk;

    /** 实际收入合计 */
    @Excel(name = "实际收入合计")
    private BigDecimal xjlsjqkSrHj;

    /** 设备款 */
    @Excel(name = "设备款")
    private BigDecimal xjlsjqkSbk;

    /** 建安款 */
    @Excel(name = "建安款")
    private BigDecimal xjlsjqkJak;

    /** 实际支持合计 */
    @Excel(name = "实际总支出")
    private BigDecimal xjlsjqkZcHj;

    /** 应付余额 */
    @Excel(name = "应付余额")
    private BigDecimal xjlsjqkYfwsk;

    /** 是否删除：未删除 0、  删除 1 */
   // @Excel(name = "是否删除：未删除 0、  删除 1")
    private String isDelete;

    /** 履约保证金 */
    @Excel(name = "履约保证金")
    private BigDecimal xjlsjqkLybzj;

    /** 其他 */
    @Excel(name = "其他款项")
    private BigDecimal xjlsjqkQt;

    /** 工程款 */
    @Excel(name = "工程款")
    private BigDecimal xjlsjqkGck;

    /** 项目应收账款总额 */
    @Excel(name = "项目应收账款总额")
    private BigDecimal xjlsjqkXmyszkze;


    /** 项目应付账款总额 */
    @Excel(name = "项目应付账款总额")
    private BigDecimal xjlsjqkXmyfzkzje;


    /** 项目应付账款总额 */
    @Excel(name = "应收余额")
    private BigDecimal xjlsjqkYsye;


}
