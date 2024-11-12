package com.zkrs.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zkrs.common.annotation.Excel;
import com.zkrs.common.core.domain.BaseEntity;

/**
 * 业务字典对象 business_dictionary
 * 
 * @author zkrs
 * @date 2022-07-04
 */

public class BusinessDictionary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 父亲id */
    @Excel(name = "父亲id")
    private Long pId;


    /** 0表示目录、1表示子项 */
    @Excel(name = "0表示目录、1表示子项")
    private String isCatalogue;

    /** 字典名称 */
    @Excel(name = "字典名称")
    private String zdName;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDelete;

    /** 父目录名称 */
    @Excel(name = "父目录名称")
    private String fName;

    /** 操作前name */
    private String oldName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getIsCatalogue() {
        return isCatalogue;
    }

    public void setIsCatalogue(String isCatalogue) {
        this.isCatalogue = isCatalogue;
    }

    public String getZdName() {
        return zdName;
    }

    public void setZdName(String zdName) {
        this.zdName = zdName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }
}
