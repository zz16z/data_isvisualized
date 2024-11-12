package com.zkrs.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;

/**
 * 融资放款对象 project_financing_loan
 *
 * @author zkrs
 * @date 2022-10-13
 */
public class ProjectFinancingLoan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 关联project_financing的ID */
    @Excel(name = "关联project_financing的ID")
    private Long financingId;

    /** 项目ID */
    @Excel(name = "项目ID")
    private String projectId;

    /** 年份 */
    @Excel(name = "年份")
    private String loanYear;

    /** 一月 */
    @Excel(name = "一月")
    private BigDecimal jan;

    /** 二月 */
    @Excel(name = "二月")
    private BigDecimal feb;

    /** 三月 */
    @Excel(name = "三月")
    private BigDecimal mar;

    /** 四月 */
    @Excel(name = "四月")
    private BigDecimal apr;

    /** 五月 */
    @Excel(name = "五月")
    private BigDecimal may;

    /** 六月 */
    @Excel(name = "六月")
    private BigDecimal jun;

    /** 七月 */
    @Excel(name = "七月")
    private BigDecimal jul;

    /** 八月 */
    @Excel(name = "八月")
    private BigDecimal aug;

    /** 九月 */
    @Excel(name = "九月")
    private BigDecimal sep;

    /** 十月 */
    @Excel(name = "十月")
    private BigDecimal oct;

    /** 十一月 */
    @Excel(name = "十一月")
    private BigDecimal nov;

    /** 十二月 */
    @Excel(name = "十二月")
    private BigDecimal dec;

    /** 累计提款 */
    @Excel(name = "累计提款")
    private BigDecimal all;

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
    public void setLoanYear(String loanYear)
    {
        this.loanYear = loanYear;
    }

    public String getLoanYear()
    {
        return loanYear;
    }
    public void setJan(BigDecimal jan)
    {
        this.jan = jan;
    }

    public BigDecimal getJan()
    {
        return jan;
    }
    public void setFeb(BigDecimal feb)
    {
        this.feb = feb;
    }

    public BigDecimal getFeb()
    {
        return feb;
    }
    public void setMar(BigDecimal mar)
    {
        this.mar = mar;
    }

    public BigDecimal getMar()
    {
        return mar;
    }
    public void setApr(BigDecimal apr)
    {
        this.apr = apr;
    }

    public BigDecimal getApr()
    {
        return apr;
    }
    public void setMay(BigDecimal may)
    {
        this.may = may;
    }

    public BigDecimal getMay()
    {
        return may;
    }
    public void setJun(BigDecimal jun)
    {
        this.jun = jun;
    }

    public BigDecimal getJun()
    {
        return jun;
    }
    public void setJul(BigDecimal jul)
    {
        this.jul = jul;
    }

    public BigDecimal getJul()
    {
        return jul;
    }
    public void setAug(BigDecimal aug)
    {
        this.aug = aug;
    }

    public BigDecimal getAug()
    {
        return aug;
    }
    public void setSep(BigDecimal sep)
    {
        this.sep = sep;
    }

    public BigDecimal getSep()
    {
        return sep;
    }
    public void setOct(BigDecimal oct)
    {
        this.oct = oct;
    }

    public BigDecimal getOct()
    {
        return oct;
    }
    public void setNov(BigDecimal nov)
    {
        this.nov = nov;
    }

    public BigDecimal getNov()
    {
        return nov;
    }
    public void setDec(BigDecimal dec)
    {
        this.dec = dec;
    }

    public BigDecimal getDec()
    {
        return dec;
    }
    public void setAll(BigDecimal all)
    {
        this.all = all;
    }

    public BigDecimal getAll()
    {
        return all;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("financingId", getFinancingId())
                .append("projectId", getProjectId())
                .append("loanYear", getLoanYear())
                .append("jan", getJan())
                .append("feb", getFeb())
                .append("mar", getMar())
                .append("apr", getApr())
                .append("may", getMay())
                .append("jun", getJun())
                .append("jul", getJul())
                .append("aug", getAug())
                .append("sep", getSep())
                .append("oct", getOct())
                .append("nov", getNov())
                .append("dec", getDec())
                .append("all", getAll())
                .toString();
    }
}
