package com.dy.sensor.product.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.dy.sensor.common.exception.BizException;
import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.support.ComRead;
import com.dy.sensor.common.support.Pagination;
import com.dy.sensor.common.support.PaginationParam;
import com.dy.sensor.common.support.xls.CellStyle;
import com.dy.sensor.common.support.xls.ExcelColumnBean;
import com.dy.sensor.common.support.xls.ExportDto;
import com.dy.sensor.common.support.xls.FileBean;
import com.dy.sensor.foundation.util.JsonUtil;
import com.dy.sensor.product.dao.IProductDao;
import com.dy.sensor.product.model.ProductDataBean;
import com.dy.sensor.product.model.po.ProductPo;
import com.dy.sensor.product.model.vo.ProductDataVo;
import com.dy.sensor.product.model.vo.ProductVo;
import com.dy.sensor.product.service.IProductService;
import com.dy.sensor.sys.dao.ISysParamDao;
import com.dy.sensor.sys.model.vo.SysParamVo;

public class ProductServiceImpl implements IProductService {
	private static Log logger = LogFactory.getLog(ProductServiceImpl.class);
	private IProductDao productDao;
	private ISysParamDao sysParamDao;


	/**
	 * 保存产品信息
	 * @param productPo
	 * @return
	 * @throws RollbackableBizException
	 */
	public String addProduct(ProductPo productPo)
			throws RollbackableBizException {
		try {
			if (null == productPo.getId() || productPo.getId().intValue() == 0) {//新增
				productDao.saveProduct(productPo);
			} else {//更新
				//productDao.updateProduct(productPo);
			}
		} catch (Exception e) {
			logger.error("保存产品信息时发生异常:" + e.getMessage());
		}
		return productPo.getId().toString();
	}

	/**
	 * 修改温湿度标准值
	 * @param productPo
	 * @return
	 * @throws RollbackableBizException
	 */
	public String updateStd(ProductPo productPo)
			throws RollbackableBizException {
		try {

			if (null == productPo.getId() || productPo.getId().intValue() == 0) {//新增
				productDao.saveProduct(productPo);
			} else {//更新
				productDao.updateProduct(productPo);
			}
		} catch (Exception e) {
			logger.error("保存产品信息时发生异常:" + e.getMessage());
		}
		return productPo.getId().toString();
	}

	/**
	 * 根据ID查询产品信息
	 * @param productPo
	 * @return
	 * @throws RollbackableBizException
	 */
	public ProductVo getProductById(String productId)
			throws RollbackableBizException {
		return productDao.getProductById(productId);
	}

	/**
	 * 发送指令
	 */
	@Override
	public List<ProductDataVo> sendCommand(String productId)
			throws RollbackableBizException {

		List<ProductDataVo> list = productDao
				.getProductAndBarCodeByProductId(productId);
		//查询系统设置
		SysParamVo sysParamVo = sysParamDao.findSysParamById("1");
		ComRead comread = new ComRead();
		List<ProductDataVo> returnList = comread.execute(list, sysParamVo);
		return returnList;
	}

	@Override
	public Pagination<ProductDataVo> getProductDataListByPage(
			PaginationParam paginationParam) throws RollbackableBizException {
		return productDao.getProductDataListByPage(paginationParam);
	}

	

	@Override
	public void addProductDataBatch(List<ProductDataVo> list)
			throws RollbackableBizException {
		productDao.saveProductDataBatch(list);
	}

	public void setProductDao(IProductDao productDao) {
		this.productDao = productDao;
	}

	public void setSysParamDao(ISysParamDao sysParamDao) {
		this.sysParamDao = sysParamDao;
	}

	@Override
	public void deleteProductDataById(String productDataId)
			throws RollbackableBizException {
		this.productDao.deleteProductDataById(productDataId);
	}

	@Override
	public void updateProductData(ProductDataVo productDataVo)
			throws RollbackableBizException {
		this.productDao.updateProductData(productDataVo);
	}

}
