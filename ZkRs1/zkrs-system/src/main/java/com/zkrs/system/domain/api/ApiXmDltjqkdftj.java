package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//电费统计
@Data
public class ApiXmDltjqkdftj {

    //电费总收入
    String  dltjDfsy;
    //预计电费收入
    @ApiResultsVo(attributeName="预计电费收入")
    String  dltjYjdfsr;
    //实际电费收入
    @ApiResultsVo(attributeName="实际电费收入")
    String  dltjSjdfsr;
    //展示数据
    ApiRs data;




}
