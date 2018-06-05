package com.ctrip.framework.apollo.portal.entity.bo.emai;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Description:邮件参数类
 * @author:方典典
 * @time:2017年12月25日 上午10:38:34
 */
public class EmailKeyValue {
	@JSONField(name = "Key")
	private String key;
	@JSONField(name = "Value")
	private String value;

	public EmailKeyValue(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public EmailKeyValue() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
