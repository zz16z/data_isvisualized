package com.zkrs.system.domain.api;

import lombok.Data;

//项目电量列表标题数据
@Data
public class ApiXmElectricityTotal {

    //累计容量
    String xmqkRl;
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


}
