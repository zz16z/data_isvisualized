package com.zkrs.system.service.impl;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.common.core.redis.RedisCache;
import com.zkrs.common.utils.DateUtils;
import com.zkrs.common.utils.StringUtils;
import com.zkrs.common.utils.bean.BeanUtils;
import com.zkrs.common.utils.uuid.IdUtils;
import com.zkrs.system.domain.*;
import com.zkrs.system.enumeration.CommonlyCache;
import com.zkrs.system.mapper.*;
import com.zkrs.system.section.ApiResultsVo;
import com.zkrs.system.service.IFinancingStillService;
import com.zkrs.system.service.IProjectCashService;
import com.zkrs.system.tool.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import com.zkrs.system.service.IProjectInfoService;
import com.zkrs.system.mapper.ElectricityStatisticsMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * 项目情况Service业务层处理
 *
 * @author zkrs
 * @date 2022-06-30
 */
@Service
public class ProjectInfoServiceImpl implements IProjectInfoService {
    @Autowired
    private ProjectInfoMapper projectInfoMapper;


    @Autowired
    private ActualCashMapper actualCashMapper;//资金实际

    @Autowired
    private ExpectCashMapper expectCashMapper;//资金预计

    @Autowired
    private ProjectFinancingMapper projectFinancingMapper;//融资
    @Autowired
    private ElectricityStatisticsMapper electricityStatisticsMapper; //电费
    @Autowired
    private FinancingDateMapper financingDateMapper; //放款
    @Autowired
    private FinancingStillMapper financingStillMapper; //还款
    @Autowired
    private ProjectCashMapper projectCashMapper;

    @Autowired
    private ProjectFinancingLoanMapper projectFinancingLoanMapper;

    @Autowired
    private ProjectFinancingRepaymentMapper projectFinancingRepaymentMapper;



    /**
     * 查询项目情况
     *
     * @param xmqkProjectId 项目情况主键
     * @return 项目情况
     */
    @Override
    public ProjectInfo selectProjectInfoByXmqkProjectId(String xmqkProjectId) {
        return projectInfoMapper.selectProjectInfoByXmqkProjectId(xmqkProjectId);
    }

    /**
     * 查询项目情况列表
     *
     * @param projectInfo 项目情况
     * @return 项目情况
     */
    @Override
    public List<ProjectInfo> selectProjectInfoList(ProjectInfo projectInfo) {
        return projectInfoMapper.selectProjectInfoList(projectInfo);
    }




    /**
     * 新增项目情况
     *
     * @param projectInfo 项目情况
     * @return 结果
     */
    @Override
    public int insertProjectInfo(ProjectInfo projectInfo) {
        projectInfo.setCreateTime(DateUtils.getNowDate());

        return projectInfoMapper.insertProjectInfo(projectInfo);
    }

    /**
     * 修改项目情况
     *
     * @param projectInfo 项目情况
     * @return 结果
     */
    @Override
    public int updateProjectInfo(ProjectInfo projectInfo) {
        projectInfo.setUpdateTime(DateUtils.getNowDate());
        return projectInfoMapper.updateProjectInfo(projectInfo);
    }

    /**
     * 批量删除项目情况
     *
     * @param xmqkProjectIds 需要删除的项目情况主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteProjectInfoByXmqkProjectIds(String[] xmqkProjectIds) {
        //资金实际
        actualCashMapper.deleteActualCashByXjlsjqkIdsJl(xmqkProjectIds);

        //资金预计
        expectCashMapper.deleteExpectCashByXjlyjqkIdsJl(xmqkProjectIds);

        //融资
        projectFinancingMapper.deleteProjectFinancingByStil(xmqkProjectIds);
        projectFinancingMapper.deleteProjectFinancingByXmrzIdJlL(xmqkProjectIds);
        projectFinancingMapper.deleteProjectFinancingByXmrzIdsJl(xmqkProjectIds);


        //电费
        electricityStatisticsMapper.deleteElectricityStatisticsByDltjIdsJl(xmqkProjectIds);

        return projectInfoMapper.deleteProjectInfoByXmqkProjectIds(xmqkProjectIds);
    }

    /**
     * 删除项目情况信息
     *
     * @param xmqkProjectId 项目情况主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteProjectInfoByXmqkProjectId(String xmqkProjectId) {
        //资金实际
        actualCashMapper.deleteActualCashByXjlsjqkIdJl(xmqkProjectId);

        //资金预计
        expectCashMapper.deleteExpectCashByXjlyjqkIdJl(xmqkProjectId);

        //融资
        projectFinancingMapper.deleteProjectFinancingByStil(new String[]{xmqkProjectId});
        projectFinancingMapper.deleteProjectFinancingByXmrzIdJlL(new String[]{xmqkProjectId});
        projectFinancingMapper.deleteProjectFinancingByXmrzIdJl(xmqkProjectId);



        //电费
        electricityStatisticsMapper.deleteElectricityStatisticsByDltjIdJl(xmqkProjectId);
        return projectInfoMapper.deleteProjectInfoByXmqkProjectId(xmqkProjectId);
    }


    /**
     * 查询项目情况列表缓存
     *
     * @param projectInfo 项目情况缓存
     * @return 项目情况缓存集合
     */
    public List<ProjectInfo> selectProjectInfoCache(ProjectInfo projectInfo) {

        return projectInfoMapper.selectProjectInfoCache(projectInfo);
    }


    /**
     * 验证醒目名称
     */
    public List<ProjectInfo>  checkByname(List<ProjectInfo>   listdata){
        return projectInfoMapper.checkByname(listdata);
    }

    /**
     * 根据项目名称查找项目ID
     */
    public List<ProjectResult> getAllNameAndId(){
        return projectInfoMapper.getAllNameAndId();
    }

    /**
     * 项目情况统计导入
     *
     * @param listData 项目情况统计导入
     * @return 结果AjaxResult
     */
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBox(List<ProjectInfoVerify> listData, String xmid) {

        AjaxResult rs = AjaxResult.success("导入失败");
        //验证数据
        ImportError errorInfo = new ImportError();
        if (verification(listData, errorInfo)) {
            String   projectInfoList= FastJsonUtils.Object2String(listData);
            List<ProjectInfo> listItem= FastJsonUtils.getJsonToList(projectInfoList,ProjectInfo.class);
            //自验证前端传过来集合中有没有重复的项目名称
            List<String> dateList = new ArrayList<>();
            listItem.forEach(item->{
                dateList.add(item.getXmqkXmmc());
            });
            List<String> dateList2 = new ArrayList<>();
            for (String s : dateList) {
                if (dateList2.contains(s)) {
                    rs = AjaxResult.error("导入数据中项目名称重复："+s);
                    return rs;
                }
                dateList2.add(s);
            }


            List<ProjectInfo> addItem = new ArrayList<>();
            List<ProjectInfo> updateItem = new ArrayList<>();
            //查询是否存在项目
            for (ProjectInfo projectInfo : listItem) {
                int i = -1;
                i = projectInfoMapper.selectProjectIsExist(projectInfo.getXmqkXmmc());
                //存在项目就修改
                if(i>0){
                    updateItem.add(projectInfo);
                }else{
                    //不存在项目就新增
                    addItem.add(projectInfo);
                }
            }
            if(addItem.size()>0){
                int i = projectInfoMapper.importBox(addItem);
            }
            if(updateItem.size()>0){
                for (ProjectInfo projectInfo : updateItem) {
                    projectInfoMapper.updateProjectInfoByXmmc(projectInfo);
                }
            }
            rs = AjaxResult.success("数据导入成功");
        } else {
            rs = AjaxResult.error("序号" + errorInfo.getProjectName() + "下:" + errorInfo.getErrorInfo());
        }
        return rs;
    }

    //实施导入
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBoxConstruction(List<ProjectInfoVerify> listData) {
        AjaxResult rs = AjaxResult.success("导入失败");
        //验证数据
        ImportError errorInfo = new ImportError();
        if (verificationConstruction(listData, errorInfo)) {
            String  projectInfoList= FastJsonUtils.Object2String(listData);
            List<ProjectInfo> listItem= FastJsonUtils.getJsonToList(projectInfoList,ProjectInfo.class);
            //自验证前端传过来集合中有没有重复的项目名称
            List<String> dateList = new ArrayList<>();
            listItem.forEach(item->{
                dateList.add(item.getXmqkXmmc());
            });
            List<String> dateList2 = new ArrayList<>();
            for (String s : dateList) {
                if (dateList2.contains(s)) {
                    rs = AjaxResult.error("导入数据中项目名称重复："+s);
                    return rs;
                }
                dateList2.add(s);
            }
            //查所有ID、项目名称
            List<String> dateList3 = new ArrayList<>();
            List<ProjectResult> plist = getAllNameAndId();
            plist.forEach(item->{
                dateList3.add(item.getName());
            });
            //比对项目名是否存在
            for (ProjectInfo projectInfo : listItem) {
                if(!dateList3.contains(projectInfo.getXmqkXmmc())){
                    rs = AjaxResult.error("项目不存在："+projectInfo.getXmqkXmmc());
                    return rs;
                }
            }
            //给ID赋值
            for (ProjectInfo projectInfo : listItem) {
                String xmqkXmmc = projectInfo.getXmqkXmmc();
                for (ProjectResult projectResult : plist) {
                    if(xmqkXmmc.equals(projectResult.getName())) {
                        projectInfo.setXmqkProjectId(projectResult.getId());
                    }
                }
            }
            //修改项目信息
            for (ProjectInfo projectInfo : listItem) {
                projectInfoMapper.importBoxConstruction(projectInfo);
            }
            rs = AjaxResult.success("数据导入成功");
        } else {
            rs = AjaxResult.error("序号" + errorInfo.getProjectName() + "下:" + errorInfo.getErrorInfo());
        }
        return rs;
    }

    //经营情况导入
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBoxManagement(List<ManagementCash> listData) {
        AjaxResult rs = AjaxResult.success("导入失败");
        //验证数据
        ImportError errorInfo = new ImportError();
        if (verificationManagement(listData, errorInfo)) {
            String  projectInfoList= FastJsonUtils.Object2String(listData);
            List<ManagementCash> listItem= FastJsonUtils.getJsonToList(projectInfoList,ManagementCash.class);
            //自验证前端传过来集合中有没有重复的项目名称
//            List<String> dateList = new ArrayList<>();
//            listItem.forEach(item->{
//                dateList.add(item.getXmqkXmmc());
//            });
//            List<String> dateList2 = new ArrayList<>();
//            for (String s : dateList) {
//                if (dateList2.contains(s)) {
//                    rs = AjaxResult.error("导入数据中项目名称重复："+s);
//                    return rs;
//                }
//                dateList2.add(s);
//            }
            //查所有ID、项目名称
            List<String> dateList3 = new ArrayList<>();
            List<ProjectResult> plist = getAllNameAndId();
            plist.forEach(item->{
                dateList3.add(item.getName());
            });
            //比对项目名是否存在
            for (ManagementCash projectInfo : listItem) {
                if(!dateList3.contains(projectInfo.getXmqkXmmc())){
                    rs = AjaxResult.error("项目不存在："+projectInfo.getXmqkXmmc());
                    return rs;
                }
            }
            List<String> listSelectData=new ArrayList<>();
            //给ID赋值
            for (ManagementCash projectInfo : listItem) {
                String xmqkXmmc = projectInfo.getXmqkXmmc();
                for (ProjectResult projectResult : plist) {
                    if(xmqkXmmc.equals(projectResult.getName())) {
                        projectInfo.setXmqkProjectId(projectResult.getId());
                        listSelectData.add(projectResult.getId());
                    }
                }
            }
            //查询导入信息关联的项目
            List<ExpectCasOrActualCash>   selectCkekLits  =expectCashMapper.selectCkekLits(listSelectData);
            List<ManagementCash> addList=new ArrayList<>();
            List<ManagementCash> upList=new ArrayList<>();
            //根据selectCkekLits(类似数据库)判断修改增加
            for (ManagementCash projectInfo : listItem) {
                //根据导入信息判断增加修改（项目id+导入日期）
                String isChek=projectInfo.getXmqkProjectId()+":"+projectInfo.getXjlyjqkRq();
                Boolean  isfage=true;
                for (ExpectCasOrActualCash chekData : selectCkekLits) {
                    String isChek1=chekData.getXmid()+":"+chekData.getXjlyjqkRq();
                    if(isChek.equals(isChek1)) {
                      //修改
                        projectInfo.setXjlyjqkId(chekData.getExpectCashXjlyjqkId());
                        projectInfo.setXjlsjqkId(chekData.getActualCashID())  ;
                        projectInfo.setXjlsjqkYjxjlId(chekData.getXjlsjqkYjxjlId());
                        upList.add(projectInfo);
                        isfage=false;
                        break;
                    }
                }
                if(isfage){
                    //添加
                    //设置关联id
                    projectInfo.setXjlsjqkYjxjlId(IdUtils.fastSimpleUUID());
                    addList.add(projectInfo);
                }
            }
            if(addList.size()>0){
                expectCashMapper.addBatch(addList);
                actualCashMapper.addBatch(addList);
            }
            if(upList.size()>0){
                expectCashMapper.updateBatch(upList);
                actualCashMapper.updateBatch(upList);
            }


            //新增项目信息
//            for (ManagementCash managementCash : listItem) {
//                expectCashMapper.importBoxManagement(managementCash);
//                actualCashMapper.importBoxManagement(managementCash);
//            }
            rs = AjaxResult.success("数据导入成功");
        } else {
            rs = AjaxResult.error("序号" + errorInfo.getProjectName() + "下:" + errorInfo.getErrorInfo());
        }
        return rs;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBoxElectricity(List<ElectricityCash> listData) {
        AjaxResult rs = AjaxResult.success("导入失败");
        //验证数据
        ImportError errorInfo = new ImportError();
        if (verificationElectricity(listData, errorInfo)) {
            String  projectInfoList= FastJsonUtils.Object2String(listData);
            List<ElectricityCash> listItem= FastJsonUtils.getJsonToList(projectInfoList,ElectricityCash.class);
            //查所有ID、项目名称
            List<String> dateList3 = new ArrayList<>();
            List<ProjectResult> plist = getAllNameAndId();
            plist.forEach(item->{
                dateList3.add(item.getName());
            });
            //比对项目名是否存在
            for (ElectricityCash projectInfo : listItem) {
                if(!dateList3.contains(projectInfo.getXmqkXmmc())){
                    rs = AjaxResult.error("项目不存在："+projectInfo.getXmqkXmmc());
                    return rs;
                }
            }
            List<String> repeatList = listItem.stream().collect(Collectors.groupingBy(ElectricityCash -> ElectricityCash.getXmqkXmmc()+"@"+ElectricityCash.getDltjRq(), Collectors.counting()))
                    .entrySet().stream().filter(e -> e.getValue() > 1)
                    .map(Map.Entry::getKey).collect(Collectors.toList());
            if(repeatList!=null&&repeatList.size()>0){
                String chekName =repeatList.get(0);
                String[] splitName=chekName.split("@");
                rs = AjaxResult.error(splitName[0]+":"+splitName[1]+"时间重复");
                return rs;
            }

            //给ID赋值
            for (ElectricityCash projectInfo : listItem) {
                String xmqkXmmc = projectInfo.getXmqkXmmc();
                for (ProjectResult projectResult : plist) {
                    if(xmqkXmmc.equals(projectResult.getName())) {
                        projectInfo.setXmqkProjectId(projectResult.getId());
                    }
                }
            }
            //新增项目信息
//            for (ElectricityCash electricityCash : listItem) {
//                electricityStatisticsMapper.importBoxElectricityCash(electricityCash);
//            }
            List<ElectricityCash> addListItem =new ArrayList<>();
            List<ElectricityCash> upListItem =new ArrayList<>();
            for (ElectricityCash projectInfo : listItem) {
                Integer id= electricityStatisticsMapper.selectProjectInfoChek(projectInfo);
                if(id!=null&&id>0){
                    projectInfo.setDltjId(id+"");
                    upListItem.add(projectInfo);
                }else {
                    addListItem.add(projectInfo);
                }
            }

            if(addListItem.size()>0){
                for (ElectricityCash electricityCash : addListItem) {
                    electricityStatisticsMapper.importBoxElectricityCash(electricityCash);
                }
            }
            if(upListItem.size()>0){
                for (ElectricityCash electricityCash : upListItem) {
                    electricityStatisticsMapper.updateElectricity(electricityCash);
                }
            }
            //mysql8支持
            //electricityStatisticsMapper.insertUpdateBatch0(listItem);
            rs = AjaxResult.success("数据导入成功");
        }else{
            rs = AjaxResult.error("序号" + errorInfo.getProjectName() + "下:" + errorInfo.getErrorInfo());
        }
        return rs;
    }

    /*
     *项目融资导入
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBoxFinancing(List<FinancingInfo> listData) {
        AjaxResult rs = AjaxResult.success("导入失败");
        //验证数据
        ImportError errorInfo = new ImportError();
        if (verificationFinancing(listData, errorInfo)) {
            String  projectInfoList= FastJsonUtils.Object2String(listData);
            List<FinancingInfo> listItem= FastJsonUtils.getJsonToList(projectInfoList,FinancingInfo.class);
            //查所有ID、项目名称
            List<String> dateList3 = new ArrayList<>();
            List<ProjectResult> plist = getAllNameAndId();
            plist.forEach(item->{
                dateList3.add(item.getName());
            });
            //比对项目名是否存在
            for (FinancingInfo projectInfo : listItem) {
                if(!dateList3.contains(projectInfo.getXmqkXmmc())){
                    rs = AjaxResult.error("项目不存在："+projectInfo.getXmqkXmmc());
                    return rs;
                }

//                if(projectInfo.getXmrzQyrq()==null||"".equals(projectInfo.getXmrzQyrq())){
//                    rs = AjaxResult.error(projectInfo.getXmqkXmmc()+":项目下签约日期不能为空");
//                    return rs;
//                }
            }

            //给ID赋值
            for (FinancingInfo projectInfo : listItem) {
                String xmqkXmmc = projectInfo.getXmqkXmmc();
                for (ProjectResult projectResult : plist) {
                    if(xmqkXmmc.equals(projectResult.getName())) {
                        projectInfo.setXmrzProjectId(projectResult.getId());
                        projectInfo.setXmrzRzjg(projectResult.getXmqkRzf());
                    }
                }
            }
            List<FinancingInfo>    resultFinancingInfoList=null;
            List<FinancingInfo>    duplicateList =null;

            List<FinancingInfo> addItem = new ArrayList<>();
            List<FinancingInfo> updateItem = new ArrayList<>();

            List<FinancingInfo> addItemLoan = new ArrayList<>();
            List<FinancingInfo> updateItemLoan = new ArrayList<>();

            for (FinancingInfo financingInfo : listItem) {
                //判断项目ID  融资机构是否一样，如果一样就是修改，不一样就是新增
                Integer i = projectFinancingMapper.isExistProject(financingInfo);
                //存在
                if( i!=null&&i>0){
                    financingInfo.setXmrzId(Long.parseLong(i+""));
                    updateItem.add(financingInfo);
                }else{
                    addItem.add(financingInfo);
                }

                //判断项目ID  年份是否一样，如果一样就是修改，不一样就是新增
                //按照融资机构+签约日期作为主键标识
                Integer j =projectFinancingLoanMapper.isExistProject(financingInfo);
                if( j!=null&&j>0){
                    updateItemLoan.add(financingInfo);
                }else{
                    addItemLoan.add(financingInfo);
                }
            }
            if(addItem.size()>0){
                //因为每个项目只有一个融资机构，所以要对融资机构去重才行
//                resultFinancingInfoList = addItem.stream().collect(
//                        Collectors.collectingAndThen(Collectors.toCollection(()->new TreeSet<>(
//                                Comparator.comparing(FinancingInfo -> FinancingInfo.getXmqkXmmc() + ":" + FinancingInfo.getXmrzRzjg()))), ArrayList::new));
                resultFinancingInfoList = addItem.stream().collect(
                        Collectors.collectingAndThen(Collectors.toCollection(()->new TreeSet<>(
                                Comparator.comparing(FinancingInfo -> FinancingInfo.getXmqkXmmc() ))), ArrayList::new));
                for (FinancingInfo financingInfo : resultFinancingInfoList) {
                    projectFinancingMapper.importBoxFinancingInfo(financingInfo);
                }
            }
            if(updateItem.size()>0){
                duplicateList = updateItem.stream().collect(
                        Collectors.collectingAndThen(Collectors.toCollection(()->new TreeSet<>(
                                Comparator.comparing(FinancingInfo -> FinancingInfo.getXmqkXmmc() ))), ArrayList::new));
                for (FinancingInfo financingInfo : duplicateList) {
                    projectFinancingMapper.updateProjectFinancingInfo(financingInfo);
                }
            }
            if(addItemLoan.size()>0){
                getXmrzIdValue(resultFinancingInfoList,addItemLoan);
                List<FinancingDate> addFinancingDateList= addLoan(addItemLoan);
                if(addFinancingDateList.size()>0){
                    financingDateMapper.importBox(addFinancingDateList);
                }

            }
            if(updateItemLoan.size()>0){
                //设置id
                getXmrzIdValue(duplicateList,updateItemLoan);
                List<FinancingDate> upFinancingDateList= addLoan(updateItemLoan);
                if(upFinancingDateList.size()>0){
                    StringBuffer  sql =new StringBuffer();
                    int lensII=0;
                    int count=4;
                    for( int lensI=0;lensI< upFinancingDateList.size();lensI++ ){

                        sql.append(" insert into  financing_date( ");
                        sql.append(" id, ");
                        sql.append("  financing_id, ");
                        sql.append(" times, ");
                        sql.append(" january, ");
                        sql.append(" total_loans, ");
                        sql.append(" total_withdrawal, ");
                        sql.append(" total_repayment, ");
                        sql.append(" paragraph_type, ");
                        sql.append(" is_yj_sj) ");
                        sql.append(" values(  ");
                        sql.append(upFinancingDateList.get(lensI).getId()).append(",");
                        sql.append(upFinancingDateList.get(lensI).getFinancingId()).append(",");
                        sql.append("'").append(DateUtils.dateTimeFormat(upFinancingDateList.get(lensI).getTimes(),"yyyy-MM-dd")).append("'").append(",");
                        sql.append(upFinancingDateList.get(lensI).getJanuary()).append(",");
                        sql.append(upFinancingDateList.get(lensI).getTotalLoans()).append(",");
                        sql.append(upFinancingDateList.get(lensI).getTotalWithdrawal()).append(",");
                        sql.append(upFinancingDateList.get(lensI).getTotalRepayment()).append(",");
                        sql.append(upFinancingDateList.get(lensI).getParagraphType()).append(",");
                        sql.append(upFinancingDateList.get(lensI).getIsYjSj());

                        sql.append(" )");
                        sql.append(" on duplicate key update  january = ");
                        sql.append(upFinancingDateList.get(lensI).getJanuary());
                        sql.append(" ;");

                        if(lensII>count){
                            financingDateMapper.insertUpdateBatchSql(sql.toString());
                            lensII=0;
                            sql.setLength(0);
                        }else {
                            lensII++;
                        }


                    }
                    if(sql.length()>0){

                        financingDateMapper.insertUpdateBatchSql(sql.toString());
                    }
                }
            }
            //新增项目信息
            rs = AjaxResult.success("数据导入成功");
        }else{
            rs = AjaxResult.error("序号" + errorInfo.getProjectName() + "下:" + errorInfo.getErrorInfo());
        }
        return rs;
    }
    //去重设置id
    void getXmrzIdValue(List<FinancingInfo> itmes,List<FinancingInfo> addUplist) {
        if((itmes!=null&&itmes.size()>0)&&(addUplist!=null&&addUplist.size()>0)){
            for (FinancingInfo item0 : addUplist) {
                String xm_name = item0.getXmqkXmmc();
                for (FinancingInfo item1 : itmes) {
                    if ((item1.getXmqkXmmc()).equals(xm_name)) {
                        item0.setXmrzId(item1.getXmrzId());
                    }
                }
            }
        }


    }

    List<FinancingDate>  addLoan(List<FinancingInfo> itmes){

        List<FinancingDate>  listRs=new ArrayList<>();
        for (FinancingInfo itme : itmes) {
            if(StringUtils.isEmpty(itme.getLoanYear())){
                continue;
            }

            FinancingDate  financingDate1=new FinancingDate();
            //融资表关联id
            financingDate1.setFinancingId(itme.getXmrzId()+"");
            //时间
            financingDate1.setTimes(DateUtils.dateTime("yyyy-MM" ,itme.getLoanYear()+"-"+"01"));
            financingDate1.setQuDate(itme.getLoanYear()+"-"+"01");
            //金额
            financingDate1.setJanuary(itme.getJan());
            if( DecimalUtils.format(itme.getJan()).compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingDate1);
            }





            FinancingDate  financingDate2=new FinancingDate();
            //融资表关联id
            financingDate2.setFinancingId(itme.getXmrzId()+"");
            //时间
            financingDate2.setTimes(DateUtils.dateTime("yyyy-MM" ,itme.getLoanYear()+"-"+"02"));
            financingDate2.setQuDate(itme.getLoanYear()+"-"+"02");
            //金额
            financingDate2.setJanuary(itme.getFeb());
            if( DecimalUtils.format(itme.getFeb()).compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingDate2);;
            }




            FinancingDate  financingDate3=new FinancingDate();
            //融资表关联id
            financingDate3.setFinancingId(itme.getXmrzId()+"");
            //时间
            financingDate3.setTimes(DateUtils.dateTime("yyyy-MM" ,itme.getLoanYear()+"-"+"03"));
            financingDate3.setQuDate(itme.getLoanYear()+"-"+"03");
            //金额
            financingDate3.setJanuary(itme.getMar());
            if( DecimalUtils.format(itme.getMar()).compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingDate3);
            }




            FinancingDate  financingDate4=new FinancingDate();
            //融资表关联id
            financingDate4.setFinancingId(itme.getXmrzId()+"");
            //时间
            financingDate4.setTimes(DateUtils.dateTime("yyyy-MM" ,itme.getLoanYear()+"-"+"04"));
            financingDate4.setQuDate(itme.getLoanYear()+"-"+"04");
            //金额
            financingDate4.setJanuary(itme.getApr());
            if( DecimalUtils.format(itme.getApr()).compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingDate4);
            }




            FinancingDate  financingDate5=new FinancingDate();
            //融资表关联id
            financingDate5.setFinancingId(itme.getXmrzId()+"");
            //时间
            financingDate5.setTimes(DateUtils.dateTime("yyyy-MM" ,itme.getLoanYear()+"-"+"05"));
            financingDate5.setQuDate(itme.getLoanYear()+"-"+"05");
            //金额
            financingDate5.setJanuary(itme.getMay());
            if( DecimalUtils.format(itme.getMay()).compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingDate5);
            }




            FinancingDate  financingDate6=new FinancingDate();
            //融资表关联id
            financingDate6.setFinancingId(itme.getXmrzId()+"");
            //时间
            financingDate6.setTimes(DateUtils.dateTime("yyyy-MM" ,itme.getLoanYear()+"-"+"06"));
            financingDate6.setQuDate(itme.getLoanYear()+"-"+"06");
            //金额
            financingDate6.setJanuary(itme.getJun());
            if( DecimalUtils.format(itme.getJun()).compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingDate6);
            }



            FinancingDate  financingDate7=new FinancingDate();
            //融资表关联id
            financingDate7.setFinancingId(itme.getXmrzId()+"");
            //时间
            financingDate7.setTimes(DateUtils.dateTime("yyyy-MM" ,itme.getLoanYear()+"-"+"07"));
            financingDate7.setQuDate(itme.getLoanYear()+"-"+"07");
            //金额
            financingDate7.setJanuary(itme.getJul());
            if( DecimalUtils.format(itme.getJul()).compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingDate7);
            }



            FinancingDate  financingDate8=new FinancingDate();
            //融资表关联id
            financingDate8.setFinancingId(itme.getXmrzId()+"");
            //时间
            financingDate8.setTimes(DateUtils.dateTime("yyyy-MM" ,itme.getLoanYear()+"-"+"08"));
            financingDate8.setQuDate(itme.getLoanYear()+"-"+"08");
            //金额
            financingDate8.setJanuary(itme.getAug());
            if( DecimalUtils.format(itme.getAug()).compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingDate8);
            }



            FinancingDate  financingDate9=new FinancingDate();
            //融资表关联id
            financingDate9.setFinancingId(itme.getXmrzId()+"");
            //时间
            financingDate9.setTimes(DateUtils.dateTime("yyyy-MM" ,itme.getLoanYear()+"-"+"09"));
            financingDate9.setQuDate(itme.getLoanYear()+"-"+"09");
            //金额
            financingDate9.setJanuary(itme.getSep());
            if( DecimalUtils.format(itme.getSep()).compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingDate9);
            }



            FinancingDate  financingDate10=new FinancingDate();
            //融资表关联id
            financingDate10.setFinancingId(itme.getXmrzId()+"");
            //时间
            financingDate10.setTimes(DateUtils.dateTime("yyyy-MM" ,itme.getLoanYear()+"-"+"10"));
            financingDate10.setQuDate(itme.getLoanYear()+"-"+"10");
            //金额
            financingDate10.setJanuary(itme.getOct());
            if( DecimalUtils.format(itme.getOct()).compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingDate10);
            }


            FinancingDate  financingDate11=new FinancingDate();
            //融资表关联id
            financingDate11.setFinancingId(itme.getXmrzId()+"");
            //时间
            financingDate11.setTimes(DateUtils.dateTime("yyyy-MM" ,itme.getLoanYear()+"-"+"11"));
            financingDate11.setQuDate(itme.getLoanYear()+"-"+"11");
            //金额
            financingDate11.setJanuary(itme.getNov());
            if( DecimalUtils.format(itme.getNov()).compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingDate11);
            }



            FinancingDate  financingDate12=new FinancingDate();
            //融资表关联id
            financingDate12.setFinancingId(itme.getXmrzId()+"");
            //时间
            financingDate12.setTimes(DateUtils.dateTime("yyyy-MM" ,itme.getLoanYear()+"-"+"12"));
            financingDate12.setQuDate(itme.getLoanYear()+"-"+"12");
            //金额
            financingDate12.setJanuary(itme.getDec());
            if( DecimalUtils.format(itme.getDec()).compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingDate12);
            }






        }

        return listRs;
    }




    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBoxExpectedFinancing(List<ExpectedFinancing> listData) {
        AjaxResult rs = AjaxResult.success("导入失败");
        //验证数据
        ImportError errorInfo = new ImportError();
        if (verificationExpectedFinancing(listData, errorInfo)) {
            String  projectInfoList= FastJsonUtils.Object2String(listData);
            List<ExpectedFinancing> listItem= FastJsonUtils.getJsonToList(projectInfoList,ExpectedFinancing.class);
            //查所有ID、项目名称
            List<String> dateList3 = new ArrayList<>();
            List<ProjectResult> plist = getAllNameAndId();
            plist.forEach(item->{
                dateList3.add(item.getName());
            });
            //比对项目名是否存在
            for (ExpectedFinancing projectInfo : listItem) {
                if(!dateList3.contains(projectInfo.getXmqkXmmc())){
                    rs = AjaxResult.error("项目不存在："+projectInfo.getXmqkXmmc());
                    return rs;
                }
            }
            //给项目ID赋值
            for (ExpectedFinancing projectInfo : listItem) {
                String xmqkXmmc = projectInfo.getXmqkXmmc();
                for (ProjectResult projectResult : plist) {
                    if(xmqkXmmc.equals(projectResult.getName())) {
                        projectInfo.setXmqkProjectId(projectResult.getId());
                    }
                }
            }
            //根据项目Id查出融资机构ID，给融资机构ID赋值
            for (ExpectedFinancing financingInfo : listItem) {
                String xmrzProjectId = financingInfo.getXmqkProjectId();
                Long xmrzId =  projectFinancingMapper.selectByProjectId(xmrzProjectId);
                financingInfo.setFinancingId(xmrzId);
            }
            //分别插入表
            for (ExpectedFinancing financingInfo : listItem) {
                financingInfo.setIsYjSj('0');
                financingInfo.setParagraphType('0');
                if(financingInfo.getTimes().length()!=0){
                    financingDateMapper.importBoxFinancingInfo(financingInfo);
                }
                if(financingInfo.getFinancingRq().length()!=0){
                    financingStillMapper.importBoxFinancingInfo(financingInfo);
                }
            }
            rs = AjaxResult.success("数据导入成功");
        }else{
            rs = AjaxResult.error("序号" + errorInfo.getProjectName() + "下:" + errorInfo.getErrorInfo());
        }
        return rs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBoxExpectedTrueFinancing(List<ExpectedTrueFinancing> listData) {
        AjaxResult rs = AjaxResult.success("导入失败");
        //验证数据
        ImportError errorInfo = new ImportError();
        if (verificationTrueExpectedFinancing(listData, errorInfo)) {
            String  projectInfoList= FastJsonUtils.Object2String(listData);
            List<ExpectedTrueFinancing> listItem= FastJsonUtils.getJsonToList(projectInfoList,ExpectedTrueFinancing.class);
            //查所有ID、项目名称
            List<String> dateList3 = new ArrayList<>();
            List<ProjectResult> plist = getAllNameAndId();
            plist.forEach(item->{
                dateList3.add(item.getName());
            });
            //比对项目名是否存在
            for (ExpectedTrueFinancing projectInfo : listItem) {
                if(!dateList3.contains(projectInfo.getXmqkXmmc())){
                    rs = AjaxResult.error("项目不存在："+projectInfo.getXmqkXmmc());
                    return rs;
                }
            }
            //给项目ID赋值
            for (ExpectedTrueFinancing projectInfo : listItem) {
                String xmqkXmmc = projectInfo.getXmqkXmmc();
                for (ProjectResult projectResult : plist) {
                    if(xmqkXmmc.equals(projectResult.getName())) {
                        projectInfo.setXmqkProjectId(projectResult.getId());
                    }
                }
            }
            //根据项目Id查出融资机构ID，给融资机构ID赋值
            for (ExpectedTrueFinancing financingInfo : listItem) {
                String xmrzProjectId = financingInfo.getXmqkProjectId();
                Long xmrzId =  projectFinancingMapper.selectByProjectId(xmrzProjectId);
                financingInfo.setFinancingId(xmrzId);
            }
            //分别插入表
            for (ExpectedTrueFinancing financingInfo : listItem) {
                financingInfo.setIsYjSj('1');
                financingInfo.setParagraphType('1');
                if(financingInfo.getTimes().length()!=0){
                    financingDateMapper.importBoxTrueFinancingInfo(financingInfo);
                }
                if(financingInfo.getFinancingRq().length()!=0){
                    financingStillMapper.importBoxTrueFinancingInfo(financingInfo);
                }
            }
            rs = AjaxResult.success("数据导入成功");
        }else{
            rs = AjaxResult.error("序号" + errorInfo.getProjectName() + "下:" + errorInfo.getErrorInfo());
        }
        return rs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBoxProjectCash(List<ImportProjectCash> listData) {
        AjaxResult rs = AjaxResult.success("导入失败");
        //验证数据
        ImportError errorInfo = new ImportError();
        if (verificationProjectCash(listData, errorInfo)) {
            String  projectInfoList= FastJsonUtils.Object2String(listData);
            List<ImportProjectCash> listItem= FastJsonUtils.getJsonToList(projectInfoList,ImportProjectCash.class);
            //查所有ID、项目名称
            List<String> dateList3 = new ArrayList<>();
            List<ProjectResult> plist = getAllNameAndId();
            plist.forEach(item->{
                dateList3.add(item.getName());
            });
            //比对项目名是否存在
            for (ImportProjectCash projectInfo : listItem) {
                if(!dateList3.contains(projectInfo.getXmqkXmmc())){
                    rs = AjaxResult.error("项目不存在："+projectInfo.getXmqkXmmc());
                    return rs;
                }
            }
            //给项目ID赋值
            for (ImportProjectCash projectInfo : listItem) {
                projectInfo.setOutIn('0');
                String xmqkXmmc = projectInfo.getXmqkXmmc();
                for (ProjectResult projectResult : plist) {
                    if(xmqkXmmc.equals(projectResult.getName())) {
                        projectInfo.setProjectId(projectResult.getId());
                    }
                }
            }
            List<ImportProjectCash> addList=new ArrayList<>();
            List<ImportProjectCash> upList=new ArrayList<>();
            for(ImportProjectCash projectCash0 : listItem){
                Integer  id=  projectCashMapper.selectProjectInfoChekCashIn(projectCash0);
                if(id!=null&&id>0){
                    projectCash0.setCashId(id+"");
                    upList.add(projectCash0);
                }else {
                    addList.add(projectCash0);
                }
            }
            if(addList.size()>0){
                for (ImportProjectCash projectCash : addList) {
                    projectCashMapper.insertProjectCashImport(projectCash);
                    projectCashMapper.insertProjectCashInfoImport(projectCash);
                }
            }
            if(upList.size()>0){
                for (ImportProjectCash projectCash : upList) {
                    projectCashMapper.updateProjectCashTo(projectCash);
                    projectCashMapper.updateProjectCashInfoTo(projectCash);
                }
            }

            rs = AjaxResult.success("数据导入成功");
        }else{
            rs = AjaxResult.error("序号" + errorInfo.getProjectName() + "下:" + errorInfo.getErrorInfo());
        }
        return rs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBoxProjectCashIn(List<ImportProjectCash> listData) {
        AjaxResult rs = AjaxResult.success("导入失败");
        //验证数据
        ImportError errorInfo = new ImportError();
        if (verificationProjectCash(listData, errorInfo)) {
            String  projectInfoList= FastJsonUtils.Object2String(listData);
            List<ImportProjectCash> listItem= FastJsonUtils.getJsonToList(projectInfoList,ImportProjectCash.class);
            //查所有ID、项目名称
            List<String> dateList3 = new ArrayList<>();
            List<ProjectResult> plist = getAllNameAndId();
            plist.forEach(item->{
                dateList3.add(item.getName());
            });
            //比对项目名是否存在
            for (ImportProjectCash projectInfo : listItem) {
                if(!dateList3.contains(projectInfo.getXmqkXmmc())){
                    rs = AjaxResult.error("项目不存在："+projectInfo.getXmqkXmmc());
                    return rs;
                }
            }
            //给项目ID赋值
            for (ImportProjectCash projectInfo : listItem) {
                projectInfo.setOutIn('1');
                String xmqkXmmc = projectInfo.getXmqkXmmc();
                for (ProjectResult projectResult : plist) {
                    if(xmqkXmmc.equals(projectResult.getName())) {
                        projectInfo.setProjectId(projectResult.getId());
                    }
                }
            }
            List<ImportProjectCash> addList=new ArrayList<>();
            List<ImportProjectCash> upList=new ArrayList<>();
            for(ImportProjectCash projectCash0 : listItem){
                Integer  id=  projectCashMapper.selectProjectInfoChekCashIn(projectCash0);
                if(id!=null&&id>0){
                    projectCash0.setCashId(id+"");
                    upList.add(projectCash0);
                }else {
                    addList.add(projectCash0);
                }
            }
            if(addList.size()>0){
                for (ImportProjectCash projectCash : addList) {
                    projectCashMapper.insertProjectCashImport(projectCash);
                    projectCashMapper.insertProjectCashInfoImport(projectCash);
                }
            }
            if(upList.size()>0){
                for (ImportProjectCash projectCash : upList) {
                    projectCashMapper.updateProjectCashTo(projectCash);
                    projectCashMapper.updateProjectCashInfoTo(projectCash);
                }
            }

            rs = AjaxResult.success("数据导入成功");
        }else{
            rs = AjaxResult.error("序号" + errorInfo.getProjectName() + "下:" + errorInfo.getErrorInfo());
        }
        return rs;
    }

    /*
    还款情况
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBoxRepayment(List<ProjectFinancingRepayment> listData) {
        AjaxResult rs = AjaxResult.success("导入失败");
        String  stringUuid=IdUtils.fastSimpleUUID();
        //验证数据
        ImportError errorInfo = new ImportError();
        if (verificationRepayment(listData, errorInfo)) {
            String  projectInfoList= FastJsonUtils.Object2String(listData);
            List<ProjectFinancingRepayment> listItem= FastJsonUtils.getJsonToList(projectInfoList,ProjectFinancingRepayment.class);
            //查所有ID、项目名称
            List<String> dateList3 = new ArrayList<>();
            List<ProjectResult> plist = getAllNameAndId();
            plist.forEach(item->{
                dateList3.add(item.getName());
            });
            //比对项目名是否存在
            for (ProjectFinancingRepayment projectInfo : listItem) {
                if(!dateList3.contains(projectInfo.getXmqkXmmc())){
                    rs = AjaxResult.error("项目不存在："+projectInfo.getXmqkXmmc());
                    return rs;
                }
            }
            //给项目ID赋值,项目名称相同的赋值项目id
            for (ProjectFinancingRepayment projectInfo : listItem) {
                String xmqkXmmc = projectInfo.getXmqkXmmc();
                for (ProjectResult projectResult : plist) {
                    if(xmqkXmmc.equals(projectResult.getName())) {
                        projectInfo.setProjectId(projectResult.getId());
                        projectInfo.setRzjg(projectResult.getXmqkRzf());
                    }
                }
            }

            List<ProjectFinancingRepayment> addUpdateItem = new ArrayList<>();

            for (ProjectFinancingRepayment projectInfo : listItem) {

                //比对融资机构(获取融资机构id)
                Integer i =  projectFinancingMapper.selectProjectFinancingByProjectIdAndRzjg(projectInfo.getProjectId(),projectInfo.getRzjg());
                if(i!=null && i>0){
                    projectInfo.setFinancingId(Long.parseLong(i+""));
                }else{
                    rs = AjaxResult.error(projectInfo.getXmqkXmmc()+"无融资方："+projectInfo.getRzjg());
                    return rs;
                }

                addUpdateItem.add(projectInfo);
            }
            if(addUpdateItem.size()>0){
                List< FinancingStill>  upFinancingStillList=  dataToAdd(addUpdateItem);
                if(upFinancingStillList.size()>0){
//                    financingStillMapper.insertUpdateBatch(upFinancingStillList);
                    StringBuffer  sql =new StringBuffer();
                    int lensII=0;
                    int count=4;
                    for( int lensI=0;lensI< upFinancingStillList.size();lensI++ ){

                        sql.append(" insert into  financing_still( ");
                        sql.append(" id, ");
                        sql.append(" financing_id, ");
                        sql.append(" financing_rq, ");
                        sql.append(" financing_cb, ");
                        sql.append(" financing_lx, ");
                        sql.append(" financing_sxf, ");
                        sql.append(" financing_bzj, ");
                        sql.append(" begin_pay, ");
                        sql.append(" financing_bysjhk) ");

                        sql.append(" values(  ");
                        sql.append(upFinancingStillList.get(lensI).getId()).append(",");
                        sql.append(upFinancingStillList.get(lensI).getFinancingId()).append(",");
                        sql.append("'").append(  DateUtils.dateTimeFormat(upFinancingStillList.get(lensI).getFinancingRq(),"yyyy-MM-dd")).append("'").append(",");
                        sql.append(upFinancingStillList.get(lensI).getFinancingCb()).append(",");
                        sql.append(upFinancingStillList.get(lensI).getFinancingLx()).append(",");
                        sql.append(upFinancingStillList.get(lensI).getFinancingSxf()).append(",");
                        sql.append(upFinancingStillList.get(lensI).getFinancingBzj()).append(",");
                        sql.append("'").append("".equals(upFinancingStillList.get(lensI).getBeginPay())?null:upFinancingStillList.get(lensI).getBeginPay()).append("'").append(",");
                        sql.append(upFinancingStillList.get(lensI).getFinancingBysjhk());
                        sql.append(" )");

                        sql.append(" on duplicate key update   ");
                        sql.append("begin_pay =").append("'").append("".equals(upFinancingStillList.get(lensI).getBeginPay())?null:upFinancingStillList.get(lensI).getBeginPay()).append("'").append(",");

                        sql.append("financing_cb =").append(upFinancingStillList.get(lensI).getFinancingCb()).append(",");
                        sql.append("financing_lx =").append(upFinancingStillList.get(lensI).getFinancingLx()).append(",");
                        sql.append("financing_sxf =").append(upFinancingStillList.get(lensI).getFinancingSxf());
                        sql.append("  ;");

                        if(lensII>count){
                            financingStillMapper.insertUpdateBatchSQl(sql.toString());
                            lensII=0;
                            sql.setLength(0);
                        }else {
                            lensII++;
                        }


                    }
                    if(sql.length()>0){

                        financingStillMapper.insertUpdateBatchSQl(sql.toString());
                    }
                }

            }

            rs = AjaxResult.success("数据导入成功");
        }else{
            rs = AjaxResult.error("序号" + errorInfo.getProjectName() + "下:" + errorInfo.getErrorInfo());
        }
        return rs;
    }




    public  List< FinancingStill>  dataToAdd(List<ProjectFinancingRepayment>  items){
        List< FinancingStill>  listRs=new ArrayList<>();
        for (ProjectFinancingRepayment item : items) {
            if(StringUtils.isEmpty(item.getRepaymentYear())){
                continue;
            }
            String hkDate=item.getBeginPay().substring(8,10);
            FinancingStill    financingStill1=new FinancingStill();
            //融资表关联id
            financingStill1.setFinancingId(item.getFinancingId());
            financingStill1.setBeginPay(item.getBeginPay());
            //日期  hkDate
            financingStill1.setFinancingRq(DateUtils.dateTime("yyyy-MM-dd" ,item.getRepaymentYear()+"-"+"01"+"-"+hkDate));
            financingStill1.setQuDate(item.getRepaymentYear()+"-"+"01");
            //成本
            financingStill1.setFinancingCb(item.getJanPrincipal());
            //利息
            financingStill1.setFinancingLx(item.getJanInterest());
            //手续费
            financingStill1.setFinancingSxf(item.getJanService());
            //判断下如果没有金额不添加数据
            BigDecimal rs1=  DecimalUtils.format(item.getJanPrincipal()).add( DecimalUtils.format(item.getJanInterest())).add( DecimalUtils.format(item.getJanService()));
            if(rs1.compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingStill1);
            }






            FinancingStill    financingStill2=new FinancingStill();
            //融资表关联id
            financingStill2.setFinancingId(item.getFinancingId());
            financingStill2.setBeginPay(item.getBeginPay());
            //日期
            financingStill2.setFinancingRq(DateUtils.dateTime("yyyy-MM-dd" ,item.getRepaymentYear()+"-"+"02"+"-"+hkDate));
            financingStill2.setQuDate(item.getRepaymentYear()+"-"+"02");
            //成本
            financingStill2.setFinancingCb(item.getFebPrincipal());
            //利息
            financingStill2.setFinancingLx(item.getFebInterest());
            //手续费
            financingStill2.setFinancingSxf(item.getFebService());
            //判断下如果没有金额不添加数据
            BigDecimal rs2=  DecimalUtils.format(item.getFebPrincipal()).add( DecimalUtils.format(item.getFebInterest())).add( DecimalUtils.format(item.getFebService()));
            if(rs2.compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingStill2);
            }



            FinancingStill    financingStill3=new FinancingStill();
            //融资表关联id
            financingStill3.setFinancingId(item.getFinancingId());
            financingStill3.setBeginPay(item.getBeginPay());
            //日期
            financingStill3.setFinancingRq(DateUtils.dateTime("yyyy-MM-dd" ,item.getRepaymentYear()+"-"+"03"+"-"+hkDate));
            financingStill3.setQuDate(item.getRepaymentYear()+"-"+"03");
            //成本
            financingStill3.setFinancingCb(item.getMarPrincipal());
            //利息
            financingStill3.setFinancingLx(item.getMarInterest());
            //手续费
            financingStill3.setFinancingSxf(item.getMarService());
            //判断下如果没有金额不添加数据
            BigDecimal rs3=  DecimalUtils.format(item.getMarPrincipal()).add( DecimalUtils.format(item.getMarInterest())).add( DecimalUtils.format(item.getMarService()));
            if(rs3.compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingStill3);
            }



            FinancingStill    financingStill4=new FinancingStill();
            //融资表关联id
            financingStill4.setFinancingId(item.getFinancingId());
            financingStill4.setBeginPay(item.getBeginPay());
            //日期
            financingStill4.setFinancingRq(DateUtils.dateTime("yyyy-MM-dd" ,item.getRepaymentYear()+"-"+"04"+"-"+hkDate));
            financingStill4.setQuDate(item.getRepaymentYear()+"-"+"04");
            //成本
            financingStill4.setFinancingCb(item.getAprPrincipal());
            //利息
            financingStill4.setFinancingLx(item.getAprInterest());
            //手续费
            financingStill4.setFinancingSxf(item.getAprService());
            //判断下如果没有金额不添加数据
            BigDecimal rs4=  DecimalUtils.format(item.getAprPrincipal()).add( DecimalUtils.format(item.getAprInterest())).add( DecimalUtils.format(item.getAprService()));
            if(rs4.compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingStill4);
            }



            FinancingStill    financingStill5=new FinancingStill();
            //融资表关联id
            financingStill5.setFinancingId(item.getFinancingId());
            financingStill5.setBeginPay(item.getBeginPay());
            //日期
            financingStill5.setFinancingRq(DateUtils.dateTime("yyyy-MM-dd" ,item.getRepaymentYear()+"-"+"05"+"-"+hkDate));
            financingStill5.setQuDate(item.getRepaymentYear()+"-"+"05");
            //成本
            financingStill5.setFinancingCb(item.getMayPrincipal());
            //利息
            financingStill5.setFinancingLx(item.getMayInterest());
            //手续费
            financingStill5.setFinancingSxf(item.getMayService());
            //判断下如果没有金额不添加数据
            BigDecimal rs5=  DecimalUtils.format(item.getMayPrincipal()).add( DecimalUtils.format(item.getMayInterest())).add( DecimalUtils.format(item.getMayService()));
            if(rs5.compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingStill5);
            }



            FinancingStill    financingStill6=new FinancingStill();
            //融资表关联id
            financingStill6.setFinancingId(item.getFinancingId());
            financingStill6.setBeginPay(item.getBeginPay());
            //日期
            financingStill6.setFinancingRq(DateUtils.dateTime("yyyy-MM-dd" ,item.getRepaymentYear()+"-"+"06"+"-"+hkDate));
            financingStill6.setQuDate(item.getRepaymentYear()+"-"+"06");
            //成本
            financingStill6.setFinancingCb(item.getJunPrincipal());
            //利息
            financingStill6.setFinancingLx(item.getJunInterest());
            //手续费
            financingStill6.setFinancingSxf(item.getJunService());
            //判断下如果没有金额不添加数据
            BigDecimal rs6=  DecimalUtils.format(item.getJunPrincipal()).add( DecimalUtils.format(item.getJunInterest())).add( DecimalUtils.format(item.getJunService()));
            if(rs6.compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingStill6);
            }



            FinancingStill    financingStill7=new FinancingStill();
            //融资表关联id
            financingStill7.setFinancingId(item.getFinancingId());
            financingStill7.setBeginPay(item.getBeginPay());
            //日期
            financingStill7.setFinancingRq(DateUtils.dateTime("yyyy-MM-dd" ,item.getRepaymentYear()+"-"+"07"+"-"+hkDate));
            financingStill7.setQuDate(item.getRepaymentYear()+"-"+"07");
            //成本
            financingStill7.setFinancingCb(item.getJulPrincipal());
            //利息
            financingStill7.setFinancingLx(item.getJulInterest());
            //手续费
            financingStill7.setFinancingSxf(item.getJulService());
            //判断下如果没有金额不添加数据
            BigDecimal rs7=  DecimalUtils.format(item.getJulPrincipal()).add( DecimalUtils.format(item.getJulInterest())).add( DecimalUtils.format(item.getJulService()));
            if(rs7.compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingStill7);
            }



            FinancingStill    financingStill8=new FinancingStill();
            //融资表关联id
            financingStill8.setFinancingId(item.getFinancingId());
            financingStill8.setBeginPay(item.getBeginPay());
            //日期
            financingStill8.setFinancingRq(DateUtils.dateTime("yyyy-MM-dd" ,item.getRepaymentYear()+"-"+"08"+"-"+hkDate));
            financingStill8.setQuDate(item.getRepaymentYear()+"-"+"08");
            //成本
            financingStill8.setFinancingCb(item.getAugPrincipal());
            //利息
            financingStill8.setFinancingLx(item.getAugInterest());
            //手续费
            financingStill8.setFinancingSxf(item.getAugService());
            //判断下如果没有金额不添加数据
            BigDecimal rs8=  DecimalUtils.format(item.getAugPrincipal()).add( DecimalUtils.format(item.getAugInterest())).add( DecimalUtils.format(item.getAugService()));
            if(rs8.compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingStill8);
            }



            FinancingStill    financingStill9=new FinancingStill();
            //融资表关联id
            financingStill9.setFinancingId(item.getFinancingId());
            financingStill9.setBeginPay(item.getBeginPay());
            //日期
            financingStill9.setFinancingRq(DateUtils.dateTime("yyyy-MM-dd" ,item.getRepaymentYear()+"-"+"09"+"-"+hkDate));
            financingStill9.setQuDate(item.getRepaymentYear()+"-"+"09");
            //成本
            financingStill9.setFinancingCb(item.getSepPrincipal());
            //利息
            financingStill9.setFinancingLx(item.getSepInterest());
            //手续费
            financingStill9.setFinancingSxf(item.getSepService());
            BigDecimal rs9=  DecimalUtils.format(item.getSepPrincipal()).add( DecimalUtils.format(item.getSepInterest())).add( DecimalUtils.format(item.getSepService()));
            if(rs9.compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingStill9);
            }




            FinancingStill    financingStill10=new FinancingStill();
            //融资表关联id
            financingStill10.setFinancingId(item.getFinancingId());
            financingStill10.setBeginPay(item.getBeginPay());
            //日期
            financingStill10.setFinancingRq(DateUtils.dateTime("yyyy-MM-dd" ,item.getRepaymentYear()+"-"+"10"+"-"+hkDate));
            financingStill10.setQuDate(item.getRepaymentYear()+"-"+"10");
            //成本
            financingStill10.setFinancingCb(item.getOctPrincipal());
            //利息
            financingStill10.setFinancingLx(item.getOctInterest());
            //手续费
            financingStill10.setFinancingSxf(item.getOctService());
            //判断下如果没有金额不添加数据
            BigDecimal rs10=  DecimalUtils.format(item.getOctPrincipal()).add( DecimalUtils.format(item.getOctInterest())).add( DecimalUtils.format(item.getOctService()));
            if(rs10.compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingStill10);
            }




            FinancingStill    financingStill11=new FinancingStill();
            //融资表关联id
            financingStill11.setFinancingId(item.getFinancingId());
            financingStill11.setBeginPay(item.getBeginPay());
            //日期
            financingStill11.setFinancingRq(DateUtils.dateTime("yyyy-MM-dd" ,item.getRepaymentYear()+"-"+"11"+"-"+hkDate));
            financingStill11.setQuDate(item.getRepaymentYear()+"-"+"11");
            //成本
            financingStill11.setFinancingCb(item.getNovPrincipal());
            //利息
            financingStill11.setFinancingLx(item.getNovInterest());
            //手续费
            financingStill11.setFinancingSxf(item.getNovService());
            //判断下如果没有金额不添加数据
            BigDecimal rs11=  DecimalUtils.format(item.getNovPrincipal()).add( DecimalUtils.format(item.getNovInterest())).add( DecimalUtils.format(item.getNovService()));
            if(rs11.compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingStill11);
            }



            FinancingStill    financingStill12=new FinancingStill();
            //融资表关联id
            financingStill12.setFinancingId(item.getFinancingId());
            financingStill12.setBeginPay(item.getBeginPay());
            //日期
            financingStill12.setFinancingRq(DateUtils.dateTime("yyyy-MM-dd" ,item.getRepaymentYear()+"-"+"12"+"-"+hkDate));
            financingStill12.setQuDate(item.getRepaymentYear()+"-"+"12");
            //成本
            financingStill12.setFinancingCb(item.getDecPrincipal());
            //利息
            financingStill12.setFinancingLx(item.getDecInterest());
            //手续费
            financingStill12.setFinancingSxf(item.getDecService());
            //判断下如果没有金额不添加数据
            BigDecimal rs12=  DecimalUtils.format(item.getDecPrincipal()).add( DecimalUtils.format(item.getDecInterest())).add( DecimalUtils.format(item.getDecService()));
            if(rs12.compareTo(BigDecimal.ZERO)>0){
                listRs.add(financingStill12);
            }


        }

        return listRs;
    }




    private boolean verificationRepayment(List<ProjectFinancingRepayment> listData, ImportError errorInfo) {
        Boolean rs = true;
        for (ProjectFinancingRepayment item : listData) {
            errorInfo.setProjectName(item.getXh());
            if (StringUtils.isEmpty(item.getXmqkXmmc())) {
                errorInfo.setErrorInfo("项目名称不能为空！");
                rs = false;
                break;
            }
//            if (StringUtils.isEmpty(item.getRzjg())) {
//                errorInfo.setErrorInfo("融资机构不能为空！");
//                rs = false;
//                break;
//            }
            if (StringUtils.isEmpty(item.getRepaymentYear())) {
                errorInfo.setErrorInfo("年份不能为空！");
                rs = false;
                break;
            }
            if (item.getBeginPay()==null||"".equals(item.getBeginPay())) {
                errorInfo.setErrorInfo("开始还本日期不能为空");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getBeginPay())) {
                if (!Valid.isValidDate(item.getBeginPay())) {
                    errorInfo.setErrorInfo("开始还本日期时间格式不对");
                    rs = false;
                    break;
                }
            }
        }
        return rs;
    }


    Boolean verificationProjectCash (List<ImportProjectCash> listData, ImportError errorInfo){
        Boolean rs = true;
        for (ImportProjectCash item : listData) {
            errorInfo.setProjectName(item.getXh());
            if (StringUtils.isEmpty(item.getXmqkXmmc())) {
                errorInfo.setErrorInfo("项目名称不能为空！");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getProjectEpc())) {
                if (!Valid.isValidDate(item.getProjectEpc())) {
                    errorInfo.setErrorInfo("Epc签约日期时间格式不对");
                    rs = false;
                    break;
                }
            }
        }
        return rs;
    }


    Boolean verificationTrueExpectedFinancing (List<ExpectedTrueFinancing> listData, ImportError errorInfo){
        Boolean rs = true;
        for (ExpectedTrueFinancing item : listData) {
            errorInfo.setProjectName(item.getXh());
            if (StringUtils.isEmpty(item.getXmqkXmmc())) {
                errorInfo.setErrorInfo("项目名称不能为空！");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getTimes())) {
                if (!Valid.isValidDate(item.getTimes())) {
                    errorInfo.setErrorInfo("实际放款日期时间格式不对");
                    rs = false;
                    break;
                }
            }
            if (Valid.isNoNull(item.getFinancingRq())) {
                if (!Valid.isValidDate(item.getFinancingRq())) {
                    errorInfo.setErrorInfo("实际还款日期时间格式不对");
                    rs = false;
                    break;
                }
            }
        }
        return rs;
    }
    Boolean verificationExpectedFinancing (List<ExpectedFinancing> listData, ImportError errorInfo){
        Boolean rs = true;
        for (ExpectedFinancing item : listData) {
            errorInfo.setProjectName(item.getXh());
            if (StringUtils.isEmpty(item.getXmqkXmmc())) {
                errorInfo.setErrorInfo("项目名称不能为空！");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getTimes())) {
                if (!Valid.isValidDate(item.getTimes())) {
                    errorInfo.setErrorInfo("预计放款日期时间格式不对");
                    rs = false;
                    break;
                }
            }
            if (Valid.isNoNull(item.getFinancingRq())) {
                if (!Valid.isValidDate(item.getFinancingRq())) {
                    errorInfo.setErrorInfo("预计还款日期时间格式不对");
                    rs = false;
                    break;
                }
            }
        }
        return rs;
    }
    Boolean verificationFinancing (List<FinancingInfo> listData, ImportError errorInfo){
        Boolean rs = true;
        for (FinancingInfo item : listData) {
            errorInfo.setProjectName(item.getXh());
            if (StringUtils.isEmpty(item.getXmqkXmmc())) {
                errorInfo.setErrorInfo("项目名称不能为空！");
                rs = false;
                break;
            }
//            if (Valid.isNoNull(item.getXmrzQyrq())) {
//                if (!Valid.isValidDate(item.getXmrzQyrq())) {
//                    errorInfo.setErrorInfo("签约日期格式不对");
//                    rs = false;
//                    break;
//                }
//            }
            if (Valid.isNoNull(item.getXmrzYjxbfksj())) {
                if (!Valid.isValidDate(item.getXmrzYjxbfksj())) {
                    errorInfo.setErrorInfo("预计下次放款时间格式不对");
                    rs = false;
                    break;
                }
            }
            if (StringUtils.isNotEmpty(item.getXmrzRzqk())) {
                BusinessDictionary pidByName = BusinessUtils.getPidByName(108L, item.getXmrzRzqk());
                if (pidByName==null) {
                    errorInfo.setErrorInfo("融资情况格式请参考字典！");
                    rs = false;
                    break;
                } else {
                    item.setXmrzRzqk(pidByName.getId()+"");
                }
            }

        }
        return rs;
    }

    Boolean verificationElectricity (List<ElectricityCash> listData, ImportError errorInfo){
        Boolean rs = true;
        for (ElectricityCash item : listData) {
            errorInfo.setProjectName(item.getXh());
            if (StringUtils.isEmpty(item.getXmqkXmmc())) {
                errorInfo.setErrorInfo("项目名称不能为空！");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getDltjRq())) {
                if (!Valid.isValidDate(item.getDltjRq())) {
                    errorInfo.setErrorInfo("日期时间格式不对");
                    rs = false;
                    break;
                }
            }
        }
        return rs;
    }
    Boolean verificationManagement (List<ManagementCash> listData, ImportError errorInfo){
        Boolean rs = true;
        for (ManagementCash item : listData) {
            //关联ID
            //item.setXjlsjqkYjxjlId(IdUtils.fastSimpleUUID());
//            item.setXmqkProjectId(IdUtils.fastSimpleUUID());
            errorInfo.setProjectName(item.getXh());
            if (StringUtils.isEmpty(item.getXmqkXmmc())) {
                errorInfo.setErrorInfo("项目名称不能为空！");
                rs = false;
                break;
            }
//            if (Valid.isNoNull(item.getXjlyjqkRq())) {
//                if (!Valid.isValidDate(item.getXjlyjqkRq())) {
//                    errorInfo.setErrorInfo("日期时间格式不对");
//                    rs = false;
//                    break;
//                }
//            }
        }
        return rs;
    }

    Boolean verificationConstruction (List<ProjectInfoVerify> listData, ImportError errorInfo){
        Boolean rs = true;
        for (ProjectInfoVerify item : listData) {
            if (StringUtils.isEmpty(item.getXmqkXmmc())) {
                errorInfo.setErrorInfo("项目名称不能为空！");
                rs = false;
                break;
            }
        }
        return rs;
    }

    //导入数据验证
    Boolean verification(List<ProjectInfoVerify> listData, ImportError errorInfo) {
        Boolean rs = true;
        //  7 项目现状、 8 项目类型、 9 建设状态 、 50 履约分公司
        BusinessDictionary  isBusiness=null;
        for (ProjectInfoVerify item : listData) {
            //设置项目id
            item.setXmqkProjectId(IdUtils.fastSimpleUUID());
            errorInfo.setProjectName(item.getXh());
            if (StringUtils.isEmpty(item.getXmqkXmmc())) {
                errorInfo.setErrorInfo("项目名称不能为空！");
                rs = false;
                break;
            }
            if (StringUtils.isNotEmpty(item.getXmqkXmlxName())) {
                isBusiness=BusinessUtils.getPidByName(8L,item.getXmqkXmlxName());
                if (isBusiness==null) {
                    errorInfo.setErrorInfo("项目类型格式请参考字典！");
                    rs = false;
                    break;
                } else {
                    item.setXmqkXmlx(new Long(isBusiness.getId()));
                }
            }


            if (StringUtils.isNotEmpty(item.getXmqkXmxzName())) {
                isBusiness=BusinessUtils.getPidByName(7L,item.getXmqkXmxzName());
                if (isBusiness==null) {
                    errorInfo.setErrorInfo("项目现状格式请参考字典！");
                    rs = false;
                    break;
                } else {
                    item.setXmqkXmxz(new Long(isBusiness.getId()));
                }
            }


            if (StringUtils.isNotEmpty(item.getXmqkLyfgsName())) {
                isBusiness=BusinessUtils.getPidByName(50L,item.getXmqkLyfgsName());
                if (isBusiness==null) {
                    errorInfo.setErrorInfo("履约分公司格式请参考字典！");
                    rs = false;
                    break;
                } else {
                    item.setXmqkLyfgs(new Long(isBusiness.getId()));
                }
            }

            if (StringUtils.isNotEmpty(item.getXmqkJsztName())) {
                isBusiness=BusinessUtils.getPidByName( 9L,item.getXmqkJsztName());
                if (isBusiness==null) {
                    errorInfo.setErrorInfo("建设状态格式请参考字典！");
                    rs = false;
                    break;
                } else {
                    item.setXmqkJszt(isBusiness.getId());
                }
            }





//
//            if (Valid.isNoNull(item.getXmqkEpcZjjsj()) && !Valid.isDouble(item.getXmqkEpcZjjsj() + "", 2)) {
//
//                errorInfo.setErrorInfo("增加EPC结算价,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//
//
//            if (Valid.isNoNull(item.getXmqkYjsyhj()) && !Valid.isDouble(item.getXmqkYjsyhj() + "", 2)) {
//
//                errorInfo.setErrorInfo("预计收入合计,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//
//
//            if (Valid.isNoNull(item.getXmqkXmgscb()) && !Valid.isDouble(item.getXmqkXmgscb() + "", 2)) {
//
//                errorInfo.setErrorInfo("项目公司成本,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//
//
//            if (Valid.isNoNull(item.getXmqkXmepccb()) && !Valid.isDouble(item.getXmqkXmepccb() + "", 2)) {
//
//                errorInfo.setErrorInfo("项目EPC成本,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//
//
//            if (Valid.isNoNull(item.getXmqkQzzyf()) && !Valid.isDouble(item.getXmqkQzzyf() + "", 2)) {
//
//                errorInfo.setErrorInfo("其中资源费,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//
//
//            if (Valid.isNoNull(item.getXmqkZcb()) && !Valid.isDouble(item.getXmqkZcb() + "", 2)) {
//
//                errorInfo.setErrorInfo("总成本,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//
//
//            if (Valid.isNoNull(item.getXmqkSjYsysgk()) && !Valid.isDouble(item.getXmqkSjYsysgk() + "", 2)) {
//
//                errorInfo.setErrorInfo("已收预收购款,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//
//
//            if (Valid.isNoNull(item.getXmqkYsrzk()) && !Valid.isDouble(item.getXmqkYsrzk() + "", 2)) {
//
//                errorInfo.setErrorInfo("已收融资款,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//
//
//            if (Valid.isNoNull(item.getXmqkGzk()) && !Valid.isDouble(item.getXmqkGzk() + "", 2)) {
//
//                errorInfo.setErrorInfo("工程回款(业主回款),格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//
//
//            if (Valid.isNoNull(item.getXmqkYsdf()) && !Valid.isDouble(item.getXmqkYsdf() + "", 2)) {
//
//                errorInfo.setErrorInfo("已收电费,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//
//
//            if (Valid.isNoNull(item.getXmqkSjsrhj()) && !Valid.isDouble(item.getXmqkSjsrhj() + "", 2)) {
//
//                errorInfo.setErrorInfo("实际收入合计,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//
//
//            if (Valid.isNoNull(item.getXmqkYsye()) && !Valid.isDouble(item.getXmqkYsye() + "", 2)) {
//
//                errorInfo.setErrorInfo("预计应收余额,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//
//
//            if (Valid.isNoNull(item.getXmqkYfsbk()) && !Valid.isDouble(item.getXmqkYfsbk() + "", 2)) {
//
//                errorInfo.setErrorInfo("已付设备款,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//
//            if (Valid.isNoNull(item.getXmqkYfjak()) && !Valid.isDouble(item.getXmqkYfjak() + "", 2)) {
//
//                errorInfo.setErrorInfo("已付建安款,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//
//            if (Valid.isNoNull(item.getXmqkYfqtkx()) && !Valid.isDouble(item.getXmqkYfqtkx() + "", 2)) {
//
//                errorInfo.setErrorInfo("已付其他款项,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//            if (Valid.isNoNull(item.getXmqkYfzje()) && !Valid.isDouble(item.getXmqkYfzje() + "", 2)) {
//
//                errorInfo.setErrorInfo("已付总金额,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//            if (Valid.isNoNull(item.getXmqkYssjye()) && !Valid.isDouble(item.getXmqkYssjye() + "", 2)) {
//
//                errorInfo.setErrorInfo("实际应收余额,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//            if (Valid.isNoNull(item.getXmqkSjyfwfkx()) && !Valid.isDouble(item.getXmqkSjyfwfkx() + "", 2)) {
//
//                errorInfo.setErrorInfo("实际应付未付款项,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//            if (Valid.isNoNull(item.getXmqkHtzfye()) && !Valid.isDouble(item.getXmqkHtzfye() + "", 2)) {
//
//                errorInfo.setErrorInfo("合同支付余额,格式不对正确为：00.00");
//                rs = false;
//                break;
//            }
//            if (Valid.isNoNull(item.getXmqkYflybzj()) && !Valid.isDouble(item.getXmqkYflybzj() + "", 4)) {
//
//                errorInfo.setErrorInfo("已付履约保证金,格式不对正确为：00.0000");
//                rs = false;
//                break;
//            }
//            if (Valid.isNoNull(item.getXmqkXmdqz()) && !Valid.isDouble(item.getXmqkXmdqz() + "",0)) {
//
//                errorInfo.setErrorInfo("项目当前产值,格式不对正确为整数：00");
//                rs = false;
//                break;
//            }
//
//
//            if (Valid.isNoNull(item.getXmqkSyzkw()) && !Valid.isDouble(item.getXmqkSyzkw() + "",0)) {
//
//                errorInfo.setErrorInfo("升压站开挖,格式为整数");
//                rs = false;
//                break;
//            }
//            if (Valid.isNoNull(item.getXmqkJckw()) && !Valid.isDouble(item.getXmqkJckw() + "",0)) {
//
//                errorInfo.setErrorInfo("基础开挖,格式为整数");
//                rs = false;
//                break;
//            }
//            if (Valid.isNoNull(item.getXmqkSbdhl()) && !Valid.isDouble(item.getXmqkSbdhl() + "",0)) {
//
//                errorInfo.setErrorInfo("设备到货率,格式为整数");
//                rs = false;
//                break;
//            }
//            if (Valid.isNoNull(item.getXmqkSbaz()) && !Valid.isDouble(item.getXmqkSbaz() + "",0)) {
//
//                errorInfo.setErrorInfo("设备安装,格式为整数");
//                rs = false;
//                break;
//            }
//            if (Valid.isNoNull(item.getXmqkWxsg()) && !Valid.isDouble(item.getXmqkWxsg() + "",0)) {
//
//                errorInfo.setErrorInfo("外线安装,格式为整数");
//                rs = false;
//                break;
//            }



        }

        return rs;
    }



    public String getStringName( List<ProjectInfo> cf) {
        StringBuffer  rs=new StringBuffer();
        for (ProjectInfo item : cf) {
            rs.append(item.getXmqkXmmc());
            rs.append(",");
        }
        return rs.toString();
    }

}



