package com.ctrip.framework.apollo.portal.util.fegin;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.fasterxml.jackson.annotation.JsonInclude;

import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;

/**
 * fegin网络请求工具类
 * 
 * @author lixiao
 *
 */
public class FeignUtils {

	// 保存生成的api
	private static Map<String, BaseApi> apiMap = new HashMap<>();

	private static final Logger log = LoggerFactory.getLogger(FeignUtils.class);

	@SuppressWarnings("unchecked")
	public static <T extends BaseApi> T getHttpApi(Class<T> api, String url) {
		if (StringUtils.isEmpty(url)) {
			throw new RuntimeException("系统缺失调用第三方服务的路径配置！");
		}
		T base = (T) apiMap.get(api.getSimpleName());
		if (base == null) {
			base = create(api, url);
			apiMap.put(api.getSimpleName(), base);
		}
		return base;
	}

	/**
	 * 创建请求的client
	 * 
	 * @param api
	 *            api的类，必须继承BaseApi
	 * @param url
	 *            请求的baseURl-统一
	 * @return
	 */
	private static <T extends BaseApi> T create(Class<T> api, String url) {

		return Feign.builder().client(new OkHttpClient(EhiOkHttpClient.getInstance().getOkHttp()))
				.encoder(new JacksonEncoder(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
						.configure(SerializationFeature.INDENT_OUTPUT, false)))
				.decoder(new JacksonDecoder())
				// 请求拦截器
				.requestInterceptor(new RequestInterceptor() {
					@Override
					public void apply(RequestTemplate template) {
						log.info(template.bodyTemplate());
						log.info(template.toString());
						log.info(template.url());
					}
				}).target(api, url);
	}

	private FeignUtils() {
	}
}
