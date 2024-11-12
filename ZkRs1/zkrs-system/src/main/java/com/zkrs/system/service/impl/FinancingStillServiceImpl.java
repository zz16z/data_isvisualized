package com.zkrs.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.common.utils.DateUtils;
import com.zkrs.system.domain.*;
import com.zkrs.system.tool.FastJsonUtils;
import com.zkrs.system.tool.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zkrs.system.mapper.FinancingStillMapper;
import com.zkrs.system.service.IFinancingStillService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 还款情况Service业务层处理
 * 
 * @author zkrs
 * @date 2022-07-23
 */
@Service
public class FinancingStillServiceImpl implements IFinancingStillService 
{
    @Autowired
    private FinancingStillMapper financingStillMapper;

    /**
     * 查询还款情况
     * 
     * @param id 还款情况主键
     * @return 还款情况
     */
    @Override
    public FinancingStill selectFinancingStillById(Long id)
    {
        return financingStillMapper.selectFinancingStillById(id);
    }

    /**
     * 查询还款情况列表
     * 
     * @param financingStill 还款情况
     * @return 还款情况
     */
    @Override
    public List<FinancingStill> selectFinancingStillList(FinancingStill financingStill)
    {
        return financingStillMapper.selectFinancingStillList(financingStill);
    }

    /**
     * 新增还款情况
     * 
     * @param financingStill 还款情况
     * @return 结果
     */
    @Override
    public int insertFinancingStill(FinancingStill financingStill)
    {
        financingStill.setCreateTime(DateUtils.getNowDate());
        return financingStillMapper.insertFinancingStill(financingStill);
    }

    /**
     * 修改还款情况
     * 
     * @param financingStill 还款情况
     * @return 结果
     */
    @Override
    public int updateFinancingStill(FinancingStill financingStill)
    {
        financingStill.setUpdateTime(DateUtils.getNowDate());
        return financingStillMapper.updateFinancingStill(financingStill);
    }

    /**
     * 批量删除还款情况
     * 
     * @param ids 需要删除的还款情况主键
     * @return 结果
     */
    @Override
    public int deleteFinancingStillByIds(Long[] ids)
    {
        return financingStillMapper.deleteFinancingStillByIds(ids);
    }

    /**
     * 删除还款情况信息
     * 
     * @param id 还款情况主键
     * @return 结果
     */
    @Override
    public int deleteFinancingStillById(Long id)
    {
        return financingStillMapper.deleteFinancingStillById(id);
    }

    @Override
    @Transactional(rollbackFor= Exception.class)
    public AjaxResult insertFinancingDateList(List<FinancingStill> financingStillList) throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        try {
            //自验证前端传过来集合中有没有重复的月份
            List<String> dateList = new ArrayList<>();
            List<Long> idList = new ArrayList<>();
            financingStillList.forEach(item->{
                String sDate=sdf.format(item.getFinancingRq());
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
            FinancingStill financingStill = new FinancingStill();
            financingStill.setFinancingId(financingStillList.get(0).getFinancingId());
            List<FinancingStill> financingStills = financingStillMapper.selectFinancingStillList(financingStill);
            for (FinancingStill date : financingStills) {
                String sDate=sdf.format(date.getFinancingRq());
                if(dateList.contains(sDate)){
                    Long aLong = idList.get(dateList.indexOf(sDate));
                    if(aLong==null){
                        return AjaxResult.error("月份"+sDate+"已存在");
                    }

                }
            }
             financingStillMapper.insertFinancingDateList1(financingStillList);
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
     * 融资时间导入
     *
     * @param listData 融资时间导入
     * @return 结果AjaxResult
     */
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBox(List<FinancingStillVerify> listData, String xmType  , String  msg_id,String  isYjSj){
        AjaxResult rs=AjaxResult.success("导入失败");
        //验证数据
        ImportError errorInfo=new ImportError() ;
        if(verification(listData,errorInfo,xmType,msg_id,isYjSj)){
            String   projectInfoList= FastJsonUtils.Object2String(listData);
            List<FinancingStill> listItem= FastJsonUtils.getJsonToList(projectInfoList,FinancingStill.class);
            //自验证前端传过来集合中有没有重复的月份
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
            List<String> dateList = new ArrayList<>();
            listItem.forEach(item->{
                String sDate=sdf.format(item.getFinancingRq());
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
            FinancingStill financingStill = new FinancingStill();
            financingStill.setFinancingId(listItem.get(0).getFinancingId());
            List<FinancingStill> financingStills = financingStillMapper.selectFinancingStillList(financingStill);
            for (FinancingStill date : financingStills) {
                String sDate=sdf.format(date.getFinancingRq());
                if(dateList.contains(sDate)){
                    return AjaxResult.error("月份"+sDate+"已存在");
                }
            }

            financingStillMapper.importBox(listItem);
            rs=AjaxResult.success("数据导入成功");
        }else{
            rs = AjaxResult.success("序号" + errorInfo.getProjectName() + "下:" + errorInfo.getErrorInfo());
        }
        return   rs;


    }
    //导入数据验证
    Boolean  verification(List<FinancingStillVerify> listData,ImportError errorInfo,String xmType,String  msg_id,String  isYjSj){
        Boolean  rs=true;
        for(FinancingStillVerify item:listData){
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

            if (Valid.isNoNull(item.getFinancingLx()) && !Valid.isDouble(item.getFinancingLx() , 2)) {

                errorInfo.setErrorInfo("利息,格式不对正确为：00,00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getFinancingSxf()) && !Valid.isDouble(item.getFinancingSxf() , 2)) {

                errorInfo.setErrorInfo("手续费,格式不对正确为：00,00");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getFinancingBzj()) && !Valid.isDouble(item.getFinancingBzj() , 2)) {

                errorInfo.setErrorInfo("还款保证金,格式不对正确为：00,00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getFinancingBzj()) && !Valid.isDouble(item.getFinancingBzj() , 2)) {

                errorInfo.setErrorInfo("成本,格式不对正确为：00,00");
                rs = false;
                break;
            }

//
//            if (Valid.isNoNull(item.getFinancingRq())) {
//                if (!Valid.isValidMonthDate(item.getFinancingRq())) {
//                    errorInfo.setErrorInfo("日期格式不对:正确YYYY-MM-DD");
//                    rs = false;
//                    break;
//                }
//            }


            if (Valid.isNoNull(item.getFinancingRq())) {
                if (!Valid.convertResultOne1(item.getFinancingRq(),item)) {
                    errorInfo.setErrorInfo("日期格式不对");
                    rs = false;
                    break;
                }
            }

            if (Valid.isNoNull(item.getFinancingBysjhk()) && !Valid.isDouble(item.getFinancingBysjhk() , 2)) {

                errorInfo.setErrorInfo("款合计,格式不对正确为：00,00");
                rs = false;
                break;
            }



        }

        return  rs;
    }
}
