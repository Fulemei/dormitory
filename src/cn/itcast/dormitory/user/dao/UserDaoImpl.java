package cn.itcast.dormitory.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.dormitory.jdbc.JdbcUtil;
import cn.itcast.dormitory.user.entity.User;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean login(String sid, String password) {
		boolean flag = false;
		try {
			JdbcUtil.getConnection();
			ResultSet rs = JdbcUtil.selectSql("select * from t_student where sid='"+sid+"' and password='"+password+"'");
			while(rs.next()) {
				if(rs.getString(sid).equals(sid)&&rs.getString(password).equals(password)) {
					flag = true;
				}
			}
			JdbcUtil.freeAll();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean register(User user) {
		boolean flag = false;
			JdbcUtil.getConnection();
			int i = JdbcUtil.addUpdDel("insert into t_student values('"+user.getSid()+"','"+user.getName()+"','"+user.getPassword()+"','"+user.getEmail()+"','"+user.getDormitory()+"','"+user.getTel_number()+"','"+user.getClasses()+"')");
		    if(i>0) {
		    	flag = true;
		    }
		    JdbcUtil.freeAll();
		return flag;
	}

	@Override
	public List<User> getUserAll() {
		List<User> list = new ArrayList<User>();
		try {
			JdbcUtil.getConnection();
			ResultSet rs = JdbcUtil.selectSql("select * from t_student");
			while(rs.next()) {
				User user = new User();
				user.setSid(rs.getString("SID"));
				user.setName(rs.getString("NAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setEmail(rs.getString("EMAIL"));
				user.setDormitory(rs.getString("DORMITORY"));
				user.setTel_number(rs.getString("TEL_NUMBER"));
				user.setClasses(rs.getString("CLASS"));
				list.add(user);
			}
			JdbcUtil.freeAll();
			return list;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(String sid) {
		boolean flag = false;
		JdbcUtil.getConnection();
		int i = JdbcUtil.addUpdDel("delete from t_student where sid ='"+sid+"'");
		if(i>0) {
			flag = true;
		}
		JdbcUtil.freeAll();
		return flag;
	}

	@Override
	public boolean update(String sid, String name, String password, String email, String dormitory, String tel_number,
			String classes) {
		boolean flag = false;
		JdbcUtil.getConnection();
		String sql = "update t_student set name ='"+name
				     +"' , password ='"+password
				     +"' , emali ='"+email
				     +"' , dormitory ='"+dormitory
				     +"' , tel_number ='"+tel_number
				     +"' , classes ='"+classes+"' where sid = '"+sid+"'";
		int i = JdbcUtil.addUpdDel(sql);
		if(i>0) {
			flag = true;
		}
		JdbcUtil.freeAll();
		return flag;
	}

	@Override
	public boolean ajaxValidateSid(String sid) {
		boolean flag = false;
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil.selectSql("select * from t_student where sid='"+sid+"'");
		if(rs != null) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean ajaxValidateEmail(String email) {
		boolean flag = false;
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil.selectSql("select * from t_student where email='"+email+"'");
		if(rs != null) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean ajaxValidateNumber(String tel_number) {
		boolean flag = false;
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil.selectSql("select * from t_student where tel_number='"+tel_number+"'");
		if(rs != null) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean updatePassword(String sid, String newPass, String password) {
		boolean flag = false;
		int i = JdbcUtil.addUpdDel("update t_student set password='"+newPass+"where sid ='"+sid+"'");
		if(i>0) {
			flag = true;
		}
		return flag;
	}

}
