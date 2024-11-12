package com.zkrs.system.domain;


import lombok.Data;



/**
 *  expect_cash
 * 
 * @author zkrs
 * @date 2022-07-06
 */
@Data
public class ImportError
{
    private static final long serialVersionUID = 1L;
     //项目名称
     String   projectName;
     //错误信息
     String   errorInfo;

}
