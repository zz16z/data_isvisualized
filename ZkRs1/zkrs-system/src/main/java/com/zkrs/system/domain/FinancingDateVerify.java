package com.zkrs.system.domain;

import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;



/**
 * 融资时间对象 financing_date
 * 
 * @author zkrs
 * @date 2022-07-10
 */
@Data
public class FinancingDateVerify extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private Long id;

    /** 融资表关联id */
//    @Excel(name = "融资表关联id")
    private String financingId;

    @Excel(name = "序号")
    private String xh;

    @Excel(name = "项目名称")
    private String xmqkXmmc;

    /** 时间 */
    @Excel(name = "时间")
   // @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiResultsVo(attributeName = "date")
    private String times;

    /** 金额 */
    @Excel(name = "金额")
    private String january;



    /** 放款合计 */
    @Excel(name = "放款合计")
    private String totalLoans;

    /** 提款合计 */
    @Excel(name = "提款合计")
    private String totalWithdrawal;

    /** 还款合计 */
    @Excel(name = "还款合计")
    private String totalRepayment;

    private String paragraphType;

    // 判断实际和预计：0预计、1实际
    private  String isYjSj;

}
