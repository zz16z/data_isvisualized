package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//发电量详情-实际收入
@Data
public class ApiXmFdlxqSjsr  {



    /*
    实际收入
     */
    //电费收益
    @ApiResultsVo(attributeName="电费收益")
    String  dltjDfsy;
    //补贴
    @ApiResultsVo(attributeName="补贴")
    String  dltjBt;
    //其他
    @ApiResultsVo(attributeName="其他")
    String  dltjQt;
    //实际收入合计
    @ApiResultsVo(attributeName="实际收入合计")
    String  dltjBysrhj;







}
