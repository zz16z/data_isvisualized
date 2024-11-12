package com.zkrs.web.controller.api;


import com.github.pagehelper.PageInfo;
import com.zkrs.common.core.domain.R;
import com.zkrs.common.utils.reflect.ReflectUtils;
import com.zkrs.system.domain.ActualCash;
import com.zkrs.system.domain.api.*;
import com.zkrs.system.section.ApiResultsVo;
import com.zkrs.system.service.api.IApiProjectInfoService;
import com.zkrs.system.tool.ConvertTypeUtils;
import com.zkrs.system.tool.FastJsonUtils;
import io.swagger.annotations.*;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.zkrs.common.utils.PageUtils.startPage;

@Api("首页总览")
@RestController
@RequestMapping("/api/home/overview")
public class HomePageController {
    @Autowired
    IApiProjectInfoService apiProjectInfoService;
    @ApiOperation("项目分类:返回类型ApiProjectType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "corporation", value = "分公司(不传默认为总览)", dataType = "String", dataTypeClass = String.class),
    })
    @GetMapping("/projectType")
    public R<List<ApiProjectType>> getXmRl(String corporation)
    {
        R<List<ApiProjectType>> apiResult = new R<>();
        apiResult.setCode(-1);
        //  List<ApiRs>  listApiRs=new ArrayList<>();
        List<ApiProjectType>  rs= apiProjectInfoService.selectProjectInfoRlTtal(corporation);
        if(rs!=null&&rs.size()>0){
            //    ConvertTypeUtils.convertResultList(rs,listApiRs);
            apiResult.setCode(200);
            apiResult.setData(rs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return apiResult ;
    }




    @ApiOperation("项目建设情况：返回类型ApiXmJsqk")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "corporation", value = "分公司(不传默认为总览)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmFag", value = "项目标记：0老项目、1新项目", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectJsqk")
    public R<ApiRs> getXmJsqk(String corporation,String xmFag)
    {
        R<ApiRs> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmJsqkSql> rs= apiProjectInfoService.selectProjectJsqkTtal( corporation,xmFag);
        ApiRs apiRs=new ApiRs();
        if(rs!=null&&rs.size()>0&&rs.get(0)!=null){
            ConvertTypeUtils.convertSQLResult(rs,apiRs);
            apiResult.setCode(200);
            apiResult.setData(apiRs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }

        return apiResult;
    }



    @ApiOperation("项目现状(改)：返回类型ApiXmXzTtal")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "corporation", value = "分公司(不传默认为总览)", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXz")
    public R<ApiRsTo> getProjectXz(String corporation)
    {
        R<ApiRsTo> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmXzTtal> rs= apiProjectInfoService.selectProjectXzTtal(corporation);
        ApiRsTo apiRs=new ApiRsTo();
        if(rs!=null&&rs.size()>0){
            ConvertTypeUtils.bothOfYou(rs,apiRs);
            apiResult.setCode(200);
            apiResult.setData(apiRs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }

    @ApiOperation("总览：返回类型ApiXmZlTtal")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isDq", value = "0新项目,1总项目", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "corporation", value = "分公司(不传默认为总览)", dataType = "String", dataTypeClass = String.class),
    })
    @GetMapping("/projectZl")
    public R<ApiXmZlTtal> getProjectZl(String  isDq,String corporation)
    {
        R<ApiXmZlTtal> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmZlTtal> rs= apiProjectInfoService.selectProjectZl( isDq,corporation);
        ApiXmZlTtal apiRs=null;
        if(rs!=null&&rs.size()>0){
            if("0".equals(isDq)){//0新项目
                apiRs= rs.get(0);
            }
            if("1".equals(isDq)){//老项目
                apiRs= rs.get(1);
            }
            apiResult.setCode(200);
            apiResult.setData(apiRs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }




    @ApiOperation("项目公司情况(统计当月份)：返回类型ApiXmDltj")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "month", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "corporation", value = "分公司(不传默认为总览)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", dataTypeClass = String.class),
    })
    @GetMapping("/projectDltj")
    public R<ApiXmDltj> getProjectDltj(String  month,String corporation,String  year)
    {
        R<ApiXmDltj> apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmDltj  rs= apiProjectInfoService.selectProjectDltjl(month,corporation,year);
        if(rs!=null){
            ConvertTypeUtils.formatNUllAll(rs,"0");
            apiResult.setCode(200);
            apiResult.setData(rs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }

    @ApiOperation("项目经营数据(统计当年月份)：返回类型ApiXmJysj")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "month", value = "年份(如：2022),如果传null查询全年全部", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "corporation", value = "分公司(不传默认为总览)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectJysj")
    public R<ApiRsBaen> getProjectJysj(String  month,String corporation,String beginTime,String endTime)
    {

        R<ApiRsBaen> apiResult = new R<>();
        apiResult.setCode(-1);
        ApiRsBaen rsBaen=new ApiRsBaen();
        ApiXmJysj  rs= apiProjectInfoService.selectProjectJysj(month,corporation,beginTime,endTime);
        ApiXmJysSub apiXmJysSub=new ApiXmJysSub();
        ApiRs apiRs=new ApiRs();
        ApiRs apiRsSub=new ApiRs();
        if(rs!=null){
            ConvertTypeUtils.convertResult(rs,apiRs);
            rsBaen.setContent(apiRs);
            ConvertTypeUtils.convertFilterNull(rs,"0");
            //计算差额
            apiXmJysSub.setYjlrSub(new BigDecimal(rs.getXjlyjqkYjcszj()).subtract(new BigDecimal(rs.getXjlyjqkYjxmgscb()))+"") ;
            apiXmJysSub.setYjepcSub(new BigDecimal(rs.getXjlyjqkYjepcjsj()).subtract(new BigDecimal(rs.getXjlyjqkYjecpcb()))+"") ;
            apiXmJysSub.setSjSub(new BigDecimal(rs.getXjlsjqkSrHj()).subtract(new BigDecimal(rs.getXjlsjqkZcHj()))+"") ;
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


    @ApiOperation("资金预警(当前月预警)：返回类型ApiXmZjyj")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "corporation", value = "分公司(不传默认为总览)", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectZjyj")
    public R<List<ApiXmZjyj>> getProjectZjyj(String corporation)
    {
        R<List<ApiXmZjyj>> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmZjyj>  rs= apiProjectInfoService.selectProjectZjyj(corporation);
        if(rs!=null){
            apiResult.setCode(200);
            apiResult.setData(rs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }




    @ApiOperation("资金预警详情(当前月预警,支出大于收入)：返回类型ApiXmZjyj")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "corporation", value = "分公司(不传默认为总览)", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectZjyjxq")
    public R<ApiXmPublicList> getProjectZjyjxq( Integer   pageNum ,   Integer pageSize ,String corporation )
    {
        R<ApiXmPublicList> apiResult = new R<>();
        apiResult.setCode(-1);
        startPage();
        List<ApiXmZjyjxq>  rs= apiProjectInfoService.selectProjectZjyjxq( corporation);

        ConvertTypeUtils.zdIdToName(rs);
        ApiXmPublicList  rsList=null;
        if(rs!=null){
            rsList=new ApiXmPublicList();
            rsList.setRsList(rs);
            rsList.setTotal(new PageInfo(rs).getTotal());
            apiResult.setCode(200);
            apiResult.setData(rsList);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }










    @ApiOperation("工期预警( 并网日期前20天并且工期进度小于90% )：返回类型ApiXmZjyj")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "isFull", value = "0表示查询全部，1表示分页查询(需要分页参数)", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "corporation", value = "分公司(不传默认为总览)", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectGqyj")
    public R<ApiXmPublicList> getProjectGqyjj(Integer   pageNum ,   Integer pageSize ,Integer isFull,String corporation)
    {
        R<ApiXmPublicList> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmGqyj>  rs= null;
        ApiXmPublicList  rsList=new ApiXmPublicList();
        if(isFull==0){ //查询全部
            rs= apiProjectInfoService.selectProjectGqyjj(corporation);
        }else{ //分页
            startPage();
            rs= apiProjectInfoService.selectProjectGqyjj(corporation);
        }

        if(rs!=null&&rs.size()>0){
            if(isFull>0){//分页
                rsList.setTotal(new PageInfo(rs).getTotal());
            }
            ConvertTypeUtils.zdIdToName(rs);
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





    @ApiOperation("出售预警：返回类型ApiXmCsyj")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "isFull", value = "0表示查询全部，1表示分页查询(需要分页参数)", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "corporation", value = "分公司(不传默认为总览)", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectCsyj")
    public R<ApiXmPublicList> getProjectCsyj(Integer   pageNum ,   Integer pageSize ,Integer isFull,String corporation)
    {
        R<ApiXmPublicList> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmCsyj>  rs= null;
        ApiXmPublicList  rsList=new ApiXmPublicList();
        if(isFull==0){ //查询全部
            rs= apiProjectInfoService.selectProjectCsyj(corporation);
        }else{ //分页
            startPage();
            rs= apiProjectInfoService.selectProjectCsyj(corporation);
        }

        if(rs!=null&&rs.size()>0){
            if(isFull>0){//分页
                rsList.setTotal(new PageInfo(rs).getTotal());
            }
            ConvertTypeUtils.zdIdToName(rs);
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


    @ApiOperation("融资预警(成本+利息+手续费=还款金额)-日期等于当前月的：返回类型ApiXmRzyj")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "isFull", value = "0表示查询全部，1表示分页查询(需要分页参数)", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "corporation", value = "分公司(不传默认为总览)", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectRzyj")
    public R<ApiXmPublicList> getProjectRzyj(Integer   pageNum ,   Integer pageSize ,Integer isFull,String corporation)
    {
        R<ApiXmPublicList> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmRzyj>  rs= null;
        ApiXmPublicList  rsList=new ApiXmPublicList();
        if(isFull==0){ //查询全部
            rs= apiProjectInfoService.selectProjectRzyj(corporation);

        }else{ //分页
            startPage();
            rs= apiProjectInfoService.selectProjectRzyj(corporation);

        }

        if(rs!=null&&rs.size()>0){
            if(isFull>0){//分页
                rsList.setTotal(new PageInfo(rs).getTotal());
            }
            ConvertTypeUtils.zdIdToName(rs);
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



    @ApiOperation("项目履约分公司：返回类型ApiXmLyfgs")
    @GetMapping("/projectlyfgs")
    public R<ApiRs> getProjectlyfgs()
    {
        R<ApiRs> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmLyfgs> rs= apiProjectInfoService.selectProjectlyfgs();
        ApiRs apiRs=new ApiRs();
        if(rs!=null&&rs.size()>0){
            ConvertTypeUtils.convertResultListObj(rs,apiRs);
            apiResult.setCode(200);
            apiResult.setData(apiRs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }


    @ApiOperation("现金流情况：返回类型ApiXmXjlqk,备注：0 预计收入  、1  预计支出、2 实际收入、 3  实际支出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "corporationId", value = "分公司id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXjlqk")
    public R<List<ApiRs>> getProjectXjlqk(String year,String  corporationId,String beginTime,String endTime)
    {
        R<List<ApiRs>> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiRs>  listApiRs=new ArrayList<>();
        List<ApiXmXjlqk> rs= apiProjectInfoService.selectProjectXjlqk(year,corporationId,beginTime,endTime);
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



    @ApiOperation("实际收款情况：返回类型ApiXmJysj")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "corporation", value = "分公司", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmxz", value = "项目状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkJszt", value = "建设状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmlx", value = "项目类型", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkQy", value = "区域", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmFag", value = "项目标记：0老项目、1新项目", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmrzRzqk", value = "融资情况", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkRzf", value = "融资方", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectJysjSjskqk")
    public R<ApiRs> getProjectJysjSjskqk(String yearValue ,String  monthValue,String corporation,String xmqkXmxz, String xmqkJszt,String xmqkXmlx,String xmqkQy ,String xmFag,
                                         String xmrzRzqk,String xmqkRzf,String beginTime,String endTime)
    {

        R<ApiRs> apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmJysjSjskqk  rs= apiProjectInfoService.selectProjectJysjSjskqk(yearValue,monthValue,corporation,xmqkXmxz,  xmqkJszt, xmqkXmlx, xmqkQy , xmFag, xmrzRzqk, xmqkRzf,beginTime,endTime);
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



    @ApiOperation("实际付款情况：返回类型ApiXmJysj")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "corporation", value = "分公司", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmxz", value = "项目状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkJszt", value = "建设状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmlx", value = "项目类型", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkQy", value = "区域", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmFag", value = "项目标记：0老项目、1新项目", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmrzRzqk", value = "融资情况", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkRzf", value = "融资方", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectJysjSjfkqk")
    public R<ApiRs> getProjectJysjSjfkqk(String yearValue ,String  monthValue,String corporation,String xmqkXmxz, String xmqkJszt,String xmqkXmlx,
                                         String xmqkQy ,String xmFag,String xmrzRzqk,String xmqkRzf,String beginTime,String endTime)
    {

        R<ApiRs> apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmJysjSjfkqk  rs= apiProjectInfoService.selectProjectJysjSjfkqk(yearValue,monthValue,corporation,xmqkXmxz,  xmqkJszt, xmqkXmlx,xmqkQy,xmFag, xmrzRzqk, xmqkRzf,beginTime,endTime);
        ApiRs apiRs=new ApiRs();
        if(rs!=null){
            ApiXmJysjSjfkqkChildren rs1= new ApiXmJysjSjfkqkChildren() ;
            BeanUtils.copyProperties(rs,rs1);
            ConvertTypeUtils.convertResult(rs1,apiRs);
            apiRs.setItem(rs);
            apiResult.setCode(200);
            apiResult.setData(apiRs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }


    @ApiOperation("预计经营情况：返回类型ApiXmJysj")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "corporation", value = "分公司", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmxz", value = "项目状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkJszt", value = "建设状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmlx", value = "项目类型", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkQy", value = "区域", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmFag", value = "项目标记：0老项目、1新项目", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmrzRzqk", value = "融资情况", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkRzf", value = "融资方", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectJysjYjjyqk")
    public R<ApiRs> getProjectJysjYjjyqk(String yearValue ,String  monthValue,String corporation,String xmqkXmxz, String xmqkJszt,String xmqkXmlx,String xmqkQy ,String xmFag
    ,String xmrzRzqk,String xmqkRzf,String beginTime,String endTime)
    {

        R<ApiRs> apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmJysjYjjyqk  rs= apiProjectInfoService.selectProjectJysjYjjyqk(yearValue,monthValue,corporation,xmqkXmxz,  xmqkJszt, xmqkXmlx, xmqkQy , xmFag,
                 xmrzRzqk, xmqkRzf,beginTime,endTime);
        ApiRs apiRs=new ApiRs();
        if(rs!=null){
            ApiXmJysjYjjyqkChildren  item=new ApiXmJysjYjjyqkChildren();
            BeanUtils.copyProperties(rs,item);
            ConvertTypeUtils.convertResult(item,apiRs);
            apiRs.setItem(rs);
            apiResult.setCode(200);
            apiResult.setData(apiRs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }


    @ApiOperation("预计经营情况统计总数：返回类型xmTotal")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "corporation", value = "分公司", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmxz", value = "项目状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkJszt", value = "建设状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmlx", value = "项目类型", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkQy", value = "区域", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmFag", value = "项目标记：0老项目、1新项目", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmrzRzqk", value = "融资情况", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkRzf", value = "融资方", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectInfoTotal")
    public R<ApiXmTotal> projectInfoTotal(String yearValue ,String  monthValue,String corporation,String xmqkXmxz, String xmqkJszt,String xmqkXmlx,String xmqkQy ,String xmFag
            ,String xmrzRzqk,String xmqkRzf,String beginTime,String endTime)
    {

        R<ApiXmTotal> apiResult = new R<>();
        ApiXmTotal  rs= apiProjectInfoService.projectInfoTotal(yearValue,monthValue,corporation,xmqkXmxz,  xmqkJszt, xmqkXmlx, xmqkQy , xmFag,
                xmrzRzqk, xmqkRzf,beginTime,endTime);

        if(rs!=null){
            apiResult.setData(rs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }



    //项目经营数据下面的：项目清单
    @ApiOperation("项目经营数据下面的：项目清单分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "isFull", value = "0表示查询全部，1表示分页查询(需要分页参数)", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "corporation", value = "分公司(不传默认为总览)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmxz", value = "项目状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkJszt", value = "建设状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmlx", value = "项目类型", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkQy", value = "区域", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmFag", value = "项目标记：0老项目、1新项目", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmrzRzqk", value = "融资情况", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkRzf", value = "融资方", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectInfoList")
    public R<ApiXmjyList> projectInfoList(String yearValue ,String  monthValue,String corporation,String xmqkXmxz, String xmqkJszt,String xmqkXmlx,String xmqkQy ,String xmFag
            ,String xmrzRzqk,String xmqkRzf,String beginTime,String endTime,Integer isFull,Integer pageNum,Integer pageSize)
    {

        R<ApiXmjyList> apiResult = new R<>();
        apiResult.setCode(-1);


        Map<String,String>  rs1=null;

        List<ApiXmjy>  rs= null;

        ApiXmjyList  rsList=new ApiXmjyList();

        rs1=apiProjectInfoService.projectInfoListTotal(yearValue,monthValue,corporation,xmqkXmxz,  xmqkJszt, xmqkXmlx, xmqkQy , xmFag,
                xmrzRzqk, xmqkRzf,beginTime,endTime);

        if(isFull==0){ //查询全部
            rs= apiProjectInfoService.selectProjectInfoList(yearValue,monthValue,corporation,xmqkXmxz,  xmqkJszt, xmqkXmlx, xmqkQy , xmFag,
                    xmrzRzqk, xmqkRzf,beginTime,endTime);
        }else{ //分页
            startPage(pageNum,pageSize);
            rs= apiProjectInfoService.selectProjectInfoList(yearValue,monthValue,corporation,xmqkXmxz,  xmqkJszt, xmqkXmlx, xmqkQy , xmFag,
                    xmrzRzqk, xmqkRzf,beginTime,endTime);
        }

        if(rs!=null&&rs.size()>0){
            if(isFull>0){//分页
                rsList.setTotal(new PageInfo(rs).getTotal());
            }
          //  ConvertTypeUtils.zdIdToName(rs);
            rsList.setItme(rs1);
            rsList.setRsList(rs);
            apiResult.setData(rsList);
            apiResult.setCode(200);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult;
    }











    ///////////////列表//////////////////////////////////

    @ApiOperation("项目现状与建设状态列表:返回类型ApiXmXmjsszqk")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "isFull", value = "0表示查询全部(必传)，1表示分页查询(需要分页参数)", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "corporation", value = "分公司", dataType = "String", dataTypeClass = String.class),


            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmxz", value = "项目现状", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "corporation", value = "分公司", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "jszt", value = "建设状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmFag", value = "项目标记：0老项目、1新项目", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkJszt", value = "建设状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmrzRzqk", value = "融资情况", dataType = "String", dataTypeClass = String.class),

            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmqklb")
    public R<ApiXmPublicList> getProjectXmqklb(Integer   pageNum ,   Integer pageSize ,Integer isFull, String yearValue
            , String  monthValue ,String xmxz ,String corporation,String jszt , String xmFag ,String xmqkJszt,String xmrzRzqk,String beginTime,String endTime )
    {
        R<ApiXmPublicList> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmXmXzxq>  rs= null;
        ApiXmPublicList  rsList=new ApiXmPublicList();
        if(isFull==0){ //查询全部
            rs= apiProjectInfoService.selectProjectXmqklb(yearValue,monthValue,xmxz,corporation,jszt,xmFag,xmqkJszt,xmrzRzqk,beginTime,endTime);
        }else{ //分页
            startPage();
            rs= apiProjectInfoService.selectProjectXmqklb(yearValue,monthValue,xmxz,corporation,jszt,xmFag,xmqkJszt,xmrzRzqk,beginTime,endTime);
        }

        if(rs!=null&&rs.size()>0){
            if(isFull>0){//分页
                rsList.setTotal(new PageInfo(rs).getTotal());
            }
            ConvertTypeUtils.zdIdToName(rs);
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


    @ApiOperation("发电量情况列表:返回类型ApiXmXmjsszqk")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "isFull", value = "0表示查询全部(必传)，1表示分页查询(需要分页参数)", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "corporation", value = "分公司", dataType = "String", dataTypeClass = String.class),


            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmmc", value = "xmmc", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "corporation", value = "分公司", dataType = "String", dataTypeClass = String.class)

    })
    @GetMapping("/projectXmfdlqklb")
    public R<ApiXmPublicList> getProjectXmfdlqklb(Integer   pageNum ,   Integer pageSize ,Integer isFull, String yearValue
            , String  monthValue ,String xmmc ,String corporation )
    {
        R<ApiXmPublicList> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmDltjlb>  rs= null;
        ApiXmPublicList  rsList=new ApiXmPublicList();
        if(isFull==0){ //查询全部
            rs= apiProjectInfoService.selectProjectXmfdlqklb(yearValue,monthValue,xmmc,corporation);
        }else{ //分页
            startPage();
            rs= apiProjectInfoService.selectProjectXmfdlqklb(yearValue,monthValue,xmmc,corporation);
        }

        if(rs!=null&&rs.size()>0){
            if(isFull>0){//分页
                rsList.setTotal(new PageInfo(rs).getTotal());
            }
            ConvertTypeUtils.zdIdToName(rs);
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





    @ApiOperation("发电详情：返回类型ApiXmJysj")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "corporation", value = "分公司", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmType", value = "项目类型", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmFag", value = "新老项目", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmxz", value = "项目现状", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkLyfgs", value = "履约分公司", dataType = "String", dataTypeClass = String.class),

            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmfdlxq")
    public R<ApiXmFdlxq> getProjectXmfdlxq(String yearValue ,String  monthValue,String corporation,String xmid,String  xmType,String xmFag,String xmqkXmxz,String xmqkLyfgs,String beginTime,String endTime)
    {

        R<ApiXmFdlxq> apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmFdlxq  rs= apiProjectInfoService.selectProjectJXmfdlxq(yearValue,monthValue,corporation, xmid,xmType,xmFag,xmqkXmxz,xmqkLyfgs,beginTime,endTime);
        ApiRs apiRs=new ApiRs();
        if(rs!=null){
            ConvertTypeUtils.formatNUll(rs,"0");
            apiResult.setCode(200);
            apiResult.setData(rs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }


    @ApiOperation("发电详情-实际收入：返回类型  ApiXmFdlxqSjsr")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "corporation", value = "分公司", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmType", value = "项目类型", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmFag", value = "新老项目", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmxz", value = "项目现状", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkLyfgs", value = "履约分公司", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmsjsr")
    public R<ApiRs> getProjectXmsjsr(String yearValue ,String  monthValue,String corporation,String xmid,String xmType,String xmFag,String xmqkXmxz,String xmqkLyfgs,String beginTime,String endTime)
    {

        R<ApiRs> apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmFdlxq  rs= apiProjectInfoService.selectProjectJXmfdlxq(yearValue,monthValue,corporation, xmid,xmType,xmFag,xmqkXmxz,xmqkLyfgs, beginTime, endTime);
        ApiRs apiRs=new ApiRs();
        if(rs!=null){
            ApiXmFdlxqSjsr rss= FastJsonUtils.getJsonToBean(rs,ApiXmFdlxqSjsr.class);
            ConvertTypeUtils.convertResult(rss,apiRs);
            apiResult.setCode(200);
            apiResult.setData(apiRs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }



    @ApiOperation("发电详情-实际支出：返回类型ApiXmFdlxqSjzc")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "corporation", value = "分公司", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmType", value = "项目类型", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmFag", value = "新老项目", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkXmxz", value = "项目现状", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmqkLyfgs", value = "履约分公司", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmfdlxqsjzc")
    public R<ApiRs> getProjectXmsjzc(String yearValue ,String  monthValue,String corporation,String xmid,String xmType,String xmFag,String xmqkXmxz,String xmqkLyfgs,String beginTime,String endTime)
    {

        R<ApiRs> apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmFdlxq  rs= apiProjectInfoService.selectProjectJXmfdlxq(yearValue,monthValue,corporation, xmid,xmType,xmFag,xmqkXmxz,xmqkLyfgs,beginTime,endTime);
        ApiRs apiRs=new ApiRs();
        if(rs!=null){
            ApiXmFdlxqSjzc rss= FastJsonUtils.getJsonToBean(rs,ApiXmFdlxqSjzc.class);
            ConvertTypeUtils.convertResult(rss,apiRs);
            apiResult.setCode(200);
            apiResult.setData(apiRs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }



}
