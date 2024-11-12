package com.zkrs.system.enumeration;

public enum CommonlyCache {

    PROJECT_INFO("projectInfo_cache",1,"项目名称缓存"),

    DICTIONARIES("describe_cache",2,"业务字典缓存");

    private String name;
    private int index;
    //描述信息
    private String describe;

    private CommonlyCache(String name, int index, String describe) {

        this.name = name;

        this.index = index;

        this.describe = describe;

    }




    public static String getName(int index) {

        for (CommonlyCache c : CommonlyCache.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

  //描述信息
    public static String getDescribe(int index) {

        for (CommonlyCache c : CommonlyCache.values()) {
            if (c.getIndex() == index) {
                return c.describe;
            }
        }
        return null;
    }

    public String getName() {
        return name;

    }
    public void setName(String name) {
        this.name = name;

    }
    public int getIndex() {
        return index;

    }

    public void setIndex(int index) {
        this.index = index;

      }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
