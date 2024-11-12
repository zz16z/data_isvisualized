package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

//施工情况-光伏
@Data
public class ApiXmSgqkGf {

    //升压站安装(升压站开挖)
    @ApiResultsVo(attributeName="升压站安装")
    String  xmqkSyzkw;

    //设备到货率
    @ApiResultsVo(attributeName="设备到货率")
    String  xmqkSbdhl;

    //设备安装
    @ApiResultsVo(attributeName="设备安装")
    String  xmqkSbaz;
    //外线安装(外线施工)
    @ApiResultsVo(attributeName="外线安装")
    String  xmqkWxsg;

    //基础开挖
    @ApiResultsVo(attributeName="基础开挖")
    String  xmqkJckw;

    //施工进度
    @ApiResultsVo(attributeName="施工进度")
    String  xmqkSgjd;

    //展示数据
    ApiRs  rs;
    //设备到货
    String  xmqkSbdh;


}
