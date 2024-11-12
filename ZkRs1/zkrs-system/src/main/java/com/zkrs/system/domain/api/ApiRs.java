package com.zkrs.system.domain.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiRs implements Serializable {
    public  String[] key;
    public  String[] value;

    public  Object  item;


}
