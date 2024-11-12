package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//项目融资统计-放款
@Data
public class ApiXmRztjFk {



    //已放款
    @ApiResultsVo(attributeName="key_v")
    String january ;
    //放款月份
    @ApiResultsVo(attributeName="value_v")
    String  fkyf;

}
