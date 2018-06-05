/**
 * 
 */
package com.ctrip.framework.apollo.portal.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ctrip.framework.apollo.portal.entity.bo.emai.EmailKeyValue;
import com.ctrip.framework.apollo.portal.entity.bo.emai.EmailModel;

/**
 * @Description:邮件发送工具
 * @author:方典典
 * @time:2018年4月21日 下午4:32:07
 */
public class EmailUtils {

	public static JSONArray generateEmail(int edmId, String emailTo, List<EmailKeyValue> params) {
		EmailModel emailModel = new EmailModel();
		emailModel.setAttachmentPath("");
		emailModel.setClientKey("E9FFBFD19B43");
		emailModel.setEdmId(edmId);
		emailModel.setEmailTo(emailTo);
		emailModel.setEmailBcc("");
		emailModel.setEmailCc("");
		emailModel.setOprNo("27894");
		emailModel.setConfirmation_no("");
		emailModel.setKeyValues(params);
		JSONArray emailList = new JSONArray();
		emailList.add(JSON.toJSON(emailModel));
		return emailList;
	}

	private EmailUtils() {
	}
}
