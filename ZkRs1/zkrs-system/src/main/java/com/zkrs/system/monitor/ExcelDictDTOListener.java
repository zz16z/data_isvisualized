package com.zkrs.system.monitor;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zkrs.system.domain.ProjectFinancing;
import com.zkrs.system.domain.dto.ProjectFinancingDto;
import com.zkrs.system.service.IProjectFinancingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ExcelDictDTOListener  extends AnalysisEventListener<ProjectFinancingDto> {

    private static final Logger log = LoggerFactory.getLogger(ExcelDictDTOListener.class);

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<ProjectFinancingDto> list = new ArrayList<ProjectFinancingDto>();

    private IProjectFinancingService projectFinancingService;

    //传入mapper对象
    public ExcelDictDTOListener(IProjectFinancingService projectFinancingService) {
        this.projectFinancingService = projectFinancingService;
    }

    /**
     *遍历每一行的记录
     * @param data
     * @param context
     */
    @Override
    public void invoke(ProjectFinancingDto data, AnalysisContext context) {
        log.info("解析到一条记录: {}", data);
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }


    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        projectFinancingService.insertProjectFinancingAll(list);  //批量插入
        log.info("存储数据库成功！");
    }

}
