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
 * 融资时间对象 financing_date
 * 
 * @author zkrs
 * @date 2022-07-10
 */
@Data
public class FinancingDate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private Long id;

    /** 融资表关联id */
//    @Excel(name = "融资表关联id")
    private String financingId;


    @Excel(name = "项目名称")
    private String xmqkXmmc;


    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date times;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal january;



    /** 放款合计 */
    @Excel(name = "放款合计")
    private BigDecimal totalLoans;

    /** 提款合计 */
    @Excel(name = "提款合计")
    private BigDecimal totalWithdrawal;

    /** 还款合计 */
//    @Excel(name = "还款合计")
    private BigDecimal totalRepayment;

    /** 放款0 还款1  */
//    @Excel(name = "放款0 还款1 ")
    private String paragraphType;
    // 判断实际和预计：0预计、1实际
    private  String isYjSj;

    private String quDate;

}
