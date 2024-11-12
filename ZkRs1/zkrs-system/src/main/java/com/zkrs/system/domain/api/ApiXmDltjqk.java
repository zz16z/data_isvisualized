package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//电量统计情况
@Data
public class ApiXmDltjqk {

    //累计发电量
    @ApiResultsVo(attributeName="累计发电量")
    String  dltjLjfdl;
    //实际发电量
    @ApiResultsVo(attributeName="实际发电量")
    String  dltjSjjsdl;
    //预计发电量
    @ApiResultsVo(attributeName="预计发电量")
    String  dltjYjfdl;



    //电费总收入
    @ApiResultsVo(attributeName="电费总收入")
    String  dltjDfsy;
    //预计电费收入
    @ApiResultsVo(attributeName="预计电费收入")
    String  dltjYjdfsr;
    //实际电费收入
    @ApiResultsVo(attributeName="实际电费收入")
    String  dltjSjdfsr;




}
