package com.zkrs.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.zkrs.system.domain.ProjectCash;
import com.zkrs.system.service.IProjectCashService;
import com.zkrs.common.utils.poi.ExcelUtil;
import com.zkrs.common.core.page.TableDataInfo;

/**
 * 现金流入流出Controller
 *
 * @author zkrs
 * @date 2022-10-08
 */
@RestController
@RequestMapping("/system/cash")
public class ProjectCashController extends BaseController
{
    @Autowired
    private IProjectCashService projectCashService;

    /**
     * 查询现金流入流出列表
     */
    @PreAuthorize("@ss.hasPermi('system:cash:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectCash projectCash)
    {
        startPage();
        List<ProjectCash> list = projectCashService.selectProjectCashList(projectCash);
        return getDataTable(list);
    }

    /**
     * 导出现金流入流出列表
     */
    @PreAuthorize("@ss.hasPermi('system:cash:export')")
    @Log(title = "现金流入流出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectCash projectCash)
    {
        List<ProjectCash> list = projectCashService.selectProjectCashList(projectCash);
        ExcelUtil<ProjectCash> util = new ExcelUtil<ProjectCash>(ProjectCash.class);
        util.exportExcel(response, list, "现金流入流出数据");
    }

    /**
     * 获取现金流入流出详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cash:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(projectCashService.selectProjectCashById(id));
    }

    /**
     * 新增现金流入流出
     */
    @PreAuthorize("@ss.hasPermi('system:cash:add')")
    @Log(title = "现金流入流出", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectCash projectCash)
    {
        return toAjax(projectCashService.insertProjectCash(projectCash));
    }

    /**
     * 修改现金流入流出
     */
    @PreAuthorize("@ss.hasPermi('system:cash:edit')")
    @Log(title = "现金流入流出", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectCash projectCash)
    {
        return toAjax(projectCashService.updateProjectCash(projectCash));
    }

    /**
     * 删除现金流入流出
     */
    @PreAuthorize("@ss.hasPermi('system:cash:remove')")
    @Log(title = "现金流入流出", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(projectCashService.deleteProjectCashByIds(ids));
    }
}
