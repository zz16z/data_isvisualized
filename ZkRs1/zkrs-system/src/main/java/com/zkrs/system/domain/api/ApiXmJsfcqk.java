package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//项目建设收支持情况
@Data

public class ApiXmJsfcqk {

    //全并
    @ApiResultsVo(attributeName="全并")
     String qbBl;
    //未开工
    @ApiResultsVo(attributeName="未开工")
    String wkgBl;
    //在建
    @ApiResultsVo(attributeName="在建")
    String zjBl;








}
