package com.zkrs.system.section;

import com.zkrs.system.enumeration.CommonlyCache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})//该注解可以放在方法上，属性上，类上
@Retention(RetentionPolicy.RUNTIME)//运行环境下
public @interface ApiResultsVo {

    /**
     * 字段名称
     */
    public String   attributeName()  ;


    /**
     * 字段判断取值0key ,1vulue
     */
    public String   isKye()  default  "" ;


    /**
     * 字段过滤空
     */
    public String   filterNull()  default  "" ;


}
