package com.zkrs.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zkrs.system.domain.FinancingDate;
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
import com.zkrs.system.domain.ProjectFinancingLoan;
import com.zkrs.system.service.IProjectFinancingLoanService;
import com.zkrs.common.utils.poi.ExcelUtil;
import com.zkrs.common.core.page.TableDataInfo;

/**
 * 融资放款Controller
 * 
 * @author zkrs
 * @date 2022-10-13
 */
@RestController
@RequestMapping("/system/loan")
public class ProjectFinancingLoanController extends BaseController
{
    @Autowired
    private IProjectFinancingLoanService projectFinancingLoanService;

    /**
     * 查询融资放款列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ProjectFinancingLoan projectFinancingLoan)
    {
        startPage();
        List<ProjectFinancingLoan> list = projectFinancingLoanService.selectProjectFinancingLoanList(projectFinancingLoan);
        return getDataTable(list);
    }

    /**
     * 查询融资放款列表
     */
    @GetMapping("/listInfo")
    public List<ProjectFinancingLoan> listInfo(ProjectFinancingLoan projectFinancingLoan)
    {
        return projectFinancingLoanService.selectProjectFinancingLoanList(projectFinancingLoan);
    }

    /**
     * 导出融资放款列表
     */
    @Log(title = "融资放款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectFinancingLoan projectFinancingLoan)
    {
        List<ProjectFinancingLoan> list = projectFinancingLoanService.selectProjectFinancingLoanList(projectFinancingLoan);
        ExcelUtil<ProjectFinancingLoan> util = new ExcelUtil<ProjectFinancingLoan>(ProjectFinancingLoan.class);
        util.exportExcel(response, list, "融资放款数据");
    }

    /**
     * 获取融资放款详细信息
     */
    @GetMapping(value = "/{financingId}")
    public AjaxResult getInfo(@PathVariable("financingId") Long financingId)
    {
        return AjaxResult.success(projectFinancingLoanService.selectProjectFinancingLoanByFinancingId(financingId));
    }

    @Log(title = "批量插入或更新", businessType = BusinessType.EXPORT)
    @PostMapping("/addList")
    public AjaxResult addList(HttpServletResponse response,@RequestBody String datalist  ) throws Exception {
        List<ProjectFinancingLoan>  listData= FastJsonUtils.getJsonToList(datalist,ProjectFinancingLoan.class);
        AjaxResult ajaxResult = projectFinancingLoanService.insertFinancingLoanList(listData);
        return  ajaxResult;
    }

    /**
     * 新增融资放款
     */
    @Log(title = "融资放款", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectFinancingLoan projectFinancingLoan)
    {
        return toAjax(projectFinancingLoanService.insertProjectFinancingLoan(projectFinancingLoan));
    }

    /**
     * 修改融资放款
     */
    @Log(title = "融资放款", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectFinancingLoan projectFinancingLoan)
    {
        return toAjax(projectFinancingLoanService.updateProjectFinancingLoan(projectFinancingLoan));
    }

    /**
     * 删除融资放款
     */
    @Log(title = "融资放款", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(projectFinancingLoanService.deleteProjectFinancingLoanByIds(ids));
    }
}
