package com.zkrs.system.domain;

import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;


@Data
public class ExportConstructionBaseInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Excel(name = "项目名称")
    private String xmqkXmmc;

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

    /** 设备到货 */
    @Excel(name = "设备到货")
    private String xmqkSbdh;

    /** 设备安装*/
    @Excel(name = "设备安装")
    private String xmqkSbaz;

    /** 升压站安装 */
    @Excel(name = "升压站安装")
    private String xmqkSyzkw;

    /** 基础开挖 */
    @Excel(name = "基础开挖")
    private String xmqkJckw;

    /** 外线施工*/
    @Excel(name = "外线施工")
    private String xmqkWxsg;

    /** 施工进度*/
    @Excel(name = "施工进度")
    private String xmqkSgjd;

}
