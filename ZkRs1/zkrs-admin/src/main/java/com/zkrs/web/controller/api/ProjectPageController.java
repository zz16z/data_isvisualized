package com.zkrs.web.controller.api;


import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zkrs.common.core.domain.R;
import com.zkrs.system.domain.api.*;
import com.zkrs.system.section.ApiResultsVo;
import com.zkrs.system.service.api.IApiProjectFinancingService;
import com.zkrs.system.service.api.IApiProjectInfoService;
import com.zkrs.system.tool.ConvertTypeUtils;
import com.zkrs.system.tool.FastJsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.zkrs.common.utils.PageUtils.startPage;

@Api("项目管理")
@RestController
@RequestMapping("/api/home/projectManagement")
public class ProjectPageController {
    @Autowired
    IApiProjectFinancingService apiFinancingServiceService;




    @ApiOperation("项目管理：返回类型ApiXmXmgl")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "aggregation", value = "分页大小", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmFag", value = "项目标记：0老项目、1新项目", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "isFull", value = "0表示查询全部，1表示分页查询(需要分页参数)", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "xmqkQy", value = "区域", dataType = "String", dataTypeClass = String.class)
    })
    @PostMapping("/projectXmgl")
    public R<ApiXmPublicList> getProjectXmgl(ApiXmXmgl apiXmXmgl)
    {
        R<ApiXmPublicList> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmXmgl>  rs= null;
        ApiXmPublicList  rsList=new ApiXmPublicList();
        int isFull= apiXmXmgl.getIsFull();
        if(isFull==0){ //查询全部
            rs= apiFinancingServiceService.selectgetProjectXmgl(apiXmXmgl);

        }else{ //分页
            startPage();
            rs= apiFinancingServiceService.selectgetProjectXmgl(apiXmXmgl);

        }
        ConvertTypeUtils.zdIdToName(rs);
        if(rs!=null&&rs.size()>0){
            if(isFull>0){//分页
                rsList.setTotal(new PageInfo(rs).getTotal());
            }
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

/////////////////////////项目管理页面的详情页面///////////////////////////////////////////////////


    @ApiOperation("预计情况(收入、支出一个接口)：返回类型预计详情：ApiXmYjqkxqyjqk、预计预计收入：ApiXmYjqkxqyjsr、预计预计支出：ApiXmYjqkxqyjzc")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "selectType", value = "(参数必传)0预计情况、1预计输入、2预计支出", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmyjqkxq")
    public R<JSONObject> getProjectXmyjqkxq(String yearValue , String  monthValue, String corporation, String xmid, String selectType,String beginTime,String endTime)
    {

        R<JSONObject> apiResult = new R<>();
        JSONObject rss= new JSONObject();
        apiResult.setCode(-1);
        ApiXmYjqkxq  rs= apiFinancingServiceService.selectProjectXmyjqkxq(yearValue,monthValue,corporation,xmid,beginTime,endTime);
        ApiRs apiRs=new ApiRs();
        ApiXmYjqkxqyjqk  yjqk ;
        ApiXmYjqkxqyjsr yjsr;
        ApiXmYjqkxqyjzc yjzc;
        if(rs!=null){
            if("0".equals(selectType)){
                yjqk = FastJsonUtils.getJsonToBean(rs,ApiXmYjqkxqyjqk.class);
                ConvertTypeUtils.convertResultOne(yjqk,apiRs);
                yjqk.setData(apiRs);
                rss=FastJsonUtils.Object2json(yjqk);
            }
            if("1".equals(selectType)){
                yjsr  = FastJsonUtils.getJsonToBean(rs,ApiXmYjqkxqyjsr.class);
                ConvertTypeUtils.convertResultOne(yjsr,apiRs);
                yjsr.setData(apiRs);
                rss=FastJsonUtils.Object2json(yjsr);
            }
            if("2".equals(selectType)){
                yjzc  = FastJsonUtils.getJsonToBean(rs,ApiXmYjqkxqyjzc.class);
                ConvertTypeUtils.convertResultOne(yjzc,apiRs);
                yjzc.setData(apiRs);
                rss=FastJsonUtils.Object2json(yjzc);
            }
            apiResult.setCode(200);
            apiResult.setData(rss);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }



    @ApiOperation("实际情况(收入、支出一个接口)：返回类型收入：ApiXmSjqkxqsr、支出：ApiXmSjqkxqzc")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "selectType", value = "(参数必传)0实际收入、1实际支出", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmsjqkxq")
    public R<JSONObject> getProjectXmsjqkxq(String yearValue ,String  monthValue,String corporation,String xmid,String selectType,String beginTime,String endTime)
    {

        R<JSONObject> apiResult = new R<>();
        apiResult.setCode(-1);
        JSONObject rss=new JSONObject();
        ApiXmSjqkxqsr  jqkxqsr;
        ApiXmSjqkxqzc  jqkxqzc;
        ApiXmSjqkxq  rs= apiFinancingServiceService.selectProjectXmsjqkxq(yearValue,monthValue,corporation,xmid,beginTime,endTime);
        ApiRs apiRs=new ApiRs();
        if(rs!=null){
            if("0".equals(selectType)){
                jqkxqsr = FastJsonUtils.getJsonToBean(rs,ApiXmSjqkxqsr.class);
                ConvertTypeUtils.convertResultOne(jqkxqsr,apiRs);
                jqkxqsr.setXjlsjqkYfwsk(jqkxqsr.getXjlsjqkYsye());
                jqkxqsr.setData(apiRs);
                rss=FastJsonUtils.Object2json(jqkxqsr);
            }
            if("1".equals(selectType)){
                jqkxqzc  = FastJsonUtils.getJsonToBean(rs,ApiXmSjqkxqzc.class);
                ConvertTypeUtils.convertResultOne(jqkxqzc,apiRs);
                jqkxqzc.setData(apiRs);
                rss=FastJsonUtils.Object2json(jqkxqzc);
            }
            apiResult.setCode(200);
            apiResult.setData(rss);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }


    //电量统计
    @ApiOperation("电量统计(电量收入一个接口)：返回类型电量统计:ApiXmDltjqkdltj、ApiXmDltjqkdftj")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "selectType", value = "(参数必传)0电量统计、1电费统计", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmDltj")
    public R<JSONObject> getProjectXmDltj(String yearValue ,String  monthValue,String corporation,String xmid,String  selectType,String beginTime,String endTime)
    {

        R<JSONObject> apiResult = new R<>();
        JSONObject  rss=new  JSONObject();
        apiResult.setCode(-1);
        ApiXmDltjqk  rs= apiFinancingServiceService.selectProjectXmDltj(yearValue,monthValue,corporation,xmid,beginTime,endTime);
        ApiRs apiRs=new ApiRs();

        ApiXmDltjqkdltj  dltjqkdltj;
        ApiXmDltjqkdftj   dltjqkdftj;
        if(rs!=null){
            if("0".equals(selectType)){
                dltjqkdltj  = FastJsonUtils.getJsonToBean(rs,ApiXmDltjqkdltj.class);
                ConvertTypeUtils.convertResultOne(dltjqkdltj,apiRs);
                dltjqkdltj.setData(apiRs);
                rss=FastJsonUtils.Object2json(dltjqkdltj);
            }
            if("1".equals(selectType)){
                dltjqkdftj = FastJsonUtils.getJsonToBean(rs,ApiXmDltjqkdftj.class);
                ConvertTypeUtils.convertResultOne(dltjqkdftj,apiRs);
                dltjqkdftj.setData(apiRs);
                rss=FastJsonUtils.Object2json(dltjqkdftj);
            }
            apiResult.setCode(200);
            apiResult.setData(rss);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }



    @ApiOperation("施工情况(风电)：返回类型ApiXmYjqkxq")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmTypeId", value = "项目类型判断：0风电、1光伏", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmGsqk")
    public R<ApiXmSgqk> getProjectXmGsqk(String yearValue ,String  monthValue,String corporation,String xmid,String xmTypeId)
    {

        R<ApiXmSgqk> apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmSgqk  rs= apiFinancingServiceService.selectProjectXmGsqk(yearValue,monthValue,corporation,xmid,xmTypeId);
        ApiRs apiRs=new ApiRs();
        if(rs!=null){
            ConvertTypeUtils.convertResultOne(rs,apiRs);
            rs.setRs(apiRs);
            apiResult.setCode(200);
            apiResult.setData(rs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }

    @ApiOperation("施工情况(光伏)：返回类型ApiXmYjqkxq")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmTypeId", value = "项目类型判断：0风电、1光伏", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmGsqkGf")
    public R<ApiXmSgqkGf> getProjectXmGsqkGh(String yearValue ,String  monthValue,String corporation,String xmid,String xmTypeId)
    {

        R<ApiXmSgqkGf> apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmSgqkGf  rs= apiFinancingServiceService.selectProjectXmGsqkGh(yearValue,monthValue,corporation,xmid,xmTypeId);
        ApiRs apiRs=new ApiRs();
        if(rs!=null){
            ConvertTypeUtils.convertResultOne(rs,apiRs);
            rs.setRs(apiRs);
            apiResult.setCode(200);
            apiResult.setData(rs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }

    @ApiOperation("项目融资(按照当年统计)：返回类型ApiXmYjqkxq")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id(必传)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmRztji")
    public R<ApiXmRztjRs> getProjectXmRztji(String yearValue ,String  monthValue,String corporation,String xmid,String beginTime,String endTime)
    {

        R<ApiXmRztjRs> apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmRztj>  rs= apiFinancingServiceService.selectprojectXmRztjik(yearValue,monthValue,corporation,xmid,beginTime,endTime);
        ApiXmRztjRs apiRs=new ApiXmRztjRs();
        if(rs!=null&&rs.size()>0){
            ConvertTypeUtils.convertResultKeyValue(rs,apiRs);
            apiResult.setCode(200);
            apiResult.setData(apiRs);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }


    @ApiOperation("项目现金流(标题数据)：返回类型ApiXmXjl")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年(如果传null表示查询全部)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmXjl")
    public R<ApiXmXjl> getProjectXmXjl(String yearValue ,String  monthValue,String corporation,String xmid,String beginTime,String endTime)
    {

        R<ApiXmXjl> apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmXjl  rs= apiFinancingServiceService.selectprojectXmXjl(yearValue,monthValue,corporation,xmid,beginTime,endTime);
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


    @ApiOperation("项目现金流(年统计-总计)： 返回类型<List<ApiRs> 顺序：预计总收入、实际总收入、预计总支出、实际总支出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年(如果传null表示查询全部)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmNf")
    public R<List<ApiRs> > getProjectXmNf(String yearValue ,String  monthValue,String corporation,String xmid)
    {

        R<List<ApiRs> > apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmXjlNf>  rs= apiFinancingServiceService.selectprojectXmNf(yearValue,monthValue,corporation,xmid);
        List<ApiRs> apiRsList=new ArrayList<>();
        if(rs!=null&&rs.size()>0){
            ConvertTypeUtils.convertResultStatistics(rs,apiRsList,ApiXmXjlNf.class);
            apiResult.setCode(200);
            apiResult.setData(apiRsList);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }

    @ApiOperation("项目现金流(月统计)：返回类型<List<ApiRs> 顺序：预计总收入、实际总收入、预计总支出、实际总支出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年(必传)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmYf")
    public R<List<ApiRs> > getProjectXmYf(String yearValue ,String  monthValue,String corporation,String xmid,String beginTime,String endTime)
    {

        R<List<ApiRs> > apiResult = new R<>();
        apiResult.setCode(-1);
        List<ApiXmXjlNf>  rs= apiFinancingServiceService.selectprojectXmYf(yearValue,monthValue,corporation,xmid,beginTime,endTime);
        List<ApiRs> apiRsList=new ArrayList<>();
        List<ApiRs> apiRsListMoth=new ArrayList<>();
        if(rs!=null&&rs.size()>0){
            ConvertTypeUtils.convertResultStatistics(rs,apiRsList,ApiXmXjlNf.class);
            ConvertTypeUtils.convertResultAddMoth(apiRsList,apiRsListMoth);
            apiResult.setCode(200);
            apiResult.setData(apiRsListMoth);
            apiResult.setMsg("操作成功");
        }else{
        apiResult.setCode(200);
        apiResult.setMsg("无数据");
    }
        return  apiResult ;
    }



    @ApiOperation("融资详情(标题数据)：返回类型ApiXmRzxq")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id(必传)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmRzxq")
    public R<ApiXmRzxq > getProjectXmRzxq(String yearValue ,String  monthValue,String corporation,String xmid,String beginTime,String endTime)
    {
        R<ApiXmRzxq > apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmRzxq rs= apiFinancingServiceService.selectprojectXmRzxq(yearValue,monthValue,corporation,xmid,beginTime,endTime);

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




    @ApiOperation("融资详情(资金放款情况)：返回类型ApiXmRzxqFkqk")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id(必传)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmRzxqFkqk")
    public R<ApiXmRzxqFkqk > getProjectXmRzxqFkqk(String yearValue ,String  monthValue,String corporation,String xmid,String beginTime,String endTime)
    {
        R<ApiXmRzxqFkqk > apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmRzxqFkqk rs= apiFinancingServiceService.selectprojectXmRzxqFkqk(yearValue,monthValue,corporation,xmid,beginTime,endTime);

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




    @ApiOperation("融资详情(资金还款情况)：返回类型ApiXmRzxqHkqk(ApiXmRzxqHkqk)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id(必传)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmRzxqHkqk")
    public R<ApiXmRzxqHkqk > getProjectXmRzxqHkqk(String yearValue ,String  monthValue,String corporation,String xmid,String beginTime,String endTime)
    {
        R<ApiXmRzxqHkqk > apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmRzxqHkqkItem rs= apiFinancingServiceService.selectprojectXmRzxqHkqk(yearValue,monthValue,corporation,xmid,beginTime,endTime);
        ApiRs apiRs=new ApiRs();
        if(rs!=null){
            ApiXmRzxqHkqk rsobj=new  ApiXmRzxqHkqk();
            ConvertTypeUtils.convertResultOne(rs,apiRs);
            rsobj.setTbData(apiRs);
            rsobj.setItem(rs);
            apiResult.setCode(200);
            apiResult.setData(rsobj);
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }


    @ApiOperation("项目管理标题数据：返回类型ApiXmrzbtsj")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearValue", value = "年", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xmid", value = "项目id(必传)", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "monthValue", value = "月", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/projectXmglbtsj")
    public R<ApiXmrzbtsj > getProjectXmglbtsj(String yearValue ,String  monthValue,String corporation,String xmid)
    {
        R<ApiXmrzbtsj > apiResult = new R<>();
        apiResult.setCode(-1);
        ApiXmrzbtsj rs= apiFinancingServiceService.selectprojectXmglbtsj(yearValue,monthValue,corporation,xmid);
        ApiRs apiRs=new ApiRs();
        List<ApiXmrzbtsj> list=new ArrayList<>();
        if(rs!=null){
            list.add(rs);
            ConvertTypeUtils.zdIdToName(list);
            apiResult.setCode(200);
            apiResult.setData(list.get(0));
            apiResult.setMsg("操作成功");
        }else{
            apiResult.setCode(200);
            apiResult.setMsg("无数据");
        }
        return  apiResult ;
    }


}
