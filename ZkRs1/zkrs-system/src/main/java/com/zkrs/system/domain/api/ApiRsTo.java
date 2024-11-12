package com.zkrs.system.domain.api;

import lombok.Data;

import java.io.Serializable;

@Data
//返回二位数组
public class ApiRsTo implements Serializable {
    public  String[][] key;
    public  String[][] value;


}
