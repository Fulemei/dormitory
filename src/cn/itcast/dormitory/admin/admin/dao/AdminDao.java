package cn.itcast.dormitory.admin.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.itcast.dormitory.jdbc.JdbcUtil;

public class AdminDao {
	public boolean login(String adminid, String password) throws SQLException {
		boolean flag = false;
			JdbcUtil.getConnection();
			ResultSet rs = JdbcUtil.selectSql("select * from t_admin where adminid='"+adminid+"'and password='"+password+"'");
			while(rs.next()) {
				
					flag = true;
				
			}
			JdbcUtil.freeAll();
			return flag;
			}
}
