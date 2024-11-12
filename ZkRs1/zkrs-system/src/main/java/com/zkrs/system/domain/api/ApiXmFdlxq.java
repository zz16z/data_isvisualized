package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiObjFormat;
import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//发电量详情
@Data
public class ApiXmFdlxq {


    //发电量情况
    @ApiResultsVo(attributeName="发电量情况")
    String  dltjQdlqk;
    //预计发电量
    @ApiObjFormat(frmatName="1")
    @ApiResultsVo(attributeName="预计发电量")
    Integer  dltjYjfdl;
    //实际结算电量
    @ApiObjFormat(frmatName="1")
    @ApiResultsVo(attributeName="实际结算电量")
    String  dltjSjjsdl;
    //累计发电量dltjLjfdl修改为（dltjSjfdl）
    @ApiObjFormat(frmatName="1")
    @ApiResultsVo(attributeName="累计发电量")
    Integer  dltjLjfdl;
    //上月结算电费应收金额
    @ApiResultsVo(attributeName="上月结算电费应收金额")
    String  dltjSyjsdfysje;
    //电费结算累计应收金额
    @ApiResultsVo(attributeName="电费结算累计应收金额")
    String  dltjDljsljysje;
    //电费结算累计实际到账
    @ApiResultsVo(attributeName="电费结算累计实际到账")
    String  dltjDfjsljsjdz;
    //累计未到账电费金额
    @ApiResultsVo(attributeName="累计未到账电费金额")
    String  dltjLjwdzdf;


    /*
    实际收入
     */
    //电费收益
    @ApiResultsVo(attributeName="电费收益")
    String  dltjDfsy;
    //补贴
    @ApiResultsVo(attributeName="补贴")
    String  dltjBt;
    //其他
    @ApiResultsVo(attributeName="其他")
    String  dltjQt;
    //实际收入合计
    @ApiObjFormat(frmatName="1")
    @ApiResultsVo(attributeName="实际收入合计")
    String  dltjBysrhj;


    /*
    实际支出：
     */
    //下网电费
    @ApiResultsVo(attributeName="下网电费")
    String  dltjXwdf;
    //人工工资
    @ApiResultsVo(attributeName="人工工资")
    String  dltjRggz;
    //报销款
//    @ApiResultsVo(attributeName="报销款")
//    String  dltjBxk;
    //文件办理及其他
    @ApiResultsVo(attributeName="文件办理及其他")
    String  dltjWjbljqt;
    //利息
    @ApiResultsVo(attributeName="利息")
    String  dltjLx;
    //实际支出合计
    @ApiObjFormat(frmatName="1")
    @ApiResultsVo(attributeName="实际支出合计")
    String  dltjByzchj;

    /////////电量详情字段
    @ApiResultsVo(attributeName="累积上网电量")
    String  dltjLjswdl;

    @ApiResultsVo(attributeName="本月上网电量")
    String  dltjSjfdl;

    @ApiResultsVo(attributeName="累积结算电量")
    String  dltjLjjsdl;

    @ApiResultsVo(attributeName="上网电量偏差")
    String  dltjPc;

    @ApiResultsVo(attributeName="结算累积应收")
    Integer  dltjLjfdlData;

    @ApiResultsVo(attributeName="本月结算电量")
    String  dltjSjjsdlData;


    @ApiResultsVo(attributeName="结算累积到账")
    String  dltjDfjsljsjdzData;

    @ApiResultsVo(attributeName="本月结算电费")
    String  dltjSjdfsr;


    @ApiResultsVo(attributeName="结算累积未到账")
    String  dltjLjwdzdfData;



}
