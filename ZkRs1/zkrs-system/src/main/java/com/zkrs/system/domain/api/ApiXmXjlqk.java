package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//现金流情况
@Data

public class ApiXmXjlqk {
    public  ApiXmXjlqk(){}
    //字段判断取值0key ,1vulue
    @ApiResultsVo(attributeName="1月")
    String month1="0";
    @ApiResultsVo(attributeName="2月")
    String month2="0";
    @ApiResultsVo(attributeName="3月")
    String  month3="0";
    @ApiResultsVo(attributeName="4月")
    String  month4="0";
    @ApiResultsVo(attributeName="5月")
    String  month5="0";
    @ApiResultsVo(attributeName="6月")
    String  month6="0";
    @ApiResultsVo(attributeName="7月")
    String  month7="0";
    @ApiResultsVo(attributeName="8月")
    String  month8="0";
    @ApiResultsVo(attributeName="9月")
    String  month9="0";
    @ApiResultsVo(attributeName="10月")
    String  month10="0";
    @ApiResultsVo(attributeName="11月")
    String  month11="0";
    @ApiResultsVo(attributeName="12月")
    String  month12="0";







}
