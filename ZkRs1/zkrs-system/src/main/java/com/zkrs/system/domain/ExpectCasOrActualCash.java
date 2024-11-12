package com.zkrs.system.domain;

import lombok.Data;

@Data
public class ExpectCasOrActualCash {
    private static final long serialVersionUID = 1L;
    //expectCash表id
    String  expectCashXjlyjqkId;
    //醒目id
    String xmid;
    ////expectCash表日期导入验证
    String xjlyjqkRq;
    //两表关联ID
    String xjlsjqkYjxjlId;
    // actualCash表id
    String actualCashID;

}
