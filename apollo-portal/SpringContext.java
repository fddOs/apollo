package com.ctrip.framework.apollo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:SpringContext
 * @author:方典典
 * @time:2018年3月12日 下午2:06:32
 */
@Configuration
public class SpringContext implements ApplicationContextAware{
	
	private static ApplicationContext  application;

	private static final Logger log = LoggerFactory.getLogger(SpringContext.class);

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		application = applicationContext;
		log.info("setApplicationContext");
	}

	public static ApplicationContext getApplicationContext() {
		return application;
	}
	
}
