package com.ganji.dpd.sparrow.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

public class DBUtil {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection(String url, String user, String pwd) throws SQLException {
		return DriverManager.getConnection(url, user, pwd);
	}

	public static String generateURL(String host, String port, String db){
		StringBuilder buff = new StringBuilder();
		buff.append("jdbc:mysql://");
		buff.append(host);
		buff.append(":");
		buff.append(port+"/");
		if(db!=null && !"".equals(db.trim())){
			buff.append(db);
		}
		buff.append("?useUnicode=true&characterEncoding=UTF-8");
		return buff.toString();
	}
	
	public static boolean hasBlobField(ResultSetMetaData rsmd) throws SQLException{
		boolean result = false;
		int colCount = rsmd.getColumnCount();
		for(int i = 1; i<=colCount; i++){
			if(hasBlob(rsmd.getColumnType(i))){
				result = true;
				break;
			}
		}
		return result;
	}
	
	private static boolean hasBlob(int type){
		boolean result = false;
		switch (type) {
		case Types.BINARY:
			result = true;
			break;
			
		case Types.VARBINARY:
			result = true;
			break;
			
		case Types.LONGVARBINARY:
			result = true;
			break;
			
		case Types.BLOB:
			result = true;
			break;
			
		default:
			break;
		}
		return result;
	}

}
