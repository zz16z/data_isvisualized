package com.zkrs.system.domain.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;



/**
 * 项目情况对象 project_info
 * 
 * @author zkrs
 * @String 2022-06-30
 */
@Data
public class ApiProjectInfo
{
    private static final long serialVersionUID = 1L;

    /** 项目id */

    private String xmqkProjectId;


    //项目名称
    private String xmqkXmmc;

    /** 容量(KW) */
    private String xmqkRl;

    /** 项目类型 */
    private Long xmqkXmlx;

    /** 项目现状 */
    private Long xmqkXmxz;

    /** 履约分公司 */
    private Long xmqkLyfgs;

    /** 建设状态 */
    private Long xmqkJszt;

    /** 区域 */
    private Long xmqkQy;

    /** 动工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String xmqkHtsj;

    /** 并网时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String xmqkBwsj;

    /** 融资方 */
    private String xmqkRzf;

    /** 收购方 */
    private String xmqkSgf;

    /** 合作方 */
    private String xmqkHzf;

    /** 融资经理 */
    private String xmqkRzjl;

    /** 商务经理 */
    private String xmqkSwjl;

    /** 项目当前值 */
    private String xmqkXmdqz;

    /** 升压站开挖 */
    private String xmqkSyzkw;

    /** 基础开挖 */
    private String xmqkJckw;

    /** 建设情况 */
    private String xmqkJsqk;

    /** 设备到货 */
    private String xmqkSbdh;

    /** 调整预计出售单价(元/千瓦) */
    private String xmqkTzyjcsdj;

    /** 预计出售总价 */
    private String xmqkYjcszj;

    /** EPC结算价  */
    private String xmqkEpcJsj;

    /** 增加EPC结算价 */
    private String xmqkEpcZjjsj;

    /** 预计收入合计 */
    private String xmqkYjsyhj;

    /** 项目公司成本 */
    private String xmqkXmgscb;

    /** 项目EPC成本 */
    private String xmqkXmepccb;

    /** 其中资源费 */
    private String xmqkQzzyf;

    /** 总成本 */
    private String xmqkZcb;

    /** 已收预收购款 */
    private String xmqkSjYsysgk;

    /** 已收融资款 */
    private String xmqkYsrzk;

    /** 工程回款(业主回款) */
    private String xmqkGzk;

    /** 已收电费 */
    private String xmqkYsdf;

    /** 实际收入合计 */
    private String xmqkSjsrhj;

    /** 应收余额 */
    private String xmqkYsye;

    /** 已付设备款 */
    private String xmqkYfsbk;

    /** 已付建安款 */
    private String xmqkYfjak;

    /** 已付其他款项 */
    private String xmqkYfqtkx;

    /** 已付总金额 */
    private String xmqkYfzje;

    /** 应收余额 */
    private String xmqkYsye1;

    /** 实际应付未付款项 */
    private String xmqkSjyfwfkx;

    /** 合同支付余额 */
    private String xmqkHtzfye;

    /** 已付履约保证金 */
    private String xmqkYflybzj;

    /** 已退履约保证金 */
    private String xmqkYtlybzj;

    /** 备注 */
    private String xmqkRemark;




    /** 设备安装*/

    private String xmqkSbaz;


    /** 外线施工*/

    private String xmqkWxsg;


    /** 项目类型名称*/

    private String xmqkXmlxName;


    /** 项目现状名称*/

    private String xmqkXmxzName;

    /** 项目状态名称*/

    private String xmqkJsztName;


    /** 风机厂商*/

    private String xmqkFjcs;

    /** 塔筒厂商*/
    private String xmqkTtcs;

    /** 组件厂商*/

    private String xmqkZjcs;

    /** 支架厂商*/
    private String xmqkZjiacs;

    /** 施工进度*/
    private String xmqkSgjd;



}
