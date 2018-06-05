package com.ctrip.framework.apollo.portal.entity.bo.emai;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:邮件Model
 * @author:方典典
 * @time:2017年12月25日 上午10:39:12
 */
public class EmailModel {
	@JSONField(name = "AttachmentPath")
	private String attachmentPath;
	@JSONField(name = "ClientKey")
	private String clientKey;
	@JSONField(name = "EdmId")
	private int edmId;
	@JSONField(name = "EmailBcc")
	private String emailBcc;
	@JSONField(name = "EmailCc")
	private String emailCc;
	@JSONField(name = "EmailTo")
	private String emailTo;
	@JSONField(name = "OprNo")
	private String oprNo;
	@JSONField(name = "confirmation_no")
	private String confirmation_no;
	@JSONField(name = "KeyValues")
	private List<EmailKeyValue> keyValues;

	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public int getEdmId() {
		return edmId;
	}

	public void setEdmId(int edmId) {
		this.edmId = edmId;
	}

	public String getEmailBcc() {
		return emailBcc;
	}

	public void setEmailBcc(String emailBcc) {
		this.emailBcc = emailBcc;
	}

	public String getEmailCc() {
		return emailCc;
	}

	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getOprNo() {
		return oprNo;
	}

	public void setOprNo(String oprNo) {
		this.oprNo = oprNo;
	}

	public String getConfirmation_no() {
		return confirmation_no;
	}

	public void setConfirmation_no(String confirmation_no) {
		this.confirmation_no = confirmation_no;
	}

	public List<EmailKeyValue> getKeyValues() {
		return this.keyValues == null ? null : new ArrayList<>(this.keyValues);
	}

	public void setKeyValues(List<EmailKeyValue> keyValues) {
		this.keyValues = (keyValues == null ? null : new ArrayList<>(keyValues));
	}
}
