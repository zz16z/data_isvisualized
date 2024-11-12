//package com.zkrs.system.domain.test;
//
//import cn.afterturn.easypoi.excel.annotation.Excel;
//import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
//import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
//import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
//
//import java.util.List;
//
///**
// * @Title: Entity
// * @Description: 课程
// * @author JueYue
// *   2013-08-31 22:53:07
// * @version V1.0
// *
// */
//@SuppressWarnings("serial")
//@ExcelTarget("courseEntity")
//public class CourseEntityNoAnn implements java.io.Serializable {
//
//    private String        id;
//
//    @Excel(name = "课程名称", orderNum = "1", width = 15, needMerge = true)
//    private String        name;
//
//
//    @ExcelCollection(id = "shuxuest", name = "学生", orderNum = "4")
//    private List<StudentEntity> students;
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
//
//    public List<StudentEntity> getStudents() {
//        return students;
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
//    public void setStudents(List<StudentEntity> students) {
//        this.students = students;
//    }
//
//
//}
