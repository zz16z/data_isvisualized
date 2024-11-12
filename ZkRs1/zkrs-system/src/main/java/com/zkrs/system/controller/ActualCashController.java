package com.zkrs.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zkrs.system.domain.ActualCashVerify;
import com.zkrs.system.section.RedisMonitor;
import com.zkrs.system.tool.ExcelTool;
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
import com.zkrs.system.domain.ActualCash;
import com.zkrs.system.service.IActualCashService;
import com.zkrs.common.utils.poi.ExcelUtil;
import com.zkrs.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 现金流实际情况Controller
 *
 * @author zkrs
 * @date 2022-07-01
 */
@RestController
@RequestMapping("/system/actual/cash")
public class ActualCashController extends BaseController
{
    @Autowired
    private IActualCashService actualCashService;

    /**
     * 查询现金流实际情况列表
     */
   // @PreAuthorize("@ss.hasPermi('system:actual:cash:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActualCash actualCash)
    {
        startPage();
        List<ActualCash> list = actualCashService.selectActualCashList(actualCash);
        return getDataTable(list);
    }

    /**
     * 导出现金流实际情况列表
     */
   // @PreAuthorize("@ss.hasPermi('system:actual:cash:export')")
    @Log(title = "现金流实际情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActualCash actualCash)
    {
        List<ActualCash> list = actualCashService.selectActualCashexport(actualCash);
        ExcelUtil<ActualCash> util = new ExcelUtil<ActualCash>(ActualCash.class);
        util.exportExcel(response, list, "实际现金流情况数据");
    }

    /**
     * 获取现金流实际情况详细信息
     */
  //  @PreAuthorize("@ss.hasPermi('system:actual:cash:query')")
    @GetMapping(value = "/{xjlsjqkId}")
    public AjaxResult getInfo(@PathVariable("xjlsjqkId") Long xjlsjqkId)
    {
        return AjaxResult.success(actualCashService.selectActualCashByXjlsjqkId(xjlsjqkId));
    }



    /**
     * 根据关联id获取现金流实际情况详细信息
     */
    @GetMapping(value = "/get_info_gl")
    public AjaxResult getInfoGl( String  id)
    {
        return AjaxResult.success(actualCashService.selectActualCashByxjlsjqkYjxjlId(id));
    }
    /**
     * 新增现金流实际情况
     */
   // @PreAuthorize("@ss.hasPermi('system:actual:cash:add')")
    @Log(title = "现金流实际情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActualCash actualCash)
    {
        return toAjax(actualCashService.insertActualCash(actualCash));
    }

    /**
     * 修改现金流实际情况
     */
   // @PreAuthorize("@ss.hasPermi('system:actual:cash:edit')")
    @Log(title = "现金流实际情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActualCash actualCash)
    {
        return toAjax(actualCashService.updateActualCash(actualCash));
    }

    /**
     * 删除现金流实际情况
     */
   // @PreAuthorize("@ss.hasPermi('system:actual:cash:remove')")
    @Log(title = "现金流实际情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{xjlsjqkIds}")
    public AjaxResult remove(@PathVariable Long[] xjlsjqkIds)
    {
        return toAjax(actualCashService.deleteActualCashByXjlsjqkIds(xjlsjqkIds));
    }

    //导入
    @PostMapping("/importBox")
    public AjaxResult importBox(MultipartFile file, String xmid ,String xjlsjqkYjxjlId ) throws Exception
    {  //xmid：项目id，xjlsjqkYjxjlId：预计现金流关联id
        AjaxResult rs=AjaxResult.success("导入失败");
        ExcelUtil<ActualCashVerify> util = new ExcelUtil(ActualCashVerify.class);
        List<ActualCashVerify> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
                if(listData.size()==1){
                    rs=   actualCashService.importBox(listData,xmid,xjlsjqkYjxjlId);
                }else{
                    rs= AjaxResult.success("仅支持单条导入");
                }

        }else{
            rs= AjaxResult.success("数据为空");
        }

        return rs;
    }

    //下载模板
    @PostMapping("/downloadTemplate")
    public void export(HttpServletResponse response)
    {
        ExcelTool.downloadExcel(response,"实际现金流模板");
    }



}
