package com.zkrs.web.controller.api;


import com.github.pagehelper.PageInfo;
import com.zkrs.common.core.domain.R;
import com.zkrs.system.domain.api.*;
import com.zkrs.system.service.api.IApiProjectFinancingService;
import com.zkrs.system.service.api.impl.ApiElectricityStatisticsServiceImpl;
import com.zkrs.system.tool.ConvertTypeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.zkrs.common.utils.PageUtils.startPage;

@Api("项目资金管理")
@RestController
@RequestMapping("/api/home/projectCapitalManagement")
public class ProjectZjPageController {
    @Autowired
    ApiElectricityStatisticsServiceImpl apiElectricityStatisticsServiceImpl;



    @ApiOperation("项目建设收支持情况(实际收入 / 所有实际:取当年)：返回类型ApiXmJsqk")
    @GetMapping("/projectJsqk")
    public R<ApiRs> getXmJsqk()
    {
        R<ApiRs> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmFgsskqk> rs= apiElectricityStatisticsServiceImpl.selectProjectJsqk();
        ApiRs apiRs=new ApiRs();
        if(rs!=null&&rs.size()>0){
            ConvertTypeUtils.convertResultIsNull(rs,apiRs);
            apiResult.setCode(200);
            apiResult.setData(apiRs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }

        return apiResult;
    }

    @ApiOperation("风、光伏项目：返回类型ApiXmFgxm")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "xmTYpe", value = "项目类型", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "yearValue", value = "年份(必传)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月份", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectFgxm")
    public R<ApiRsBaen> getProjectFgxm(String xmTYpe , String yearValue ,String  monthValue ,String beginTime,String endTime)
    {
        R<ApiRsBaen> apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmFgxm rs= apiElectricityStatisticsServiceImpl.selectProjectFgxm(xmTYpe,yearValue,monthValue,beginTime,endTime);
        ApiXmJysSub apiXmJysSub=new ApiXmJysSub();
        ApiRsBaen rsBaen=new ApiRsBaen();
        ApiRs apiRsSub=new ApiRs();
        ApiRs apiRs=new ApiRs();
        if(rs!=null){
            ConvertTypeUtils.convertResult(rs,apiRs);
            rsBaen.setContent(apiRs);
            ConvertTypeUtils.convertFilterNull(rs,"0");
            //计算差额
            apiXmJysSub.setYjlrSub(new BigDecimal(rs.getXjlyjqkYjcszj()).subtract(new BigDecimal(rs.getXjlyjqkYjxmgscb()))+"") ;
            apiXmJysSub.setYjepcSub(new BigDecimal(rs.getXjlyjqkYjepcjsj()).subtract(new BigDecimal(rs.getXjlsjqkSrHj()))+"") ;
            apiXmJysSub.setSjSub(new BigDecimal(rs.getXjlyjqkYjecpcb()).subtract(new BigDecimal(rs.getXjlsjqkZcHj()))+"") ;
            ConvertTypeUtils.convertResult(apiXmJysSub,apiRsSub);

            rsBaen.setContentSbu(apiRsSub);
            apiResult.setData(rsBaen);
            apiResult.setCode(200);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }




    @ApiOperation("分公司收款情况：返回类型ApiXmFgsskqk")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年份(必传)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月份", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectFgsskqk")
    public R<ApiRs> getProjectRzyj(String yearValue, String  monthValue,String beginTime,String endTime )
    {
        R<ApiRs> apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmPublicList  rsList=new ApiXmPublicList();
        ApiRs apiRs=new ApiRs();
        List<ApiXmFgsskqk>  rs=  apiElectricityStatisticsServiceImpl.selectprojectFgsskqk(yearValue,monthValue,beginTime,endTime);
        if(rs!=null&&rs.size()>0){
            ConvertTypeUtils.convertResultIsNull(rs,apiRs);
            apiResult.setData(apiRs);
            apiResult.setCode(200);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }



    @ApiOperation("现金流情况合计：返回类型ApiXmXjlqkHj")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年份(必传)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月份", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "projectType", value = "项目类型(不传为总计)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXjlqkHj")
    public R<List<ApiXmXjlqkHj>> getProjectXjlqkHj(String yearValue, String  monthValue ,String  projectType,String beginTime,String endTime)
    {
        R<List<ApiXmXjlqkHj>> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmXjlqkHj>  rs=  apiElectricityStatisticsServiceImpl.selectprojectXjlqkHj(yearValue,monthValue,projectType,beginTime,endTime);

        String b = rs.get(0).getHj();   //预计现金流总支出总计
        String b1 = rs.get(1).getHj();   //预计现金流总收入总计
        String b2 = rs.get(2).getHj();   //实际现金流总收入总计
        String b3 = rs.get(3).getHj();   //实际现金流总支出总计


        BigDecimal c1 = new BigDecimal(b);
        BigDecimal c2 = new BigDecimal(b1);
        BigDecimal c3 = new BigDecimal(b2);
        BigDecimal c4 = new BigDecimal(b3);

        ApiXmXjlqkHj a = new ApiXmXjlqkHj();
        a.setFName("预计现金流差额");
        a.setHj(c1.subtract(c2).doubleValue()+"");
        ApiXmXjlqkHj a1 = new ApiXmXjlqkHj();
        a1.setFName("实际现金流差额");
        a1.setHj(c3.subtract(c4).doubleValue()+"");

        rs.add(a);
        rs.add(a1);

        if(rs!=null&&rs.size()>0){
            apiResult.setData(rs);
            apiResult.setCode(200);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }



    @ApiOperation("资金现金流情况：返回类型ApiXmXjlqk,备注：0 预计收入  、1  预计支出、2 实际收入、 3  实际支出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年份(必传)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月份", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "projectType", value = "项目类型(不传为总计)", dataType = "String", dataTypeClass = String.class)

    })
    @GetMapping("/projectZjXjlqk")
    public R<List<ApiRs>> getProjectZjXjlqk(String yearValue, String  monthValue ,String  projectType)
    {
        R<List<ApiRs>> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiRs>  listApiRs=new ArrayList<>();
        List<ApiXmXjlqk> rs= apiElectricityStatisticsServiceImpl.selectProjectZjXjlqk(yearValue,monthValue,projectType);
        //防止list All elements are null引起的异常
        List<ApiXmXjlqk> rss=new ArrayList<>();
        if(rs!=null&&rs.size()>0){
            for(ApiXmXjlqk item :rs){
                if(item==null){
                    rss.add(new ApiXmXjlqk());
                }else {
                    rss.add(item);
                }
            }
            ConvertTypeUtils.convertResultList(rss,listApiRs);
            apiResult.setCode(200);
            apiResult.setData(listApiRs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }





    @ApiOperation("资金统计：ApiXmZjtj")
    @GetMapping("/projectZjTj")
    public R<ApiXmZjtj> getProjectZjTj()
    {
        R<ApiXmZjtj> apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmZjtj  rs=  apiElectricityStatisticsServiceImpl.selectProjectZjTj();


        //应收履约保证金=已退履约保证金 —— 已付履约保证金
        String s = apiElectricityStatisticsServiceImpl.selectLybzjBack();
        rs.setXmqkYflybzj(s);

        //实际收入总额 - 实际支付总额=现金流
        String cash = apiElectricityStatisticsServiceImpl.selectCash();
        rs.setCash(cash);
        if(rs!=null){
            apiResult.setData(rs);
            apiResult.setCode(200);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }




    @ApiOperation("融资情况：返回类型ApiXmXjrzqk")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年份(必传)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月份", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "projectType", value = "项目类型(不传为总计)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)

    })
    @GetMapping("/projectZjRzqk")
    public R<ApiRs> getProjectZjRzqk(String yearValue, String  monthValue ,String  projectType,String beginTime,String endTime)
    {
        R<ApiRs> apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmXjrzqk rs= apiElectricityStatisticsServiceImpl.selectProjectZjRzqk(yearValue,monthValue,projectType,beginTime,endTime);
        ApiRs apiRs=new ApiRs();
        if(rs!=null){
            ConvertTypeUtils.convertResult(rs,apiRs);
            apiResult.setCode(200);
            apiResult.setData(apiRs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }





    @ApiOperation("建设项目收支情况与分公司收款情况:返回类型ApiXmXmjsszqk")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "isFull", value = "0表示查询全部(必传)，1表示分页查询(需要分页参数)", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "corporation", value = "分公司", dataType = "String", dataTypeClass = String.class),

            @ApiImplicitParam(name = "xmmc", value = "项目名称", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "projectState", value = "项目现状", dataType = "String", dataTypeClass = String.class),

            @ApiImplicitParam(name = "xmqkJszt", value = "建设状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmlx", value = "项目类型", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmFag", value = "项目标记：0老项目、1新项目", dataType = "String", dataTypeClass = String.class),

            @ApiImplicitParam(name = "xmrzRzqk", value = "融资情况", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkHzf", value = "合作方", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkSgf", value = "收购方", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkSgf", value = "融资方", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectJsxmszqk")
    public R<ApiXmPublicList> getProjectJsxmszqk(Integer   pageNum ,   Integer pageSize ,Integer isFull,String corporation  , String xmmc , String yearValue , String  monthValue, String projectState , String  xmqkJszt,String  xmqkXmlx,String  xmFag ,String xmrzRzqk,String xmqkHzf,String xmqkSgf,String xmqkRzf,String beginTime,String endTime)
    {
        R<ApiXmPublicList> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmXmjsszqk>  rs= null;
        ApiXmPublicList  rsList=new ApiXmPublicList();
        //查询总和
        ApiXmXmjsszqkTotal  total =apiElectricityStatisticsServiceImpl.selectProjectJsxmszqkTotal(corporation,xmmc,yearValue,monthValue,projectState, xmqkJszt,  xmqkXmlx,  xmFag,xmrzRzqk,xmqkHzf,xmqkSgf,xmqkRzf,beginTime,endTime);
        if(isFull==0){ //查询全部
            rs= apiElectricityStatisticsServiceImpl.selectProjectJsxmszqk(corporation,xmmc,yearValue,monthValue,projectState,   xmqkJszt,  xmqkXmlx,  xmFag ,xmrzRzqk,xmqkHzf,xmqkSgf,xmqkRzf,beginTime,endTime);
        }else{ //分页
            startPage();
            rs= apiElectricityStatisticsServiceImpl.selectProjectJsxmszqk(corporation,xmmc,yearValue,monthValue,projectState,   xmqkJszt,  xmqkXmlx,  xmFag,xmrzRzqk,xmqkHzf,xmqkSgf,xmqkRzf,beginTime,endTime);
        }

        if(rs!=null&&rs.size()>0){
            if(isFull>0){//分页
                rsList.setTotal(new PageInfo(rs).getTotal());
            }
            ConvertTypeUtils.zdIdToName(rs);
            rsList.setRsList(rs);
            apiResult.setData(rsList);
            rsList.setApiXmXmjsszqkTotal(total);
            apiResult.setCode(200);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }




    @ApiOperation("项目融资:返回类型ApiXmZjrz,首年IRR:xmrzSnirr,全周期综合IRR:xmrzQzqzhirr")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "isFull", value = "0表示查询全部(必传)，1表示分页查询(需要分页参数)", dataType = "Integer", dataTypeClass = Integer.class),

            @ApiImplicitParam(name = "xllx", value = "xllx", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmmc", value = "项目名称", dataType = "String", dataTypeClass = String.class),

            @ApiImplicitParam(name = "financeManager", value = "融资经理", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "financingInstitutions", value = "融资机构", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "financingSituation", value = "融资情况", dataType = "String", dataTypeClass = String.class),

            @ApiImplicitParam(name = "xmFag", value = "项目标记：0老项目、1新项目", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkJszt", value = "建设状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmxz", value = "项目现状", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmrzQyrq", value = "签约日期", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
//            xmqk_rzjl   融资经理  financeManager
//
//            xmrz_rzjg   融资机构  financingInstitutions
//
//            xmrz_rzqk  融资情况   financingSituation
    })
    @GetMapping("/projectZjXmrz")
    public R<ApiXmPublicList> getProjectZjXmrz(Integer   pageNum ,   Integer pageSize ,Integer isFull
            , String xllx , String yearValue , String  monthValue ,String  xmqkXmmc,String financeManager,String financingInstitutions ,String financingSituation ,String xmFag,String xmqkXmxz,String xmqkJszt,String xmrzQyrq,String beginTime,String endTime)

    {
        R<ApiXmPublicList> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmZjrz>  rs= null;
        ApiXmZjrzTotal  rs1=null;
        rs1=apiElectricityStatisticsServiceImpl.selectProjectZjXmrzTotal(xllx,yearValue,monthValue,xmqkXmmc,financeManager,financingInstitutions,financingSituation,xmFag,xmqkXmxz,xmqkJszt,xmrzQyrq,beginTime,endTime);
        ApiXmPublicList  rsList=new ApiXmPublicList();
        if(isFull==0){ //查询全部
            rs= apiElectricityStatisticsServiceImpl.selectProjectZjXmrz(xllx,yearValue,monthValue,xmqkXmmc,financeManager,financingInstitutions,financingSituation,xmFag,xmqkXmxz,xmqkJszt,xmrzQyrq,beginTime,endTime);
        }else{ //分页
            startPage();
            rs= apiElectricityStatisticsServiceImpl.selectProjectZjXmrz(xllx,yearValue,monthValue,xmqkXmmc,financeManager,financingInstitutions,financingSituation,xmFag,xmqkXmxz,xmqkJszt,xmrzQyrq,beginTime,endTime);
        }

        if(rs!=null&&rs.size()>0){
            if(isFull>0){//分页
                rsList.setTotal(new PageInfo(rs).getTotal());
            }
            ConvertTypeUtils.zdIdToName(rs);
            rsList.setApiXmZjrzTotal(rs1);
            rsList.setRsList(rs);
            apiResult.setData(rsList);
            apiResult.setCode(200);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }



    @ApiOperation("电量列表:返回类型ApiXmZjrz,首年IRR:xmrzSnirr,全周期综合IRR:xmrzQzqzhirr")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "isFull", value = "0表示查询全部(必传)，1表示分页查询(需要分页参数)", dataType = "Integer", dataTypeClass = Integer.class),

            @ApiImplicitParam(name = "xllx", value = "xllx", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmmc", value = "项目名称", dataType = "String", dataTypeClass = String.class),

            @ApiImplicitParam(name = "xmqkLyfgs", value = "履约分公司", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkQy", value = "区域", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmlx", value = "项目分类", dataType = "String", dataTypeClass = String.class),

            @ApiImplicitParam(name = "xmFag", value = "新老项目", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmxz", value = "项目现状", dataType = "String", dataTypeClass = String.class),

            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)

    })
    @GetMapping("/projectElectricityList")
    public R<ApiXmPublicList> getProjectElectricityList(Integer   pageNum ,   Integer pageSize ,Integer isFull
            , String xllx , String yearValue , String  monthValue ,String  xmqkXmmc,String xmqkLyfgs,String xmqkQy ,String xmqkXmlx ,String xmFag,String xmqkXmxz,String beginTime,String endTime)

    {
        R<ApiXmPublicList> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmElectricityList>  rs= null;
        ApiXmElectricityTotal  rs1=null;
        rs1=apiElectricityStatisticsServiceImpl.selectProjectElectricityTotal(xllx,yearValue,monthValue,xmqkXmmc, xmqkLyfgs, xmqkQy , xmqkXmlx ,xmFag ,xmqkXmxz,beginTime,endTime);
        ApiXmPublicList  rsList=new ApiXmPublicList();
        if(isFull==0){ //查询全部
            rs= apiElectricityStatisticsServiceImpl.selectProjectElectricityList(xllx,yearValue,monthValue,xmqkXmmc, xmqkLyfgs, xmqkQy , xmqkXmlx ,xmFag,xmqkXmxz,beginTime,endTime);
        }else{ //分页
            startPage();
            rs= apiElectricityStatisticsServiceImpl.selectProjectElectricityList(xllx,yearValue,monthValue,xmqkXmmc,xmqkLyfgs, xmqkQy , xmqkXmlx,xmFag, xmqkXmxz,beginTime,endTime);
        }

        if(rs!=null&&rs.size()>0){
            if(isFull>0){//分页
                rsList.setTotal(new PageInfo(rs).getTotal());
            }
            ConvertTypeUtils.zdIdToName(rs);
            rsList.setItme(rs1);
            rsList.setRsList(rs);
            apiResult.setData(rsList);
            apiResult.setCode(200);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }



    @ApiOperation("资金还款:返回类型ApiXmZjhk")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "isFull", value = "0表示查询全部(必传)，1表示分页查询(需要分页参数)", dataType = "Integer", dataTypeClass = Integer.class),

            @ApiImplicitParam(name = "xllx", value = "xllx", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmmc", value = "项目名称", dataType = "String", dataTypeClass = String.class),

            @ApiImplicitParam(name = "financeManager", value = "融资经理", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "financingInstitutions", value = "融资机构", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "financingSituation", value = "融资情况", dataType = "String", dataTypeClass = String.class),

    })
    @GetMapping("/projectZjHk")
    public R<ApiXmPublicList> getProjectZjHk(Integer   pageNum ,   Integer pageSize ,Integer isFull
            , String xllx , String yearValue , String  monthValue, String  xmqkXmmc ,String  financeManager ,String financingInstitutions,String financingSituation)

    {
        R<ApiXmPublicList> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmZjhk>  rs= null;
        ApiXmPublicList  rsList=new ApiXmPublicList();
        ApiXmZjrzTotal rs1 =apiElectricityStatisticsServiceImpl.selectProjectZjHkTotal(xllx,yearValue,monthValue,  xmqkXmmc,  financeManager , financingInstitutions, financingSituation);
        if(isFull==0){ //查询全部
            rs= apiElectricityStatisticsServiceImpl.selectProjectZjHk(xllx,yearValue,monthValue,  xmqkXmmc,  financeManager , financingInstitutions, financingSituation);
        }else{ //分页
            startPage();
            rs= apiElectricityStatisticsServiceImpl.selectProjectZjHk(xllx,yearValue,monthValue,xmqkXmmc,  financeManager , financingInstitutions, financingSituation);
        }

        if(rs!=null&&rs.size()>0){
            if(isFull>0){//分页
                rsList.setTotal(new PageInfo(rs).getTotal());
            }
            rsList.setRsList(rs);
            rsList.setApiXmZjrzTotal(rs1);
            apiResult.setData(rsList);
            apiResult.setCode(200);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }






}
