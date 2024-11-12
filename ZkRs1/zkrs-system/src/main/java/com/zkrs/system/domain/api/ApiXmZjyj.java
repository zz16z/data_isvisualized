package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//资金预警
@Data
public class ApiXmZjyj {

    //项目名称
    @ApiResultsVo(attributeName="项目名称")
    String  xmqkXmmc;
    //支出
    @ApiResultsVo(attributeName="支出")
    String  xjlsjqkZcHj;
    //收入
    @ApiResultsVo(attributeName="收入")
    String  xjlsjqkSrHj;


}
