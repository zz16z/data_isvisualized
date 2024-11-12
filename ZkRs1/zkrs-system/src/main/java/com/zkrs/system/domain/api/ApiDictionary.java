package com.zkrs.system.domain.api;

import com.zkrs.common.annotation.Excel;
import lombok.Data;

@Data
public class ApiDictionary {


    /**
     * id
     */
    private Long id;




    /**
     * 字典名称
     */
    private String zdName;

    /**
     * 备注
     */
    private String remarks;



    /**
     * 父id
     */
    private String pId;



}


