package com.dy.sensor.product.service;


import java.util.List;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.support.Pagination;
import com.dy.sensor.common.support.PaginationParam;
import com.dy.sensor.common.support.xls.FileBean;
import com.dy.sensor.product.model.po.ProductPo;
import com.dy.sensor.product.model.vo.ProductDataVo;
import com.dy.sensor.product.model.vo.ProductVo;

public interface IProductService {
	public String addProduct(ProductPo productPo) throws RollbackableBizException;
	public String updateStd(ProductPo productPo) throws RollbackableBizException;
	public ProductVo getProductById(String productId) throws RollbackableBizException;
	public List<ProductDataVo> sendCommand(String productId) throws RollbackableBizException;
	public void addProductDataBatch(List<ProductDataVo> list) throws RollbackableBizException;
	public Pagination<ProductDataVo> getProductDataListByPage(PaginationParam paginationParam) throws RollbackableBizException;
	public void deleteProductDataById(String productDataId) throws RollbackableBizException;
	public void updateProductData(ProductDataVo productDataVo)  throws RollbackableBizException;
}
