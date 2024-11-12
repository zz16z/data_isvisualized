package com.zkrs.system.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.zkrs.common.utils.DateUtils;
import com.zkrs.system.domain.ProjectFinancingVerify;
import com.zkrs.system.domain.dto.ProjectFinancingDto;
import com.zkrs.system.monitor.ExcelDictDTOListener;
import com.zkrs.system.tool.ExcelTool;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zkrs.common.annotation.Log;
import com.zkrs.common.core.controller.BaseController;
import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.common.enums.BusinessType;
import com.zkrs.system.domain.ProjectFinancing;
import com.zkrs.system.service.IProjectFinancingService;
import com.zkrs.common.utils.poi.ExcelUtil;
import com.zkrs.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.zkrs.common.core.domain.AjaxResult.CODE_TAG;
import static com.zkrs.common.core.domain.AjaxResult.MSG_TAG;

/**
 * 项目融资Controller
 * 
 * @author zkrs
 * @date 2022-07-01
 */
@RestController
@RequestMapping("/system/financing")
public class ProjectFinancingController extends BaseController {
    @Autowired
    private IProjectFinancingService projectFinancingService;

    /**
     * 查询项目融资列表
     */
    //@PreAuthorize("@ss.hasPermi('system:financing:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectFinancing projectFinancing) {
        startPage();
        List<ProjectFinancing> list = projectFinancingService.selectProjectFinancingList(projectFinancing);
        return getDataTable(list);
    }

    /**
     * 导出项目融资列表
     */
   // @PreAuthorize("@ss.hasPermi('system:financing:export')")
    @Log(title = "项目融资", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectFinancing projectFinancing) {
        List<ProjectFinancing> list = projectFinancingService.selectProjectFinancingExport(projectFinancing);
        ExcelUtil<ProjectFinancing> util = new ExcelUtil<ProjectFinancing>(ProjectFinancing.class);
        util.exportExcel(response, list, "项目融资数据");

           //EasyExcel导出
//        //获取模板，//使用response.getOutputStream()下载,并使用项目下的模板填充
//        ClassPathResource classPathResource = new ClassPathResource("templates/test1.xlsx");
//        try (InputStream inputStream = classPathResource.getInputStream();
//             ServletOutputStream outputStream = response.getOutputStream()) {
//            String filename = URLEncoder.encode("99", "utf-8");
//            response.setHeader("Content-disposition", "attachment;filename=" + filename+ ".xlsx");
//            //设置输出流和模板信息
//            ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build();
//            WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").build();
//            //开启自动换行,自动换行表示每次写入一条list数据是都会重新生成一行空行,此选项默认是关闭的,需要提前设置为true
//            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
//            List<ProjectFinancing>  list=  projectFinancingService.selectProjectFinancingList(null);
//            excelWriter.fill(list, fillConfig, writeSheet);
////            excelWriter.fill(list, writeSheet);
//            excelWriter.finish();
//            } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

    /**
     * 获取项目融资详细信息
     */
   // @PreAuthorize("@ss.hasPermi('system:financing:query')")
    @GetMapping(value = "/{xmrzId}")
    public AjaxResult getInfo(@PathVariable("xmrzId") Long xmrzId) {
        return AjaxResult.success(projectFinancingService.selectProjectFinancingByXmrzId(xmrzId));
    }

    /**
     * 新增项目融资
     */
   // @PreAuthorize("@ss.hasPermi('system:financing:add')")
    @Log(title = "项目融资", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectFinancing projectFinancing) {
        Integer rs=-1;
        //机构名称重复验证
        rs= projectFinancingService.organNameChek(projectFinancing);
        if(rs>0){
            return  AjaxResult.error("项目下已经存在融资机构");
        }
        rs=projectFinancingService.insertProjectFinancing(projectFinancing);
        if(rs>0){
            return  AjaxResult.success("200",projectFinancing.getXmrzId());
        }
        return  AjaxResult.error();
    }

    /**
     * 修改项目融资
     */
   // @PreAuthorize("@ss.hasPermi('system:financing:edit')")
    @Log(title = "项目融资", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectFinancing projectFinancing) {
        return toAjax(projectFinancingService.updateProjectFinancing(projectFinancing));
    }

    /**
     * 删除项目融资
     */
    //@PreAuthorize("@ss.hasPermi('system:financing:remove')")
    @Log(title = "项目融资", businessType = BusinessType.DELETE)
    @DeleteMapping("/{xmrzIds}")
    public AjaxResult remove(@PathVariable Long[] xmrzIds) {
        return toAjax(projectFinancingService.deleteProjectFinancingByXmrzIds(xmrzIds));
    }


    /**
     * 导入
     *
     * @param file 文件对象
     */
    @PostMapping("/import")
   // @PreAuthorize("@ss.hasPermi('system:financing:import')")
    @Log(title = "项目融资导入", businessType = BusinessType.DELETE)
    public AjaxResult importData(@RequestParam("file") MultipartFile file) {
        AjaxResult rs = new AjaxResult();
        rs.put(CODE_TAG, 200);
        rs.put(MSG_TAG, "上传成功");
        try {
            // 读取文件流
            EasyExcel.read
                    (file.getInputStream(),// 前端上传的文件
                            ProjectFinancingDto.class,// 跟excel对应的实体类
                            new ExcelDictDTOListener(projectFinancingService))// 监听器

                    .sheet("模板").doRead();

        } catch (IOException e) {
            e.printStackTrace();
            rs.put(CODE_TAG, -200);
            rs.put(MSG_TAG, "上传成功失败");

        }

        return rs;
    }




//下载模板
    @PostMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response)
    {
        ExcelTool.downloadExcel(response,"项目融资模板");
    }



    //导入
    @PostMapping("/importBox")
    public AjaxResult importBox(MultipartFile file,String xmid ) throws Exception
    {
        AjaxResult rs=AjaxResult.success("导入失败");
        ExcelUtil<ProjectFinancingVerify> util = new ExcelUtil(ProjectFinancingVerify.class);
        List<ProjectFinancingVerify> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
            rs=   projectFinancingService.importBox(listData,xmid);
        }else{
            rs= AjaxResult.success("数据为空");
        }
        return rs;
    }


}


