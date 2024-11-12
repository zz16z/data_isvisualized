//package com.zkrs.system.domain.test;
//
//import cn.afterturn.easypoi.excel.annotation.Excel;
//
//import java.util.Date;
//
///**
// * @author JueYue
// * @version V1.0
// * @Title: Entity
// * @Description: 学生
// *   2013-08-31 22:53:34
// */
//@SuppressWarnings("serial")
//public class StudentEntity implements java.io.Serializable {
//    /**
//     * id
//     */
//    private String        id;
//    /**
//     * 学生姓名
//     */
//    @Excel(name = "学生姓名", height = 20, width = 30, isImportField = "true_st")
//    private String        name;
//    /**
//     * 学生性别
//     */
//    @Excel(name = "学生性别", replace = { "男_1", "女_2" }, suffix = "生", isImportField = "true_st")
//    private int           sex;
//
//    @Excel(name = "出生日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd", isImportField = "true_st", width = 20)
//    private Date          birthday;
//
//    @Excel(name = "进校日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd")
//    private Date registrationDate;
//
//
//
//    public String getId() {
//        return this.id;
//    }
//
//
//    public String getName() {
//        return this.name;
//    }
//
//    public int getSex() {
//        return this.sex;
//    }
//
//
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//    public void setSex(int sex) {
//        this.sex = sex;
//    }
//
//    public Date getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(Date birthday) {
//        this.birthday = birthday;
//    }
//
//    public Date getRegistrationDate() {
//        return registrationDate;
//    }
//
//    public void setRegistrationDate(Date registrationDate) {
//        this.registrationDate = registrationDate;
//    }
//
//}
