package com.zkrs.system.domain.api;

import com.zkrs.system.section.ApiResultsVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

//融资详情-还款款情况
@Data
public class ApiXmRzxqHkqk implements Serializable {





    //展示数据
    ApiXmRzxqHkqkItem  item;
    //图表数据
    ApiRs   tbData;

}
