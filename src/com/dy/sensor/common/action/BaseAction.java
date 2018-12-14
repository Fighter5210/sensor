package com.dy.sensor.common.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.struts2.ServletActionContext;

import com.dy.sensor.common.support.GetParamMap;
import com.dy.sensor.common.support.PaginationParam;
import com.dy.sensor.sys.model.po.SysUserPo;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action����
 * 
 * @author Spring.Cao
 * @version v1.0 2013-03-22
 */
public class BaseAction<T> extends ActionSupport {

	private static final long serialVersionUID = -1L;

	private List<T> dataList = Collections.emptyList();
	private Integer rows = 0;
	private Integer page = 0;
	private Integer total = 0;
	private Integer record = 0;
	private String sord;
	private String sidx;
	private String search;
	private String fileName;// 生成的文件名称;
	private String filePath;// 文件存放的目录位置;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}
	protected PaginationParam getPaginationParam() {
		PaginationParam paginationParam = new PaginationParam();
		paginationParam.setOrderField(sidx);
		paginationParam.setOrderType(sord);
		paginationParam.setPageSize(rows);
		paginationParam.setParams(GetParamMap.getParamMap(this.getRequest().getParameterMap()));
		paginationParam.setCurrentPage(Integer.valueOf((String) paginationParam.getParams().get("page")));
		SysUserPo shiroUser = (SysUserPo) SecurityUtils.getSubject().getPrincipal();

		// 判断是否为普通用户，做数据权限控制。
		if (shiroUser != null && false == shiroUser.getIsManager()) {
			paginationParam.getParams().put("loginName", shiroUser.getLoginName());
		}

		if (shiroUser != null) {
			String superAdmin = this.getRequest().getParameter("superAdmin");
			if (superAdmin == null || !"true".equals(superAdmin)) {
				paginationParam.getParams().put("userId", shiroUser.getUserId());
			}
		}

		return paginationParam;
	}

	protected void stringOut(String str) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print("{\"result\":\"" + str + "\"}");
	}

	protected void jsonOut(Object object) throws Exception {
		String jsonString = JSONObject.fromObject(object).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(jsonString);
		System.out.println(jsonString);
	}

	protected void ObjectOut(Object object) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(object);
	}

	protected void errorOut(Exception e) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=utf-8");
		response.setStatus(500);
		response.getWriter().print(e.getMessage());
	}

	protected void arrayOut(Object object) throws Exception {
		String jsonString = JSONArray.fromObject(object).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=utf-8");
		System.out.println(jsonString);
		response.getWriter().print(jsonString);
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getRecord() {
		return record;
	}

	public void setRecord(Integer record) {
		this.record = record;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * 下载输出流文件;
	 * 
	 * @param fileName
	 *            文件名字+文件类型，例：temp.xls
	 * @return
	 * @throws Exception
	 */
	public InputStream getDownloadFile() throws Exception {

		String excelSavePath = this.filePath + fileName; // 这是相对路径;

		return ServletActionContext.getServletContext().getResourceAsStream(excelSavePath);

	}
}
