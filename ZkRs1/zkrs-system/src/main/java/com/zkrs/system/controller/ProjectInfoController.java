package com.zkrs.system.controller;

//import cn.afterturn.easypoi.excel.ExcelExportUtil;
//import cn.afterturn.easypoi.excel.ExcelImportUtil;
//import cn.afterturn.easypoi.excel.entity.ExportParams;
//import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.zkrs.common.annotation.Log;
import com.zkrs.common.core.controller.BaseController;
import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.common.core.page.TableDataInfo;
import com.zkrs.common.enums.BusinessType;
import com.zkrs.common.utils.poi.ExcelUtil;
import com.zkrs.common.utils.uuid.IdUtils;
import com.zkrs.system.domain.*;
import com.zkrs.system.service.*;
import com.zkrs.system.tool.ExcelTool;
import com.zkrs.system.tool.FastJsonUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目情况Controller
 *
 * @author zkrs
 * @date 2022-06-30
 */
@RestController
@RequestMapping("/system/info")
public class ProjectInfoController extends BaseController
{
    @Autowired
    private IProjectInfoService projectInfoService;

    @Autowired
    private IActualCashService actualCashService;

    @Autowired
    private IProjectFinancingService projectFinancing;

    @Autowired
    private IFinancingDateService financingDateService;

    @Autowired
    private IElectricityStatisticsService electricityStatisticsService;

    @Autowired
    private IProjectCashService projectCashService;

    @Autowired
    private IProjectFinancingRepaymentService projectFinancingRepaymentService;

    /**
     * 查询项目情况列表
     */
   // @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectInfo projectInfo)
    {
        startPage();
        List<ProjectInfo> list = projectInfoService.selectProjectInfoList(projectInfo);
        return getDataTable(list);
    }

    /**
     * 导出项目情况列表
     */
   // @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "项目情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectInfo projectInfo)
    {
        List<ProjectInfo> list = projectInfoService.selectProjectInfoList(projectInfo);
        ExcelUtil<ProjectInfo> util = new ExcelUtil<ProjectInfo>(ProjectInfo.class);
        util.exportExcel(response, list, "项目情况数据");
    }

    /**
     * 导出施工情况
     */
    @Log(title = "施工情况", businessType = BusinessType.EXPORT)
    @PostMapping("/exportConstruction")
    public void exportConstruction(HttpServletResponse response, ProjectInfo projectInfo)
    {
        List<ProjectInfo> list = projectInfoService.selectProjectInfoList(projectInfo);
        List<ExportConstructionBaseInfo> clist = new ArrayList<>();
        for (ProjectInfo info : list) {
            ExportConstructionBaseInfo constructionBaseInfo = new ExportConstructionBaseInfo();
            constructionBaseInfo.setXmqkXmmc(info.getXmqkXmmc());
            constructionBaseInfo.setXmqkFjcs(info.getXmqkFjcs());
            constructionBaseInfo.setXmqkTtcs(info.getXmqkTtcs());
            constructionBaseInfo.setXmqkZjcs(info.getXmqkZjcs());
            constructionBaseInfo.setXmqkZjiacs(info.getXmqkZjiacs());
            constructionBaseInfo.setXmqkSbdh(info.getXmqkSbdh());
            constructionBaseInfo.setXmqkSbaz(info.getXmqkSbaz());
            constructionBaseInfo.setXmqkSyzkw(info.getXmqkSyzkw());
            constructionBaseInfo.setXmqkJckw(info.getXmqkJckw());
            constructionBaseInfo.setXmqkWxsg(info.getXmqkWxsg());
            constructionBaseInfo.setXmqkSgjd(info.getXmqkSgjd());
            clist.add(constructionBaseInfo);
        }
        ExcelUtil<ExportConstructionBaseInfo> util = new ExcelUtil<ExportConstructionBaseInfo>(ExportConstructionBaseInfo.class);
        util.exportExcel(response, clist, "施工情况数据");
    }

    /**
     * 导出经营情况
     */
    @Log(title = "经营情况", businessType = BusinessType.EXPORT)
    @PostMapping("/exportManagement")
    public void exportManagement(HttpServletResponse response, ProjectInfo projectInfo)
    {
        List<ExportManagementCash> list =  actualCashService.selectProjectInfoList(projectInfo);
        ExcelUtil<ExportManagementCash> util = new ExcelUtil<ExportManagementCash>(ExportManagementCash.class);
        util.exportExcel(response, list, "经营情况数据");
    }

    /**
     * 导出融资情况
     */
    @Log(title = "融资情况", businessType = BusinessType.EXPORT)
    @PostMapping("/exportFinancing")
    public void exportFinancing(HttpServletResponse response, ProjectInfo projectInfo)
    {
        List<ExportFinancingInfo> list =  projectFinancing.selectProjectInfoList(projectInfo);
        ExcelUtil<ExportFinancingInfo> util = new ExcelUtil<ExportFinancingInfo>(ExportFinancingInfo.class);
        util.exportExcel(response, list, "融资情况数据");
    }

    /**
     * 导出融资情况
     */
    @Log(title = "融资情况", businessType = BusinessType.EXPORT)
    @PostMapping("/exportRepayment")
    public void exportRepayment(HttpServletResponse response, ProjectInfo projectInfo)
    {
        List<ExportRepayment> list =  projectFinancingRepaymentService.selectProjectInfoList(projectInfo);
        ExcelUtil<ExportRepayment> util = new ExcelUtil<ExportRepayment>(ExportRepayment.class);
        util.exportExcel(response, list, "还款情况数据");
    }


    /**
     * 导出预计融资情况
     */
    @Log(title = "预计融资情况", businessType = BusinessType.EXPORT)
    @PostMapping("/exportPreFinancing")
    public void exportPreFinancing(HttpServletResponse response, ProjectInfo projectInfo)
    {

        List<ExportExpectedFinancing> list =  financingDateService.selectProjectInfoList(projectInfo);
        ExcelUtil<ExportExpectedFinancing> util = new ExcelUtil<ExportExpectedFinancing>(ExportExpectedFinancing.class);
        util.exportExcel(response, list, "预计融资情况");
    }
    /**
     * 导出实际融资情况
     */
    @Log(title = "实际融资情况", businessType = BusinessType.EXPORT)
    @PostMapping("/exportTrueFinancing")
    public void exportTrueFinancing(HttpServletResponse response, ProjectInfo projectInfo)
    {
        List<ExportExpectedTrueFinancing> list =  financingDateService.selectProjectInfoListTrue(projectInfo);
        ExcelUtil<ExportExpectedTrueFinancing> util = new ExcelUtil<ExportExpectedTrueFinancing>(ExportExpectedTrueFinancing.class);
        util.exportExcel(response, list, "预计融资情况");
    }

    /**
     * 导出电量收支情况
     */
    @Log(title = "导出电量收支情况", businessType = BusinessType.EXPORT)
    @PostMapping("/exportElectricity")
    public void exportElectricity(HttpServletResponse response, ProjectInfo projectInfo)
    {
        List<ExportElectricity> list =  electricityStatisticsService.selectProjectInfoList(projectInfo);
        ExcelUtil<ExportElectricity> util = new ExcelUtil<ExportElectricity>(ExportElectricity.class);
        util.exportExcel(response, list, "电量收支情况");
    }

    /**
     * 导出预计现金流出情况
     */
    @Log(title = "导出预计现金流出情况", businessType = BusinessType.EXPORT)
    @PostMapping("/exportProjectCash")
    public void exportProjectCash(HttpServletResponse response, ProjectInfo projectInfo)
    {
        List<ExportProjectCash> list =  projectCashService.selectProjectInfoList(projectInfo);
        ExcelUtil<ExportProjectCash> util = new ExcelUtil<ExportProjectCash>(ExportProjectCash.class);
        util.exportExcel(response, list, "预计现金流出情况");
    }
    /**
     * 导出预计现金流出情况
     */
    @Log(title = "导出预计现金流入情况", businessType = BusinessType.EXPORT)
    @PostMapping("/exportProjectCashIn")
    public void exportProjectCashIn(HttpServletResponse response, ProjectInfo projectInfo)
    {
        List<ExportProjectCash> list =  projectCashService.selectProjectInfoListCashIn(projectInfo);
        ExcelUtil<ExportProjectCash> util = new ExcelUtil<ExportProjectCash>(ExportProjectCash.class);
        util.exportExcel(response, list, "预计现金流入情况");
    }


    /**
     * 获取项目情况详细信息
     */
  //  @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{xmqkProjectId}")
    public AjaxResult getInfo(@PathVariable("xmqkProjectId") String xmqkProjectId)
    {
        return AjaxResult.success(projectInfoService.selectProjectInfoByXmqkProjectId(xmqkProjectId));
    }

    /**
     * 新增项目情况
     */
   // @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "项目情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectInfo projectInfo)
    {
        String  projectId= IdUtils.fastSimpleUUID();
        projectInfo.setXmqkProjectId(projectId);
        int rsInt=projectInfoService.insertProjectInfo(projectInfo);
        if(rsInt>0){//保存成功
            return AjaxResult.success("200",projectId);
        }
        return AjaxResult.error();
    }

    /**
     * 修改项目情况
     */
   //("@ss.hasPermi('system:info:edit')")
    @Log(title = "项目情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectInfo projectInfo)
    {
        return toAjax(projectInfoService.updateProjectInfo(projectInfo));
    }

    /**
     * 删除项目情况
     */
   // @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "项目情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{xmqkProjectIds}")
    public AjaxResult remove(@PathVariable String[] xmqkProjectIds)
    {
        return toAjax(projectInfoService.deleteProjectInfoByXmqkProjectIds(xmqkProjectIds));
    }



    //下载模板
    @PostMapping("/downloadTemplate")
    public void export(HttpServletResponse response)
    {
        ExcelTool.downloadExcel(response,"项目情况模板");
    }

    //下载模板
    @PostMapping("/downloadTemplateConstruction")
    public void exportConstruction(HttpServletResponse response)
    {
        ExcelTool.downloadExcel(response,"施工情况模板");
    }

    //下载模板
    @PostMapping("/downloadTemplateManagement")
    public void exportConstructionManagement(HttpServletResponse response)
    {
        ExcelTool.downloadExcel(response,"经营情况模板");
    }

    @PostMapping("/downloadElectricity")
    public void exportConstructionElectricity(HttpServletResponse response)
    {
        ExcelTool.downloadExcel(response,"电量统计模板");
    }

    @PostMapping("/handleImportFinancing")
    public void handleImportFinancing(HttpServletResponse response)
    {
        ExcelTool.downloadExcel(response,"融资情况模板");
    }

    @PostMapping("/handleImportExpectedFinancing")
    public void handleImportExpectedFinancing(HttpServletResponse response)
    {
        ExcelTool.downloadExcel(response,"预计融资情况模板");
    }

    @PostMapping("/handleImportActualFinancing")
    public void handleImportActualFinancing(HttpServletResponse response)
    {
        ExcelTool.downloadExcel(response,"实际融资情况模板");
    }

    @PostMapping("/handleImportProjectCashMode")
    public void handleImportProjectCashMode(HttpServletResponse response)
    {
        ExcelTool.downloadExcel(response,"预计现金流出情况模板");
    }

    @PostMapping("/handleImportProjectCashIn")
    public void handleImportProjectCashIn(HttpServletResponse response)
    {
        ExcelTool.downloadExcel(response,"预计现金流入情况模板");
    }

    @PostMapping("/handleImportRepayment")
    public void handleImportRepayment(HttpServletResponse response)
    {
        ExcelTool.downloadExcel(response,"还款情况模板");
    }
    //导入
    @PostMapping("/importBox")
    public AjaxResult importBox(MultipartFile file, String xmid ) throws Exception
    {
        AjaxResult rs=AjaxResult.error("导入失败");
        ExcelUtil<ProjectInfoVerify> util = new ExcelUtil(ProjectInfoVerify.class);
        List<ProjectInfoVerify> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
            rs=   projectInfoService.importBox(listData,xmid);
        }else{
            rs= AjaxResult.success("数据为空");
        }
        return rs;
    }

    //导入施工情况
    @PostMapping("/importBoxConstruction")
    public AjaxResult importBoxConstruction(MultipartFile file) throws Exception
    {
        AjaxResult rs=AjaxResult.error("导入失败");
        ExcelUtil<ProjectInfoVerify> util = new ExcelUtil(ProjectInfoVerify.class);
        List<ProjectInfoVerify> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
            rs=   projectInfoService.importBoxConstruction(listData);
        }else{
            rs= AjaxResult.success("数据为空");
        }
        return rs;
    }
    //导入
    @PostMapping("/importBoxManagement")
    public AjaxResult importBoxManagement(MultipartFile file) throws Exception
    {
        AjaxResult rs=AjaxResult.error("导入失败");
        ExcelUtil<ManagementCash> util = new ExcelUtil(ManagementCash.class);
        List<ManagementCash> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
            rs=   projectInfoService.importBoxManagement(listData);
        }else{
            rs= AjaxResult.success("数据为空");
        }
        return rs;
    }
    //导入
    @PostMapping("/importBoxElectricity")
    public AjaxResult importBoxElectricity(MultipartFile file) throws Exception
    {
        AjaxResult rs=AjaxResult.error("导入失败");
        ExcelUtil<ElectricityCash> util = new ExcelUtil(ElectricityCash.class);
        List<ElectricityCash> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
            rs=   projectInfoService.importBoxElectricity(listData);
        }else{
            rs= AjaxResult.success("数据为空");
        }
        return rs;
    }
    //导入
    @PostMapping("/importBoxFinancing")
    public AjaxResult importBoxFinancing(MultipartFile file) throws Exception
    {
        AjaxResult rs=AjaxResult.error("导入失败");
        ExcelUtil<FinancingInfo> util = new ExcelUtil(FinancingInfo.class);
        List<FinancingInfo> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
            rs=   projectInfoService.importBoxFinancing(listData);
        }else{
            rs= AjaxResult.success("数据为空");
        }
        return rs;
    }
    //预计收款还款导入
    @PostMapping("/importBoxExpectedFinancing")
    public AjaxResult importBoxExpectedFinancing(MultipartFile file) throws Exception
    {
        AjaxResult rs=AjaxResult.error("导入失败");
        ExcelUtil<ExpectedFinancing> util = new ExcelUtil(ExpectedFinancing.class);
        List<ExpectedFinancing> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
            rs=   projectInfoService.importBoxExpectedFinancing(listData);
        }else{
            rs= AjaxResult.success("数据为空");
        }
        return rs;
    }
    //实际收款还款导入
    @PostMapping("/importBoxExpectedTrueFinancing")
    public AjaxResult importBoxExpectedTrueFinancing(MultipartFile file) throws Exception
    {
        AjaxResult rs=AjaxResult.error("导入失败");
        ExcelUtil<ExpectedTrueFinancing> util = new ExcelUtil(ExpectedTrueFinancing.class);
        List<ExpectedTrueFinancing> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
            rs=   projectInfoService.importBoxExpectedTrueFinancing(listData);
        }else{
            rs= AjaxResult.success("数据为空");
        }
        return rs;
    }

    @PostMapping("/check")
    public AjaxResult checkByname( ProjectInfo listItem  ){
        AjaxResult rs=AjaxResult.success("200","验证成功");
           if(listItem!=null&&!"".equals(listItem.getXmqkXmmc())){
               List<ProjectInfo> listData=new ArrayList<ProjectInfo> ();
               listData.add(listItem);
               List<ProjectInfo> rsData=   projectInfoService.checkByname(listData);
               if(rsData.size()>0){
                   rs=AjaxResult.success("-1",listData.get(0).getXmqkXmmc());
               }
           }
        return  rs ;
    }

    //导入
    @PostMapping("/importProjectCash")
    public AjaxResult importProjectCash(MultipartFile file) throws Exception
    {
        AjaxResult rs=AjaxResult.error("导入失败");
        ExcelUtil<ImportProjectCash> util = new ExcelUtil(ImportProjectCash.class);
        List<ImportProjectCash> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
            rs=   projectInfoService.importBoxProjectCash(listData);
        }else{
            rs= AjaxResult.success("数据为空");
        }
        return rs;
    }
    //导入
    @PostMapping("/importProjectCashIn")
    public AjaxResult importProjectCashIn(MultipartFile file) throws Exception
    {
        AjaxResult rs=AjaxResult.error("导入失败");
        ExcelUtil<ImportProjectCash> util = new ExcelUtil(ImportProjectCash.class);
        List<ImportProjectCash> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
            rs=   projectInfoService.importBoxProjectCashIn(listData);
        }else{
            rs= AjaxResult.success("数据为空");
        }
        return rs;
    }
    //导入
    @PostMapping("/importRepayment")
    public AjaxResult importRepayment(MultipartFile file) throws Exception
    {
        AjaxResult rs=AjaxResult.error("导入失败");
        ExcelUtil<ProjectFinancingRepayment> util = new ExcelUtil(ProjectFinancingRepayment.class);
        List<ProjectFinancingRepayment> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
            rs=   projectInfoService.importBoxRepayment(listData);
        }else{
            rs= AjaxResult.success("数据为空");
        }
        return rs;
    }



//    //合并单元格导出
//    @PostMapping("/productTjExport")
//    public void  hbdyg(HttpServletResponse response)throws Exception{
//        String title = "产品信息统计";
//
//        List<ProductSuitTjVo> list = new ArrayList<>();  //mybatis 一对多数据格式
//        ExportParams params = new ExportParams();
//        params.setTitle(title);         //设置表头
//        params.setSheetName(title);     //设置sheet名
//
//        ProductSuitTjVo iteme=new ProductSuitTjVo();
//        iteme.setMsgName("产品1");
//        iteme.setProdState("上线");
//        iteme.setProductManager("测试");
//        ProductSuitTjVo iteme1=new ProductSuitTjVo();
//        iteme.setMsgName("产品2");
//        iteme.setProdState("上线2");
//        iteme.setProductManager("测试2");
//        List<SuitTjVo> suitList=new ArrayList<>();
//        SuitTjVo  t1=new SuitTjVo();
//        t1.setSalePrice("100");
//        t1.setTalentAmtCin(200+"");
//        t1.setTalentAmtMin(3000+"");
//        t1.setSalePrice("999");
//        SuitTjVo  t2=new SuitTjVo();
//        t2.setSalePrice("1");
//        t2.setTalentAmtCin(1+"");
//        t2.setTalentAmtMin(1+"");
//        t2.setSalePrice("1");
//        suitList.add(t1);
//        suitList.add(t2);
//        iteme.setSuitList(suitList);
//        list.add(iteme);
//        list.add(iteme1);
//       // ExportExcel.setExportExcelFormat(response, workbook, title);
//        Workbook workbook = ExcelExportUtil.exportExcel(params, ProductSuitTjVo.class, list);
//        response.setHeader("Content-Disposition", "attachment;filename=" + new String(title.getBytes(), "ISO8859-1"));
//        response.setHeader("Pargam", "no-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        ServletOutputStream outStream = null;
//        try {
//            outStream = response.getOutputStream();
//            workbook.write(outStream);
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        } finally {
//            outStream.flush();
//            outStream.close();
//        }
//    }
//
//
//    //合并单元格导入
//    @PostMapping("/importBox1")
//    public AjaxResult importBo1x(MultipartFile file, String xmid ) throws Exception
//    {
//        AjaxResult rs=AjaxResult.success("导入失败");
//        ImportParams params = new ImportParams();
//        params.setHeadRows(2);
////        params.setImportFields(new String[]{"产品名称","产品状态","产品经理","小达人佣金","中达人佣金","销售价","结算价"});
//        params.setImportFields(new String[]{"产品名称","产品状态","产品经理","学生_小达人佣金","学生_中达人佣金","学生_销售价","学生_结算价"});
////        params.setImportFields(new String[]{"产品名称","产品状态","产品经理"});
////        C:\Users\yangqianshu\Desktop\00000000\项目信息_1661321140804.xlsx
//        List<ProductSuitTjVo> list = ExcelImportUtil.importExcel(
//                file.getInputStream(),
//                ProductSuitTjVo.class, params);
//        System.out.println("1234");
//
//
////        boolean isOK = true;
////        try {
////            ImportParams params = new ImportParams();
////            params.setHeadRows(2);
////            params.setImportFields(new String[]{"课程名称","老师姓名","学生_学生姓名","学生_学生性别","学生_出生日期"});
////            List<CourseEntityNoAnn> list = ExcelImportUtil.importExcel(
////                    file.getInputStream(),
////                    CourseEntityNoAnn.class, params);
////            System.out.println("1");
////        } catch (Exception e) {
////            e.printStackTrace();
////            isOK = false;
////        }
//        return rs;
//    }

}
