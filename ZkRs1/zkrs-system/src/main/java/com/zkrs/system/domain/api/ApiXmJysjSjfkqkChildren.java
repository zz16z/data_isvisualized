package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//项目经营数据-实际付款情况
@Data
public class ApiXmJysjSjfkqkChildren   {


    //已付设备款
    @ApiResultsVo(attributeName="已付设备款")
    String xjlsjqkSbk;
    //已付建安款
    @ApiResultsVo(attributeName="已付建安款")
    String xjlsjqkJak;
    //已付其他款项
    @ApiResultsVo(attributeName="已付其他款项")
    String xjlsjqkQt;



}
