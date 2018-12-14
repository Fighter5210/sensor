package com.dy.sensor.common.support.xls;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class CellStyle {

    private final static short DEFAULT_STYLE = 0;
    private final static short TITLE_STYLE = 1;
    private final static short ACCOUNT_EXP_STYLE = 2;
    private final static short AUTH_EXP_STYLE = 3;
    private final static short MONEY_STYLE = 4;
    private final static short OFFSET_STYLE = 5;

    public static HSSFCellStyle getDefaultStyle(HSSFWorkbook wb) {
        return getStyle(wb, DEFAULT_STYLE);
    }

    public static HSSFCellStyle getTitleStyle(HSSFWorkbook wb) {
        return getStyle(wb, TITLE_STYLE);
    }

    public static HSSFCellStyle getAccountStyle(HSSFWorkbook wb) {
        return getStyle(wb, ACCOUNT_EXP_STYLE);
    }

    public static HSSFCellStyle getAuthStyle(HSSFWorkbook wb) {
        return getStyle(wb, AUTH_EXP_STYLE);
    }

    public static HSSFCellStyle getMoneyStyle(HSSFWorkbook wb) {
        return getStyle(wb, MONEY_STYLE);
    }
    
    public static HSSFCellStyle getOffsetStyle(HSSFWorkbook wb) {
        return getStyle(wb, OFFSET_STYLE);
    }

    private static HSSFCellStyle getStyle(HSSFWorkbook wb, short type) {
        HSSFCellStyle style = wb.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置底线和颜色
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 设置左边线和颜色
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN); // 设置右边线和颜色
        style.setRightBorderColor(HSSFColor.BLACK.index);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN); // 设置上面线和颜色
        style.setTopBorderColor(HSSFColor.BLACK.index);

        switch (type) {
        case 0:
            break;
        case 1:
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            // 设置title字体
            HSSFFont titlefont = wb.createFont();
            titlefont.setFontHeightInPoints((short) 14); // 字体大小
            titlefont.setFontName("宋体"); // 什么字体
            titlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
            style.setFont(titlefont);
            break;
        case 2:
            style.setFillForegroundColor(HSSFColor.RED.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            break;
        case 3:
            style.setFillForegroundColor(HSSFColor.YELLOW.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            break;
        case 4:
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
            break;
        case 5:
        	 HSSFFont font=wb.createFont();
        	 font.setColor(HSSFFont.COLOR_RED);
             style.setFont(font);
            break;
        default:
            break;
        }

        return style;
    }
}
