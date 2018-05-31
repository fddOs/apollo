package com.ctrip.framework.apollo.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ehai.dbconfig.model.DBInfo;

public class SqlHelper {
	private static final Logger log = LoggerFactory.getLogger(SqlHelper.class);

	/**
	 * Sqlserver 连接测试类
	 * 
	 * @param dbInfo
	 *            信息对象
	 * @return 是否正常连接
	 */
	public static boolean connectTest(DBInfo dbInfo) {
		if (dbInfo == null) {
			log.debug("DBInfo =null");
			return false;
		}
		// 加载JDBC驱动
		String driverName = "com.mysql.jdbc.Driver";
		// 连接服务器和数据库sample
		String dbURL = "jdbc:mysql://" + dbInfo.getServerName() + ":3306/" + dbInfo.getDbName() + "?useSSL=false";// 连接服务器和数据库
		String userName = dbInfo.getLoginName(); // 默认用户名
		String userPwd = dbInfo.getLoginPwd();// 密码
		Connection dbConn = null;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);

			log.info("mysql - ConnectionSuccess,{}", dbInfo.toString());
			return true;
		} catch (Exception e) {
			log.error("mysql - Connectionfail" + dbInfo.toString(), e);
		} finally {
			if (dbConn != null) {
				try {
					dbConn.close();
				} catch (SQLException e) {
					log.debug(e.getMessage());
				}
			}
		}
		return false;
	}
}
