package com.zkrs.system.domain.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;



/**
 * 项目融资对象 project_financing
 * 
 * @author zkrs
 * @String 2022-07-01
 */
@Data
public class ApiProjectFinancing
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long xmrzId;

    /** 项目id */
    private String xmrzProjectId;

    @Excel(name = "项目名称")
    private String xmqkXmmc;


    /** 融资机构 */
    private String xmrzRzjg;

    /** 签约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String xmrzQyrq;

    /** 融资负责人 */
    private String xmrzRzfzr;

    /** 融资总额 */
    private String xmrzRzze;

    /** 已放款总额 */
    private String xmrzYfkze;

    /** 预计下笔放款 */
    private String xmrzYjxbfk;

    /** 保证金 */
    private String xmrzBzj;

    /** 融资利率 */
    private String xmrzRzll;

    /** 手续费收取方式 */
    private String xmrzSxfsqfs;

    /** 首年IRR */
    private String xmrzSnirr;

    /** 全周期综合irr */
    private String xmrzQzqzhirr;

    /** 融资情况 */
    private String xmrzRzqk;

    /** 下笔放款条件 */
    private String xmrzXbfktj;

    /** 成本 */
    private String xmrzCb;
    /** 利息 */
    private String xmrzLx;
    /** 手续费 */
    private String xmrzSxf;
    /** 还款保证金 */
    private String xmrzHkbzj;


}
