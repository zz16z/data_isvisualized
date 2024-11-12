package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//项目状态
@Data
public class ApiXmXzTtal {

    //在建
    @ApiResultsVo(attributeName="在建")
    String  projectZj;
    //未开工
    @ApiResultsVo(attributeName="未开工")
    String  projectWkg;
    //待出售
    @ApiResultsVo(attributeName="待出售")
    String   projectDcs;
    //已出售待股转
    @ApiResultsVo(attributeName="已出售待股转")
    String projectYcsdgz;
    //已股转收尾款
    @ApiResultsVo(attributeName="已股转收尾款")
    String projectYgzswk;
    //分公司清欠
    @ApiResultsVo(attributeName="分公司清欠")
    String projectFgsqq;
    //业主自持
    @ApiResultsVo(attributeName="业主自持")
    String projectYzzc;


    String countjs;
    String zdName;
    //数据标记：0最新、1老的、2数据字典总的字典
    String fagvalue;
    //新老时间标记
    String newOldTime;


}
