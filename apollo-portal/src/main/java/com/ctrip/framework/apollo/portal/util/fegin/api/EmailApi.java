package com.ctrip.framework.apollo.portal.util.fegin.api;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ctrip.framework.apollo.portal.util.fegin.BaseApi;

import feign.RequestLine;

/**
 * @Description:邮件接口API
 * @author:方典典
 * @time:2017年12月25日 上午10:59:46
 */
public interface EmailApi extends BaseApi {

	/**
	 * @Description:发送邮件接口
	 * @param jsonArray
	 * @return Object
	 * @exception:
	 * @author: 方典典
	 * @time:2017年12月25日 上午10:59:35
	 */
	@RequestLine("POST /Email")
	List<Object> sendEmail(JSONArray jsonArray);

}
