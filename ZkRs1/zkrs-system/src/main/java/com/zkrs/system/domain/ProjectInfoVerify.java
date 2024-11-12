package com.zkrs.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;


@Data
public class ProjectInfoVerify extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 项目id */
//    @Excel(name = "项目id")
    private String xmqkProjectId;


    @Excel(name = "序号")
    private String xh;

    /** $column.columnComment */
    @Excel(name = "项目名称")
    //@Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String xmqkXmmc;

    /** 容量(KW) */
    @Excel(name = "容量(KW)")
    private String xmqkRl;

    /** 项目类型 */
//    @Excel(name = "项目类型")
    private Long xmqkXmlx;

    /** 项目现状 */
//    @Excel(name = "项目现状")
    private Long xmqkXmxz;

    /** 履约分公司 */
    @Excel(name = "履约分公司")
    private Long xmqkLyfgs;

    /** 建设状态 */
//    @Excel(name = "建设状态")
    private Long xmqkJszt;

    /** 区域 */
    @Excel(name = "区域")
    private String xmqkQy;

    /** 动工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "动工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String xmqkHtsj;

    /** 并网时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "并网时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String xmqkBwsj;

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

    /** 商务经理 */
    @Excel(name = "商务经理")
    private String xmqkSwjl;

    /** 项目当前产值 */
    @Excel(name = "项目当前产值")
    private String xmqkXmdqz;

    /** 升压站安装*/
    @Excel(name = "升压站安装%")
    private String xmqkSyzkw;

    /** 基础开挖% */
    @Excel(name = "基础开挖%")
    private String xmqkJckw;

    /** 建设情况 */
    @Excel(name = "建设情况")
    private String xmqkJsqk;

    /** 设备到货 */
    @Excel(name = "设备到货%")
    private String xmqkSbdh;

    /** 调整预计出售单价(元/千瓦) */
    @Excel(name = "调整预计出售单价(元/千瓦)")
    private String xmqkTzyjcsdj;

    /** 预计出售总价 */
    @Excel(name = "预计出售总价")
    private String xmqkYjcszj;

    /** EPC结算价  */
    @Excel(name = "EPC结算价")
    private String xmqkEpcJsj;

    /** 增加EPC结算价 */
    @Excel(name = "增加EPC结算价")
    private String xmqkEpcZjjsj;

    /** 预计收入合计 */
    @Excel(name = "预计收入合计")
    private String xmqkYjsyhj;

    /** 项目公司成本 */
    @Excel(name = "项目公司成本")
    private String xmqkXmgscb;

    /** 项目EPC成本 */
    @Excel(name = "项目EPC成本")
    private String xmqkXmepccb;

    /** 其中资源费改为合同支付余额 */
    @Excel(name = "合同支付余额")
    private String xmqkQzzyf;

    /** 总成本 */
    @Excel(name = "总成本")
    private String xmqkZcb;

    /** 已收预收购款 */
    @Excel(name = "已收预收购款")
    private String xmqkSjYsysgk;

    /** 已收融资款 */
    @Excel(name = "已收融资款")
    private String xmqkYsrzk;

    /** 工程回款(业主回款) */
    @Excel(name = "工程回款(业主回款)")
    private String xmqkGzk;

    /** 已收电费 */
    @Excel(name = "已收电费")
    private String xmqkYsdf;

    /** 实际收入合计 */
    @Excel(name = "实际收入合计")
    private String xmqkSjsrhj;

    /** 预计应收余额 */
    @Excel(name = "预计应收余额")
    private String xmqkYsye;

    /** 已付设备款 */
    @Excel(name = "已付设备款")
    private String xmqkYfsbk;

    /** 已付建安款 */
    @Excel(name = "已付建安款")
    private String xmqkYfjak;

    /** 已付其他款项 */
    @Excel(name = "已付其他款项")
    private String xmqkYfqtkx;

    /** 已付总金额 */
    @Excel(name = "已付总金额")
    private String xmqkYfzje;

    /** 实际应收余额 */
    @Excel(name = "实际应收余额")
    private String xmqkYssjye;

    /** 实际应付未付款项 */
    @Excel(name = "实际应付未付款项")
    private String xmqkSjyfwfkx;

    /** 合同支付余额 */
//    @Excel(name = "合同支付余额")
    private String xmqkHtzfye;

    /** 已付履约保证金 */
    @Excel(name = "已付履约保证金")
    private String xmqkYflybzj;

    /** 已退履约保证金 */
    @Excel(name = "已退履约保证金")
    private String xmqkYtlybzj;

    /** 备注 */
    @Excel(name = "备注")
    private String xmqkRemark;

    //    /** 是否删除：未删除 0、  删除 1 */
//    @Excel(name = "是否删除：未删除 0、  删除 1")
    private String isDelete;


    /** 设备安装*/
    @Excel(name = "设备安装%")
    private String xmqkSbaz;


    /** 外线施工%*/
    @Excel(name = "外线施工%")
    private String xmqkWxsg;


    /** 项目类型名称*/
    @Excel(name = "项目类型")
    private String xmqkXmlxName;


    /** 项目现状名称*/
    @Excel(name = "项目现状")
    private String xmqkXmxzName;

    /** 建设状态*/
    @Excel(name = "建设状态")
    private String xmqkJsztName;

    /** 履约分公司*/
    @Excel(name = "履约分公司")
    private String xmqkLyfgsName;


    /** 风机厂商*/
    @Excel(name = "风机厂商")
    private String xmqkFjcs;

    /** 塔筒厂商*/
    @Excel(name = "塔筒厂商")
    private String xmqkTtcs;

    /** 组件厂商*/
    @Excel(name = "组件厂商")
    private String xmqkZjcs;

    /** 支架厂商*/
    @Excel(name = "支架厂商")
    private String xmqkZjiacs;
    /** 施工进度*/
    @Excel(name = "施工进度%")
    private String xmqkSgjd;

    /**
     * 应出售日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "应出售日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String xmqkYcsrq;



    /**
     * 预警原因
     */
    @Excel(name = "预警原因")
    private String xmqkYjyy;

    /**
     * 设备到货率
     */
    @Excel(name = "设备到货率")
    private String xmqkSbdhl;


}
