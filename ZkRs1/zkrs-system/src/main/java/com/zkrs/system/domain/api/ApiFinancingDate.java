package com.zkrs.system.domain.api;

import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 融资时间对象 financing_date
 * 
 * @author zkrs
 * @date 2022-07-10
 */
@Data
public class ApiFinancingDate
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private Long id;

    /** 融资表关联id */
    private String financingId;

    /** 项目名称 */
    private String xmqkXmmc;

    /** 时间年份 */
    private String times;

    /** 一月金额 */
    private String january;

    /** 二月金额 */
    private String february;

    /**  三月金额 */
    private String march;

    /** 四月金额 */
    private String april;

    /**  五月金额 */
    private String may;

    /** 六月金额 */
    private String june;

    /** 七月金额 */
    private String july;

    /** 八月金额 */
    private String august;

    /** 九月金额 */
    private String september;

    /** 十月金额 */
    private String october;

    /** 十一月金额 */
    private String november;

    /** 十二月金额 */
    private String december;

    /** 一月几号 */
    private String januaryDay;

    /** 二月几号 */
    private String februaryDay;

    /** 三月几号 */
    private String marchDay;

    /** 四月几号 */
    private String aprilDay;

    /** 五月几号 */
    private String mayDay;

    /** 六月几号 */
    private String juneDay;

    /** 七月几号 */
    private String julyDay;

    /** 八月几号 */
    private String augustDay;

    /** 九月几号 */
    private String septemberDay;

    /** 十月几号 */
    private String octoberDay;

    /** 十一月几号 */
    private String novemberDay;

    /** 十二月几号 */
    private String decemberDay;

    /** 放款合计 */
    private String totalLoans;

    /** 提款合计 */
    private String totalWithdrawal;

    /** 还款合计 */
    private String totalRepayment;

    /** 放款0 还款1  */
    private String paragraphType;
}
