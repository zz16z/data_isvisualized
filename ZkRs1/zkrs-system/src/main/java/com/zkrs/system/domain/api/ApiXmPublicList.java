package com.zkrs.system.domain.api;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

//资金预警详情列表
@Data
public class ApiXmPublicList {
    List<?> rsList;
    long total  ;
    ApiXmXmjsszqkTotal apiXmXmjsszqkTotal;
    //融资标题数据
    ApiXmZjrzTotal apiXmZjrzTotal;
    //标题数据
    Object  itme;
}
