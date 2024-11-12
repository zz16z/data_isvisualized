package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//项目公司情况
@Data
public class ApiXmDltj {

    //累计发电量
    @ApiResultsVo(attributeName="累计发电量")
    String   dltjLjfdl;
    //预计发电量
    @ApiResultsVo(attributeName="预计发电量")
    String   dltjYjfdl;
    //实际发电量
    @ApiResultsVo(attributeName="实际发电量")
    String   dltjSjjsdl;
    //电费收入
    @ApiResultsVo(attributeName="电费收入")
    String   dltjBysrhj;
    //电费支出
    @ApiResultsVo(attributeName="电费支出")
    String   dltjByzchj;

}
