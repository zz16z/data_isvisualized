package com.zkrs.system.domain.api;

import lombok.Data;

import java.util.List;

@Data
public class ApiXmjyList {

    List<ApiXmjy> rsList;

    long total  ;

    Object  itme;
}
