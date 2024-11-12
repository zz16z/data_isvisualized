package com.zkrs.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.system.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zkrs.system.mapper.ProjectFinancingRepaymentMapper;
import com.zkrs.system.service.IProjectFinancingRepaymentService;

/**
 * 融资还款Service业务层处理
 * 
 * @author zkrs
 * @date 2022-10-14
 */
@Service
public class ProjectFinancingRepaymentServiceImpl implements IProjectFinancingRepaymentService 
{
    @Autowired
    private ProjectFinancingRepaymentMapper projectFinancingRepaymentMapper;

    /**
     * 查询融资还款
     * 
     * @param financingId 融资还款主键
     * @return 融资还款
     */
    @Override
    public ProjectFinancingRepayment selectProjectFinancingRepaymentByFinancingId(Long financingId)
    {
        return projectFinancingRepaymentMapper.selectProjectFinancingRepaymentByFinancingId(financingId);
    }

    /**
     * 查询融资还款列表
     * 
     * @param projectFinancingRepayment 融资还款
     * @return 融资还款
     */
    @Override
    public List<ProjectFinancingRepayment> selectProjectFinancingRepaymentList(ProjectFinancingRepayment projectFinancingRepayment)
    {
        return projectFinancingRepaymentMapper.selectProjectFinancingRepaymentList(projectFinancingRepayment);
    }

    /**
     * 新增融资还款
     * 
     * @param projectFinancingRepayment 融资还款
     * @return 结果
     */
    @Override
    public int insertProjectFinancingRepayment(ProjectFinancingRepayment projectFinancingRepayment)
    {
        return projectFinancingRepaymentMapper.insertProjectFinancingRepayment(projectFinancingRepayment);
    }

    /**
     * 修改融资还款
     * 
     * @param projectFinancingRepayment 融资还款
     * @return 结果
     */
    @Override
    public int updateProjectFinancingRepayment(ProjectFinancingRepayment projectFinancingRepayment)
    {
        return projectFinancingRepaymentMapper.updateProjectFinancingRepayment(projectFinancingRepayment);
    }

    /**
     * 批量删除融资还款
     * 
     * @param financingIds 需要删除的融资还款主键
     * @return 结果
     */
    @Override
    public int deleteProjectFinancingRepaymentByFinancingIds(Long[] financingIds)
    {
        return projectFinancingRepaymentMapper.deleteProjectFinancingRepaymentByFinancingIds(financingIds);
    }

    /**
     * 删除融资还款信息
     * 
     * @param financingId 融资还款主键
     * @return 结果
     */
    @Override
    public int deleteProjectFinancingRepaymentByFinancingId(Long financingId)
    {
        return projectFinancingRepaymentMapper.deleteProjectFinancingRepaymentByFinancingId(financingId);
    }

    @Override
    public AjaxResult insertFinancingRepaymentList(List<ProjectFinancingRepayment> listData) {
        try{
            //自验证前端传过来集合中有没有重复的月份
            List<String> dateList = new ArrayList<>();
            listData.forEach(item->{
                dateList.add(item.getRepaymentYear());
            });
            List<String> dateList2 = new ArrayList<>();
            for (String s : dateList) {
                if (dateList2.contains(s)) {
                    return AjaxResult.error("年份"+s+"只能存在一条数据");
                }
                dateList2.add(s);
            }
            for (ProjectFinancingRepayment listDatum : listData) {
                if(listDatum.getId()!=null){
                    projectFinancingRepaymentMapper.updateProjectFinancingRepaymentById(listDatum);
                }else{
                    projectFinancingRepaymentMapper.insertProjectFinancingRepayment(listDatum);
                }
            }

            return AjaxResult.success("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("添加失败");
        }
    }

    @Override
    public int deleteProjectFinancingRepaymentByIds(Long[] ids) {
        return projectFinancingRepaymentMapper.deleteProjectFinancingRepaymentByIds(ids);
    }

    @Override
    public List<ExportRepayment> selectProjectInfoList(ProjectInfo projectInfo) {
        List<ExportFinancingStill> exportFinancingStills = projectFinancingRepaymentMapper.selectProjectInfoList(projectInfo);
        List<String> list  =   new  ArrayList<String>();
        for (ExportFinancingStill exportFinancingStill : exportFinancingStills) {
            if(exportFinancingStill.getFinancingRq()!=null && exportFinancingStill.getFinancingRq()!=""){
                String year = exportFinancingStill.getFinancingRq().substring(0, 4);
                String key = exportFinancingStill.getFinancingId()+"-"+year;
                list.add(key);
            }
        }
        Set set = new  HashSet();
        List<String> newList = new  ArrayList();
        for (String year:list) {
            if(set.add(year)){
                newList.add(year);
            }
        }
        List<ExportRepayment> exportList = new ArrayList<>();
        if(newList.size()>0){
        for(int i= 0;i<newList.size();i++) {
            ExportRepayment exportRepayment = new ExportRepayment();

            for (ExportFinancingStill still : exportFinancingStills) {
                if (still.getFinancingRq() != null && still.getFinancingRq() != "") {
                    String[] split = newList.get(i).split("-");
                    String rzjg = still.getFinancingId();
                    String year = still.getFinancingRq().substring(0, 4);
                    String month = still.getFinancingRq().substring(5, 7);
                    if(year.equals(split[1]) && rzjg.equals(split[0])) {
                            exportRepayment.setRepaymentYear(year);
                            exportRepayment.setXmqkXmmc(still.getXmqkXmmc());
                            exportRepayment.setRzjg(still.getXmrzRzjg());
                            exportRepayment.setBeginPay(still.getBeginPay());
                            BigDecimal cb = still.getFinancingCb();
                            BigDecimal lx = still.getFinancingLx();
                            BigDecimal sxf = still.getFinancingSxf();
                            if (month.equals("01")) {
                                exportRepayment.setJanPrincipal(cb);
                                exportRepayment.setJanInterest(lx);
                                exportRepayment.setJanService(sxf);
                            }
                            if (month.equals("02")) {
                                exportRepayment.setFebPrincipal(cb);
                                exportRepayment.setFebInterest(lx);
                                exportRepayment.setFebService(sxf);
                                ;
                            }
                            if (month.equals("03")) {
                                exportRepayment.setMarPrincipal(cb);
                                exportRepayment.setMarInterest(lx);
                                exportRepayment.setMarService(sxf);
                            }
                            if (month.equals("04")) {
                                exportRepayment.setAprPrincipal(cb);
                                exportRepayment.setAprInterest(lx);
                                exportRepayment.setAprService(sxf);
                            }
                            if (month.equals("05")) {
                                exportRepayment.setMayPrincipal(cb);
                                exportRepayment.setMayInterest(lx);
                                exportRepayment.setMayService(sxf);
                            }
                            if (month.equals("06")) {
                                exportRepayment.setJunPrincipal(cb);
                                exportRepayment.setJunInterest(lx);
                                exportRepayment.setJunService(sxf);
                            }
                            if (month.equals("07")) {
                                exportRepayment.setJulPrincipal(cb);
                                exportRepayment.setJulInterest(lx);
                                exportRepayment.setJulService(sxf);
                            }
                            if (month.equals("08")) {
                                exportRepayment.setAugPrincipal(cb);
                                exportRepayment.setAugInterest(lx);
                                exportRepayment.setAugService(sxf);
                            }
                            if (month.equals("09")) {
                                exportRepayment.setSepPrincipal(cb);
                                exportRepayment.setSepInterest(lx);
                                exportRepayment.setSepService(sxf);
                            }
                            if (month.equals("10")) {
                                exportRepayment.setOctPrincipal(cb);
                                exportRepayment.setOctInterest(lx);
                                exportRepayment.setOctService(sxf);
                            }
                            if (month.equals("11")) {
                                exportRepayment.setNovPrincipal(cb);
                                exportRepayment.setNovInterest(lx);
                                exportRepayment.setNovService(sxf);
                            }
                            if (month.equals("12")) {
                                exportRepayment.setDecPrincipal(cb);
                                exportRepayment.setDecInterest(lx);
                                exportRepayment.setDecService(sxf);
                            }
                    }
                }
            }
                exportList.add(exportRepayment);

        }
        }
        return exportList;
    }
}
