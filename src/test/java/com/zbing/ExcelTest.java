package com.zbing;

import com.zbing.excel.Bean;
import com.zbing.excel.ExcelBean;
import com.zbing.excel.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExcelTest
 * @Author zbing
 * @Description
 * @Date 2021/7/8 15:40
 * XSSFWorkbook：2007版本（包含2007）以后的扩展名为.xlsx使用该类
 * HSSFWorkbook：2003版本（包含2003）以前的扩展名为.xls使用该类
 * SXSSFWorkbook：07的优化版本，相当于为其添加了缓存功能，可以对插入大批量的数据进行优化。
 **/
public class ExcelTest {

    @Test
    public void createHSSFWorkbook() {
        String path = "C:\\Users\\zbing\\Desktop\\";
        //创建Workbook工作簿
        Workbook w = new HSSFWorkbook();
        //创建Sheet
        Sheet sheet = w.createSheet("Excel统计表");
        //创建行
        Row row = sheet.createRow(0);
        //创建列
        Cell cell = row.createCell(0);
        //设置改行该列的值
        cell.setCellValue("统计个数");
        cell = row.createCell(1);
        cell.setCellValue(666);
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("生成日期");
        cell = row.createCell(1);
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        cell.setCellValue(date);
        //写入到文件
        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream(path + "测试表.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            w.write(fo);
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createXSSFWorkbook() {
        String path = "C:\\Users\\zbing\\Desktop\\";
        //创建Workbook工作簿
        Workbook w = new XSSFWorkbook();
        //创建Sheet
        Sheet sheet = w.createSheet("Excel统计表");
        //创建行
        Row row = sheet.createRow(0);
        //创建列
        Cell cell = row.createCell(0);
        //设置改行该列的值
        cell.setCellValue("统计个数");
        cell = row.createCell(1);
        cell.setCellValue(666);
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("生成日期");
        cell = row.createCell(1);
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        cell.setCellValue(date);
        //写入到文件
        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream(path + "测试表.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            w.write(fo);
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        try {
            Map<Integer, List<ExcelBean>> map = new HashMap<>();
            map.computeIfAbsent(0, v -> {
                final ArrayList<ExcelBean> lists = new ArrayList<>();
                for (int i = 1; i < 7; i++) {
                    final ExcelBean excelBean = new ExcelBean();
                    excelBean.setHeadTextName("n" + i);
                    excelBean.setPropertyName("n" + i);
                    lists.add(excelBean);
                }
                return lists;
            });
            List<Object> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Bean bean = new Bean();
                list.add(bean);
            }
            String file = "C:\\Users\\zbing\\Desktop\\测试表.xls";
            Workbook workbook = ExcelUtil.createExcelFile(
                    list, map,false, null
            );

            try(FileOutputStream stream=new FileOutputStream(file)){
                workbook.write(stream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aa()  {

    }

}
