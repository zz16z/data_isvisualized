package com.zkrs.system.service;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.*;

import java.util.List;

/**
 * 数据清除Service接口
 * 
 * @author zkrs
 * @date 2022-06-30
 */
public interface IDataClearService
{

    //清除项目情况数据
    public  int   dataClearInfo();
    //清除施工情况数据
    public  int  dataClearConstruction();
    //清除经营情况数据
    public  int   dataClearManagement();
    //清除融资情况数据
    public  int  dataClearFinancing();
    //清除还款情况数据
    public  int  dataClearTepayment();
    //清除电量收支情况数据
    public  int  dataClearElectricity();
    //清除预计现金流出情况数据
    public  int   dataClearProjectCash();
    //清除预计现金流入情况数据
    public  int   dataClearProjectCashIn();



}
