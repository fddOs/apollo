package com.ctrip.framework.apollo.common.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.ctrip.framework.apollo.common.SpringContext;
import com.ctrip.framework.apollo.common.utils.SqlHelper;
import com.ehai.dbconfig.controller.DBConfig;
import com.ehai.dbconfig.dbinterface.IDBConfigInitListener;
import com.ehai.dbconfig.dbinterface.IDBConfigUpdateListener;
import com.ehai.dbconfig.model.DBInfo;
import com.ehai.dbconfig.model.DBResult;

/**
 * druid 数据连接池配置
 * 
 * @author 18834
 *
 */
@Component
public class DruidConfigution {
	private static final Logger log = LoggerFactory.getLogger(DruidConfigution.class);

	@Value("${url}")
	private String url;
	@Value("${key}")
	private String key;
	@Value("${name}")
	private String name;
	
	@Bean
	@Primary
	//@ConfigurationProperties("spring.datasource.druid")
	public DataSource getDataSource() {

		log.info("getDataSource");

		DBConfig.init(new IDBConfigInitListener() {

			@Override
			public boolean isConnectSuccess(String arg0, DBInfo arg1) {
				return SqlHelper.connectTest(arg1);
			}
		});
		DBResult dbResult = DBConfig.getConfig(url, key, name,
				new IDBConfigUpdateListener() {

					@Override
					public void configUpdate(DBInfo arg0) {
						DruidDataSource dataSource = (DruidDataSource) SpringContext.getApplicationContext()
								.getBean(DataSource.class);
						if (dataSource != null) {
							try {
								dataSource.restart();
								initDataBase(dataSource, arg0);
								dataSource.init();
								log.info("重新连接池初始化成功");
							} catch (SQLException e) {
								log.info("重新连接池初始化失败");
							}
						}

					}
				});
		DruidDataSource datasource = new DruidDataSource();
		if (dbResult != null && dbResult.isSucess()) {
			DBInfo dbInfo = dbResult.getDbInfo();
			initDataBase(datasource, dbInfo);
			log.info("获取数据库连接信息成功");
		} else {
			log.info("获取数据库连接信息失败 {}", dbResult);
		}
		return datasource;
	}

	/**
	 * 初始化数据库连接池
	 * 
	 * @param dataDruidSource
	 * @param dbInfo
	 *            1433;DatabaseName=
	 */
	private void initDataBase(DruidDataSource dataDruidSource, DBInfo dbInfo) {
		if (dbInfo != null) {
			dataDruidSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataDruidSource
					.setUrl("jdbc:mysql://" + dbInfo.getServerName() + ":3306/" + dbInfo.getDbName() + "?useSSL=false");
			dataDruidSource.setUsername(dbInfo.getLoginName());
			dataDruidSource.setPassword(dbInfo.getLoginPwd());
		}
	}

}
