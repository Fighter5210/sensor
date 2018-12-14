package com.dy.sensor.common.tools;

import java.io.FileInputStream;  
import java.io.FileOutputStream;
import java.io.IOException;  
import java.io.InputStream;  
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;  
import java.util.Date;
import java.util.List;  
   
import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.hssf.util.HSSFColor;

import com.dy.sensor.common.exception.BizException;
import com.dy.sensor.foundation.util.UUIDGenerator;
   
   
/**
 * 
* @Title: XlsMain.java
* @Description: TODO
* @author huangrongsheng  
* @date 2015-1-9 上午11:02:32
* @version V1.0
 */
public class XlsMain {  
   
//    public static void main(String[] args) throws IOException {  
////        GatherDeviceAttrPo xls = null;  
////        List<GatherDeviceAttrPo> list = XlsMain.readXls(new FileInputStream("D:/gather_device_attr.xls") );  
////           
////        for (int i = 0; i < list.size(); i++) {  
////        	//// 0 ATTR_ID 1 CATE_ID  2 ATTR_NAME  3 ATTR_CNAME  4 DEF_VAL  5 IS_ACTIVE   6 REMARK
////            xls = (GatherDeviceAttrPo) list.get(i);  
////            System.out.println(xls.getAttrId() + "    " + xls.getCateId() + "    " 
////                    + xls.getAttrName() + "    " + xls.getAttrCname() + "    " 
////                    + xls.getDefVal()+ "    " + xls.getIsActive()+ "    " + xls.getRemark());  
////        }  
//      GatherDeviceInfoVo xls = null;  
//      List<GatherDeviceInfoVo> list = XlsMain.readDeviceXls(new FileInputStream("D:/gatherDeviceInfo_Test.xls") );  
//         
//      for (int i = 0; i < list.size(); i++) {  
//          //0序列号	1主机名称	2主机类型	3管理部门	4设备使用状态	5主机备注	6采购合同	7购买时间
//          //8原始价值(元)	9购买批次	10地理位置	11楼层	12下U位	13U高 	14机柜号	 	15加电状态	16位置说明
//          xls = (GatherDeviceInfoVo) list.get(i);  
//          System.out.println(xls.getSn() + "    " + xls.getDeviceName() + "    " 
//                  + xls.getTerraceType() + "    " + xls.getManager() + "    " 
//                  + xls.getDeviceStatus()+ "    " + xls.getDescription()+ "    " 
//                  + xls.getPurCont() + "    " + xls.getBuydate() + "    " 
//                  + xls.getOldworth()+ "    " + xls.getBuybatch()+ "    "   
//                  + xls.getAddress() + "    " + xls.getFloor() + "    " 
//                  + xls.getUbits()+ "    " + xls.getUhight()+ "    " 
//                  + xls.getCabinet() + "    " + xls.getPowerupStatus() + "    " 
//                  + xls.getAdddesc());  
//      }  
//   
//    }  
//   
//    /**  
//     * 读取xls文件内容  
//     *  
//     * @return List<GatherDeviceAttrPo>对象  
//     * @throws IOException  
//     *             输入/输出(i/o)异常  
//     */ 
//    public static List<GatherDeviceAttrPo> readXls(InputStream is) throws BizException,IOException {  
////        InputStream is = new FileInputStream("D:/gather_device_attr.xls");  
//        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);  
//        GatherDeviceAttrPo xlsDto = null;  
//        List<GatherDeviceAttrPo> list = new ArrayList<GatherDeviceAttrPo>();  
//        // 循环工作表Sheet  
//        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {  
//            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);  
//            if (hssfSheet == null) {  
//                continue;  
//            } 
//            HSSFRow hssfRow0 = hssfSheet.getRow(0);
//            int columnNum=hssfRow0.getPhysicalNumberOfCells();
//            if(columnNum !=SysConstant.GATHERDEVICEATTR_TEMPLATE_COLUMN_NUM){
//          	  throw new BizException("解析上传的文件与模板文件列数不符,无法继续处理,请核实上传文件内容!！");
//            }             
//            // 循环行Row  
//            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {  
//                HSSFRow hssfRow = hssfSheet.getRow(rowNum);  
//                if (hssfRow == null) {  
//                    continue;  
//                }  
//                xlsDto = new GatherDeviceAttrPo();  
//                // 循环列Cell  
//                //    0 ATTR_NAME 1 ATTR_CNAME 2 CATE_ID   3 IS_ACTIVE   4 REMARK
//                
//                	xlsDto.setAttrId(UUIDGenerator.getUUID());   
//
//                HSSFCell attrName = hssfRow.getCell(0);  
//                if (attrName != null) {  
//                	xlsDto.setAttrName(getValue(attrName));   
//                }  
//                 
//                HSSFCell attrCname = hssfRow.getCell(1);  
//                if (attrCname != null) {  
//                	xlsDto.setAttrCname(getValue(attrCname));    
//                }
//                
//                
//                HSSFCell cateId = hssfRow.getCell(2);  
//                if (cateId != null) {  
//                	xlsDto.setCateId(getValue(cateId));  
//                }  
//                                 
//                HSSFCell isActive = hssfRow.getCell(3);  
//                if (isActive == null) {  
//                	xlsDto.setIsActive("Y");
//                }else{
//                	xlsDto.setIsActive(getValue(isActive));                 	
//                }
//                
//                HSSFCell remark = hssfRow.getCell(4);  
//                if (remark == null) {  
//                	xlsDto.setRemark("");  
//                }else{
//                	xlsDto.setRemark(getValue(remark)); 
//                }
//                list.add(xlsDto);  
//            }  
//        }  
//        return list;  
//    } 
//    
//    /**
//	 * 将数据导出到Excel
//	 * 
//	 * @param list
//	 */
//	public static void exportToExcel(List<GatherDeviceAttrVo> list,
//			String sheetTitle, String realPath) {
//		// 第一步，创建一个webbook，对应一个Excel文件
//		HSSFWorkbook wb = new HSSFWorkbook();
//		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
//		HSSFSheet sheet = wb.createSheet(sheetTitle);
//		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
//		HSSFRow row = sheet.createRow((int) 0);
//		// 第四步，创建单元格，并设置值表头
//		// 创建表头格式
//		HSSFCellStyle styleTitle = wb.createCellStyle();
//		styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		styleTitle.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
//		HSSFFont font = wb.createFont();
//		font.setFontName("黑体");
//		styleTitle.setFont(font);
//
//		HSSFCell cell = row.createCell(0);
//		cell.setCellValue("英文名称");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(1);
//		cell.setCellValue("中文名称");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(2);
//		cell.setCellValue("属性类型");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(3);
//		cell.setCellValue("启用状态");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(4);
//		cell.setCellValue("备注");
//		cell.setCellStyle(styleTitle);
//
//		// 第五步，写入实体数据 实际应用中这些数据从数据库得到
//		int i = 1;
//		for (GatherDeviceAttrVo vo : list) {
//			row = sheet.createRow(i);
//			// 第六步，创建单元格，并设置值
//			row.createCell(0).setCellValue(vo.getAttrName());
//			row.createCell(1).setCellValue(vo.getAttrCname());
//			row.createCell(2).setCellValue(vo.getCateName());
//			row.createCell(3).setCellValue(vo.getIsActive());
//			row.createCell(4).setCellValue(vo.getRemark());
//			i += 1;
//		}
//		// 第七步，设置单元格宽度为自适应
//		for (int j = 0; j < 5; j++) {
//			sheet.autoSizeColumn(j);
//		}
//		// 第八步，将文件存到指定位置
//		try {
//			FileOutputStream fout = new FileOutputStream(realPath);
//			wb.write(fout);
//			fout.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//   
//    /**  
//     * 得到Excel表中的值  
//     *  
//     * @param hssfCell  
//     *            Excel中的每一个格子  
//     * @return Excel中每一个格子中的值  
//     */ 
//    @SuppressWarnings("static-access")  
//    private static String getValue(HSSFCell hssfCell) {  
//        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {  
//            // 返回布尔类型的值  
//            return String.valueOf(hssfCell.getBooleanCellValue());  
//        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {  
//            // 返回数值类型的值  
//            return String.valueOf(hssfCell.getNumericCellValue());  
//        } else {  
//            // 返回字符串类型的值  
//            return String.valueOf(hssfCell.getStringCellValue());  
//        }  
//    }
//    
//    /**
//	 * 将主机信息数据导出到Excel
//	 * 
//	 * @param list
//	 */
//	public static void exportDeviceToExcel(List<GatherDeviceInfoVo> list,
//			String sheetTitle, String realPath) {
//		// 第一步，创建一个webbook，对应一个Excel文件
//		HSSFWorkbook wb = new HSSFWorkbook();
//		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
//		HSSFSheet sheet = wb.createSheet(sheetTitle);
//		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
//		HSSFRow row = sheet.createRow((int) 0);
//		// 第四步，创建单元格，并设置值表头
//		// 创建表头格式
//		HSSFCellStyle styleTitle = wb.createCellStyle();
//		styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		styleTitle.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
//		HSSFFont font = wb.createFont();
//		font.setFontName("黑体");
//		styleTitle.setFont(font);
//
//		HSSFCell cell = row.createCell(0);
//		cell.setCellValue("序列号");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(1);
//		cell.setCellValue("主机名称");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(2);
//		cell.setCellValue("主机类型");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(3);
//		cell.setCellValue("管理部门");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(4);
//		cell.setCellValue("设备使用状态");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(5);
//		cell.setCellValue("主机备注");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(6);
//		cell.setCellValue("采购合同");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(7);
//		cell.setCellValue("购买时间");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(8);
//		cell.setCellValue("原始价值(元)");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(9);
//		cell.setCellValue("购买批次");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(10);
//		cell.setCellValue("地理位置");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(11);
//		cell.setCellValue("楼层");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(12);
//		cell.setCellValue("下U位");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(13);
//		cell.setCellValue("U高");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(14);
//		cell.setCellValue("机柜号");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(15);
//		cell.setCellValue("加电状态");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(16);
//		cell.setCellValue("位置说明");
//		cell.setCellStyle(styleTitle);		
//		
//		// 第五步，写入实体数据 实际应用中这些数据从数据库得到
//		int i = 1;
//		for (GatherDeviceInfoVo vo : list) {
//			row = sheet.createRow(i);
//			// 第六步，创建单元格，并设置值
//			row.createCell(0).setCellValue(vo.getSn());
//			row.createCell(1).setCellValue(vo.getDeviceName());
//			row.createCell(2).setCellValue(vo.getTerraceType());
//			row.createCell(3).setCellValue(vo.getManager());
//			row.createCell(4).setCellValue(vo.getDeviceStatus());
//			row.createCell(5).setCellValue(vo.getDescription());			
//			
//			row.createCell(6).setCellValue(vo.getPurCont());
//			if(vo.getBuydate()!=null){
//				row.createCell(7).setCellValue(vo.getBuydate());
//			}else{
//				row.createCell(7).setCellValue("");
//			}
//			if(vo.getOldworth()!=null){
//				row.createCell(8).setCellValue(vo.getOldworth().toString());
//			}else{
//				row.createCell(8).setCellValue("");
//			}
//			row.createCell(9).setCellValue(vo.getBuybatch());
//			
//			row.createCell(10).setCellValue(vo.getAddress());		
//			row.createCell(11).setCellValue(vo.getFloor());
//			row.createCell(12).setCellValue(vo.getUbits());
//			row.createCell(13).setCellValue(vo.getUhight());
//			row.createCell(14).setCellValue(vo.getCabinet());
//			row.createCell(15).setCellValue(vo.getPowerupStatus());
//			row.createCell(16).setCellValue(vo.getAdddesc());
//			i += 1;
//		}
//		// 第七步，设置单元格宽度为自适应
//		for (int j = 0; j < 17; j++) {
//			sheet.autoSizeColumn(j);
//		}
//		// 第八步，将文件存到指定位置
//		try {
//			FileOutputStream fout = new FileOutputStream(realPath);
//			wb.write(fout);
//			fout.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//
//    /**  
//     * 读取主机信息xls文件内容  
//     *  
//     * @return List<GatherDeviceInfoVo>对象  
//     * @throws IOException  
//     *             输入/输出(i/o)异常  
//     */ 
//	public static List<GatherDeviceInfoVo> readDeviceXls(InputStream fis) throws IOException{
////      InputStream is = new FileInputStream("D:/gatherDeviceInfo_Test.xls");  
//      HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fis);  
//      GatherDeviceInfoVo xlsDto = null;  
//      List<GatherDeviceInfoVo> list = new ArrayList<GatherDeviceInfoVo>();  
//      // 循环工作表Sheet  
//      for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {  
//          HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);  
//          if (hssfSheet == null) {  
//              continue;  
//          } 
//          HSSFRow hssfRow0 = hssfSheet.getRow(0);
//          int columnNum=hssfRow0.getPhysicalNumberOfCells();
//          if(columnNum !=SysConstant.GATHERDEVICEINFO_TEMPLATE_COLUMN_NUM){
//        	  throw new IOException("解析上传的文件与模板文件列数不符,无法继续处理,请核实上传文件内容!！");
//          }          
//          
//          // 循环行Row  
//          for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {  
//              HSSFRow hssfRow = hssfSheet.getRow(rowNum);
//              if (hssfRow == null) {  
//                  continue;  
//              } 
//              xlsDto = new GatherDeviceInfoVo();  
//              // 循环列Cell 
//              //0序列号	1主机名称	2主机类型	3管理部门	4设备使用状态	5主机备注	6采购合同	7购买时间	
//
//              HSSFCell sn = hssfRow.getCell(0);  
//              if (sn != null) {  
//              	xlsDto.setSn(getValue(sn));  
//              }  
//              
//              HSSFCell deviceName = hssfRow.getCell(1);  
//              if (deviceName != null) {  
//              	xlsDto.setDeviceName(getValue(deviceName));  
//              }  
//                
//              HSSFCell terraceType = hssfRow.getCell(2);  
//              if (terraceType != null) {  
//              	xlsDto.setTerraceType(getValue(terraceType));   
//              }  
//               
//              HSSFCell manager = hssfRow.getCell(3);  
//              if (manager != null) {  
//              	xlsDto.setManager(getValue(manager));    
//              }  
//             
//              HSSFCell deviceStatus = hssfRow.getCell(4);  
//              if (deviceStatus == null) {  
//              	  xlsDto.setDeviceStatus("");  
//              }else{
//              	 xlsDto.setDeviceStatus(getValue(deviceStatus));  
//              }
//             
//              HSSFCell description = hssfRow.getCell(5);  
//              if (description == null) {  
//              	xlsDto.setDescription("");
//              }else{
//              	xlsDto.setDescription(getValue(description));                 	
//              }
//              
//              HSSFCell purCont = hssfRow.getCell(6);  
//              if (purCont == null) {  
//              	xlsDto.setPurCont("");  
//              }else{
//              	xlsDto.setPurCont(getValue(purCont)); 
//              }
//              HSSFCell buydate = hssfRow.getCell(7);  
//              if (buydate == null) {  
//              	xlsDto.setBuydate(new Date());  
//              }else{
//            	  SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            		String str=getValue(buydate);
//            		Date d = new Date();
//					try {
//						d = sim.parse(str);
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//              	xlsDto.setBuydate(d); 
//              } 
//              // 循环列Cell 
//              //8原始价值(元)	9购买批次	10地理位置	11楼层	12下U位	13U高 	14机柜号	 	15加电状态	16位置说明
//              HSSFCell oldworth = hssfRow.getCell(8);  
//              if (oldworth == null) {  
//              	xlsDto.setOldworth(new BigDecimal(0));  
//              }else{
//             	xlsDto.setOldworth(new BigDecimal(getValue(oldworth))); 
//              } 
//              HSSFCell buybatch = hssfRow.getCell(9);  
//              if (buybatch == null) {  
//              	xlsDto.setBuybatch("");
//              }else{
//              	xlsDto.setBuybatch(getValue(buybatch));                 	
//              }
//              HSSFCell address = hssfRow.getCell(10);  
//              if (address == null) {  
//              	xlsDto.setAddress("");
//              }else{
//              	xlsDto.setAddress(getValue(address));                 	
//              }   
//              HSSFCell floor = hssfRow.getCell(11);  
//              if (floor == null) {  
//              	xlsDto.setFloor("");
//              }else{
//              	xlsDto.setFloor(getValue(floor));                 	
//              }   
//              HSSFCell ubits = hssfRow.getCell(12);  
//              if (ubits == null) {  
//              	xlsDto.setUbits("");
//              }else{
//              	xlsDto.setUbits(getValue(ubits));                 	
//              }   
//              HSSFCell uhight = hssfRow.getCell(13);  
//              if (uhight == null) {  
//              	xlsDto.setUhight("");
//              }else{
//              	xlsDto.setUhight(getValue(uhight));                 	
//              } 
//              HSSFCell cabinet = hssfRow.getCell(14);  
//              if (cabinet == null) {  
//              	xlsDto.setCabinet("");
//              }else{
//              	xlsDto.setCabinet(getValue(cabinet));                 	
//              }
//              HSSFCell powerupStatus = hssfRow.getCell(15);  
//              if (powerupStatus == null) {  
//              	xlsDto.setPowerupStatus("");
//              }else{
//              	xlsDto.setPowerupStatus(getValue(powerupStatus));                 	
//              }
//              HSSFCell adddesc = hssfRow.getCell(16);  
//              if (adddesc == null) {  
//              	xlsDto.setAdddesc("");
//              }else{
//              	xlsDto.setAdddesc(getValue(adddesc));                 	
//              }
//              list.add(xlsDto);  
//          }  
//      }  
//      return list; 
//	}  
   
} 
