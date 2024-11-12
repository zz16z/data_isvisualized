package com.zkrs.web.controller.api;


import com.zkrs.common.core.domain.R;
import com.zkrs.system.domain.api.ApiDictionary;
import com.zkrs.system.mapper.BusinessDictionaryMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("数据字典")
@RestController
@RequestMapping("/api/Dictionaries")
public class DictionariesController {

    @Autowired
    private BusinessDictionaryMapper businessDictionaryMapper;


    @ApiOperation("查询字典信息,返回id、zdName：字典名称、备注：remarks")

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "目录不传为查询全部(项目现状：7、项目类型：8、建设状态：9、履约分公司：50)", dataType = "String", dataTypeClass = String.class),
    })
    @GetMapping("/List")
    public R<List<ApiDictionary>> getList(String id )
    {
        return  R.ok(businessDictionaryMapper.selectApiList(id)) ;
    }

}
