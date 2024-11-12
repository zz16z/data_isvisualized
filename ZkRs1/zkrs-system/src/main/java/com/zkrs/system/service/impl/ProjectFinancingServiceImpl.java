package com.zkrs.system.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.common.core.redis.RedisCache;
import com.zkrs.common.utils.DateUtils;
import com.zkrs.common.utils.StringUtils;
import com.zkrs.system.domain.*;
import com.zkrs.system.domain.dto.ProjectFinancingDto;
import com.zkrs.system.mapper.*;
import com.zkrs.system.tool.FastJsonUtils;
import com.zkrs.system.tool.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import com.zkrs.system.service.IProjectFinancingService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 项目融资Service业务层处理
 *
 * @author zkrs
 * @date 2022-07-01
 */
@Service
public class ProjectFinancingServiceImpl implements IProjectFinancingService
{
    @Autowired
    private ProjectFinancingMapper projectFinancingMapper;

    @Autowired
    private ActualCashMapper actualCashMapper;//资金实际

    @Autowired
    private ExpectCashMapper expectCashMapper;//资金预计
    @Autowired
    private FinancingDateMapper financingDateMapper;
    @Autowired
    private ElectricityStatisticsMapper electricityStatisticsMapper; //电费

    @Autowired
    private ProjectFinancingLoanMapper projectFinancingLoanMapper;

    @Autowired
    private  ProjectFinancingRepaymentMapper projectFinancingRepaymentMapper;

    @Autowired
    RedisCache redisCache;

    /**
     * 查询项目融资
     *
     * @param xmrzId 项目融资主键
     * @return 项目融资
     */
    @Override
    public ProjectFinancing selectProjectFinancingByXmrzId(Long xmrzId)
    {
        return projectFinancingMapper.selectProjectFinancingByXmrzId(xmrzId);
    }

    /**
     * 查询项目融资列表
     *
     * @param projectFinancing 项目融资
     * @return 项目融资
     */
    @Override
    public List<ProjectFinancing> selectProjectFinancingList(ProjectFinancing projectFinancing)
    {
        return projectFinancingMapper.selectProjectFinancingList(projectFinancing);
    }

    /**
     * 查询项目融资列表
     *
     * @param projectFinancing 项目融资
     * @return 项目融资
     */
    @Override
    public List<ProjectFinancing> selectProjectFinancingExport(ProjectFinancing projectFinancing)
    {
        return projectFinancingMapper.selectProjectFinancingExport(projectFinancing);
    }

    /**
     * 新增项目融资
     *
     * @param projectFinancing 项目融资
     * @return 结果
     */
    @Override
    public int insertProjectFinancing(ProjectFinancing projectFinancing)
    {
        projectFinancing.setCreateTime(DateUtils.getNowDate());
        return projectFinancingMapper.insertProjectFinancing(projectFinancing);
    }

    /**
     * 修改项目融资
     *
     * @param projectFinancing 项目融资
     * @return 结果
     */
    @Override
    public int updateProjectFinancing(ProjectFinancing projectFinancing)
    {
        projectFinancing.setUpdateTime(DateUtils.getNowDate());
        return projectFinancingMapper.updateProjectFinancing(projectFinancing);
    }

    /**
     * 批量删除项目融资
     *
     * @param xmrzIds 需要删除的项目融资主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteProjectFinancingByXmrzIds(Long[] xmrzIds)
    {

        //financingDateMapper.deleteFinancingDateByIdsJl(xmrzIds);
        //financingDateMapper.deleteFinancingDateByIdsJlHK(xmrzIds);
        projectFinancingLoanMapper.deleteProjectFinancingLoan(xmrzIds);
        projectFinancingRepaymentMapper.deleteProjectFinancingRepayment(xmrzIds);
        return projectFinancingMapper.deleteProjectFinancingByXmrzIds(xmrzIds);
    }

    /**
     * 删除项目融资信息
     *
     * @param xmrzId 项目融资主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteProjectFinancingByXmrzId(Long xmrzId)
    {
        financingDateMapper.deleteFinancingDateByIdJl(xmrzId);
        financingDateMapper.deleteFinancingDateByIdJlHk(xmrzId);
        return projectFinancingMapper.deleteProjectFinancingByXmrzId(xmrzId);
    }


    /**
     * 新增项目融资批量
     *
     * @param projectFinancingLits 项目融资
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertProjectFinancingAll(List<ProjectFinancingDto>  projectFinancingLits) {
        return projectFinancingMapper.insertProjectFinancingAll(projectFinancingLits);
    }


    /**
     * 新增项目融资批量
     *
     * @param listData 电量统计导入
     * @return 结果AjaxResult
     */

    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBox(List<ProjectFinancingVerify> listData, String xmid){
        AjaxResult rs=AjaxResult.success("导入失败");
        //验证数据
        ImportError errorInfo=new ImportError() ;
        if(verification(listData,errorInfo,xmid)){
            String   projectInfoList= FastJsonUtils.Object2String(listData);
            List<ProjectFinancing> listItem= FastJsonUtils.getJsonToList(projectInfoList,ProjectFinancing.class);
            projectFinancingMapper.importBox(listItem);
            rs=AjaxResult.success("数据导入成功");
        }else{
            rs = AjaxResult.success("序号" + errorInfo.getProjectName() + "下:" + errorInfo.getErrorInfo());
        }
        return   rs;
    }

    @Override
    public List<ExportFinancingInfo> selectProjectInfoList(ProjectInfo projectInfo) {
        List<ExportFinancingDate> exportFinancingDates = projectFinancingMapper.selectProjectInfoList(projectInfo);
        List<String> list  =   new ArrayList<String>();
        List<ExportFinancingInfo> exportList = new ArrayList<>();
        if(exportFinancingDates.size()>0){
            for (ExportFinancingDate exportFinancingDate : exportFinancingDates) {
                if(exportFinancingDate.getTimes()!=null && exportFinancingDate.getTimes()!=""){
                    String year = exportFinancingDate.getTimes().substring(0, 4);
                    String key = exportFinancingDate.getFinancingId()+"-"+year;
                    list.add(key);
                    //  map.put(exportFinancingDate.getFinancingId(),exportFinancingDate.getTimes().substring(0,4));
                }
            }
            Set set = new HashSet();
            List<String> newList = new  ArrayList();
            for (String key:list) {
                if(set.add(key)){
                    newList.add(key);
                }
            }
            if(newList.size()>0){
                for(int i= 0;i<newList.size();i++){
                    ExportFinancingInfo exportFinancingInfo = new ExportFinancingInfo();


                    for (ExportFinancingDate date : exportFinancingDates) {
                        if(date.getTimes()!=null && date.getTimes()!=""){
                            String[] split = newList.get(i).split("-");
                            String rzjg = date.getFinancingId();
                            String year = date.getTimes().substring(0, 4);
                            String month = date.getTimes().substring(5, 7);
                            if(year.equals(split[1]) && rzjg.equals(split[0])){
                                if(date.getXmrzFxts()!=null && date.getXmrzFxts()!=""){
                                    exportFinancingInfo.setXmrzFxts(date.getXmrzFxts());
                                }
                                if(date.getXmqkXmmc()!=null && date.getXmqkXmmc()!=""){
                                    exportFinancingInfo.setXmqkXmmc(date.getXmqkXmmc());
                                }
                                if(date.getXmqkRzf()!=null && date.getXmqkRzf()!=""){
                                    exportFinancingInfo.setXmrzRzjg(date.getXmqkRzf());
                                }
                                if(date.getXmrzQyrq()!=null && date.getXmrzQyrq()!=""){
                                    exportFinancingInfo.setXmrzQyrq(date.getXmrzQyrq());
                                }
                                if(date.getXmqkRzjl()!=null && date.getXmqkRzjl()!=""){
                                    exportFinancingInfo.setXmrzRzfzr(date.getXmqkRzjl());
                                }
                                if(date.getXmrzRzze()!=null){
                                    exportFinancingInfo.setXmrzRzze(date.getXmrzRzze());
                                }
                                if(date.getXmrzSnirr()!=null && date.getXmrzSnirr()!=""){
                                    exportFinancingInfo.setXmrzSnirr(date.getXmrzSnirr());
                                }
                                if(date.getXmrzQzqzhirr()!=null && date.getXmrzQzqzhirr()!=""){
                                    exportFinancingInfo.setXmrzQzqzhirr(date.getXmrzQzqzhirr());
                                }
                                if(date.getXmrzYfkze()!=null){
                                    exportFinancingInfo.setXmrzYfkze(date.getXmrzYfkze());
                                }
                                if(date.getXmrzWfkze()!=null){
                                    exportFinancingInfo.setXmrzWfkze(date.getXmrzWfkze());
                                }
                                if(date.getXmrzRzqkName()!=null && date.getXmrzRzqkName()!=""){
                                    exportFinancingInfo.setXmrzRzqk(date.getXmrzRzqkName());
                                }
                                if(date.getXmrzYjxbfksj()!=null && date.getXmrzYjxbfksj()!=""){
                                    exportFinancingInfo.setXmrzYjxbfksj(date.getXmrzYjxbfksj());
                                }
                                if(date.getXmrzYjxbfk()!=null){
                                    exportFinancingInfo.setXmrzYjxbfk(date.getXmrzYjxbfk());
                                }
                                if(date.getXmrzIsxcfk()!=null){
                                    exportFinancingInfo.setXmrzIsxcfk(date.getXmrzIsxcfk());
                                }
                                if(date.getXmrzSxfsqfs()!=null){
                                    exportFinancingInfo.setXmrzSxfsqfs(date.getXmrzSxfsqfs());
                                }

                                exportFinancingInfo.setLoanYear(year);
                                BigDecimal january = null;
                                if(date.getJanuary()!=null){
                                    january = date.getJanuary();
                                }
                                if(month.equals("01")){
                                    exportFinancingInfo.setJan(january);
                                }
                                if(month.equals("02")){
                                    exportFinancingInfo.setFeb(january);
                                }
                                if(month.equals("03")){
                                    exportFinancingInfo.setMar(january);
                                }
                                if(month.equals("04")){
                                    exportFinancingInfo.setApr(january);
                                }
                                if(month.equals("05")){
                                    exportFinancingInfo.setMay(january);
                                }
                                if(month.equals("06")){
                                    exportFinancingInfo.setJun(january);
                                }
                                if(month.equals("07")){
                                    exportFinancingInfo.setJul(january);
                                }
                                if(month.equals("08")){
                                    exportFinancingInfo.setAug(january);
                                }
                                if(month.equals("09")){
                                    exportFinancingInfo.setSep(january);
                                }
                                if(month.equals("10")){
                                    exportFinancingInfo.setOct(january);
                                }
                                if(month.equals("11")){
                                    exportFinancingInfo.setNov(january);
                                }
                                if(month.equals("12")){
                                    exportFinancingInfo.setDec(january);
                                }
                            }
                        }
                    }
                    exportList.add(exportFinancingInfo);
                }
            }
        }
        return exportList;
    }

    //导入数据验证
    Boolean  verification(List<ProjectFinancingVerify> listData,ImportError errorInfo,String xmid){

        Boolean  rs=true;
        for(ProjectFinancingVerify item:listData){
            errorInfo.setProjectName(item.getXh());
            if(Valid.isNoNull(xmid)){ //项目关联id不能空
                item.setXmrzProjectId(xmid);
            }else{
                errorInfo.setErrorInfo("没有关联项目,请在对应的项目下导入");
            }
            if (Valid.isNoNull(item.getXmrzQyrq())) {
                if (!Valid.isValidDate(item.getXmrzQyrq())) {
                    errorInfo.setErrorInfo("签约日期格式不对");
                    rs = false;
                    break;
                }
            }

            if (Valid.isNoNull(item.getXmrzYjxbfksj())) {
                if (!Valid.isValidDate(item.getXmrzYjxbfksj())) {
                    errorInfo.setErrorInfo("预计下笔放款时间式不对");
                    rs = false;
                    break;
                }
            }

            if (Valid.isNoNull(item.getXmrzRzze()) && !Valid.isDouble(item.getXmrzRzze() + "", 2)) {

                errorInfo.setErrorInfo("融资总额,格式不对正确为：00.00");
                rs = false;
                break;
            }



            if (Valid.isNoNull(item.getXmrzYfkze()) && !Valid.isDouble(item.getXmrzYfkze() + "", 2)) {

                errorInfo.setErrorInfo("已放款总额,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getXmrzYjxbfk()) && !Valid.isDouble(item.getXmrzYjxbfk() + "", 2)) {

                errorInfo.setErrorInfo("预计下笔放款,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getXmrzBzj()) && !Valid.isDouble(item.getXmrzBzj() + "", 2)) {

                errorInfo.setErrorInfo("保证金,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getXmrzRzll()) && !Valid.isDouble(item.getXmrzRzll() + "", 2)) {

                errorInfo.setErrorInfo("融资利率,格式不对正确为：00.00");
                rs = false;
                break;
            }




            if (Valid.isNoNull(item.getXmrzCb()) && !Valid.isDouble(item.getXmrzCb() + "", 2)) {

                errorInfo.setErrorInfo("成本,格式不对正确为：00.00");
                rs = false;
                break;
            }



            if (Valid.isNoNull(item.getXmrzLx()) && !Valid.isDouble(item.getXmrzLx() + "", 2)) {

                errorInfo.setErrorInfo("利息,格式不对正确为：00.00");
                rs = false;
                break;
            }



            if (Valid.isNoNull(item.getXmrzSxf()) && !Valid.isDouble(item.getXmrzSxf() + "", 2)) {

                errorInfo.setErrorInfo("手续费,格式不对正确为：00.00");
                rs = false;
                break;
            }



            if (Valid.isNoNull(item.getXmrzHkbzj()) && !Valid.isDouble(item.getXmrzHkbzj() + "", 2)) {

                errorInfo.setErrorInfo("还款保证金,格式不对正确为：00.00");
                rs = false;
                break;
            }

        }

        return  rs;
    }

    @Override
    public int  organNameChek(ProjectFinancing projectFinancing){
        return  projectFinancingMapper.organNameChek(projectFinancing);
    }
}
