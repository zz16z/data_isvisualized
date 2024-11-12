package com.zkrs.system.domain.api;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

//融资详情-放款情况
@Data
public class ApiXmRzxqFkqk implements Serializable {



    //放款合计
    String totalLoans;
    //列表数据
    List<ApiXmRzxqFkqkItem> ListData;

}
