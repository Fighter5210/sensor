package com.dy.sensor.product.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.dy.sensor.common.action.BaseAction;
import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.support.Pagination;
import com.dy.sensor.common.support.PaginationParam;
import com.dy.sensor.common.support.xls.CellStyle;
import com.dy.sensor.common.support.xls.ExcelColumnBean;
import com.dy.sensor.common.support.xls.FileBean;
import com.dy.sensor.foundation.util.JsonUtil;
import com.dy.sensor.product.model.ProductDataBean;
import com.dy.sensor.product.model.po.BarCodesPo;
import com.dy.sensor.product.model.po.ProductPo;
import com.dy.sensor.product.model.vo.ProductDataVo;
import com.dy.sensor.product.service.IBarCodesService;
import com.dy.sensor.product.service.IProductService;
import com.dy.sensor.sys.dao.ISysParamDao;
import com.dy.sensor.sys.model.vo.SysParamVo;

public class ProductAction extends BaseAction {

	private static final long serialVersionUID = 329347674483723005L;
	private IProductService productService;
	private IBarCodesService barCodesService;
	private ProductPo productPo;
	private ISysParamDao sysParamDao;
	private InputStream excelFile;
	private String fileName;

	private static ExcelColumnBean addressCode = new ExcelColumnBean("产品地址码",
			"addressCode", 3500);
	private static ExcelColumnBean barCode = new ExcelColumnBean("条形码",
			"barCode", 3500);

	private static ExcelColumnBean productDate = new ExcelColumnBean("生产日期",
			"productDate", 4000);
	private static ExcelColumnBean productBatch = new ExcelColumnBean("生产批次",
			"productBatch", 15000);
	private static ExcelColumnBean temperatureStd = new ExcelColumnBean(
			"温度标准值", "temperatureStd", 5000, HSSFCell.CELL_TYPE_NUMERIC);
	private static ExcelColumnBean temperatureData = new ExcelColumnBean(
			"温度数据", "temperatureData", 5000, HSSFCell.CELL_TYPE_NUMERIC);
	private static ExcelColumnBean temperatureOffset = new ExcelColumnBean(
			"温度偏差", "temperatureOffset", 5000, HSSFCell.CELL_TYPE_NUMERIC);
	private static ExcelColumnBean humidityStd = new ExcelColumnBean("湿度标准值",
			"humidityStd", 5000, HSSFCell.CELL_TYPE_NUMERIC);
	private static ExcelColumnBean humidityData = new ExcelColumnBean("湿度数据",
			"humidityData", 5000, HSSFCell.CELL_TYPE_NUMERIC);
	private static ExcelColumnBean humidityOffset = new ExcelColumnBean("湿度偏差",
			"humidityOffset", 5000, HSSFCell.CELL_TYPE_NUMERIC);
	
    private static List<ExcelColumnBean> offsetBeans = new ArrayList<ExcelColumnBean>();
    static {
    	offsetBeans.add(temperatureOffset);
    	offsetBeans.add(humidityOffset);
    }

	public String addProduct() {

		try {
			String productId = productService.addProduct(productPo);
			String[] addressCodes = this.getRequest().getParameterValues(
					"addressCode");
			String[] barCodes = this.getRequest().getParameterValues("barCode");

			List<BarCodesPo> barCodeList = new ArrayList<BarCodesPo>();
			if (addressCodes != null && addressCodes.length > 0) {
				for (int i = 0; i < addressCodes.length; i++) {
					BarCodesPo barCodesPo = new BarCodesPo();
					barCodesPo.setProductId(productPo.getId());
					barCodesPo.setAddressCode(addressCodes[i]);
					barCodesPo.setBarCode(barCodes[i]);
					barCodeList.add(barCodesPo);
				}
			}
			barCodesService.addBarCodesBatch(barCodeList);
			this.getRequest().setAttribute("productId", productId);
		} catch (RollbackableBizException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void updateStd() {

		try {
			productService.updateStd(productPo);
		} catch (RollbackableBizException e) {
			e.printStackTrace();
		}

	}

	public void sendCommand() throws Exception {
		try {
			List<ProductDataVo> list = productService.sendCommand(productPo
					.getId().toString());
			Pagination<ProductDataVo> pagination = new Pagination<ProductDataVo>();
			pagination.setDataList(list);
			this.jsonOut(pagination);
		} catch (RollbackableBizException e) {
			e.printStackTrace();
		}

	}

	public void getProductById() throws Exception {
		try {
			this.jsonOut(productService.getProductById(String.valueOf(productPo
					.getId())));
		} catch (RollbackableBizException e) {
			e.printStackTrace();
		}

	}
	public void updateProductData() throws Exception{
		String id = this.getRequest().getParameter("id");
		String flag = this.getRequest().getParameter("flag");
		ProductDataVo productDataVo =  new ProductDataVo();
		productDataVo.setId(Integer.parseInt(id));
		if("delete".equals(flag)){
			productDataVo.setIsDelete("1");
		}else if("recover".equals(flag)){
			productDataVo.setIsDelete("0");
		}
		productService.updateProductData(productDataVo);
		this.jsonOut(productDataVo);
	}

	public void getProductListByPage() throws Exception {
		try {
			
			PaginationParam paginationParam = this
			.getPaginationParam();
			this.jsonOut(productService.getProductDataListByPage(paginationParam));
		} catch (RollbackableBizException e) {
			e.printStackTrace();
		}

	}

	public void saveProductData() throws Exception {
		String jsonStr = this.getRequest().getParameter("jsonStr");
		JSONArray jsonArray = new JSONArray(jsonStr);
		List<ProductDataVo> list = new ArrayList<ProductDataVo>();
		if (jsonArray != null && jsonArray.length() > 0) {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				if ("1".equals(jsonObject.getString("isCheckNoPass"))
						|| "1".equals(jsonObject.getString("unReturnData"))) {
					continue;
				}
				ProductDataVo productDataVo = new ProductDataVo();
				productDataVo.setProductId(jsonObject.getInt("productId"));
				productDataVo.setAddressCode(jsonObject
						.getString("addressCode"));
				productDataVo.setBarCode(jsonObject.getString("barCode"));
				productDataVo.setProductDate(jsonObject
						.getString("productDate"));
				productDataVo.setProductBatch(jsonObject
						.getString("productBatch"));
				productDataVo.setTemperatureStd(Double.parseDouble(jsonObject
						.getString("temperatureStd")));
				productDataVo.setTemperatureData(Double.parseDouble(jsonObject
						.getString("temperatureData")));
				productDataVo
						.setTemperatureOffset(Double.parseDouble(jsonObject
								.getString("temperatureOffset")));
				productDataVo.setHumidityStd(Double.parseDouble(jsonObject
						.getString("humidityStd")));
				productDataVo.setHumidityData(Double.parseDouble(jsonObject
						.getString("humidityData")));
				productDataVo.setHumidityOffset(Double.parseDouble(jsonObject
						.getString("humidityOffset")));
				productDataVo.setIsOverTemAllowOffset(jsonObject
						.getString("isOverTemAllowOffset"));
				productDataVo.setIsOverHumAllowOffset(jsonObject
						.getString("isOverHumAllowOffset"));
				productDataVo.setIsDelete("0");
				list.add(productDataVo);
			}
			if (list != null && !list.isEmpty()) {
				productService.addProductDataBatch(list);
			}
		}

	}

	public String outPutProductData2Excel() throws Exception {

		PaginationParam paginationParam = this.getPaginationParam();
		paginationParam.setPageSize(60000);
		Pagination<ProductDataVo> prpductPagination = productService
				.getProductDataListByPage(paginationParam);
		if (prpductPagination != null) {
			HSSFWorkbook wb = this.outPutExcel(prpductPagination.getDataList());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				wb.write(baos);
				byte[] ba = baos.toByteArray();
				baos.close();
				excelFile = new ByteArrayInputStream(ba);
			} catch (IOException e) {
			}

		} else {
			String str = "0";
			this.jsonOut(str);
		}

		return "excel";
	}

	public HSSFWorkbook outPutExcel(List<ProductDataVo> list)
			throws RollbackableBizException {

		SysParamVo sysParamVo = sysParamDao.findSysParamById("1");
		String fileName_prefix = "产品数据";
		List<ExcelColumnBean> titles = packageTitles(list);

		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建sheet页
		HSSFSheet sheet = wb.createSheet();

		HSSFCellStyle defaultstyle = CellStyle.getDefaultStyle(wb);
		HSSFCellStyle titlestyle = CellStyle.getTitleStyle(wb);
		HSSFCellStyle offsetstyle = CellStyle.getOffsetStyle(wb);

		Row row = null;
		Cell cell = null;
		HSSFCellStyle cellstyle = null;

		row = sheet.createRow(0);// 创建标题行

		for (int i = 0; i < titles.size(); i++) {
			ExcelColumnBean bean = titles.get(i);
			cell = row.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(bean.getTitle()));
			cell.setCellStyle(titlestyle);
			sheet.setColumnWidth(i, bean.getWidth());
		}
		// 内容
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				ProductDataVo vo = list.get(i);
				Map<String, Object> datas = JsonUtil.getMap4Json(JsonUtil
						.getJsonString4JavaPOJO(vo));
				row = sheet.createRow(i + 1);// 创建内容行
				for (int j = 0; j < titles.size(); j++) {
					ExcelColumnBean columnBean = titles.get(j);
					String columnName = columnBean.getName();
					String columnValue = String
							.valueOf(datas.get(columnName) == null ? "" : datas
									.get(columnName));
					cell = row.createCell(j);
					cell.setCellType(columnBean.getCellType());
					if (columnBean.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						cell.setCellValue(Double.parseDouble(StringUtils
								.isBlank(columnValue) ? "0" : columnValue));
					} else {
						cell.setCellValue(new HSSFRichTextString(columnValue));
					}

					cellstyle = defaultstyle;
					
					 if (offsetBeans.contains(columnBean)) {
						 if("temperatureOffset".equals(columnName) && sysParamVo.getTemAllowOffset() < Math.abs(Long.valueOf(columnValue))){
							 cellstyle = offsetstyle;
						 }
						 if("humidityOffset".equals(columnName) &&sysParamVo.getTemAllowOffset() < Math.abs(Long.valueOf(columnValue))){
							 cellstyle = offsetstyle;
						 }
	                  }
					
					cell.setCellStyle(cellstyle);
				}

			}
			// 设置默认行高
			sheet.setDefaultRowHeight((short) 400);
		}

		FileBean dto = new FileBean();
		String contenttype = "application/ms-excel";
		String fileName_suffix = ".xls";
		try {
			fileName = new String((fileName_prefix + fileName_suffix)
					.getBytes("gbk"), "8859_1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		dto.setName(fileName_prefix + fileName_suffix);
		dto.setContenttype(contenttype);

		return wb;

	}

	
	
	private static List<ExcelColumnBean> packageTitles(List<ProductDataVo> list) {
		List<ExcelColumnBean> titles = new ArrayList<ExcelColumnBean>();
		titles.add(addressCode);
		titles.add(barCode);
		titles.add(productDate);
		titles.add(productBatch);
		titles.add(temperatureStd);
		titles.add(temperatureData);
		titles.add(temperatureOffset);
		titles.add(humidityStd);
		titles.add(humidityData);
		titles.add(humidityOffset);
		return titles;
	}

	private static List<ProductDataBean> form2bean4Product(List list) {
		List<ProductDataBean> dataList = new ArrayList<ProductDataBean>();
		for (int i = 0; i < list.size(); i++) {
			ProductDataVo form = (ProductDataVo) list.get(i);
			ProductDataBean bean = new ProductDataBean();

			dataList.add(bean);
		}
		return dataList;
	}

	public ProductPo getProductPo() {
		return productPo;
	}

	public void setProductPo(ProductPo productPo) {
		this.productPo = productPo;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	public void setBarCodesService(IBarCodesService barCodesService) {
		this.barCodesService = barCodesService;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ISysParamDao getSysParamDao() {
		return sysParamDao;
	}

	public void setSysParamDao(ISysParamDao sysParamDao) {
		this.sysParamDao = sysParamDao;
	}


}
