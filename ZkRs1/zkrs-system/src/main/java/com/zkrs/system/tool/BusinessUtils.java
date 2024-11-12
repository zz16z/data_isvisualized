package com.zkrs.system.tool;

import com.zkrs.system.domain.BusinessDictionary;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BusinessUtils {

    private static List<BusinessDictionary>  business=null;


    public static List<BusinessDictionary>  getBusinessUtils(){
        if(business==null){
            business=new LinkedList<>();
        }

        return business;

    }

    public static  void  loodData(List<BusinessDictionary> list){
        List<BusinessDictionary>  item= getBusinessUtils();
        item.addAll(list);
    }

    //根据Pid和id获取
    public static   BusinessDictionary  getPidById(Long pid,Long id){
        BusinessDictionary rs=null;
        for (BusinessDictionary item: business ) {
//            if(item.getId()==id&&item.getpId()==pid){
            if(item.getId().equals(id)&&pid.equals(item.getpId())){
                rs=item;
                break;
            }

        }
        return  rs;

    }

    //根据Pid和id获取 name
    public static   String  getPidByIdName(Long pid,Long id){
        String rs=null;
        for (BusinessDictionary item: business ) {
//            if(item.getId()==id&&item.getpId()==pid){
            if(id.equals(item.getId())&&pid.equals(item.getpId())){
                rs=item.getZdName();
                break;
            }

        }
        return  rs;

    }


    //根据Pid和id获取 name
    public static   String  getByIdName(Long id){
        String rs=null;
        for (BusinessDictionary item: business ) {
            if(item.getId().equals(id)){
                rs=item.getZdName();
                break;
            }

        }
        return  rs;

    }

    //根据Pid和name获取
    public static   BusinessDictionary  getPidByName(Long pid,String  name){
        BusinessDictionary rs=null;
        for (BusinessDictionary item: business ) {
            if(name.equals(item.getZdName())&&pid.equals(item.getpId())){
                rs=item;
                break;
            }
        }
        return  rs;
    }


    //删除BusinessDictionary
    public static   void   delete(Long id) {
        for (BusinessDictionary item : business) {
            if (item.getId().equals(id)) {
                business.remove(item);
                break;
            }
        }
    }



    //删除BusinessDictionary
    public static   void   deleteS(Long[] ids) {
        for (Long item : ids) {
            delete(item);
        }
    }



    //添加 BusinessDictionary
    public static   void   add(BusinessDictionary item){
        business.add(item);
    }

    //修改
    public static   void   edit(BusinessDictionary obj){
        for (BusinessDictionary item : business) {
            if (  item.getId().equals(obj.getId() )) {
                business.remove(item);
                business.add(obj);
                break;
            }
        }
    }





}
