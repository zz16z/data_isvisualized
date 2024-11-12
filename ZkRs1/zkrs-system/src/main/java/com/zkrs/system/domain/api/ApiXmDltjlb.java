package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiZd;
import lombok.Data;

//电量统计列表
@Data
public class ApiXmDltjlb {


    //项目名称
    String xmqkXmmc;
    //发电年份
    String fdlnf;
    //发电月份
    String fdlyf;
    //预计发电量
    String dltjYjfdl;
    //实际发电量
    String dltjSjjsdl;
    //实际收入
    String dltjBysrhj;
    //实际支出
    String dltjByzchj;

    //项目id
    String  xmid;

}
