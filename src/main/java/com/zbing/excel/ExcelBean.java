package com.zbing.excel;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @ClassName ExcelBean
 * @Author zbing
 * @Description
 * @Date 2021/7/8 15:51
 **/
public class ExcelBean {
    private String propertyName;
    private String headTextName;
    private CellStyle cellStyle;
    private int cols;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getHeadTextName() {
        return headTextName;
    }

    public void setHeadTextName(String headTextName) {
        this.headTextName = headTextName;
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
}
