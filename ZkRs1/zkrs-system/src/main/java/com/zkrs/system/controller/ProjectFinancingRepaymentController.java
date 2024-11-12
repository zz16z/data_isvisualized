package com.zkrs.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zkrs.system.domain.ProjectFinancingLoan;
import com.zkrs.system.service.IProjectFinancingRepaymentService;
import com.zkrs.system.tool.FastJsonUtils;
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
import com.zkrs.system.domain.ProjectFinancingRepayment;
import com.zkrs.common.utils.poi.ExcelUtil;
import com.zkrs.common.core.page.TableDataInfo;

/**
 * 融资还款Controller
 * 
 * @author zkrs
 * @date 2022-10-14
 */
@RestController
@RequestMapping("/system/repayment")
public class ProjectFinancingRepaymentController extends BaseController
{
    @Autowired
    private IProjectFinancingRepaymentService projectFinancingRepaymentService;

    /**
     * 查询融资还款列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ProjectFinancingRepayment projectFinancingRepayment)
    {
        startPage();
        List<ProjectFinancingRepayment> list = projectFinancingRepaymentService.selectProjectFinancingRepaymentList(projectFinancingRepayment);
        return getDataTable(list);
    }

    @GetMapping("/listInfo")
    public List<ProjectFinancingRepayment> listInfo(ProjectFinancingRepayment projectFinancingRepayment)
    {
        List<ProjectFinancingRepayment> list = projectFinancingRepaymentService.selectProjectFinancingRepaymentList(projectFinancingRepayment);
        return list;
    }

    /**
     * 导出融资还款列表
     */
    @Log(title = "融资还款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectFinancingRepayment projectFinancingRepayment)
    {
        List<ProjectFinancingRepayment> list = projectFinancingRepaymentService.selectProjectFinancingRepaymentList(projectFinancingRepayment);
        ExcelUtil<ProjectFinancingRepayment> util = new ExcelUtil<ProjectFinancingRepayment>(ProjectFinancingRepayment.class);
        util.exportExcel(response, list, "融资还款数据");
    }

    /**
     * 获取融资还款详细信息
     */
    @GetMapping(value = "/{financingId}")
    public AjaxResult getInfo(@PathVariable("financingId") Long financingId)
    {
        return AjaxResult.success(projectFinancingRepaymentService.selectProjectFinancingRepaymentByFinancingId(financingId));
    }

    /**
     * 新增融资还款
     */
    @Log(title = "融资还款", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectFinancingRepayment projectFinancingRepayment)
    {
        return toAjax(projectFinancingRepaymentService.insertProjectFinancingRepayment(projectFinancingRepayment));
    }

    /**
     * 修改融资还款
     */
    @Log(title = "融资还款", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectFinancingRepayment projectFinancingRepayment)
    {
        return toAjax(projectFinancingRepaymentService.updateProjectFinancingRepayment(projectFinancingRepayment));
    }

    /**
     * 删除融资还款
     */
    @Log(title = "融资还款", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(projectFinancingRepaymentService.deleteProjectFinancingRepaymentByIds(ids));
    }

    @Log(title = "批量插入或更新", businessType = BusinessType.EXPORT)
    @PostMapping("/addList")
    public AjaxResult addList(HttpServletResponse response,@RequestBody String datalist  ) throws Exception {
        List<ProjectFinancingRepayment>  listData= FastJsonUtils.getJsonToList(datalist,ProjectFinancingRepayment.class);
        AjaxResult ajaxResult = projectFinancingRepaymentService.insertFinancingRepaymentList(listData);
        return  ajaxResult;
    }
}
