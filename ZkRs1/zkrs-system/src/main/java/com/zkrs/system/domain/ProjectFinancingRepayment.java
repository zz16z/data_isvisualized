package com.zkrs.system.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;

/**
 * 融资还款对象 project_financing_repayment
 *
 * @author zkrs
 * @date 2022-10-14
 */
@Data
public class ProjectFinancingRepayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;



    /** 主键ID */
    private Long id;

    /** 关联project_financing的ID */
    private Long financingId;

    /** 项目ID */
    private String projectId;

    @Excel(name = "序号")
    private String xh;

    @Excel(name = "项目名称")
    private String xmqkXmmc;

    @Excel(name = "融资机构")
    private String rzjg;

    /** 开始还本时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始还本时间",  dateFormat = "yyyy-MM-dd")
    private String beginPay;

    /** 年份 */
    @Excel(name = "年份")
    private String repaymentYear;

    /** 一月本金 */
    @Excel(name = "一月本金")
    private BigDecimal janPrincipal;

    /** 一月利息 */
    @Excel(name = "一月利息")
    private BigDecimal janInterest;

    /** 一月手续费 */
    @Excel(name = "一月手续费")
    private BigDecimal janService;

    /** 二月本金 */
    @Excel(name = "二月本金")
    private BigDecimal febPrincipal;

    /** 二月利息 */
    @Excel(name = "二月利息")
    private BigDecimal febInterest;

    /** 二月手续费 */
    @Excel(name = "二月手续费")
    private BigDecimal febService;

    /** 三月本金 */
    @Excel(name = "三月本金")
    private BigDecimal marPrincipal;

    /** 三月利息 */
    @Excel(name = "三月利息")
    private BigDecimal marInterest;

    /** 三月手续费 */
    @Excel(name = "三月手续费")
    private BigDecimal marService;

    /** 四月本金 */
    @Excel(name = "四月本金")
    private BigDecimal aprPrincipal;

    /** 四月利息 */
    @Excel(name = "四月利息")
    private BigDecimal aprInterest;

    /** 四月手续费 */
    @Excel(name = "四月手续费")
    private BigDecimal aprService;

    /** 五月本金 */
    @Excel(name = "五月本金")
    private BigDecimal mayPrincipal;

    /** 五月利息 */
    @Excel(name = "五月利息")
    private BigDecimal mayInterest;

    /** 五月手续费 */
    @Excel(name = "五月手续费")
    private BigDecimal mayService;

    /** 六月本金 */
    @Excel(name = "六月本金")
    private BigDecimal junPrincipal;

    /** 六月利息 */
    @Excel(name = "六月利息")
    private BigDecimal junInterest;

    /** 六月手续费 */
    @Excel(name = "六月手续费")
    private BigDecimal junService;

    /** 七月本金 */
    @Excel(name = "七月本金")
    private BigDecimal julPrincipal;

    /** 七月利息 */
    @Excel(name = "七月利息")
    private BigDecimal julInterest;

    /** 七月手续费 */
    @Excel(name = "七月手续费")
    private BigDecimal julService;

    /** 八月本金 */
    @Excel(name = "八月本金")
    private BigDecimal augPrincipal;

    /** 八月利息 */
    @Excel(name = "八月利息")
    private BigDecimal augInterest;

    /** 八月手续费 */
    @Excel(name = "八月手续费")
    private BigDecimal augService;

    /** 九月本金 */
    @Excel(name = "九月本金")
    private BigDecimal sepPrincipal;

    /** 九月利息 */
    @Excel(name = "九月利息")
    private BigDecimal sepInterest;

    /** 九月手续费 */
    @Excel(name = "九月手续费")
    private BigDecimal sepService;

    /** 十月本金 */
    @Excel(name = "十月本金")
    private BigDecimal octPrincipal;

    /** 十月利息 */
    @Excel(name = "十月利息")
    private BigDecimal octInterest;

    /** 十月手续费 */
    @Excel(name = "十月手续费")
    private BigDecimal octService;

    /** 十一月本金 */
    @Excel(name = "十一月本金")
    private BigDecimal novPrincipal;

    /** 十一月利息 */
    @Excel(name = "十一月利息")
    private BigDecimal novInterest;

    /** 十一月手续费 */
    @Excel(name = "十一月手续费")
    private BigDecimal novService;

    /** 十二月本金 */
    @Excel(name = "十二月本金")
    private BigDecimal decPrincipal;

    /** 十二月利息 */
    @Excel(name = "十二月利息")
    private BigDecimal decInterest;

    /** 十二月手续费 */
    @Excel(name = "十二月手续费")
    private BigDecimal decService;

;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setFinancingId(Long financingId)
    {
        this.financingId = financingId;
    }

    public Long getFinancingId()
    {
        return financingId;
    }
    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }

    public String getProjectId()
    {
        return projectId;
    }
    public void setBeginPay(String beginPay)
    {
        this.beginPay = beginPay;
    }

    public String getBeginPay()
    {
        return beginPay;
    }
    public void setRepaymentYear(String repaymentYear)
    {
        this.repaymentYear = repaymentYear;
    }

    public String getRepaymentYear()
    {
        return repaymentYear;
    }
    public void setJanPrincipal(BigDecimal janPrincipal)
    {
        this.janPrincipal = janPrincipal;
    }

    public BigDecimal getJanPrincipal()
    {
        return janPrincipal;
    }
    public void setJanInterest(BigDecimal janInterest)
    {
        this.janInterest = janInterest;
    }

    public BigDecimal getJanInterest()
    {
        return janInterest;
    }
    public void setJanService(BigDecimal janService)
    {
        this.janService = janService;
    }

    public BigDecimal getJanService()
    {
        return janService;
    }
    public void setFebPrincipal(BigDecimal febPrincipal)
    {
        this.febPrincipal = febPrincipal;
    }

    public BigDecimal getFebPrincipal()
    {
        return febPrincipal;
    }
    public void setFebInterest(BigDecimal febInterest)
    {
        this.febInterest = febInterest;
    }

    public BigDecimal getFebInterest()
    {
        return febInterest;
    }
    public void setFebService(BigDecimal febService)
    {
        this.febService = febService;
    }

    public BigDecimal getFebService()
    {
        return febService;
    }
    public void setMarPrincipal(BigDecimal marPrincipal)
    {
        this.marPrincipal = marPrincipal;
    }

    public BigDecimal getMarPrincipal()
    {
        return marPrincipal;
    }
    public void setMarInterest(BigDecimal marInterest)
    {
        this.marInterest = marInterest;
    }

    public BigDecimal getMarInterest()
    {
        return marInterest;
    }
    public void setMarService(BigDecimal marService)
    {
        this.marService = marService;
    }

    public BigDecimal getMarService()
    {
        return marService;
    }
    public void setAprPrincipal(BigDecimal aprPrincipal)
    {
        this.aprPrincipal = aprPrincipal;
    }

    public BigDecimal getAprPrincipal()
    {
        return aprPrincipal;
    }
    public void setAprInterest(BigDecimal aprInterest)
    {
        this.aprInterest = aprInterest;
    }

    public BigDecimal getAprInterest()
    {
        return aprInterest;
    }
    public void setAprService(BigDecimal aprService)
    {
        this.aprService = aprService;
    }

    public BigDecimal getAprService()
    {
        return aprService;
    }
    public void setMayPrincipal(BigDecimal mayPrincipal)
    {
        this.mayPrincipal = mayPrincipal;
    }

    public BigDecimal getMayPrincipal()
    {
        return mayPrincipal;
    }
    public void setMayInterest(BigDecimal mayInterest)
    {
        this.mayInterest = mayInterest;
    }

    public BigDecimal getMayInterest()
    {
        return mayInterest;
    }
    public void setMayService(BigDecimal mayService)
    {
        this.mayService = mayService;
    }

    public BigDecimal getMayService()
    {
        return mayService;
    }
    public void setJunPrincipal(BigDecimal junPrincipal)
    {
        this.junPrincipal = junPrincipal;
    }

    public BigDecimal getJunPrincipal()
    {
        return junPrincipal;
    }
    public void setJunInterest(BigDecimal junInterest)
    {
        this.junInterest = junInterest;
    }

    public BigDecimal getJunInterest()
    {
        return junInterest;
    }
    public void setJunService(BigDecimal junService)
    {
        this.junService = junService;
    }

    public BigDecimal getJunService()
    {
        return junService;
    }
    public void setJulPrincipal(BigDecimal julPrincipal)
    {
        this.julPrincipal = julPrincipal;
    }

    public BigDecimal getJulPrincipal()
    {
        return julPrincipal;
    }
    public void setJulInterest(BigDecimal julInterest)
    {
        this.julInterest = julInterest;
    }

    public BigDecimal getJulInterest()
    {
        return julInterest;
    }
    public void setJulService(BigDecimal julService)
    {
        this.julService = julService;
    }

    public BigDecimal getJulService()
    {
        return julService;
    }
    public void setAugPrincipal(BigDecimal augPrincipal)
    {
        this.augPrincipal = augPrincipal;
    }

    public BigDecimal getAugPrincipal()
    {
        return augPrincipal;
    }
    public void setAugInterest(BigDecimal augInterest)
    {
        this.augInterest = augInterest;
    }

    public BigDecimal getAugInterest()
    {
        return augInterest;
    }
    public void setAugService(BigDecimal augService)
    {
        this.augService = augService;
    }

    public BigDecimal getAugService()
    {
        return augService;
    }
    public void setSepPrincipal(BigDecimal sepPrincipal)
    {
        this.sepPrincipal = sepPrincipal;
    }

    public BigDecimal getSepPrincipal()
    {
        return sepPrincipal;
    }
    public void setSepInterest(BigDecimal sepInterest)
    {
        this.sepInterest = sepInterest;
    }

    public BigDecimal getSepInterest()
    {
        return sepInterest;
    }
    public void setSepService(BigDecimal sepService)
    {
        this.sepService = sepService;
    }

    public BigDecimal getSepService()
    {
        return sepService;
    }
    public void setOctPrincipal(BigDecimal octPrincipal)
    {
        this.octPrincipal = octPrincipal;
    }

    public BigDecimal getOctPrincipal()
    {
        return octPrincipal;
    }
    public void setOctInterest(BigDecimal octInterest)
    {
        this.octInterest = octInterest;
    }

    public BigDecimal getOctInterest()
    {
        return octInterest;
    }
    public void setOctService(BigDecimal octService)
    {
        this.octService = octService;
    }

    public BigDecimal getOctService()
    {
        return octService;
    }
    public void setNovPrincipal(BigDecimal novPrincipal)
    {
        this.novPrincipal = novPrincipal;
    }

    public BigDecimal getNovPrincipal()
    {
        return novPrincipal;
    }
    public void setNovInterest(BigDecimal novInterest)
    {
        this.novInterest = novInterest;
    }

    public BigDecimal getNovInterest()
    {
        return novInterest;
    }
    public void setNovService(BigDecimal novService)
    {
        this.novService = novService;
    }

    public BigDecimal getNovService()
    {
        return novService;
    }
    public void setDecPrincipal(BigDecimal decPrincipal)
    {
        this.decPrincipal = decPrincipal;
    }

    public BigDecimal getDecPrincipal()
    {
        return decPrincipal;
    }
    public void setDecInterest(BigDecimal decInterest)
    {
        this.decInterest = decInterest;
    }

    public BigDecimal getDecInterest()
    {
        return decInterest;
    }
    public void setDecService(BigDecimal decService)
    {
        this.decService = decService;
    }

    public BigDecimal getDecService()
    {
        return decService;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("financingId", getFinancingId())
                .append("projectId", getProjectId())
                .append("beginPay", getBeginPay())
                .append("repaymentYear", getRepaymentYear())
                .append("janPrincipal", getJanPrincipal())
                .append("janInterest", getJanInterest())
                .append("janService", getJanService())
                .append("febPrincipal", getFebPrincipal())
                .append("febInterest", getFebInterest())
                .append("febService", getFebService())
                .append("marPrincipal", getMarPrincipal())
                .append("marInterest", getMarInterest())
                .append("marService", getMarService())
                .append("aprPrincipal", getAprPrincipal())
                .append("aprInterest", getAprInterest())
                .append("aprService", getAprService())
                .append("mayPrincipal", getMayPrincipal())
                .append("mayInterest", getMayInterest())
                .append("mayService", getMayService())
                .append("junPrincipal", getJunPrincipal())
                .append("junInterest", getJunInterest())
                .append("junService", getJunService())
                .append("julPrincipal", getJulPrincipal())
                .append("julInterest", getJulInterest())
                .append("julService", getJulService())
                .append("augPrincipal", getAugPrincipal())
                .append("augInterest", getAugInterest())
                .append("augService", getAugService())
                .append("sepPrincipal", getSepPrincipal())
                .append("sepInterest", getSepInterest())
                .append("sepService", getSepService())
                .append("octPrincipal", getOctPrincipal())
                .append("octInterest", getOctInterest())
                .append("octService", getOctService())
                .append("novPrincipal", getNovPrincipal())
                .append("novInterest", getNovInterest())
                .append("novService", getNovService())
                .append("decPrincipal", getDecPrincipal())
                .append("decInterest", getDecInterest())
                .append("decService", getDecService())
                .toString();
    }
}
