package com.liruilong.service.utils;

import com.liruilong.model.LogType;
import com.liruilong.model.OpLog;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import org.springframework.context.support.BeanDefinitionDslKt;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Liruilong 
 * @Description 
 * @Date 15:01 2020/3/2
 * @Param  
 * @return  
 **/

public class POIUtils {

    public static ResponseEntity<byte[]> OpLog2Excel(List<OpLog> list) {
        //1. 创建一个 Excel 文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2. 创建文档摘要
        workbook.createInformationProperties();
        //3. 获取并配置文档信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        //文档类别
        docInfo.setCategory("日志信息");
        //文档管理员
        docInfo.setManager("liruilong");
        //设置公司信息
        docInfo.setCompany("www.liruiong.org");
        //4. 获取文档摘要信息
        SummaryInformation summInfo = workbook.getSummaryInformation();
        //文档标题
        summInfo.setTitle("日志信息表");
        //文档作者
        summInfo.setAuthor("liruilong");
        // 文档备注
        summInfo.setComments("本文档由 liruilong 提供");
        //5. 创建样式
        //创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        HSSFSheet sheet = workbook.createSheet("日志信息表");
        //设置列的宽度
        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 5 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        //6. 创建标题行
        HSSFRow r0 = sheet.createRow(0);
        HSSFCell c0 = r0.createCell(0);
        c0.setCellValue("编号");
        c0.setCellStyle(headerStyle);
        HSSFCell c1 = r0.createCell(1);
        c1.setCellStyle(headerStyle);
        c1.setCellValue("日志类型");
        HSSFCell c2 = r0.createCell(2);
        c2.setCellStyle(headerStyle);
        c2.setCellValue("日志描述");
        HSSFCell c3 = r0.createCell(3);
        c3.setCellStyle(headerStyle);
        c3.setCellValue("操作人");
        HSSFCell c4 = r0.createCell(4);
        c4.setCellStyle(headerStyle);
        c4.setCellValue("日期");
        HSSFCell c5 = r0.createCell(5);

        for (int i = 0; i < list.size(); i++) {
            OpLog opLog = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(opLog.getId());
            row.createCell(1).setCellValue(opLog.getlogTypss().getLogtypemsg());
            row.createCell(2).setCellValue(opLog.getOperate());
            row.createCell(3).setCellValue(opLog.getHrname());
            HSSFCell cell4 = row.createCell(4);
            cell4.setCellStyle(dateCellStyle);
            cell4.setCellValue(opLog.getAdddate());
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", new String("日志表.xls".getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

   /**
    * @Author Liruilong
    * @Description
    * @Date 18:14 2020/3/2
    * @Param [file, logTypes]
    * @return java.util.List<com.liruilong.model.OpLog>
    **/

    public static List<OpLog> excel2Oplog(MultipartFile file,  List<LogType> logTypes) {
        List<OpLog>  logList= new ArrayList<>();
        OpLog opLog = null;
        try {
            //1. 创建一个 workbook 对象
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            //2. 获取 workbook 中表单的数量
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                //3. 获取表单
                HSSFSheet sheet = workbook.getSheetAt(i);
                //4. 获取表单中的行数
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    //5. 跳过标题行
                    if (j == 0) {
                        continue;//跳过标题行
                    }
                    //6. 获取行
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//防止数据中间有空行
                    }
                    //7. 获取列数
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    opLog = new OpLog();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        switch (cell.getCellType()) {
                            case STRING:
                                String cellValue = cell.getStringCellValue();
                                switch (k) {
                                    case 1:
                                        int lIndex = logTypes.indexOf(new LogType(cellValue));
                                        opLog.setLogtype(Byte.parseByte(logTypes.get(lIndex).getId() + ""));
                                        break;
                                    case 2:
                                        opLog.setOperate(cellValue);
                                        break;
                                    case 3:
                                        opLog.setHrname(cellValue);
                                        break;
                                }
                                break;
                            default: {
                                switch (k) {
                                    case 4:
                                        opLog.setAdddate(cell.getDateCellValue());
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    logList.add(opLog);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return logList;
    }
}

