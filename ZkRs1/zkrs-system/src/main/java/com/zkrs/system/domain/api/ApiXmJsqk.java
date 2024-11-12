package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;
//项目建设情况
@Data
public class ApiXmJsqk {


    //未开工
    @ApiResultsVo(attributeName="未开工")
    String notStarted;
    //'全并'
    @ApiResultsVo(attributeName="全并")
    String allunion;
    //'在建'
    @ApiResultsVo(attributeName="在建")
    String  underConstruction;
}
