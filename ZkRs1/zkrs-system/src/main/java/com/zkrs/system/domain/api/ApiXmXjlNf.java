package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

import java.io.Serializable;

//现金流-年份
@Data
public class ApiXmXjlNf  implements Serializable {



    //预计总收入
    @ApiResultsVo(attributeName="预计总收入")
    String xjlyjqkSrHj;

    //实际总收入
    @ApiResultsVo(attributeName="实际总收入")
    String xjlsjqkSrHj;

    //预计总支出
    @ApiResultsVo(attributeName="预计总支出")
    String xjlyjqkZcHj;


    //实际总支出
    @ApiResultsVo(attributeName="实际总支出")
    String xjlsjqkZcHj;

    //年份，数据库里的keyV
    String keyV;

}
