package com.zkrs.system.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;


public class ProjectFinancingDto  extends BaseEntity {


    private static final long serialVersionUID = 1L;


    private Long xmrzId;

    /** 项目id */



    private String xmrzProjectId;

    /** 融资机构 */
    @ExcelProperty("融资机构")
    private String xmrzRzjg;

    /** 签约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty("签约日期")
    private Date xmrzQyrq;

    /** 融资负责人 */
    @ExcelProperty("融资负责人")
    private String xmrzRzfzr;

    /** 融资总额 */
    @ExcelProperty("融资总额")
    private BigDecimal xmrzRzze;

    /** 已放款总额 */

    private BigDecimal xmrzYfkze;

    /** 预计下笔放款 */

    private BigDecimal xmrzYjxbfk;

    /** 保证金 */

    private BigDecimal xmrzBzj;

    /** 融资利率 */

    private String xmrzRzll;

    /** 手续费收取方式 */

    private String xmrzSxfsqfs;

    /** 首年IRR */
    @ExcelProperty("首年IRR")
    private String xmrzSnirr;

    /** 全周期综合irr */
    @ExcelProperty("全周期综合IRR")
    private String xmrzQzqzhirr;

    /** 融资情况 */
    @Excel(name = "融资情况")

    private String xmrzRzqk;

    /** varchar */


    private String xmrzXbfktj;

    /** 是否删除：未删除 0、  删除 1 */

    private String isDelete;


    public void setXmrzId(Long xmrzId)
    {
        this.xmrzId = xmrzId;
    }

    public Long getXmrzId()
    {
        return xmrzId;
    }
    public void setXmrzProjectId(String xmrzProjectId)
    {
        this.xmrzProjectId = xmrzProjectId;
    }

    public String getXmrzProjectId()
    {
        return xmrzProjectId;
    }
    public void setXmrzRzjg(String xmrzRzjg)
    {
        this.xmrzRzjg = xmrzRzjg;
    }

    public String getXmrzRzjg()
    {
        return xmrzRzjg;
    }
    public void setXmrzQyrq(Date xmrzQyrq)
    {
        this.xmrzQyrq = xmrzQyrq;
    }

    public Date getXmrzQyrq()
    {
        return xmrzQyrq;
    }
    public void setXmrzRzfzr(String xmrzRzfzr)
    {
        this.xmrzRzfzr = xmrzRzfzr;
    }

    public String getXmrzRzfzr()
    {
        return xmrzRzfzr;
    }
    public void setXmrzRzze(BigDecimal xmrzRzze)
    {
        this.xmrzRzze = xmrzRzze;
    }

    public BigDecimal getXmrzRzze()
    {
        return xmrzRzze;
    }
    public void setXmrzYfkze(BigDecimal xmrzYfkze)
    {
        this.xmrzYfkze = xmrzYfkze;
    }

    public BigDecimal getXmrzYfkze()
    {
        return xmrzYfkze;
    }
    public void setXmrzYjxbfk(BigDecimal xmrzYjxbfk)
    {
        this.xmrzYjxbfk = xmrzYjxbfk;
    }

    public BigDecimal getXmrzYjxbfk()
    {
        return xmrzYjxbfk;
    }
    public void setXmrzBzj(BigDecimal xmrzBzj)
    {
        this.xmrzBzj = xmrzBzj;
    }

    public BigDecimal getXmrzBzj()
    {
        return xmrzBzj;
    }
    public void setXmrzRzll(String xmrzRzll)
    {
        this.xmrzRzll = xmrzRzll;
    }

    public String getXmrzRzll()
    {
        return xmrzRzll;
    }
    public void setXmrzSxfsqfs(String xmrzSxfsqfs)
    {
        this.xmrzSxfsqfs = xmrzSxfsqfs;
    }

    public String getXmrzSxfsqfs()
    {
        return xmrzSxfsqfs;
    }
    public void setXmrzSnirr(String xmrzSnirr)
    {
        this.xmrzSnirr = xmrzSnirr;
    }

    public String getXmrzSnirr()
    {
        return xmrzSnirr;
    }
    public void setXmrzQzqzhirr(String xmrzQzqzhirr)
    {
        this.xmrzQzqzhirr = xmrzQzqzhirr;
    }

    public String getXmrzQzqzhirr()
    {
        return xmrzQzqzhirr;
    }
    public void setXmrzRzqk(String xmrzRzqk)
    {
        this.xmrzRzqk = xmrzRzqk;
    }

    public String getXmrzRzqk()
    {
        return xmrzRzqk;
    }
    public void setXmrzXbfktj(String xmrzXbfktj)
    {
        this.xmrzXbfktj = xmrzXbfktj;
    }

    public String getXmrzXbfktj()
    {
        return xmrzXbfktj;
    }
    public void setIsDelete(String isDelete)
    {
        this.isDelete = isDelete;
    }

    public String getIsDelete()
    {
        return isDelete;
    }

}
