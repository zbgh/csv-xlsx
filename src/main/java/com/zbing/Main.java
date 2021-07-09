package com.zbing;

import com.zbing.excel.Bean;
import com.zbing.excel.ExcelBean;
import com.zbing.excel.ExcelUtil;
import com.zbing.reflect.ReflectUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Main
 * @Author zbing
 * @Description
 * @Date 2021/7/8 15:16
 **/
public class Main {
    public static void main(String[] args) {
        final String csv = "C:\\Users\\zbing\\Desktop\\data.csv";
//        String xls = csv.replace(".csv", ".xls");
        String xlsx = csv.replace(".csv", ".xlsx");
        System.out.println(xlsx);
        conversion(csv, xlsx);
    }


    public static void conversion(String csv, String excel) {
        boolean isXls = false;
        String fileType = excel.substring(excel.lastIndexOf("."));
        if (ExcelUtil.excel2003L.equals(fileType)) {
            isXls = true;
        }
        Map<Integer, List<ExcelBean>> map = new HashMap<>();
        //表格头部和属性
        final ArrayList<ExcelBean> ebList = new ArrayList<>();
        //动态类属性
        Map<String, Object> propertiesMap = new HashMap<String, Object>();
        //表格值
        List<Object> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(csv)))) {
            String str;
            boolean isHeard = true;
            while (null != (str = reader.readLine())) {
                String[] splitStr = str.split(",");
                for (int i = 0; i < splitStr.length; i++) {
                    String s = splitStr[i];
                    //设置表头
                    if (isHeard) {
                        ExcelBean excelBean = new ExcelBean();
                        excelBean.setHeadTextName(s);
                        excelBean.setPropertyName("n" + i);
                        ebList.add(excelBean);
                    } else {
                        propertiesMap.put("n" + i, s);
                    }
                }
                if (null != propertiesMap && propertiesMap.size() > 0) {
                    Bean bean = new Bean();
                    Object obj = ReflectUtil.getObject(bean, propertiesMap);
                    list.add(obj);
                }
                isHeard = false;
            }
            map.computeIfAbsent(0, v -> ebList);
            if (CollectionUtils.isEmpty(list)) {
                System.out.println("表格无数据！");
                return;
            }
            Workbook workbook = ExcelUtil.createExcelFile(
                    list, map, isXls, null
            );

            try (FileOutputStream stream = new FileOutputStream(excel)) {
                workbook.write(stream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
