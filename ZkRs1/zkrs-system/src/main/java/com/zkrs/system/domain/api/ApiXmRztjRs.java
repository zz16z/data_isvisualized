package com.zkrs.system.domain.api;

import lombok.Data;

//项目融资统计
@Data
public class ApiXmRztjRs {

    //标题数据
    ApiXmRztjTielData    tileValue;
    ///放款 ApiXmRztjFk
    ApiRs  fkDate;
    //还款 ApiXmRztjHk
    ApiRs   hkDate;

}
