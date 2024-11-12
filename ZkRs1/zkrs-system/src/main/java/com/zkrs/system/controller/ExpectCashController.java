package com.zkrs.system.controller;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.zkrs.common.utils.uuid.IdUtils;
import com.zkrs.system.domain.ExpectCashVerify;
import com.zkrs.system.domain.dto.ExpectCashDto;
import com.zkrs.system.mapper.ActualCashMapper;
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
import com.zkrs.system.domain.ExpectCash;
import com.zkrs.system.service.IExpectCashService;
import com.zkrs.common.utils.poi.ExcelUtil;
import com.zkrs.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 现金流预计情况Controller
 *
 * @author zkrs
 * @date 2022-07-01
 */
@RestController
@RequestMapping("/system/expect/cash")
public class ExpectCashController extends BaseController
{
    @Autowired
    private IExpectCashService expectCashService;

//    /**
//     * 查询现金流预计情况列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:expect:cash:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(ExpectCash expectCash)
//    {
//        startPage();
//        List<ExpectCash> list = expectCashService.selectExpectCashList(expectCash);
//        return getDataTable(list);
//    }

    /**
     * 查询现金流预计情况列表
     */
    //@PreAuthorize("@ss.hasPermi('system:expect:cash:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExpectCashDto expectCash)
    {
        startPage();
        List<ExpectCashDto> list = expectCashService.selectExpectCashHbList(expectCash);
        return getDataTable(list);
    }

    /**
     * 导出现金流预计情况列表
     */
   // @PreAuthorize("@ss.hasPermi('system:expect:cash:export')")
    @Log(title = "现金流预计情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExpectCash expectCash)
    {
        List<ExpectCash> list = expectCashService.selectExpectCashExport(expectCash);
        ExcelUtil<ExpectCash> util = new ExcelUtil<ExpectCash>(ExpectCash.class);
        util.exportExcel(response, list, "现金流预计情况数据");
    }

    /**
     * 获取现金流预计情况详细信息
     */
   // @PreAuthorize("@ss.hasPermi('system:expect:cash:query')")
    @GetMapping(value = "/{xjlyjqkId}")
    public AjaxResult getInfo(@PathVariable("xjlyjqkId") Long xjlyjqkId)
    {
        return AjaxResult.success(expectCashService.selectExpectCashByXjlyjqkId(xjlyjqkId));
    }

    /**
     * 新增现金流预计情况
     */
   // @PreAuthorize("@ss.hasPermi('system:expect:cash:add')")
    @Log(title = "现金流预计情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExpectCash expectCash)
    {
        String  yjxjlId= IdUtils.fastSimpleUUID();
        expectCash.setXjlsjqkYjxjlId(yjxjlId);
        int rs=expectCashService.insertExpectCash(expectCash);
        if(rs>0){
            return  AjaxResult.success("200",yjxjlId);
        }

        return AjaxResult.error();
    }

    /**
     * 修改现金流预计情况
     */
  //  @PreAuthorize("@ss.hasPermi('system:expect:cash:edit')")
    @Log(title = "现金流预计情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExpectCash expectCash)
    {
        return toAjax(expectCashService.updateExpectCash(expectCash));
    }

    /**
     * 删除现金流预计情况
     */
   // @PreAuthorize("@ss.hasPermi('system:expect:cash:remove')")
    @Log(title = "现金流预计情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{xjlyjqkIds}")
    public AjaxResult remove(@PathVariable Long[] xjlyjqkIds)
    {

        return toAjax(expectCashService.deleteExpectCashByXjlyjqkIds(xjlyjqkIds));
    }


    //下载模板
    @PostMapping("/downloadTemplate")
    public void export(HttpServletResponse response)
    {
        ExcelTool.downloadExcel(response,"预计现金流模板");
    }



    //导入
    @PostMapping("/importBox")
    public AjaxResult importBox(MultipartFile file, String xmid ) throws Exception
    {
        AjaxResult rs=AjaxResult.success("导入失败");
        ExcelUtil<ExpectCashVerify> util = new ExcelUtil(ExpectCashVerify.class);
        List<ExpectCashVerify> listData = util.importExcel(file.getInputStream());
        if(listData!=null&&listData.size()>0){
            rs=   expectCashService.importBox(listData,xmid);
        }else{
            rs= AjaxResult.success("数据为空");
        }

        return rs;
    }
}
