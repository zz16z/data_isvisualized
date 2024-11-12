package com.zkrs.system.service.impl;

import java.util.List;

import com.zkrs.common.core.domain.AjaxResult;
import com.zkrs.common.core.redis.RedisCache;
import com.zkrs.common.utils.DateUtils;
import com.zkrs.common.utils.uuid.IdUtils;
import com.zkrs.system.domain.ActualCash;
import com.zkrs.system.domain.ExpectCashVerify;
import com.zkrs.system.domain.ImportError;
import com.zkrs.system.domain.dto.ExpectCashDto;
import com.zkrs.system.mapper.ActualCashMapper;
import com.zkrs.system.tool.FastJsonUtils;
import com.zkrs.system.tool.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import com.zkrs.system.mapper.ExpectCashMapper;
import com.zkrs.system.domain.ExpectCash;
import com.zkrs.system.service.IExpectCashService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 现金流预计情况Service业务层处理
 *
 * @author zkrs
 * @date 2022-07-01
 */
@Service
public class ExpectCashServiceImpl implements IExpectCashService {
    @Autowired
    private ExpectCashMapper expectCashMapper;

    @Autowired
    private ActualCashMapper actualCashMapper;//资金实际
    @Autowired
    RedisCache redisCache;
    /**
     * 查询现金流预计情况
     *
     * @param xjlyjqkId 现金流预计情况主键
     * @return 现金流预计情况
     */
    @Override
    public ExpectCash selectExpectCashByXjlyjqkId(Long xjlyjqkId) {
        return expectCashMapper.selectExpectCashByXjlyjqkId(xjlyjqkId);
    }

    /**
     * 查询现金流预计情况列表
     *
     * @param expectCash 现金流预计情况
     * @return 现金流预计情况
     */
    @Override
    public List<ExpectCash> selectExpectCashList(ExpectCash expectCash) {
        return expectCashMapper.selectExpectCashList(expectCash);
    }


    /**
     * 导出现金流预计情况列表
     *
     * @param expectCash 现金流预计情况
     * @return 现金流预计情况
     */
    @Override
    public List<ExpectCash> selectExpectCashExport(ExpectCash expectCash) {
        return expectCashMapper.selectExpectCashExport(expectCash);
    }



    /**
     * 查询现金流预计情况列表合并
     *
     * @param expectCash 现金流预计情况合并
     * @return 现金流预计情况合并
     */
    @Override
    public List<ExpectCashDto> selectExpectCashHbList(ExpectCashDto expectCash) {
        return expectCashMapper.selectExpectCashHbList(expectCash);
    }


    /**
     * 新增现金流预计情况
     *
     * @param expectCash 现金流预计情况
     * @return 结果
     */
    @Override
    public int insertExpectCash(ExpectCash expectCash) {
        expectCash.setCreateTime(DateUtils.getNowDate());
        return expectCashMapper.insertExpectCash(expectCash);
    }

    /**
     * 修改现金流预计情况
     *
     * @param expectCash 现金流预计情况
     * @return 结果
     */
    @Override
    public int updateExpectCash(ExpectCash expectCash) {
        expectCash.setUpdateTime(DateUtils.getNowDate());
        return expectCashMapper.updateExpectCash(expectCash);
    }

    /**
     * 批量删除现金流预计情况
     *
     * @param xjlyjqkIds 需要删除的现金流预计情况主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteExpectCashByXjlyjqkIds(Long[] xjlyjqkIds) {
        actualCashMapper.deleteActualCashByXjlsjqkIdsJll(xjlyjqkIds);
        return expectCashMapper.deleteExpectCashByXjlyjqkIds(xjlyjqkIds);
    }

    /**
     * 删除现金流预计情况信息
     *
     * @param xjlyjqkId 现金流预计情况主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteExpectCashByXjlyjqkId(Long xjlyjqkId) {
        Long[] id=new Long[]{xjlyjqkId};
        actualCashMapper.deleteActualCashByXjlsjqkIdsJll(id);
        return expectCashMapper.deleteExpectCashByXjlyjqkId(xjlyjqkId);
    }


    /**
     * 现金流预计情况导入
     *
     * @param listData 现金流预计情况导入
     * @return 结果AjaxResult
     */
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importBox(List<ExpectCashVerify> listData, String xmid) {
        AjaxResult rs = AjaxResult.success("导入失败");
        //验证数据
        ImportError errorInfo = new ImportError();
        if (verification(listData, errorInfo, xmid)) {
            String   projectInfoList= FastJsonUtils.Object2String(listData);
            List<ExpectCash> listItem= FastJsonUtils.getJsonToList(projectInfoList,ExpectCash.class);
            List<ActualCash> listActualCash= FastJsonUtils.getJsonToList(projectInfoList,ActualCash.class);
            expectCashMapper.importBox(listItem);
            actualCashMapper.importBox(listActualCash);
            rs = AjaxResult.success("数据导入成功");
        } else {
            rs = AjaxResult.success(errorInfo.getProjectName() + "项目下:" + errorInfo.getErrorInfo());
        }
        return rs;

    }

    //导入数据验证
    Boolean verification(List<ExpectCashVerify> listData, ImportError errorInfo, String xmid) {
        Boolean rs = true;

        for(ExpectCashVerify item:listData){
            //设置关联id
            String  stringUuid=IdUtils.fastSimpleUUID();
            item.setXjlsjqkYjxjlId(stringUuid);
            item.setXjlsjqkYjxjlId(stringUuid);
            errorInfo.setProjectName(item.getXh());
            if(Valid.isNoNull(xmid)){ //项目关联id不能空
                item.setXjlyjqkProjectId(xmid);
                item.setXjlsjqkProjectId(xmid);
            }else{
                errorInfo.setErrorInfo("没有关联项目,请在对应项目下导入");
            }
            if (Valid.isNoNull(item.getXjlyjqkRq())) {
                if (!Valid.isValidDate(item.getXjlyjqkRq())) {
                    errorInfo.setErrorInfo("日期格式不对");
                    rs = false;
                    break;
                }
            }



            if (Valid.isNoNull(item.getXjlyjqkYjcszj()) && !Valid.isDouble(item.getXjlyjqkYjcszj() + "", 2)) {

                errorInfo.setErrorInfo("已经出售总价,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getXjlyjqkYjecpcb()) && !Valid.isDouble(item.getXjlyjqkYjecpcb() + "", 2)) {

                errorInfo.setErrorInfo("预计EPC成本,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getXjlyjqkYjxmgscb()) && !Valid.isDouble(item.getXjlyjqkYjxmgscb() + "", 2)) {

                errorInfo.setErrorInfo("预计项目公司成本,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getXjlyjqkSk()) && !Valid.isDouble(item.getXjlyjqkSk() + "", 2)) {

                errorInfo.setErrorInfo("预收购款,格式不对正确为：00.00");
                rs = false;
                break;
            }


            if (Valid.isNoNull(item.getXjlyjqkRzk()) && !Valid.isDouble(item.getXjlyjqkRzk() + "", 2)) {

                errorInfo.setErrorInfo("融资款,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getXjlyjqkGck()) && !Valid.isDouble(item.getXjlyjqkGck() + "", 2)) {

                errorInfo.setErrorInfo("工程款,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getXjlyjqkYjlr()) && !Valid.isDouble(item.getXjlyjqkYjlr() + "", 2)) {

                errorInfo.setErrorInfo("预计利润,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getXjlyjqkSrHj()) && !Valid.isDouble(item.getXjlyjqkSrHj() + "", 2)) {

                errorInfo.setErrorInfo("预计总收入,格式不对正确为：00.00");
                rs = false;
                break;
            }



            if (Valid.isNoNull(item.getXjlyjqkSbk()) && !Valid.isDouble(item.getXjlyjqkSbk() + "", 2)) {

                errorInfo.setErrorInfo("设备款,格式不对正确为：00.00");
                rs = false;
                break;
            }


            if (Valid.isNoNull(item.getXjlyjqkJak()) && !Valid.isDouble(item.getXjlyjqkJak() + "", 2)) {

                errorInfo.setErrorInfo("建安款,格式不对正确为：00.00");
                rs = false;
                break;
            }


            if (Valid.isNoNull(item.getXjlyjqkQtkx()) && !Valid.isDouble(item.getXjlyjqkQtkx() + "", 2)) {

                errorInfo.setErrorInfo("其他款项,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getXjlyjqkZcHj()) && !Valid.isDouble(item.getXjlyjqkZcHj() + "", 2)) {
                errorInfo.setErrorInfo("预计总支出,格式不对正确为：00.00");
                rs = false;
                break;
            }

            if (Valid.isNoNull(item.getXjlyjqkDfsk()) && !Valid.isDouble(item.getXjlyjqkDfsk() + "", 2)) {
                errorInfo.setErrorInfo("资源费,格式不对正确为：00.00");
                rs = false;
                break;
            }



            ////////////////实际表验证///////////////////////////


            if (Valid.isNoNull(item.getXjlsjqkSk()) && !Valid.isDouble(item.getXjlsjqkSk() + "", 2)) {

                errorInfo.setErrorInfo("预收购款,格式不对正确为：00.00");
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
