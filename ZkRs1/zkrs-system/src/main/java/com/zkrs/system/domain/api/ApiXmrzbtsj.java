package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import com.zkrs.system.section.ApiZd;
import lombok.Data;

//项目管理标题数据
@Data
public class ApiXmrzbtsj {

    //项目名称
    String  xmqkXmmc;
    //项目类型
    @ApiZd(attributeName="xmqkXmlx")
    String  xmqkXmlx;
    //动工时间
    String  xmqkHtsj;
    //并网时间
    String  xmqkBwsj;
    //合作方
    String  xmqkHzf;
    //建设状态
    @ApiZd(attributeName="xmqkJszt")
    String  xmqkJszt;
    //项目状态
    @ApiZd(attributeName="xmqkXmxz")
    String  xmqkXmxz;
    //融资方
    String  xmqkRzf;
    //履约分公司
    @ApiZd(attributeName="xmqkLyfgs")
    String  xmqkLyfgs;
    //项目当前产值
    String  xmqkXmdqz;
    //收购方
    String  xmqkSgf;
    //融资经理
    String  xmqkRzjl;
    //商务经理
    String  xmqkSwjl;
    //合同支付余额
    String  xmqkQzzyf;
    //组件厂商
    String  xmqkZjcs;
    //支架厂商
    String  xmqkZjiacs;
    //出售时间
    String  xmqkYcsrq;
    //最新数据
    String  zxshjrq;
    //最新月报
    String  zxybrq;

    //塔筒厂商
    String  xmqkTtcs;
    //风机厂商
    String xmqkFjcs;






}
