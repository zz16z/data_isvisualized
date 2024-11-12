package com.zkrs.system.service.impl;


import com.zkrs.system.mapper.DataClearMapper;
import com.zkrs.system.service.IDataClearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 现金流删除业务层处理
 *
 * @author zkrs
 * @date 2022-07-01
 */
@Service
public class DataClearServiceImpl implements IDataClearService
{
    @Autowired
    private DataClearMapper dataClearServiceMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int dataClearInfo() {
        int rs=200;
        //项目信息
        dataClearServiceMapper.dataClearInfo();
        //经营情况
        dataClearServiceMapper.dataClearTepayment();
        dataClearServiceMapper.dataClearManagement();
        //融资
        dataClearServiceMapper.dataClearFinancing();
        dataClearServiceMapper.dataClearData();
        dataClearServiceMapper.dataClearSill();
        //电量
        dataClearServiceMapper.dataClearElectricity();
        //现金流
        dataClearServiceMapper.dataClearProjectCashInfoAll();
        dataClearServiceMapper.dataClearProjectCashAll();
        return rs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int dataClearConstruction() {
        int rs=200;
        return rs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int dataClearManagement() {
        int rs=200;
        dataClearServiceMapper.dataClearTepayment();
        dataClearServiceMapper.dataClearManagement();
        return rs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int dataClearFinancing() {
        int rs=200;
        //还款
        dataClearServiceMapper.dataClearSill();
        //放款
        dataClearServiceMapper.dataClearData();
        //融资
        dataClearServiceMapper.dataClearFinancing();
        return rs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int dataClearTepayment() {
        int rs=200;
        //还款
        dataClearServiceMapper.dataClearSill();
        return rs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int dataClearElectricity() {
        int rs=200;
        dataClearServiceMapper.dataClearElectricity();
        return rs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int dataClearProjectCash() {
        int rs=200;
        dataClearServiceMapper.dataClearProjectCashOut();
        dataClearServiceMapper.dataClearProjectCashZbOut();
        return rs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int dataClearProjectCashIn() {
        int rs=200;
        dataClearServiceMapper.dataClearProjectCashIn();
        dataClearServiceMapper.dataClearProjectCashZbIn();
        return rs;
    }
}
