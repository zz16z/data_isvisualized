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
 * 项目情况对象 project_info
 * 
 * @author zkrs
 * @date 2022-06-30
 */
@Data
public class ProjectInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目id */
//    @Excel(name = "项目id")
    private String xmqkProjectId;



    /** $column.columnComment */
    @Excel(name = "项目名称")
    //@Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String xmqkXmmc;

    /** 项目类型 */
   // @Excel(name = "项目类型")
    private Long xmqkXmlx;

    /** 项目类型名称*/
     @Excel(name = "项目类型")
    private String xmqkXmlxName;

    /** 容量(KW) */
    @Excel(name = "容量(KW)")
    private BigDecimal xmqkRl;

    /** 项目现状 */
  //  @Excel(name = "项目现状")
    private Long xmqkXmxz;

    /** 项目现状名称*/
    @Excel(name = "项目现状")
    private String xmqkXmxzName;

    /** 建设状态 */
   // @Excel(name = "建设状态")
    private Long xmqkJszt;

    /** 建设状态*/
    @Excel(name = "建设状态")
    private String xmqkJsztName;

    /** 履约分公司 */
   // @Excel(name = "履约分公司")
    private Long xmqkLyfgs;

    /** 履约分公司*/
    @Excel(name = "履约分公司")
    private String xmqkLyfgsName;

    /** 区域 */
      @Excel(name = "区域")
    private String xmqkQy;


//    @Excel(name = "区域")
    private String xmqkQyName;

    /** 动工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "动工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date xmqkHtsj;

    /** 并网时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "并网时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date xmqkBwsj;

    /** 融资方 */
    @Excel(name = "融资方")
    private String xmqkRzf;

    /** 收购方 */
    @Excel(name = "收购方")
    private String xmqkSgf;

    /** 合作方 */
    @Excel(name = "业主方")
    private String xmqkHzf;

    /** 融资经理 */
    @Excel(name = "融资经理")
    private String xmqkRzjl;

    @Excel(name = "手续费收取方式")
    private String xmrzSxfsqfs;

    /** 商务经理 */
    @Excel(name = "商务经理")
    private String xmqkSwjl;

    /**
     * 应出售日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "应出售日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date xmqkYcsrq;


    /**
     * 预警原因
     */
    @Excel(name = "预警原因")
    private String xmqkYjyy;

    /** 项目当前产值 */
    //@Excel(name = "项目当前产值")
    private String xmqkXmdqz;

    /** 升压站开挖 */
    //@Excel(name = "升压站开挖")
    private String xmqkSyzkw;

    /** 基础开挖 */
   // @Excel(name = "基础开挖")
    private String xmqkJckw;

    /** 建设情况 */
    //@Excel(name = "建设情况")
    private String xmqkJsqk;

    /** 设备到货 */
   // @Excel(name = "设备到货")
    private String xmqkSbdh;

    /** 调整预计出售单价(元/千瓦) */
    ///@Excel(name = "调整预计出售单价(元/千瓦)") 弃用
    private BigDecimal xmqkTzyjcsdj;

    /** 预计出售总价 */
    //@Excel(name = "预计出售总价") 弃用
    private BigDecimal xmqkYjcszj;

    /** EPC结算价  */
   // @Excel(name = "EPC结算价")弃用
    private BigDecimal xmqkEpcJsj;

    /** 增加EPC结算价 */
   // @Excel(name = "增加EPC结算价")   弃用
    private BigDecimal xmqkEpcZjjsj;

    /** 预计收入合计 */
    //@Excel(name = "预计收入合计")  弃用
    private BigDecimal xmqkYjsyhj;

    /** 项目公司成本 */
   // @Excel(name = "项目公司成本") 弃用
    private BigDecimal xmqkXmgscb;

    /** 项目EPC成本 */
   // @Excel(name = "项目EPC成本")  弃用
    private BigDecimal xmqkXmepccb;

    /** 其中资源费改为合同支付余额 */
  //  @Excel(name = "合同支付余额")
    private BigDecimal xmqkQzzyf;

    /** 总成本 */
   // @Excel(name = "总成本") 弃用
    private BigDecimal xmqkZcb;

    /** 已收预收购款 */
  //  @Excel(name = "已收预收购款") 弃用
    private BigDecimal xmqkSjYsysgk;

    /** 已收融资款 */
  //  @Excel(name = "已收融资款") 弃用
    private BigDecimal xmqkYsrzk;

    /** 工程回款(业主回款) */
   // @Excel(name = "工程回款(业主回款)") 弃用
    private BigDecimal xmqkGzk;

    /** 已收电费 */
  //  @Excel(name = "已收电费") 弃用
    private BigDecimal xmqkYsdf;

    /** 实际收入合计 */
   // @Excel(name = "实际收入合计") 弃用
    private BigDecimal xmqkSjsrhj;

    /** 预计应收余额 */
  //  @Excel(name = "预计应收余额") 弃用
    private BigDecimal xmqkYsye;

    /** 已付设备款 */
 //  @Excel(name = "已付设备款") 弃用
    private BigDecimal xmqkYfsbk;

    /** 已付建安款 */
  //  @Excel(name = "已付建安款")  弃用
    private BigDecimal xmqkYfjak;

    /** 已付其他款项 */
   // @Excel(name = "已付其他款项") 弃用
    private BigDecimal xmqkYfqtkx;

    /** 已付总金额 */
   // @Excel(name = "已付总金额") 弃用
    private BigDecimal xmqkYfzje;

    /** 实际应收余额 */
    //@Excel(name = "实际应收余额") 弃用
    private BigDecimal xmqkYssjye;

    /** 实际应付未付款项 */
   // @Excel(name = "实际应付未付款项")  弃用
    private BigDecimal xmqkSjyfwfkx;

    /** 合同支付余额 */
  //  @Excel(name = "合同支付余额")  弃用
    private BigDecimal xmqkHtzfye;

    /** 已付履约保证金 */
   // @Excel(name = "已付履约保证金")
    private BigDecimal xmqkYflybzj;

    /** 已退履约保证金 */
   // @Excel(name = "已退履约保证金")
    private BigDecimal xmqkYtlybzj;

    /** 备注 */
   // @Excel(name = "备注")
    private String xmqkRemark;

//    /** 是否删除：未删除 0、  删除 1 */
//    @Excel(name = "是否删除：未删除 0、  删除 1")
    private String isDelete;


    /** 设备安装*/
  //  @Excel(name = "设备安装")
    private String xmqkSbaz;


    /** 外线施工*/
   // @Excel(name = "外线施工")
    private String xmqkWxsg;








    /** 风机厂商*/
   // @Excel(name = "风机厂商")
    private String xmqkFjcs;

    /** 塔筒厂商*/
  //  @Excel(name = "塔筒厂商")
    private String xmqkTtcs;

    /** 组件厂商*/
  //  @Excel(name = "组件厂商")
    private String xmqkZjcs;

    /** 支架厂商*/
  //  @Excel(name = "支架厂商")
    private String xmqkZjiacs;

    /** 施工进度*/
  //  @Excel(name = "施工进度")
    private String xmqkSgjd;

    /**
     * 设备到货率
     */
 //   @Excel(name = "设备到货率")
    private BigDecimal xmqkSbdhl;
}
