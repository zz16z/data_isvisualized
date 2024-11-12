package com.zkrs.web.controller.tool;

import com.zkrs.common.utils.reflect.ReflectUtils;
import com.zkrs.system.domain.api.ApiRs;
import com.zkrs.system.domain.api.ApiXmXzTtal;
import com.zkrs.system.section.ApiResultsVo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class test {


    private static void  convertResult(Object o, ApiRs rs)  {
        Field[] fields=o.getClass().getDeclaredFields();
        rs.setKey(new String[fields.length]);
        rs.setValue(new String[fields.length]);
        for(int i=0;i<fields.length;i++){
            //获取字段名称
            String fieldName =  fields[i].getAnnotation(ApiResultsVo.class).attributeName();
                 if(fieldName!=null){
                     rs.getKey()[i]=fieldName;
                    String attributeValue= ReflectUtils.getFieldValue( o, fields[i].getName());
                       if(attributeValue!=null&&"".equals(attributeValue)){
                           rs.getValue()[i]=attributeValue;
                       }else{
                           rs.getValue()[i]="0";
                       }
                 }
        }
    }



    public static void main(String[] args)  {

        String[] testt=new String[]{};
        ApiXmXzTtal  yy=new ApiXmXzTtal();
        yy.setProjectZj("3333");
        ApiRs rs=new ApiRs();
        // System.setProperty("spring.devtools.restart.enabled", "false");
        convertResult(yy,rs);
        System.out.println("000:");
    }

}
