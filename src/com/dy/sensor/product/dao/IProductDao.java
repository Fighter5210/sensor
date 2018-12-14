package com.dy.sensor.product.dao;

import java.util.List;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.support.Pagination;
import com.dy.sensor.common.support.PaginationParam;
import com.dy.sensor.product.model.po.ProductPo;
import com.dy.sensor.product.model.vo.ProductDataVo;
import com.dy.sensor.product.model.vo.ProductVo;
/**
 * 产品管理
 * @author Fighter
 *
 */
public interface IProductDao {
	/**
	 * 添加产品信息
	 * @param productPo
	 * @return
	 */
	public void saveProduct(ProductPo productPo) throws RollbackableBizException;
	/**
	 * 修改产品信息
	 * @param productPo
	 * @return
	 */
	public void updateProduct(ProductPo productPo) throws RollbackableBizException;
	
	/**
	 * 根据ID查询产品信息
	 * @param productId
	 * @return
	 */
	public ProductVo getProductById(String productId) throws RollbackableBizException;
	/**
	 * 批量添加产品数据信息
	 * @param list
	 * @return
	 */
	public void saveProductDataBatch(List<ProductDataVo> list) throws RollbackableBizException;
	/**
	 * 根据ID查询产品信息和条形码信息
	 * @param productId
	 * @return
	 */
	public List<ProductDataVo> getProductAndBarCodeByProductId(String productId) throws RollbackableBizException;
	/**
	 * 查询产品数据信息
	 * @param ProductDataVo
	 * @return
	 * @throws RollbackableBizException
	 */
	public Pagination<ProductDataVo> getProductDataListByPage(PaginationParam paginationParam)throws RollbackableBizException;
	/**
	 * 根据ID删除产品数据
	 * @param productId
	 * @return
	 */
	public void deleteProductDataById(String productDataId) throws RollbackableBizException;
	/**
	 * 根据ID删除产品数据
	 * @param productId
	 * @return
	 */
	public void updateProductData(ProductDataVo productDataVo) throws RollbackableBizException;
}
