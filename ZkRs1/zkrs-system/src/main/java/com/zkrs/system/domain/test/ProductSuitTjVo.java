//package com.zkrs.system.domain.test;
//import cn.afterturn.easypoi.excel.annotation.Excel;
//import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
//import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
//import lombok.Data;
//
//import java.util.List;
///**
// * 作者：zhuLin
// * 日期：2020-07-30 17:36
// * 备注：产品信息 - 实体类
// */
//
//
//@ExcelTarget("ProductSuitTj")
//public class ProductSuitTjVo  implements java.io.Serializable{
//
//    //需要合并得列添加：needMerge = true 即可
//
//    @Excel(name = "产品名称",needMerge = true,width = 20, orderNum = "1")
//    private String msgName;
//
//   @Excel(name = "产品状态", needMerge = true,width = 20, orderNum = "2")
//    private String prodState;
//
//   @Excel(name = "产品经理",needMerge = true,width = 20, orderNum = "3")
//    private String productManager;
//
//
//    @ExcelCollection(id = "shuxuest",name = "学生", orderNum = "4")
//    List<SuitTjVo> suitList;
//
//    public String getMsgName() {
//        return msgName;
//    }
//
//    public void setMsgName(String msgName) {
//        this.msgName = msgName;
//    }
//
//    public String getProdState() {
//        return prodState;
//    }
//
//    public void setProdState(String prodState) {
//        this.prodState = prodState;
//    }
//
//    public String getProductManager() {
//        return productManager;
//    }
//
//    public void setProductManager(String productManager) {
//        this.productManager = productManager;
//    }
//
//    public List<SuitTjVo> getSuitList() {
//        return suitList;
//    }
//
//    public void setSuitList(List<SuitTjVo> suitList) {
//        this.suitList = suitList;
//    }
//}