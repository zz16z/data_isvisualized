package com.zkrs.system.mapper;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.ActualCash;
import com.zkrs.system.domain.ExportManagementCash;
import com.zkrs.system.domain.ManagementCash;
import com.zkrs.system.domain.ProjectInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据清除Mapper接口
 * 
 * @author zkrs
 * @date 2022-07-01
 */
public interface DataClearMapper
{

    //清除项目情况数据
    public int dataClearInfo();
    //清除施工情况数据
    public  int  dataClearConstruction();


    //清除经营情况数据-预计
    public  int   dataClearManagement();
    //清除还款情况数据
    public  int  dataClearTepayment();

    //清除融资情况数据
    public  int  dataClearFinancing();
    //放款
    public  int  dataClearData();
    //还款
    public  int  dataClearSill();


    //清除电量收支情况数据
    public  int  dataClearElectricity();


    //清除预计现金主表导出
    public  int   dataClearProjectCashZbOut();
    //清除预计现金主表导出
    public  int   dataClearProjectCashOut();
    //清除预计现金主表导入
    public  int   dataClearProjectCashZbIn();
    //清除预计现金流入情导入
    public  int   dataClearProjectCashIn();

    public  int   dataClearProjectCashAll();
    public  int   dataClearProjectCashInfoAll();
}
