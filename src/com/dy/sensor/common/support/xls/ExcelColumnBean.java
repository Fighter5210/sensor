package com.dy.sensor.common.support.xls;


import org.apache.poi.hssf.usermodel.HSSFCell;

public class ExcelColumnBean {
    private String title;
    private String name;
    private Integer width;
    /**
     * 取HSSFCell单元格类型
     */
    private Integer cellType;

    public ExcelColumnBean(String title, String name, Integer width) {
        this.title = title;
        this.name = name;
        this.width = width;
        this.cellType = HSSFCell.CELL_TYPE_STRING;// 默认文本类型
    }

    public ExcelColumnBean(String title, String name, Integer width, Integer cellType) {
        this.title = title;
        this.name = name;
        this.width = width;
        this.cellType = cellType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getCellType() {
        return cellType;
    }

    public void setCellType(Integer cellType) {
        this.cellType = cellType;
    }
}
