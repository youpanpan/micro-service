package com.chengxuunion.vo;

import java.io.Serializable;

/**
 * 页面组件实体
 *
 * @author kutome
 * @date 2018年10月24日
 * @version V1.0
 */
@SuppressWarnings("serial")
public class PageComponent extends BaseVO implements Serializable {

	/**
	 * 主键ID
	 */
	private String id;
	
	/**
	 * 页面ID
	 */
	private String pageId;
	
	/**
	 * 组件名称
	 */
	private String componentName;
	
	/**
	 * 组件标识
	 */
	private String componentId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	@Override
	public String toString() {
		return "PageComponent [id=" + id + ", pageId=" + pageId + ", componentName=" + componentName + ", componentId="
				+ componentId + ", getCreateDate()=" + getCreateDate() + ", getCreateUser()=" + getCreateUser()
				+ ", getUpdateDate()=" + getUpdateDate() + ", getUpdateUser()=" + getUpdateUser() + ", getOrderNum()="
				+ getOrderNum() + ", getState()=" + getState() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
