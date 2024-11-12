package com.zkrs.system.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.zkrs.system.domain.FinancingDateVerify;
import com.zkrs.system.tool.ExcelTool;
import com.zkrs.system.tool.FastJsonUtils;
import org.apache.ibatis.annotations.Param;
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
import com.zkrs.system.domain.FinancingDate;
import com.zkrs.system.service.IFinancingDateService;
import com.zkrs.common.utils.poi.ExcelUtil;
import com.zkrs.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 融资时间统计Controller
 *
 * @author zkrs
 * @date 2022-07-07
 */
@RestController
@RequestMapping("/system/date")
public class FinancingDateController extends BaseController
{
    @Autowired
    private IFinancingDateService financingDateService;

    /**
     * 查询融资时间列表
     */
   // @PreAuthorize("@ss.hasPermi('system:date:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinancingDate financingDate)
    {
//        startPage();
        List<FinancingDate> list = financingDateService.selectFinancingDateList(financingDate);
        return getDataTable(list);
    }

    /**
     * 导出融资时间列表
     */
   // @PreAuthorize("@ss.hasPermi('system:date:export')")
    @Log(title = "融资时间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinancingDate financingDate)
    {
        List<FinancingDate> list = financingDateService.selectFinancingDateExport(financingDate);
        ExcelUtil<FinancingDate> util = new ExcelUtil<FinancingDate>(FinancingDate.class);
        util.exportExcel(response, list, "融资时间数据");
    }

    /**
     * 获取融资时间详细信息
     */
   // @PreAuthorize("@ss.hasPermi('system:date:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(financingDateService.selectFinancingDateById(id));
    }

    /**
     * 新增融资时间
     */
  //  @PreAuthorize("@ss.hasPermi('system:date:add')")
    @Log(title = "融资时间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinancingDate financingDate)
    {
        return toAjax(financingDateService.insertFinancingDate(financingDate));
    }

    @Log(title = "批量插入或更新", businessType = BusinessType.EXPORT)
    @PostMapping("/addList")
    public AjaxResult addList(HttpServletResponse response,@RequestBody String datalist  ) throws Exception {

        List<FinancingDate>  listData= FastJsonUtils.getJsonToList(datalist,FinancingDate.class);
        AjaxResult ajaxResult = financingDateService.insertFinancingDateList(listData);
        return  ajaxResult;
    }

    /**
     * 修改融资时间
     */
   // @PreAuthorize("@ss.hasPermi('system:date:edit')")
    @Log(title = "融资时间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinancingDate financingDate)
    {
        return toAjax(financingDateService.updateFinancingDate(financingDate));
    }

    /**
     * 删除融资时间
     */
   // @PreAuthorize("@ss.hasPermi('system:date:remove')")
    @Log(title = "融资时间", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(financingDateService.deleteFinancingDateByIds(ids));
    }


    //下载模板
    @PostMapping("/downloadTemplate")
    public void export(HttpServletResponse response)
    {
        ExcelTool.downloadExcel(response,"融资时间模板");
    }



    //导入
    @PostMapping("/importBox")
    public AjaxResult importBox(MultipartFile file, String xmType,String msg_id,String  isYjSj ) throws Exception
    {
        AjaxResult rs=AjaxResult.success("导入失败");
        ExcelUtil<FinancingDateVerify> util = new ExcelUtil(FinancingDateVerify.class);
        List<FinancingDateVerify> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
            rs=   financingDateService.importBox(listData,xmType,msg_id,isYjSj);
        }else{
            rs= AjaxResult.success("数据为空");
        }

        return rs;
    }



}
