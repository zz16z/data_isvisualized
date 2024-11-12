package com.zkrs.system.controller;



import com.zkrs.common.core.controller.BaseController;
import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 数据清除Controller
 *
 * @author zkrs
 * @date 2022-06-30
 */
@RestController
@RequestMapping("/system/dataClear")
public class DataClearController extends BaseController
{
    @Autowired
    private IDataClearService dataClearService;



    //项目情况
    @GetMapping(value = "/dataClearInfo")
    public AjaxResult dataClearInfo()
    {
        return  AjaxResult.success(dataClearService.dataClearInfo()) ;
    }
    //清除经营情况数据
    @GetMapping(value = "/dataClearManagement")
    public AjaxResult dataClearManagement()
    {
        return   AjaxResult.success( dataClearService.dataClearManagement()) ;
    }

    //清除融资情况数据
    @GetMapping(value = "/dataClearFinancing")
    public AjaxResult getInfo()
    {
        return   AjaxResult.success(dataClearService.dataClearFinancing()) ;
    }


    //清除还款情况数据
    @GetMapping(value = "/dataClearTepayment")
    public AjaxResult dataClearTepayment()
    {
        return   AjaxResult.success( dataClearService.dataClearTepayment()) ;
    }


    //清除电量收支情况数据
    @GetMapping(value = "/dataClearElectricity")
    public AjaxResult dataClearElectricity()
    {
        return   AjaxResult.success(dataClearService.dataClearElectricity()) ;
    }


    //清除预计现金流出情况数据
    @GetMapping(value = "/dataClearProjectCash")
    public AjaxResult dataClearProjectCash()
    {
        return   AjaxResult.success(dataClearService.dataClearProjectCash()) ;
    }


    //清除预计现金流入情况数据
    @GetMapping(value = "/dataClearProjectCashIn")
    public AjaxResult dataClearProjectCashIn()
    {
        return   AjaxResult.success( dataClearService.dataClearProjectCashIn()) ;
    }



}
