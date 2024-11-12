package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//项目履约分公司
@Data
public class ApiXmFgsskqk {

//    //   名称
//    String zdName;
//    //比例
//    String  bl;
    @ApiResultsVo(attributeName="blByZdName")
    String  blByZdName;

}
