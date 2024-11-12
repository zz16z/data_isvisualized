package com.zkrs.system.domain.api;


import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

import java.io.Serializable;

//项目分类实体
@Data
public class ApiProjectType implements Serializable {
     //项目类型 0风电、1是光伏
     @ApiResultsVo(attributeName="项目类型")
    String projectType ;
     //项目名称
     @ApiResultsVo(attributeName="项目名称")
    String projectName ;
    /** 容量(KW) */
    @ApiResultsVo(attributeName="容量(KW)")
     String xmqkRlTtal;
    /** 项目数据 */
    @ApiResultsVo(attributeName="项目数据")
    String  projectCount;




}
