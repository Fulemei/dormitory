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
				
					flag = true;
				
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
	public boolean update(User user) {
		boolean flag = false;
		JdbcUtil.getConnection();
		String sql = "update t_student set name ='"+user.getName()
				     +"' , password ='"+user.getPassword()
				     +"' , emali ='"+user.getEmail()
				     +"' , dormitory ='"+user.getDormitory()
				     +"' , tel_number ='"+user.getTel_number()
				     +"' , classes ='"+user.getClasses()+"' where sid = '"+user.getSid()+"'";
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
		JdbcUtil.freeAll();
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
		JdbcUtil.freeAll();
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
		JdbcUtil.freeAll();
		return flag;
	}

	@Override
	public boolean updatePassword(String sid, String newPass, String password) {
		boolean flag = false;
		JdbcUtil.getConnection();
		int i = JdbcUtil.addUpdDel("update t_student set password='"+newPass+"where sid ='"+sid+"'");
		if(i>0) {
			flag = true;
		}
		JdbcUtil.freeAll();
		return flag;
	}
	
	public List<User> findBySid(String sid) throws SQLException{
		JdbcUtil.getConnection();
		List<User> list = new ArrayList<User>();
		ResultSet rs = JdbcUtil.selectSql("select * from t_student where sid='"+sid+"'");
		while(rs.next()) {
			
			User user = new User();
			user.setSid(sid);
			user.setName(rs.getString("name"));
			user.setDormitory(rs.getString("dormitory"));
			user.setEmail(rs.getString("email"));
			user.setTel_number(rs.getString("tel_number"));
			user.setPassword(rs.getString("password"));
			list.add(user);
		}
		JdbcUtil.freeAll();
		return list;
	} 

}
