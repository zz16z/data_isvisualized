package com.zkrs.system.tool;

import com.zkrs.common.utils.reflect.ReflectUtils;
import com.zkrs.system.domain.ActualCash;
import com.zkrs.system.domain.api.*;
import com.zkrs.system.section.ApiObjFormat;
import com.zkrs.system.section.ApiResultsVo;
import com.zkrs.system.section.ApiZd;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ConvertTypeUtils {

    public static void  convertResult(Object o, ApiRs rs)  {
        Field[] fields=o.getClass().getDeclaredFields();
        rs.setKey(new String[fields.length]);
        rs.setValue(new String[fields.length]);
        for(int i=0;i<fields.length;i++) {
            ApiResultsVo apiResultsVo = fields[i].getAnnotation(ApiResultsVo.class);
            if (apiResultsVo!=null) {
                //获取字段名称
                String fieldName = apiResultsVo.attributeName();
                if (fieldName != null) {
                    rs.getKey()[i] = fieldName;
                    String attributeValue = ReflectUtils.getFieldValue(o, fields[i].getName());
                    if (attributeValue != null && !"".equals(attributeValue)) {
                        rs.getValue()[i] = attributeValue;
                    } else {
                        rs.getValue()[i] = "0";
                    }
                }
            }
        }
    }

    public static void  convertSQLResult(List<ApiXmJsqkSql> list, ApiRs rs)  {
        rs.setKey(new String[list.size()]);
        rs.setValue(new String[list.size()]);
        for(int i=0;i<list.size()  ;i++ ){

            rs.getKey()[i] = list.get(i).getName();
            rs.getValue()[i]=list.get(i).getTotal();
        }

    }

    //过滤无效字段
    public static void  convertResultOne(Object o, ApiRs rs)  {
        Field[] fields=o.getClass().getDeclaredFields();
        List<String>  itemString=new ArrayList<>();
        Field[] fields1=new  Field[fields.length];
        int count=0;
        //获取注解字段
        for(int i=0;i<fields.length;i++) {
            ApiResultsVo apiResultsVo = fields[i].getAnnotation(ApiResultsVo.class);
            if (apiResultsVo!=null) {
                fields1[count]=fields[i];
                itemString.add( apiResultsVo.attributeName());
                count++;
            }
        }
        if(itemString.size()>0){
            rs.setKey(new String[count]);
            rs.setValue(new String[count]);
            for(int i=0;i<itemString.size();i++){
                rs.getKey()[i] = itemString.get(i);
                String attributeValue = ReflectUtils.getFieldValue(o, fields1[i].getName());
                if (attributeValue != null && !"".equals(attributeValue)) {
                    rs.getValue()[i] = attributeValue;
                } else {
                    rs.getValue()[i] = "0";
                }
            }

        }


    }

    public static void  convertResultList(List<?> listObj, List<ApiRs> listRs)  {

        for(int j=0;j< listObj.size();j++){
            Object o= listObj.get(j);
            Field[] fields=o.getClass().getDeclaredFields();
            ApiRs rs=new ApiRs();
            rs.setKey(new String[fields.length]);
            rs.setValue(new String[fields.length]);
            for(int i=0;i<fields.length;i++){
                ApiResultsVo       apiResultsVo =   fields[i].getAnnotation(ApiResultsVo.class);
                if(apiResultsVo!=null){
                    //获取字段名称
                    String fieldName = apiResultsVo.attributeName();
                    if(fieldName!=null){
                        rs.getKey()[i]=fieldName;
                        String attributeValue= ReflectUtils.getFieldValue( o, fields[i].getName());
                        if(attributeValue!=null&&!"".equals(attributeValue)){
                            rs.getValue()[i]=attributeValue;
                        }else{
                            rs.getValue()[i]="0";
                        }
                    }
                }
            }
            listRs.add(rs);
        }
    }
    //该方法加为空判断
    public static void  convertResultIsNull(List<?> listObj, ApiRs rs)  {
        rs.setKey(new String[listObj.size()]);
        rs.setValue(new String[listObj.size()]);
        for(int j=0;j< listObj.size();j++){
            Object o= listObj.get(j);
            if(o==null){
                break;
            }
            Field[] fields=o.getClass().getDeclaredFields();
            for(int i=0;i<fields.length;i++){
                ApiResultsVo   apiResultsVo =   fields[i].getAnnotation(ApiResultsVo.class);
                if(apiResultsVo!=null){
                    //获取字段名称
                    String fieldName = apiResultsVo.attributeName();
                    if(fieldName!=null&& "blByZdName".equals(fieldName)){
                        String attributeValue= ReflectUtils.getFieldValue( o, fields[i].getName());
                        if(attributeValue!=null&&!"".equals(attributeValue)){
                            String[]  valueSplit =attributeValue.split(":");
                            if(valueSplit[0]!=null){
                                rs.getValue()[j]=valueSplit[0];
                            }else {
                                rs.getValue()[j]="0";
                            }
                            if(valueSplit[1]!=null){
                                rs.getKey()[j]=valueSplit[1];
                            }
                        }
                    }
                }
            }

        }
    }

    //pms：实际对象字段数
    public static void  convertResultListObj(List<?> listObj, ApiRs  item )  {
        int count=listObj.size();
        item.setKey(new String[count]);
        item.setValue(new String[count]);
        for(int j=0;j< count;j++){
            Object o= listObj.get(j);
            Field[] fields=o.getClass().getDeclaredFields();
            for(int i=0;i<fields.length;i++){
                ApiResultsVo       apiResultsVo =   fields[i].getAnnotation(ApiResultsVo.class);
                if(apiResultsVo!=null){
                    //获取字段名称
                    String fieldName = apiResultsVo.attributeName();
                    String iskey = apiResultsVo.isKye();
                    if(fieldName!=null){
                        String attributeValue= ReflectUtils.getFieldValue( o, fields[i].getName());
                        // 字段判断取值0key ,1vulue
                        if("0".equals(iskey)){
                            if(attributeValue!=null&&!"".equals(attributeValue)){
                                item.getKey()[j]=attributeValue;
                            }else{
                                item.getKey()[j]="";
                            }
                        }

                        if("1".equals(iskey)){
                            if(attributeValue!=null&&!"".equals(attributeValue)){
                                item.getValue()[j]=attributeValue;
                            }else{
                                item.getValue()[j]="0";
                            }
                        }

                    }
                }
            }
        }
    }



    //字典转换为id转换Name

    public static   void zdIdToName(List<?> list){
        for (Object  t:list) {
            Field[] fields=t.getClass().getDeclaredFields();
            for(int i=0;i<fields.length;i++) {
                ApiZd       o =   fields[i].getAnnotation(ApiZd.class);
                if (o!=null) {
                    String fieldName = o.attributeName();
                    //项目分类
                    if ("xmqkXmlx".equals(fieldName)) {
                        isName( t, fields,i,8L) ;
                    }
                    //项目现状
                    if ("xmqkXmxz".equals(fieldName)) {
                        isName( t, fields,i,7L) ;
                    }
                    //建设状态
                    if ("xmqkJszt".equals(fieldName)) {
                        isName( t, fields,i,9L) ;
                    }

                    //履约分公司
                    if ("xmqkLyfgs".equals(fieldName)) {
                        isName( t, fields,i,50L) ;
                    }

                }
            }

        }

    }

    public static void  isName(Object  t,Field[] fields,int i,Long pid){
        String id = ReflectUtils.getFieldValue(t, fields[i].getName());
        if (id != null) {
            String zdName = BusinessUtils.getPidByIdName(pid, Long.parseLong(id));
            if (zdName != null) {
                ReflectUtils.setFieldValue(t, fields[i].getName(), zdName);
            }
        }
    }


    public static  void bothOfYou(List<ApiXmXzTtal> listdata, ApiRsTo apiRs) {
        //老项目、新项目
        Map<String,String>   newData= listdata.stream().filter(item->"0".equals(item.getFagvalue())).
                collect(Collectors.toMap(ApiXmXzTtal::getZdName,ApiXmXzTtal::getCountjs));

        Map<String,String> oldData= listdata.stream().filter(item->"1".equals(item.getFagvalue())).
                collect(Collectors.toMap(ApiXmXzTtal::getZdName,ApiXmXzTtal::getCountjs));

        List<ApiXmXzTtal>    tolsData= listdata.stream().filter(item->"2".equals(item.getFagvalue())).collect(Collectors.toList());

        String[][]   rsKey= new String[2][tolsData.size()];
        String[][]   rsValue= new String[2][tolsData.size()];
        for(int i=0 ;i<tolsData.size();i++){
            String zdName= tolsData.get(i).getZdName();
            if(newData!=null&&newData.size()>=1){ //新的赋值
                String  newValue=newData.get(zdName);
                if(newValue!=null){
                    //新的key
                    rsKey[0][i]="新"+zdName+"项目";
                    //新的value
                    rsValue[0][i]=newValue;
                }else {
                    //新的key
                    rsKey[0][i]="新"+zdName+"项目";
                    //新的value
                    rsValue[0][i]="0";
                }
            }else{
                //新的key
                rsKey[0][i]="新"+zdName+"项目";
                //新的value
                rsValue[0][i]="0";
            }


            if(oldData!=null&&oldData.size()>=1){ //老的赋值
                String  newValue=oldData.get(zdName);
                if(newValue!=null){
                    //老的key
                    rsKey[1][i]="老"+zdName+"项目";
                    //老的value
                    rsValue[1][i]=newValue;
                }else {
                    //老的key
                    rsKey[1][i]="老"+zdName+"项目";
                    //老的value
                    rsValue[1][i]="0";
                }
            }else{
                //老的key
                rsKey[1][i]="老"+zdName+"项目";
                //老的value
                rsValue[1][i]="0";
            }

        }

        apiRs.setKey(rsKey);
        apiRs.setValue(rsValue);


    }
    public static void  convertResultKeyValue( List<ApiXmRztj> o, ApiXmRztjRs rs) {
        ApiRs fkDate = new ApiRs();
        fkDate.setKey(new String[12]);
        fkDate.setValue(new String[12]);
        ApiRs hkDate = new ApiRs();
        hkDate.setKey(new String[12]);
        hkDate.setValue(new String[12]);
        ////数据标记 总计 0 、 放款 1  、 还款  2
        List<ApiXmRztj> valueString0 = o.stream().filter(item -> "0".equals(item.getIsbj())).collect(Collectors.toList());
        List<ApiXmRztj> valueString1 = o.stream().filter(item -> "1".equals(item.getIsbj())).collect(Collectors.toList());
        List<ApiXmRztj> valueString2 = o.stream().filter(item -> "2".equals(item.getIsbj())).collect(Collectors.toList());
        if (valueString0 != null && valueString0.size()>0&&valueString0.get(0) != null) {
            //标题数据
            ApiXmRztjTielData tileValue = FastJsonUtils.getJsonToBean(valueString0.get(0), ApiXmRztjTielData.class);
            rs.setTileValue(tileValue);
        } else {
            rs.setTileValue(new ApiXmRztjTielData());
        }
        ///放款 ApiXmRztjFk
        List<ApiXmRztjFk> apiXmRztjFkList = FastJsonUtils.getJsonToList(FastJsonUtils.Object2String(valueString1), ApiXmRztjFk.class);
        for (int i = 0; i < 12; i++) {
            if (valueString1 != null && valueString1.size() > 0) {
                ApiXmRztjFk itemFk=null;
                for (ApiXmRztjFk itme:apiXmRztjFkList){
                    String itmeString0=(i+1)+"月";
                    // itme.getFkyf()需要判断，因为数据库设置的为null
                    if( itme.getFkyf()!=null&&itmeString0.equals( itme.getFkyf()) ){
                        itemFk =  itme;
                    }
                }

                //防止数据为空
                if (itemFk != null) {
                    fkDate.getKey()[i] = (i + 1) + "月";
                    if (itemFk.getJanuary() != null) {
                        if(itemFk.getJanuary()!=null&&itemFk.getJanuary().length()>0) {
                            fkDate.getValue()[i] = itemFk.getJanuary();
                        }else {
                            fkDate.getValue()[i] = "0";
                        }
                    } else {
                        fkDate.getValue()[i] = "0";
                    }
                } else {
                    fkDate.getKey()[i] = (i + 1) + "月";
                    fkDate.getValue()[i] = "0";
                }
            } else {
                fkDate.getKey()[i] = (i + 1) + "月";
                fkDate.getValue()[i] = "0";
            }

        }
        /////还款 ApiXmRztjHk
        List<ApiXmRztjHk> apiXmRztjHkList = FastJsonUtils.getJsonToList(FastJsonUtils.Object2String(valueString2), ApiXmRztjHk.class);
        for (int i = 0; i < 12; i++) {
            if (apiXmRztjHkList != null && apiXmRztjHkList.size() > 0) {
                ApiXmRztjHk itemHk = null;
                for (ApiXmRztjHk itme1:apiXmRztjHkList){
                    String itmeString=(i+1)+"月";
                    if( itme1.getHkyf()!=null&&itmeString.equals( itme1.getHkyf()) ){
                        itemHk = itme1;
                    }
                }
                //防止数据为空
                if (itemHk != null) {
                    hkDate.getKey()[i] = (i + 1) + "月";
                    if (itemHk.getHkyf() != null) {
                        if(itemHk.getFinancingBysjhk()!=null&&itemHk.getFinancingBysjhk().length()>0) {
                            hkDate.getValue()[i] = itemHk.getFinancingBysjhk();
                        }else {
                            hkDate.getValue()[i] = "0";
                        }
                    } else {
                        hkDate.getValue()[i] = "0";
                    }
                } else {
                    hkDate.getKey()[i] = (i + 1) + "月";
                    hkDate.getValue()[i] = "0";
                }
            } else {
                hkDate.getKey()[i] = (i + 1) + "月";
                hkDate.getValue()[i] = "0";
            }

        }
        rs.setFkDate(fkDate);
        rs.setHkDate(hkDate);
    }
    //构造数组,key-value 数据统计可重用
    public static void  convertResultStatistics(List<?> o, List<ApiRs>  rs , Class ifFag)  {
        Field[] fields=ifFag.getDeclaredFields();
        //必须满足k-v
        if(fields.length<2){
            return;
        }
        //字段维度
        for(int i=0;i<fields.length-1;i++) {
            ApiRs item=new ApiRs();
            item.setKey(new String[o.size()]);
            item.setValue(new String[o.size()]);
            ApiResultsVo       zdName0=fields[i].getAnnotation(ApiResultsVo.class);
            if(zdName0!=null){
                String fieldName0 = zdName0.attributeName();
                for (int j = 0; j < o.size(); j++)  {
                    Object    t =  o.get(j);
                    Field[] fields1 = t.getClass().getDeclaredFields();
                    for (int k = 0;k < fields1.length; k++) {
                        ApiResultsVo zdName1 = fields1[k].getAnnotation(ApiResultsVo.class);
                        if(zdName0.equals(zdName1)&&zdName1!=null){
                            //赋值vule
                            String attributeValue = ReflectUtils.getFieldValue(t, fields1[k].getName());
                            if (attributeValue == null) {
                                item.getValue()[j] = "0";
                            } else {
                                item.getValue()[j] = attributeValue;
                            }
                            //keyV 字段为固定名称
                            item.getKey()[j] = ReflectUtils.getFieldValue(t, "keyV");
                        }
                    }
                }

            }
            rs.add(item);
        }

    }
    public static void  convertResultAddMoth(List<ApiRs>apiRsList, List<ApiRs> apiRsListMoth){

        for(ApiRs t:apiRsList){
            ApiRs itemMoth=new ApiRs();
            String[] key=new String[12];
            String[] value=new String[12];
            for(int i=0;i<12;i++){
                String  item=(i+1)+"月";
                String[]  ttt=t.getKey();
                int fag=1;
                //如果月份相等就添加
                for(int j=0;j<ttt.length;j++){
                    String keyString=t.getKey()[j];
                    if(item.equals(t.getKey()[j])){
                        key[i]=keyString;
                        value[i]=t.getValue()[j];
                        fag=-1;
                    }
                }
                //否则就new新的
                if(fag>0){
                    key[i]=item;
                    value[i]="0";
                }
            }
            itemMoth.setKey(key);
            itemMoth.setValue(value);
            apiRsListMoth.add(itemMoth);
        }

    }
    //格式化null为value
    public static void  formatNUllAll(Object o, String value)  {
        Field[] fields=o.getClass().getDeclaredFields();
        for(int i=0;i<fields.length;i++) {


            if(  fields[i].getType() == java.lang.String.class ){
                String attributeValue = ReflectUtils.getFieldValue(o, fields[i].getName());
                if(attributeValue==null){
                    ReflectUtils.setFieldValue(o,fields[i].getName(),value);
                }
            }
            if(  fields[i].getType() == java.lang.Integer.class ){
                Integer attributeValue = ReflectUtils.getFieldValue(o, fields[i].getName());
                if(attributeValue==null){
                    ReflectUtils.setFieldValue(o,fields[i].getName(),0);
                }
            }


        }
    }


    //部分格式化null为value
    public static void  formatNUll(Object o, String value)  {
        Field[] fields=o.getClass().getDeclaredFields();
        for(int i=0;i<fields.length;i++) {
            ApiObjFormat zdName0=fields[i].getAnnotation(ApiObjFormat.class);
            if(zdName0!=null){
                if(  fields[i].getType() == java.lang.String.class ){
                    String attributeValue = ReflectUtils.getFieldValue(o, fields[i].getName());
                    if(attributeValue==null){
                        ReflectUtils.setFieldValue(o,fields[i].getName(),value);
                    }
                }
                if(  fields[i].getType() == java.lang.Integer.class ){
                    Integer attributeValue = ReflectUtils.getFieldValue(o, fields[i].getName());
                    if(attributeValue==null){
                        ReflectUtils.setFieldValue(o,fields[i].getName(),0);
                    }
                }
            }

        }
    }

    //对象去null判断
    public static void  convertFilterNull(Object o,String val)  {
        Field[] fields=o.getClass().getDeclaredFields();
        for(int i=0;i<fields.length;i++) {
            ApiResultsVo apiResultsVo = fields[i].getAnnotation(ApiResultsVo.class);
            if (apiResultsVo!=null) {
                //获取字段名称
                String fieldName = apiResultsVo.filterNull();
                if (fieldName != null) {
                    String attributeValue = ReflectUtils.getFieldValue(o, fields[i].getName());
                    if (attributeValue == null || "".equals(attributeValue)) {
                        ReflectUtils.setFieldValue(o, fields[i].getName(),val);
                    }
                }
            }
        }
    }

}

