package com.chengxuunion.vo;

import java.io.Serializable;

/**
 * 页面实体
 *
 * @author kutome
 * @date 2018年10月24日
 * @version V1.0
 */
@SuppressWarnings("serial")
public class Page extends BaseVO implements Serializable {
	
	/**
	 * 主键ID
	 */
	private String id;
	
	/**
	 * 页面名称
	 */
	private String pageName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	@Override
	public String toString() {
		return "Page [id=" + id + ", pageName=" + pageName + ", getCreateDate()=" + getCreateDate()
				+ ", getCreateUser()=" + getCreateUser() + ", getUpdateDate()=" + getUpdateDate() + ", getUpdateUser()="
				+ getUpdateUser() + ", getOrderNum()=" + getOrderNum() + ", getState()=" + getState() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
