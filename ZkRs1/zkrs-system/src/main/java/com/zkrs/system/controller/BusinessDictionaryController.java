package com.zkrs.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zkrs.common.annotation.Excel;
import com.zkrs.system.enumeration.CommonlyCache;
import com.zkrs.system.section.RedisMonitor;
import com.zkrs.system.tool.BusinessUtils;
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
import com.zkrs.system.domain.BusinessDictionary;
import com.zkrs.system.service.IBusinessDictionaryService;
import com.zkrs.common.utils.poi.ExcelUtil;
import com.zkrs.common.core.page.TableDataInfo;

/**
 * 业务字典Controller
 *
 * @author zkrs
 * @date 2022-07-04
 */
@RestController
@RequestMapping("/system/dictionary")
public class BusinessDictionaryController extends BaseController
{
    @Autowired
    private IBusinessDictionaryService businessDictionaryService;

    /**
     * 查询业务字典列表
     */
    //  @PreAuthorize("@ss.hasPermi('system:dictionary:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessDictionary businessDictionary)
    {   List<BusinessDictionary> list=null;

        //    0 获取字典表分页、1 获取目录列表分页 、2 获取目录列表不需要分页、可选 3 根据id获取字典数据
        switch(businessDictionary.getIsCatalogue()){
            case "0" ://目录分页
                startPage();
                list = businessDictionaryService.selectBusinessDictionaryList(businessDictionary);
                break; //可选
            case "1" ://字典数(子项)据分页
                startPage();//获取目录列表分页
                list = businessDictionaryService.selectBusinessDictionaryListData(businessDictionary);
                break;
            case "2" ://获取目录列表不需要分页
                businessDictionary.setIsCatalogue("1");
                list = businessDictionaryService.selectBusinessDictionaryListData(businessDictionary);
                break; //
            case "3" ://根据id获取字典数据不分页,需要传入p_id
                businessDictionary.setIsCatalogue("1");
                list = businessDictionaryService.selectBusinessDictionaryList(businessDictionary);
                break;

        }

        return getDataTable(list);
    }

    /**
     * 导出业务字典列表
     */
    //@PreAuthorize("@ss.hasPermi('system:dictionary:export')")
    @Log(title = "业务字典", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessDictionary businessDictionary)
    {
        List<BusinessDictionary> list = businessDictionaryService.selectBusinessDictionaryList(businessDictionary);
        ExcelUtil<BusinessDictionary> util = new ExcelUtil<BusinessDictionary>(BusinessDictionary.class);
        util.exportExcel(response, list, "业务字典数据");
    }

    /**
     * 获取业务字典详细信息
     */
    // @PreAuthorize("@ss.hasPermi('system:dictionary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        Long[] ids={id};
        int rs= businessDictionaryService.checkData(ids);
        if(rs>0){
            return  new AjaxResult(-200,"目录下存在数据数据不能删除");
        }
        return AjaxResult.success(businessDictionaryService.selectBusinessDictionaryById(id));
    }

    /**
     * 新增业务字典
     */
    // @PreAuthorize("@ss.hasPermi('system:dictionary:add')")
    @Log(title = "业务字典", businessType = BusinessType.INSERT)
//    @RedisMonitor(stringType=CommonlyCache.DICTIONARIES,operation="add",index=0)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessDictionary businessDictionary)
    {
        AjaxResult rs=AjaxResult.error(-999,"添加数据失败");
        Integer isFag= businessDictionaryService.selectBusinessChekData(businessDictionary.getpId()+"",businessDictionary.getZdName());
        if(isFag>0){ //有重复数据
            rs=AjaxResult.error(-999,"字典名称重复请重新输入");
        }else{
            int isSave=businessDictionaryService.insertBusinessDictionary(businessDictionary);
            if(isSave>0){
                BusinessUtils.add(businessDictionary);
            }
            rs=AjaxResult.error(200,"添加数据成功");
        }

        return rs;
    }

    /**
     * 修改业务字典
     */
    @Log(title = "业务字典", businessType = BusinessType.UPDATE)
//    @RedisMonitor(stringType=CommonlyCache.DICTIONARIES,operation="add",index=0)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessDictionary businessDictionary)
    {
        AjaxResult rs=AjaxResult.error(-999,"修改数据失败");
        Integer isFag= businessDictionaryService.selectBusinessChekData(businessDictionary.getpId()+"",businessDictionary.getZdName());
        if(isFag>0){
            rs=AjaxResult.error(-999,"字典名称重复请重新输入");
          }else{
            int isSave=businessDictionaryService.updateBusinessDictionary(businessDictionary);
            if(isSave>0){
                BusinessUtils.edit(businessDictionary);
            }
            rs=AjaxResult.error(200,"修改数据成功");
        }

        return rs;
    }

    /**
     * 删除业务字典
     */
    //@PreAuthorize("@ss.hasPermi('system:dictionary:remove')")
    @Log(title = "业务字典", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {  //isFag:0表示目录 1表示子项数据
        //删除目录时要验证子项是否存在，否则不能删除
        int rs= businessDictionaryService.checkData(ids);
        if(rs>0){
            return  new AjaxResult(-200,"目录下存在数据数据不能删除");
        }
        int rss=businessDictionaryService.deleteBusinessDictionaryByIds(ids);
        if(rss>0){
            BusinessUtils.deleteS(ids);
        }

        return toAjax(rss);

    }
}
