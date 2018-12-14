package com.dy.sensor.common.model.vo;

import java.util.List;

/**
 * 树对象;
 * 
 * @author myh
 * 
 */
public class TreeVo {


	private String id;// 节点编号;
	private String pId;// 父节点编号;
	private String name;// 名字;
	private boolean open; // 打开状态;
	private boolean nocheck; // 是否选中的状态;
	private boolean isParent; // 是否为父节点;
	private List<TreeVo> children; // 子节点集合;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeVo> getChildren() {
		return children;
	}

	public void setChildren(List<TreeVo> children) {
		this.children = children;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

}
