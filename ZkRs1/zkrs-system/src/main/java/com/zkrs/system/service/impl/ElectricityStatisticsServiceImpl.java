package com.zkrs.system.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.common.core.redis.RedisCache;
import com.zkrs.common.utils.DateUtils;
import com.zkrs.common.utils.StringUtils;
import com.zkrs.system.domain.*;
import com.zkrs.system.enumeration.CommonlyCache;
import com.zkrs.system.tool.FastJsonUtils;
import com.zkrs.system.tool.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import com.zkrs.system.mapper.ElectricityStatisticsMapper;
import com.zkrs.system.service.IElectricityStatisticsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 电量统计Service业务层处理
 *
 * @author zkrs
 * @date 2022-07-01
 */
@Service
public class ElectricityStatisticsServiceImpl implements IElectricityStatisticsService
{
    @Autowired
    private ElectricityStatisticsMapper electricityStatisticsMapper;
    @Autowired
    RedisCache redisCache;

    /**
     * 查询电量统计
     *
     * @param dltjId 电量统计主键
     * @return 电量统计
     */
    @Override
    public ElectricityStatistics selectElectricityStatisticsByDltjId(Long dltjId)
    {
        return electricityStatisticsMapper.selectElectricityStatisticsByDltjId(dltjId);
    }

    /**
     * 查询电量统计列表
     *
     * @param electricityStatistics 电量统计
     * @return 电量统计
     */
    @Override
    public List<ElectricityStatistics> selectElectricityStatisticsList(ElectricityStatistics electricityStatistics)
    {
        return electricityStatisticsMapper.selectElectricityStatisticsList(electricityStatistics);
    }
    /**
     * 查询电量统计导出列表
     *
     * @param electricityStatistics 电量统计导出
     * @return 电量统计
     */
    @Override
    public List<ElectricityStatistics> selectElectricityStatisticsExport(ElectricityStatistics electricityStatistics)
    {
        return electricityStatisticsMapper.selectElectricityStatisticsExport(electricityStatistics);
    }




    /**
     * 新增电量统计
     *
     * @param electricityStatistics 电量统计
     * @return 结果
     */
    @Override
    public int insertElectricityStatistics(ElectricityStatistics electricityStatistics)
    {
        electricityStatistics.setCreateTime(DateUtils.getNowDate());
        return electricityStatisticsMapper.insertElectricityStatistics(electricityStatistics);
    }

    /**
     * 修改电量统计
     *
     * @param electricityStatistics 电量统计
     * @return 结果
     */
    @Override
    public int updateElectricityStatistics(ElectricityStatistics electricityStatistics)
    {
        electricityStatistics.setUpdateTime(DateUtils.getNowDate());
        return electricityStatisticsMapper.updateElectricityStatistics(electricityStatistics);
    }

    /**
     * 批量删除电量统计
     *
     * @param dltjIds 需要删除的电量统计主键
     * @return 结果
     */
    @Override
    public int deleteElectricityStatisticsByDltjIds(Long[] dltjIds)
    {
        return electricityStatisticsMapper.deleteElectricityStatisticsByDltjIds(dltjIds);
    }

    /**
     * 删除电量统计信息
     *
     * @param dltjId 电量统计主键
     * @return 结果
     */
    @Override
    public int deleteElectricityStatisticsByDltjId(Long dltjId)
    {
        return electricityStatisticsMapper.deleteElectricityStatisticsByDltjId(dltjId);
    }


    /**
     * 电量统计导入
     *
     * @param listData 电量统计导入
     * @return 结果AjaxResult
     */
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBox(List<ElectricityStatisticsVerify> listData, String xmid){
        AjaxResult rs=AjaxResult.success("导入失败");
        //验证数据
        ImportError errorInfo=new ImportError() ;
        if(verification(listData,errorInfo,xmid)){
            String   projectInfoList= FastJsonUtils.Object2String(listData);
            List<ElectricityStatistics> listItem= FastJsonUtils.getJsonToList(projectInfoList,ElectricityStatistics.class);
            electricityStatisticsMapper.importBox(listItem);
            rs=AjaxResult.success("数据导入成功");
        }else{
            rs = AjaxResult.success("序号" + errorInfo.getProjectName() + "下:" + errorInfo.getErrorInfo());
        }
        return   rs;
    }

    @Override
    public List<ExportElectricity> selectProjectInfoList(ProjectInfo projectInfo) {
        return electricityStatisticsMapper.selectProjectInfoList(projectInfo);
    }

    //导入数据验证
    Boolean  verification(List<ElectricityStatisticsVerify> listData,ImportError errorInfo,String xmid){

        Boolean  rs=true;
        for(ElectricityStatisticsVerify item:listData){
            errorInfo.setProjectName(item.getXh());
            if(Valid.isNoNull(xmid)){ //项目关联id不能空
                item.setDltjProjectId(xmid);
            }else{
                errorInfo.setErrorInfo("没有关联项目,请在对应项目下导入");
            }

            if (Valid.isNoNull(item.getDltjRq())) {
                if (!Valid.isValidDate(item.getDltjRq())) {
                    errorInfo.setErrorInfo("日期格式不对");
                    rs = false;
                    break;
                }
            }


            if (Valid.isNoNull(item.getDltjSyjsdfyssj()) && !Valid.isDouble(item.getDltjSyjsdfyssj() + "", 2)) {

                errorInfo.setErrorInfo("上月结算电费应收金额,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getDltjLjwdzdf()) && !Valid.isDouble(item.getDltjLjwdzdf() + "", 2)) {

                errorInfo.setErrorInfo("累计未到账电费,格式不对正确为：00.00");
                rs = false;
                break;
            }


            if (Valid.isNoNull(item.getDltjGdgchk()) && !Valid.isDouble(item.getDltjGdgchk() + "", 2)) {

                errorInfo.setErrorInfo("贵电工程回款,格式不对正确为：00.00");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getDltjDfsy()) && !Valid.isDouble(item.getDltjDfsy() + "", 2)) {

                errorInfo.setErrorInfo("电费收益,格式不对正确为：00.00");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getDltjBt()) && !Valid.isDouble(item.getDltjBt() + "", 2)) {

                errorInfo.setErrorInfo("补贴,格式不对正确为：00.00");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getDltjDljsljysje()) && !Valid.isDouble(item.getDltjDljsljysje() + "", 2)) {

                errorInfo.setErrorInfo("电费结算累计应收金额,格式不对正确为：00.00");
                rs = false;
                break;
            }


            if (Valid.isNoNull(item.getDltjBysrhj()) && !Valid.isDouble(item.getDltjBysrhj() + "", 2)) {

                errorInfo.setErrorInfo("本月收入合计,格式不对正确为：00.00");
                rs = false;
                break;
            }


            if (Valid.isNoNull(item.getDltjXwdf()) && !Valid.isDouble(item.getDltjXwdf() + "", 2)) {

                errorInfo.setErrorInfo("下网电费,格式不对正确为：00.00");
                rs = false;
                break;
            }


            if (Valid.isNoNull(item.getDltjRggz()) && !Valid.isDouble(item.getDltjRggz() + "", 2)) {

                errorInfo.setErrorInfo("人工工资,格式不对正确为：00.00");
                rs = false;
                break;
            }


            if (Valid.isNoNull(item.getDltjWjbljqt()) && !Valid.isDouble(item.getDltjWjbljqt() + "", 2)) {

                errorInfo.setErrorInfo("文件办理及其他,格式不对正确为：00.00");
                rs = false;
                break;
            }


            if (Valid.isNoNull(item.getDltjLx()) && !Valid.isDouble(item.getDltjLx() + "", 2)) {

                errorInfo.setErrorInfo("利息,格式不对正确为：00.00");
                rs = false;
                break;
            }


            if (Valid.isNoNull(item.getDltjSyhj()) && !Valid.isDouble(item.getDltjSyhj() + "", 2)) {

                errorInfo.setErrorInfo("收入合计,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getDltZczj()) && !Valid.isDouble(item.getDltZczj() + "", 2)) {

                errorInfo.setErrorInfo("支持总计,格式不对正确为：00.00");
                rs = false;
                break;
            }


            if (Valid.isNoNull(item.getDltjLjysgb()) && !Valid.isDouble(item.getDltjLjysgb() + "", 2)) {

                errorInfo.setErrorInfo("累计应收国补,格式不对正确为：00.00");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getDltjSjjsdl()) && !Valid.isDouble(item.getDltjSjjsdl() + "", 2)) {

                errorInfo.setErrorInfo("实际结算电量,格式不对正确为：00.00");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getDltjDfjsljsjdz()) && !Valid.isDouble(item.getDltjDfjsljsjdz() + "", 2)) {

                errorInfo.setErrorInfo("电费结算累计实际到账,格式不对正确为：00.00");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getDltjQt()) && !Valid.isDouble(item.getDltjQt() + "", 2)) {

                errorInfo.setErrorInfo("其他,格式不对正确为：00.00");
                rs = false;
                break;
            }


            if (Valid.isNoNull(item.getDltjByzchj()) && !Valid.isDouble(item.getDltjByzchj() + "", 2)) {

                errorInfo.setErrorInfo("本月支持合计,格式不对正确为：00.00");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getDltjBxk()) && !Valid.isDouble(item.getDltjBxk() + "", 2)) {

                errorInfo.setErrorInfo("报销款,格式不对正确为：00.00");
                rs = false;
                break;
            }


            if (Valid.isNoNull(item.getDltjSyjsdfysje()) && !Valid.isDouble(item.getDltjSyjsdfysje() + "", 2)) {

                errorInfo.setErrorInfo("上月结算电费应收,格式不对正确为：00.00");
                rs = false;
                break;
            }




            if (Valid.isNoNull(item.getDltjLjfdl()) && !Valid.isDouble(item.getDltjLjfdl() + "", 0)) {

                errorInfo.setErrorInfo("累计发电量,格式不对正确为：00");
                rs = false;
                break;
            }


            if (Valid.isNoNull(item.getDltjDfjsljyjdz()) && !Valid.isDouble(item.getDltjDfjsljyjdz() + "", 2)) {

                errorInfo.setErrorInfo("电费结算累计预计到账,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getDltjYjdfsr()) && !Valid.isDouble(item.getDltjYjdfsr() + "", 2)) {

                errorInfo.setErrorInfo("预计电费收入,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getDltjSjdfsr()) && !Valid.isDouble(item.getDltjSjdfsr() + "", 2)) {

                errorInfo.setErrorInfo("实际电费收入,格式不对正确为：00.00");
                rs = false;
                break;
            }


        }

        return  rs;
    }
}
