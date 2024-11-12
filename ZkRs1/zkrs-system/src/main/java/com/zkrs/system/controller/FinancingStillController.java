package com.zkrs.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zkrs.system.domain.FinancingStillVerify;
import com.zkrs.system.tool.ExcelTool;
import com.zkrs.system.tool.FastJsonUtils;
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
import com.zkrs.system.domain.FinancingStill;
import com.zkrs.system.service.IFinancingStillService;
import com.zkrs.common.utils.poi.ExcelUtil;
import com.zkrs.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 还款情况Controller
 * 
 * @author zkrs
 * @date 2022-07-23
 */
@RestController
@RequestMapping("/system/still")
public class FinancingStillController extends BaseController
{
    @Autowired
    private IFinancingStillService financingStillService;

    /**
     * 查询还款情况列表
     */

    @GetMapping("/list")
    public TableDataInfo list(FinancingStill financingStill)
    {
//        startPage();
        List<FinancingStill> list = financingStillService.selectFinancingStillList(financingStill);
        return getDataTable(list);
    }

    /**
     * 导出还款情况列表
     */

    @Log(title = "还款情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinancingStill financingStill)
    {
        List<FinancingStill> list = financingStillService.selectFinancingStillList(financingStill);
        ExcelUtil<FinancingStill> util = new ExcelUtil<FinancingStill>(FinancingStill.class);
        util.exportExcel(response, list, "还款情况数据");
    }

    /**
     * 获取还款情况详细信息
     */

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(financingStillService.selectFinancingStillById(id));
    }

    /**
     * 新增还款情况
     */

    @Log(title = "还款情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinancingStill financingStill)
    {
        return toAjax(financingStillService.insertFinancingStill(financingStill));
    }

    /**
     * 修改还款情况
     */

    @Log(title = "还款情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinancingStill financingStill)
    {
        return toAjax(financingStillService.updateFinancingStill(financingStill));
    }

    /**
     * 删除还款情况
     */
    @Log(title = "还款情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(financingStillService.deleteFinancingStillByIds(ids));
    }


    //下载模板
    @PostMapping("/downloadTemplate")
    public void export(HttpServletResponse response)
    {
        ExcelTool.downloadExcel(response,"融资还款模板");
    }



    //导入
    @PostMapping("/importBox")
    public AjaxResult importBox(MultipartFile file, String xmType, String msg_id ,String isYjSj) throws Exception
    {

        AjaxResult rs=AjaxResult.success("导入失败");
        ExcelUtil<FinancingStillVerify> util = new ExcelUtil(FinancingStillVerify.class);
        List<FinancingStillVerify> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
           rs=   financingStillService.importBox(listData,xmType,msg_id,isYjSj);
        }else{
            rs= AjaxResult.success("数据为空");
        }

        return rs;
    }


    @Log(title = "增加", businessType = BusinessType.EXPORT)
    @PostMapping("/addList")
    public AjaxResult addList(HttpServletResponse response,@RequestBody String datalist  ) throws Exception {

        List<FinancingStill>  listData= FastJsonUtils.getJsonToList(datalist,FinancingStill.class);
        return  financingStillService.insertFinancingDateList(listData);
    }
}
