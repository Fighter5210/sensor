package com.dy.sensor.product.dao.impl;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.dy.sensor.common.dao.impl.CommonDAOImpl;
import com.dy.sensor.common.exception.BizException;
import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.support.Pagination;
import com.dy.sensor.common.support.PaginationParam;
import com.dy.sensor.common.support.xls.ExcelColumnBean;
import com.dy.sensor.common.support.xls.ExportDto;
import com.dy.sensor.common.support.xls.FileBean;
import com.dy.sensor.foundation.util.JsonUtil;
import com.dy.sensor.product.dao.IProductDao;
import com.dy.sensor.product.model.po.ProductPo;
import com.dy.sensor.product.model.vo.ProductDataVo;
import com.dy.sensor.product.model.vo.ProductVo;
/**
 * 产品
 * @author Fighter
 *
 */
public class ProductDaoImpl extends CommonDAOImpl implements IProductDao {

	@Override
	public void saveProduct(ProductPo productPo) throws RollbackableBizException {
	   this.save("insertProduct", productPo);
	}
	@Override
	public void updateProduct(ProductPo productPo) throws RollbackableBizException {
	   this.update("updateProduct", productPo);
	}
	@Override
	public ProductVo getProductById(String productId) throws RollbackableBizException {
			Map<String,String> map = new HashMap<String,String>();
			map.put("productId", productId);
			return this.findObjectByMap("getProductById", map);
		}
	
	@Override
	public void saveProductDataBatch(List<ProductDataVo> list)
			throws RollbackableBizException {
		 this.batchInsert("insertProductDataList", list);
	}
	@Override
	public List<ProductDataVo> getProductAndBarCodeByProductId(String productId)
			throws RollbackableBizException {
		Map<String,String> map = new HashMap<String,String>();
		map.put("productId", productId);
		return this.findListByParam("getProductAndBarCodeByProductId", map);
	}
	@Override
	public Pagination<ProductDataVo> getProductDataListByPage(PaginationParam paginationParam)
			throws RollbackableBizException {
		return this.pageQuery("selectProductListTotal","selectProductListPage", paginationParam);
	}
	@Override
	public void deleteProductDataById(String productDataId)
			throws RollbackableBizException {
		this.delete("deleteProductDataById", productDataId);
	}
	@Override
	public void updateProductData(ProductDataVo productDataVo)
			throws RollbackableBizException {
		this.update("updateProductData", productDataVo);
	}
	
}
