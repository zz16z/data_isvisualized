package com.zkrs.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class ExportExpectedTrueFinancing extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Excel(name = "项目名称",width = 50)
    private String xmqkXmmc;

    //放款表
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际放款时间" ,width = 30, dateFormat = "yyyy-MM-dd")
    private String times;

    @Excel(name = "实际放款金额")
    private String january;

    //还款表
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际还款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String financingRq;

    @Excel(name = "实际还款本金")
    private String financingCb;

    @Excel(name = "实际还款利息")
    private String financingLx;

    @Excel(name = "实际还款手续费")
    private String financingSxf;
}
