package com.zkrs.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.common.core.redis.RedisCache;
import com.zkrs.system.domain.*;
import com.zkrs.system.tool.FastJsonUtils;
import com.zkrs.system.tool.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zkrs.system.mapper.FinancingDateMapper;
import com.zkrs.system.service.IFinancingDateService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 融资时间Service业务层处理
 *
 * @author zkrs
 * @date 2022-07-07
 */
@Service
public class FinancingDateServiceImpl implements IFinancingDateService
{
    @Autowired
    private FinancingDateMapper financingDateMapper;

    @Autowired
    RedisCache redisCache;
    /**
     * 查询融资时间
     *
     * @param id 融资时间主键
     * @return 融资时间
     */
    @Override
    public FinancingDate selectFinancingDateById(Long id)
    {
        return financingDateMapper.selectFinancingDateById(id);
    }

    /**
     * 查询融资时间列表
     *
     * @param financingDate 融资时间
     * @return 融资时间
     */
    @Override
    public List<FinancingDate> selectFinancingDateList(FinancingDate financingDate)
    {
        return financingDateMapper.selectFinancingDateList(financingDate);
    }

    /**
     * 导出融资时间列表
     *
     * @param financingDate 融资时间
     * @return 融资时间
     */
    @Override
    public List<FinancingDate> selectFinancingDateExport(FinancingDate financingDate)
    {
        return financingDateMapper.selectFinancingDateExport(financingDate);
    }


    /**
     * 新增融资时间
     *
     * @param financingDate 融资时间
     * @return 结果
     */
    @Override
    public int insertFinancingDate(FinancingDate financingDate)
    {
        return financingDateMapper.insertFinancingDate(financingDate);
    }



    /**
     * 新增融资时间
     *
     * @param financingDateList 更新添加
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor= Exception.class)
    public AjaxResult insertFinancingDateList(List<FinancingDate> financingDateList) throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        try {
                //自验证前端传过来集合中有没有重复的月份
                List<String> dateList = new ArrayList<>();
                List<Long> idList = new ArrayList<>();
                financingDateList.forEach(item->{
                    String sDate=sdf.format(item.getTimes());
                    dateList.add(sDate);
                    idList.add(item.getId());
                });
                List<String> dateList2 = new ArrayList<>();
                for (String s : dateList) {
                    if (dateList2.contains(s)) {
                        return AjaxResult.error("月份"+s+"只能存在一条数据");
                    }
                    dateList2.add(s);
                }

                //验证数据库和传过来的集合中有没有重复的月份
                FinancingDate financingDate = new FinancingDate();
                financingDate.setFinancingId(financingDateList.get(0).getFinancingId());
                financingDate.setIsYjSj(financingDateList.get(0).getIsYjSj());
                List<FinancingDate> financingDates = financingDateMapper.selectFinancingDateList(financingDate);
                for (FinancingDate date : financingDates) {
                    String sDate=sdf.format(date.getTimes());
                    if(dateList.contains(sDate)){
                        Long id = idList.get(dateList.indexOf(sDate));
                        if(id==null){
                            return AjaxResult.error("月份"+sDate+"已存在");
                        }

                    }
                }
               financingDateMapper.insertFinancingDateList(financingDateList);
        }
        catch (Exception e){
            if(e.toString().contains("There are only 1 target objects. You either specified a wrong 'keyProperty' or encountered a driver bug like #1523")){
                return AjaxResult.success();
            }
            else {
                throw  new Exception(e);
            }
        }
        return AjaxResult.success();
    }


    /**
     * 修改融资时间
     *
     * @param financingDate 融资时间
     * @return 结果
     */
    @Override

    public int updateFinancingDate(FinancingDate financingDate)
    {
        return financingDateMapper.updateFinancingDate(financingDate);
    }

    /**
     * 批量删除融资时间
     *
     * @param ids 需要删除的融资时间主键
     * @return 结果
     */
    @Override
    public int deleteFinancingDateByIds(Long[] ids)
    {
        return financingDateMapper.deleteFinancingDateByIds(ids);
    }

    /**
     * 删除融资时间信息
     *
     * @param id 融资时间主键
     * @return 结果
     */
    @Override
    public int deleteFinancingDateById(Long id)
    {
        return financingDateMapper.deleteFinancingDateById(id);
    }



    /**
     * 融资时间导入
     *
     * @param listData 融资时间导入
     * @return 结果AjaxResult
     */
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBox(List<FinancingDateVerify> listData, String xmType  ,String  msg_id,String  isYjSj){
        AjaxResult rs=AjaxResult.success("导入失败");
        //验证数据
        ImportError errorInfo=new ImportError() ;
        if(verification(listData,errorInfo,xmType,msg_id,isYjSj)){
            String   projectInfoList= FastJsonUtils.Object2String(listData);
            List<FinancingDate> listItem= FastJsonUtils.getJsonToList(projectInfoList,FinancingDate.class);

            //自验证前端传过来集合中有没有重复的月份
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
            List<String> dateList = new ArrayList<>();
            listItem.forEach(item->{
                String sDate=sdf.format(item.getTimes());
                dateList.add(sDate);
            });
            List<String> dateList2 = new ArrayList<>();
            for (String s : dateList) {
                if (dateList2.contains(s)) {
                    rs = AjaxResult.error("月份"+s+"只能存在一条数据");
                    return rs;
                }
                dateList2.add(s);
            }
            //验证数据库和传过来的集合中有没有重复的月份
            FinancingDate financingDate = new FinancingDate();
            financingDate.setFinancingId(listData.get(0).getFinancingId());
            financingDate.setIsYjSj(listData.get(0).getIsYjSj());
            List<FinancingDate> financingDates = financingDateMapper.selectFinancingDateList(financingDate);
            for (FinancingDate date : financingDates) {
                String sDate=sdf.format(date.getTimes());
                if(dateList.contains(sDate)){
                    return AjaxResult.error("月份"+sDate+"已存在");
                }
            }
            financingDateMapper.importBox(listItem);
            rs=AjaxResult.success("数据导入成功");
        }else{
            rs = AjaxResult.success("序号" + errorInfo.getProjectName() + "下:" + errorInfo.getErrorInfo());
        }
        return   rs;


    }

    @Override
    public List<ExportExpectedFinancing> selectProjectInfoList(ProjectInfo projectInfo) {
        return financingDateMapper.selectProjectInfoList(projectInfo);
    }

    @Override
    public List<ExportExpectedTrueFinancing> selectProjectInfoListTrue(ProjectInfo projectInfo) {
        return financingDateMapper.selectProjectInfoListTrue(projectInfo);
    }

    //导入数据验证
    Boolean  verification(List<FinancingDateVerify> listData,ImportError errorInfo,String xmType,String  msg_id,String  isYjSj){
        Boolean  rs=true;
        for(FinancingDateVerify item:listData){
            item.setParagraphType(xmType);
            errorInfo.setProjectName(item.getXh());
            if (!Valid.isNoNull(isYjSj) ) {
                errorInfo.setErrorInfo("未关预计实际信息,请在对应预计实际项目下导入");
                rs = false;
                break;
            }else {
                item.setIsYjSj(isYjSj);
            }

            if (!Valid.isNoNull(msg_id) ) {
                errorInfo.setErrorInfo("未关联融资项,请在对应融资项目下导入");
                rs = false;
                break;
            }else {
                item.setFinancingId(msg_id);
            }

            if (Valid.isNoNull(item.getTimes())) {

                if (!Valid.convertResultOne1(item.getTimes(),item)) {
                    errorInfo.setErrorInfo("日期格式不对");
                    rs = false;
                    break;
                }
            }

            if (Valid.isNoNull(item.getJanuary()) && !Valid.isDouble(item.getJanuary() + "", 2)) {

                errorInfo.setErrorInfo("金额,格式不对正确为：00.00");
                rs = false;
                break;
            }



            if (Valid.isNoNull(item.getTotalLoans()) && !Valid.isDouble(item.getTotalLoans() + "", 2)) {

                errorInfo.setErrorInfo("放款合计,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getTotalWithdrawal()) && !Valid.isDouble(item.getTotalWithdrawal() + "", 2)) {

                errorInfo.setErrorInfo("提款合计,格式不对正确为：00.00");
                rs = false;
                break;
            }


            if (Valid.isNoNull(item.getTotalRepayment()) && !Valid.isDouble(item.getTotalRepayment() + "", 2)) {

                errorInfo.setErrorInfo("还款合计,格式不对正确为：00.00");
                rs = false;
                break;
            }


        }

        return  rs;
    }

}
