package com.zkrs.system.tool;

import ch.qos.logback.core.util.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.zkrs.system.domain.ProjectFinancing;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
/**
 * @author 言曌
 * @date 2020-01-02 11:21
 */
public class ExcelTool {
    /**
     * 导出
     *
     * @param response
     * @param data
     * @param fileName
     * @param sheetName
     * @param clazz
     * @throws Exception
     */
    public static void writeExcel(HttpServletResponse response, List<? extends Object> data, String fileName, String sheetName, Class clazz) throws Exception {
        //表头样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置表头居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置内容靠左对齐
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        EasyExcel.write(getOutputStream(fileName, response), clazz).excelType(ExcelTypeEnum.XLSX).sheet(sheetName).registerWriteHandler(horizontalCellStyleStrategy).doWrite(data);
    }

    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        return response.getOutputStream();
    }


    //下载模板
    public static void downloadExcelOld(HttpServletRequest request, HttpServletResponse response) {
        //方法一：直接下载路径下的文件模板（这种方式貌似在SpringCloud和Springboot中，打包成JAR包时，无法读取到指定路径下面的文件，不知道记错没，你们可以自己尝试下！！！）
        try {
            //文件名称
            String fileName = "importStaffRoster.xlsx";
            //设置要下载的文件的名称
            response.setHeader("Content-disposition", "attachment;fileName=" + fileName);
            //通知客服文件的MIME类型
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            //获取文件的路径
            String filePath = FileUtil.class.getResource("/template/" + fileName).getPath();
            FileInputStream input = new FileInputStream(filePath);
            OutputStream out = response.getOutputStream();
            byte[] b = new byte[2048];
            int len;
            while ((len = input.read(b)) != -1) {
                out.write(b, 0, len);
            }
            //修正 Excel在“xxx.xlsx”中发现不可读取的内容。是否恢复此工作薄的内容？如果信任此工作簿的来源，请点击"是"
            response.setHeader("Content-Length", String.valueOf(input.getChannel().size()));
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //导入
    public static void exportExcel(HttpServletResponse response, String filename, List<T> list, String templateName) {

        //获取模板，//使用response.getOutputStream()下载,并使用项目下的模板填充
        ClassPathResource classPathResource = new ClassPathResource("templates/" + templateName + "xlsx");
        try (InputStream inputStream = classPathResource.getInputStream();
             ServletOutputStream outputStream = response.getOutputStream()) {
            String filename1 = URLEncoder.encode(filename, "utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + filename1 + ".xlsx");
            //设置输出流和模板信息
            ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").build();
            //开启自动换行,自动换行表示每次写入一条list数据是都会重新生成一行空行,此选项默认是关闭的,需要提前设置为true
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
//            List<ProjectFinancing>  list=  projectFinancingService.selectProjectFinancingList(null);
            excelWriter.fill(list, fillConfig, writeSheet);
//            excelWriter.fill(list, writeSheet);
            excelWriter.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //下载模板

    public static void  downloadExcel(HttpServletResponse response, String templateName){

        //获取模板，//使用response.getOutputStream()下载,并使用项目下的模板填充
        ClassPathResource classPathResource = new ClassPathResource("templates/"+templateName+".xlsx");
        try(
                InputStream inputStream = classPathResource.getInputStream();
                ServletOutputStream outputStream = response.getOutputStream())

        {
            String filename = URLEncoder.encode(templateName, "utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + filename + ".xlsx");
            //设置输出流和模板信息
            ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build();
            //开启自动换行,自动换行表示每次写入一条list数据是都会重新生成一行空行,此选项默认是关闭的,需要提前设置为true
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
            excelWriter.finish();
        } catch(Exception e)
        {
            e.printStackTrace();
        }

    }

////////////////////ok版//////////
//public void downFrozenTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String fileName = "模板.xlsx";
//        ServletOutputStream out;
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("content-type:octet-stream");
//        //文件在项目中的存放路径
//        String filePath = getClass().getResource("/templates/template1.xlsx").getPath();
//        String userAgent = request.getHeader("User-Agent");
//        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
//        fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
//        } else {
//        // 非IE浏览器的处理：
//        fileName = new String((fileName).getBytes("UTF-8"), "ISO-8859-1");
//        }
//        filePath = URLDecoder.decode(filePath, "UTF-8");
//        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
//        FileInputStream inputStream = new FileInputStream(filePath);
//        out = response.getOutputStream();
//        int b = 0;
//        byte[] buffer = new byte[1024];
//        while ((b = inputStream.read(buffer)) != -1) {
//        //写到输出流(out)中
//        out.write(buffer, 0, b);
//        }
//        inputStream.close();
//        if (out != null) {
//        out.flush();
//        out.close();
//        }
//        }


}