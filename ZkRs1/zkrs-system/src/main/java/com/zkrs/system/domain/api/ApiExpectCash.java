package com.zkrs.system.domain.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 现金流预计情况对象 expect_cash
 * 
 * @author zkrs
 * @date 2022-07-06
 */
@Data
public class ApiExpectCash
{
    private static final long serialVersionUID = 1L;

    /** 现金流情况id */
    private Long xjlyjqkId;

    /** 预计现金流关联id */
    private String xjlsjqkYjxjlId;

    /** 项目id */
    private String xjlyjqkProjectId;

    /** 项目名称 */
    private String xmqkXmmc;


    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String xjlyjqkRq;

    /** 已经出售总价 */
    private String xjlyjqkYjcszj;

    /** 预计EPC成本 */
    private String xjlyjqkYjecpcb;

    /** 预计项目公司成本 */
    private String xjlyjqkYjxmgscb;

    /** 收款额 */
    private String xjlyjqkSk;

    /** 融资款 */
    private String xjlyjqkRzk;

    /** 工程款 */
    private String xjlyjqkGck;

    /** 电费收款 */
    private String xjlyjqkDfsk;

    /** 预计收入合计 */
    private String xjlyjqkSrHj;

    /** 设备款 */
    private String xjlyjqkSbk;

    /** 建安款 */
    private String xjlyjqkJak;

    /** 其他款项 */
    private String xjlyjqkQtkx;

    /** 预计支持合计 */
    private String xjlyjqkZcHj;


    /** 预计利润 */
    private String xjlyjqkYjlr;
}
