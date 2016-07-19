package com.ganji.dpd.sparrow.serviceImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ganji.dpd.sparrow.service.ValidationService;
import com.ganji.dpd.sparrow.util.DBUtil;

@Service("validationService")
public class ValidationServiceImpl implements ValidationService {

	@Override
	public List<String> validate(String host, String port, String user, String pwd, String db, String tb,
			boolean disbaleBlob) throws Exception {
		List<String> result = new LinkedList<String>();
		String url = DBUtil.generateURL(host, port, db);
		Connection conn = null;
		try {
			conn = DBUtil.getConnection(url, user, pwd);
			try {
				if (tb != null) {
					String sql = "select * from " + tb + " limit 1";
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					ResultSetMetaData rsmd = rs.getMetaData();
					if (disbaleBlob && DBUtil.hasBlobField(rsmd)) {
						throw new Exception("包含二进制的BINARY、BLOB等字段");
					}
					int colCount = rsmd.getColumnCount();
					for (int i = 1; i <= colCount; i++) {
						result.add(rsmd.getColumnLabel(i));
					}
				}
			} catch (Exception e) {
				throw e;
			} finally {
				conn.close();
			}
		} catch (Exception e) {
			throw new Exception("验证链接失败:" + e.getMessage());
		}
		return result;
	}

}
