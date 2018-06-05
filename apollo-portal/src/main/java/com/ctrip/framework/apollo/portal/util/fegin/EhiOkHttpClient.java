package com.ctrip.framework.apollo.portal.util.fegin;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/*
 * okhtt工具类
 */
public class EhiOkHttpClient {

	private static EhiOkHttpClient eHiClient;

	private static final int TIME_OUT = 35;

	private EhiOkHttpClient() {
	}

	public static EhiOkHttpClient getInstance() {
		if (eHiClient == null) {
			eHiClient = new EhiOkHttpClient();
		}
		return eHiClient;
	}

	public OkHttpClient getOkHttp() {

		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.addNetworkInterceptor(new Interceptor() {

			public Response intercept(Chain chain) throws IOException {
				Request builderRequest = handleRequest(chain.request());
				return chain.proceed(builderRequest);
			}

		}).connectTimeout(TIME_OUT, TimeUnit.SECONDS).readTimeout(TIME_OUT, TimeUnit.SECONDS)
				.retryOnConnectionFailure(false);

		return builder.build();
	}

	/**
	 * 处理发出的请求
	 * 
	 * @param request
	 * @return
	 */
	private Request handleRequest(Request request) {
		RequestBody requestBody = request.body();
		RequestBody requestBodyNew;
		if (requestBody != null && requestBody.contentType() != null
				&& !requestBody.contentType().type().equals(MediaType.parse("multipart/form-data").type())) {
			try {
				Buffer bufferedSink = new Buffer();
				requestBody.writeTo(bufferedSink);
				requestBodyNew = RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), "");
			} catch (Exception e) {
				requestBodyNew = requestBody;
			}
		} else {
			requestBodyNew = requestBody;
		}
		/**
		 * 处理请求的url
		 */
		return request.newBuilder().addHeader("Accept-Encoding", "gzip")
				.addHeader("Content-Type", "application/json").url(request.url())
				.method(request.method(), requestBodyNew).build();
	}

}
