package com.zkrs.system.domain.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiRsBaen implements Serializable {
    //数据内容
    ApiRs  content;
    //差额值
    ApiRs  contentSbu;


}
