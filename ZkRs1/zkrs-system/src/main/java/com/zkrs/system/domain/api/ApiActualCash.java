package com.zkrs.system.domain.api;

import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;



/**
 * 现金流实际情况对象 actual_cash
 * 
 * @author zkrs
 * @date 2022-07-06
 */
@Data
public class ApiActualCash
{
    private static final long serialVersionUID = 1L;


    /** 现金流情况id */
    private Long xjlsjqkId;

    /** 预计现金流关联id */
  //  @Excel(name = "预计现金流关联id")
    private String xjlsjqkYjxjlId;

    /** 项目id */
   // @Excel(name = "项目id")
    private String xjlsjqkProjectId;

    //项目名称
    private String xmqkXmmc;

    /** 收款额 */
    private String xjlsjqkSk;

    /** 融资款 */
    private String xjlsjqkRzk;

    /** 电费收款 */
    private String xjlsjqkDfsk;

    /** 实际收入合计 */
    private String xjlsjqkSrHj;

    /** 设备款 */
    private String xjlsjqkSbk;

    /** 建安款 */
    private String xjlsjqkJak;

    /** 实际支持合计 */
    private String xjlsjqkZcHj;

    /** 应付未收款 */
    private String xjlsjqkYfwsk;


    /** 履约保证金 */
    private String xjlsjqkLybzj;

    /** 其他 */
    private String xjlsjqkQt;

    /** 工程款 */
    private String xjlsjqkGck;

}
