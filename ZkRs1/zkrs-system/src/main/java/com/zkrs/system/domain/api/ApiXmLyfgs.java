package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//项目履约分公司
@Data
public class ApiXmLyfgs {
    //字段判断取值0key ,1vulue
    //履约公司
    @ApiResultsVo(attributeName="公司名称",isKye="0")
    String zdName;
    //总数
    @ApiResultsVo(attributeName="总数",isKye="1")
    String  total;


}
