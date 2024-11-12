package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiZd;
import lombok.Data;

//项目电量列表标题数据
@Data
public class ApiXmElectricityList {

    //项目名称
    String xmqkXmmc;

    //容量
    String xmqkRl;

    //项目分类
    @ApiZd(attributeName="xmqkXmlx")
    String xmqkXmlx;
    //履约分公司
    @ApiZd(attributeName="xmqkLyfgs")
    String  xmqkLyfgs;
    //区域
    String  xmqkQy;
    //累计发电量
    String dltjLjjsdl;
    //预计发电量
    String dltjYjfdl;
    //实际发电量
    String dltjSjfdl;
    //电费收入
    String dltjBysrhj ;
    //电费支出
    String dltjByzchj;
    //项目id
    String xmId;

}
