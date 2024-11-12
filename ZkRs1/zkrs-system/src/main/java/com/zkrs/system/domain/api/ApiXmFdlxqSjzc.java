package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//发电量详情-实际支出
@Data
public class ApiXmFdlxqSjzc extends ApiXmFdlxq  {



    /*
    实际支出：
     */
    //下网电费
    @ApiResultsVo(attributeName="下网电费")
    String  dltjXwdf;
    //人工工资
    @ApiResultsVo(attributeName="人工工资")
    String  dltjRggz;
//    //报销款
//    @ApiResultsVo(attributeName="报销款")
//    String  dltjBxk;
    //文件办理及其他
    @ApiResultsVo(attributeName="文件办理及其他")
    String  dltjWjbljqt;
    //利息
    @ApiResultsVo(attributeName="利息")
    String  dltjLx;
    //实际支出合计
    @ApiResultsVo(attributeName="实际支出合计")
    String  dltjByzchj;






}
