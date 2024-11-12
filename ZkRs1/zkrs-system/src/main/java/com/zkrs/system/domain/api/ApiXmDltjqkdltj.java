package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//电量统计
@Data
public class ApiXmDltjqkdltj {

    //累计发电量
    String  dltjLjfdl;
    //实际发电量
    @ApiResultsVo(attributeName="实际发电量")
    String  dltjSjjsdl;
    //预计发电量
    @ApiResultsVo(attributeName="预计发电量")
    String  dltjYjfdl;
    //展示数据
    ApiRs data;






}
