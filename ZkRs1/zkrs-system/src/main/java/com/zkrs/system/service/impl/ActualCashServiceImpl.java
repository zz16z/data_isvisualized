package com.zkrs.system.service.impl;

import java.util.List;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.common.core.redis.RedisCache;
import com.zkrs.common.utils.DateUtils;
import com.zkrs.common.utils.StringUtils;
import com.zkrs.system.domain.*;
import com.zkrs.system.tool.FastJsonUtils;
import com.zkrs.system.tool.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import com.zkrs.system.mapper.ActualCashMapper;
import com.zkrs.system.service.IActualCashService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 现金流实际情况Service业务层处理
 *
 * @author zkrs
 * @date 2022-07-01
 */
@Service
public class ActualCashServiceImpl implements IActualCashService
{
    @Autowired
    private ActualCashMapper actualCashMapper;
    @Autowired
    RedisCache redisCache;
    /**
     * 查询现金流实际情况
     *
     * @param xjlsjqkId 现金流实际情况主键
     * @return 现金流实际情况
     */
    @Override
    public ActualCash selectActualCashByXjlsjqkId(Long xjlsjqkId)
    {
        return actualCashMapper.selectActualCashByXjlsjqkId(xjlsjqkId);
    }

    /**
     * 根据关联id获取现金流实际情况详细信息
     *
     * @param id 根据关联id获取现金流实际情况详细信息
     * @return 根据关联id获取现金流实际情况详细信息
     */

    @Override
    public ActualCash selectActualCashByxjlsjqkYjxjlId(String id)
    {
        return actualCashMapper.selectActualCashByxjlsjqkYjxjlId(id);
    }

    /**
     * 查询现金流实际情况列表
     *
     * @param actualCash 现金流实际情况
     * @return 现金流实际情况
     */
    @Override
    public List<ActualCash> selectActualCashList(ActualCash actualCash)
    {
        return actualCashMapper.selectActualCashList(actualCash);
    }


    /**
     * 导出现金流实际情况列表
     *
     * @param actualCash 现金流实际情况
     * @return 现金流实际情况
     */
    @Override
    public List<ActualCash> selectActualCashexport(ActualCash actualCash)
    {
        return actualCashMapper.selectActualCashexport(actualCash);
    }



    /**
     * 新增现金流实际情况
     *
     * @param actualCash 现金流实际情况
     * @return 结果
     */
    @Override
    public int insertActualCash(ActualCash actualCash)
    {
        actualCash.setCreateTime(DateUtils.getNowDate());
        return actualCashMapper.insertActualCash(actualCash);
    }

    /**
     * 修改现金流实际情况
     *
     * @param actualCash 现金流实际情况
     * @return 结果
     */
    @Override
    public int updateActualCash(ActualCash actualCash)
    {
        actualCash.setUpdateTime(DateUtils.getNowDate());
        return actualCashMapper.updateActualCash(actualCash);
    }

    /**
     * 批量删除现金流实际情况
     *
     * @param xjlsjqkIds 需要删除的现金流实际情况主键
     * @return 结果
     */
    @Override
    public int deleteActualCashByXjlsjqkIds(Long[] xjlsjqkIds)
    {
        return actualCashMapper.deleteActualCashByXjlsjqkIds(xjlsjqkIds);
    }

    /**
     * 删除现金流实际情况信息
     *
     * @param xjlsjqkId 现金流实际情况主键
     * @return 结果
     */
    @Override
    public int deleteActualCashByXjlsjqkId(Long xjlsjqkId)
    {
        return actualCashMapper.deleteActualCashByXjlsjqkId(xjlsjqkId);
    }


    /**
     * 现金流实际情况信息导入
     *
     * @param listData 现金流实际情况信息导入
     * @return 结果AjaxResult
     */
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBox(List<ActualCashVerify> listData, String xmid, String xjlsjqkYjxjlId){
        AjaxResult rs=AjaxResult.success("导入失败");
        //验证数据
        ImportError errorInfo=new ImportError() ;
        if(verification(listData,errorInfo,xmid,xjlsjqkYjxjlId)){
            String   projectInfoList= FastJsonUtils.Object2String(listData);
            List<ActualCash> listItem= FastJsonUtils.getJsonToList(projectInfoList,ActualCash.class);

            actualCashMapper.importBox(listItem);
            rs=AjaxResult.success("数据导入成功");
        }else{
            rs = AjaxResult.success("序号" + errorInfo.getProjectName() + "下:" + errorInfo.getErrorInfo());
        }
        return   rs;
    }

    @Override
    public List<ExportManagementCash> selectProjectInfoList(ProjectInfo projectInfo) {
        return actualCashMapper.selectProjectInfoList(projectInfo);
    }


    Boolean  verification(List<ActualCashVerify> listData,ImportError errorInfo,String xmid,String xjlsjqkYjxjlId){

        Boolean  rs=true;
        for(ActualCashVerify item:listData){
//            item.setXjlsjqkProjectId(xmid);
//            item.setXjlsjqkYjxjlId(xjlsjqkYjxjlId);
            errorInfo.setProjectName(item.getXh());
            //////////////////////////////////////
            if(Valid.isNoNull(xmid)){ //项目关联id不能空
                item.setXjlsjqkProjectId(xmid);
            }else{
                errorInfo.setErrorInfo("项目ID不能为空,请在对应项目下导入");
            }

            if(Valid.isNoNull(xjlsjqkYjxjlId)){ //项目关联id不能空
                item.setXjlsjqkYjxjlId(xjlsjqkYjxjlId);
            }else{
                errorInfo.setErrorInfo("预计关联id不能为空,请在对应项预计现金流下导入");
            }

            if (Valid.isNoNull(item.getXjlsjqkSk()) && !Valid.isDouble(item.getXjlsjqkSk() + "", 2)) {

                errorInfo.setErrorInfo("收款额,格式不对正确为：00.00");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getXjlsjqkRzk()) && !Valid.isDouble(item.getXjlsjqkRzk() + "", 2)) {

                errorInfo.setErrorInfo("融资款,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getXjlsjqkSrHj()) && !Valid.isDouble(item.getXjlsjqkSrHj() + "", 2)) {

                errorInfo.setErrorInfo("实际收入合计,格式不对正确为：00.00");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getXjlsjqkSbk()) && !Valid.isDouble(item.getXjlsjqkSbk() + "", 2)) {

                errorInfo.setErrorInfo("设备款,格式不对正确为：00.00");
                rs = false;
                break;
            }if (Valid.isNoNull(item.getXjlsjqkJak()) && !Valid.isDouble(item.getXjlsjqkJak() + "", 2)) {

                errorInfo.setErrorInfo("建安款,格式不对正确为：00.00");
                rs = false;
                break;
            }if (Valid.isNoNull(item.getXjlsjqkZcHj()) && !Valid.isDouble(item.getXjlsjqkZcHj() + "", 2)) {

                errorInfo.setErrorInfo("实际支持合计,格式不对正确为：00.00");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getXjlsjqkYfwsk()) && !Valid.isDouble(item.getXjlsjqkYfwsk() + "", 2)) {

                errorInfo.setErrorInfo("应付未收款,格式不对正确为：00.00");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getXjlsjqkLybzj()) && !Valid.isDouble(item.getXjlsjqkLybzj() + "", 2)) {

                errorInfo.setErrorInfo("履约保证金,格式不对正确为：00.00");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getXjlsjqkQt()) && !Valid.isDouble(item.getXjlsjqkQt() + "", 2)) {

                errorInfo.setErrorInfo("其他,格式不对正确为：00.00");
                rs = false;
                break;
            }
            if (Valid.isNoNull(item.getXjlsjqkGck()) && !Valid.isDouble(item.getXjlsjqkGck() + "", 2)) {

                errorInfo.setErrorInfo("工程款,格式不对正确为：00.00");
                rs = false;
                break;
            }



        }

        return  rs;
       }

}
