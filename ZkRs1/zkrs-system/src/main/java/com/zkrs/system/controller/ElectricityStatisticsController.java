package com.zkrs.system.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


import com.zkrs.system.domain.ElectricityStatisticsVerify;
import com.zkrs.system.domain.ProjectFinancing;
import com.zkrs.system.tool.ExcelTool;
import com.zkrs.system.tool.Valid;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zkrs.common.annotation.Log;
import com.zkrs.common.core.controller.BaseController;
import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.common.enums.BusinessType;
import com.zkrs.system.domain.ElectricityStatistics;
import com.zkrs.system.service.IElectricityStatisticsService;
import com.zkrs.common.utils.poi.ExcelUtil;
import com.zkrs.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 电量统计Controller
 *
 * @author zkrs
 * @date 2022-07-01
 */
@RestController
@RequestMapping("/system/statistics")
public class ElectricityStatisticsController extends BaseController
{
    @Autowired
    private IElectricityStatisticsService electricityStatisticsService;

    /**
     * 查询电量统计列表
     */
   // @PreAuthorize("@ss.hasPermi('system:statistics:list')")
    @GetMapping("/list")
    public TableDataInfo list(ElectricityStatistics electricityStatistics)
    {
        startPage();
        List<ElectricityStatistics> list = electricityStatisticsService.selectElectricityStatisticsList(electricityStatistics);
        return getDataTable(list);
    }

    /**
     * 导出电量统计列表
     */
 //   @PreAuthorize("@ss.hasPermi('system:statistics:export')")
    @Log(title = "电量统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ElectricityStatistics electricityStatistics)
    {
        List<ElectricityStatistics> list = electricityStatisticsService.selectElectricityStatisticsExport(electricityStatistics);
        ExcelUtil<ElectricityStatistics> util = new ExcelUtil<ElectricityStatistics>(ElectricityStatistics.class);
        util.exportExcel(response, list, "电量统计");
    }

    /**
     * 获取电量统计详细信息
     */
   // @PreAuthorize("@ss.hasPermi('system:statistics:query')")
    @GetMapping(value = "/{dltjId}")
    public AjaxResult getInfo(@PathVariable("dltjId") Long dltjId)
    {
        return AjaxResult.success(electricityStatisticsService.selectElectricityStatisticsByDltjId(dltjId));
    }

    /**
     * 新增电量统计
     */
  //  @PreAuthorize("@ss.hasPermi('system:statistics:add')")
    @Log(title = "电量统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ElectricityStatistics electricityStatistics)
    {
        return toAjax(electricityStatisticsService.insertElectricityStatistics(electricityStatistics));
    }

    /**
     * 修改电量统计
     */
   // @PreAuthorize("@ss.hasPermi('system:statistics:edit')")
    @Log(title = "电量统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ElectricityStatistics electricityStatistics)
    {
        return toAjax(electricityStatisticsService.updateElectricityStatistics(electricityStatistics));
    }

    /**
     * 删除电量统计
     */
  //  @PreAuthorize("@ss.hasPermi('system:statistics:remove')")
    @Log(title = "电量统计", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dltjIds}")
    public AjaxResult remove(@PathVariable Long[] dltjIds)
    {
        return toAjax(electricityStatisticsService.deleteElectricityStatisticsByDltjIds(dltjIds));
    }
    //导入
    @PostMapping("/importBox")
    public AjaxResult importBox(MultipartFile file,String xmid ) throws Exception
    {
        AjaxResult rs=AjaxResult.success("导入失败");
        ExcelUtil<ElectricityStatisticsVerify> util = new ExcelUtil(ElectricityStatisticsVerify.class);
        List<ElectricityStatisticsVerify> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
         rs=   electricityStatisticsService.importBox(listData,xmid);
        }else{
            rs= AjaxResult.success("数据为空");
        }

        return rs;
    }

    //下载模板
    @PostMapping("/downloadTemplate")
    public void export(HttpServletResponse response, ProjectFinancing projectFinancing)
    {
        ExcelTool.downloadExcel(response,"电量统计");
    }



}
