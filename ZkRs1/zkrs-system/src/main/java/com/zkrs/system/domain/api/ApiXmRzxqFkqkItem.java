package com.zkrs.system.domain.api;

import lombok.Data;

import java.io.Serializable;

//融资详情-放款情况-子项数据
@Data
public class ApiXmRzxqFkqkItem implements Serializable {

    //金额
    String totalLoans;
    //日期
    String rq;

}
