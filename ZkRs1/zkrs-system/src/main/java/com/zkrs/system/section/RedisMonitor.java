package com.zkrs.system.section;

import com.zkrs.system.enumeration.CommonlyCache;

import java.lang.annotation.*;
@Target(value = {ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})//该注解可以放在方法上，属性上，类上
@Retention(RetentionPolicy.RUNTIME)//运行环境下
//@Target({ ElementType.PARAMETER, ElementType.METHOD })
//@Retention(RetentionPolicy.RUNTIME)
public @interface  RedisMonitor {

    /**
     * 类型
     */
    public CommonlyCache  stringType()  ;

    /**
     * 操作add增加，edit编辑，delete删除
     */
    public String operation() ;

    /**
     * 获取参数下索引,从0开始
     */
    public int index() ;
}
