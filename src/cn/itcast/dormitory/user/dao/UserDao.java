package cn.itcast.dormitory.user.dao;

import java.sql.Connection;
import java.util.List;

import cn.itcast.dormitory.jdbc.JdbcUtil;
import cn.itcast.dormitory.user.entity.User;

public interface UserDao {
	public boolean login(String sid,String password);//登录
	public boolean register(User user);//注册
	public List<User> getUserAll();//返回用户信息集合
	public boolean delete(String sid) ;//根据id删除用户
	public boolean update(String sid,String name,String password,String email,String dormitory,String tel_number,String classes);
	public boolean ajaxValidateSid(String sid);//校验用户名是否注册
	public boolean ajaxValidateEmail(String email);//校验邮箱是否注册
	public boolean ajaxValidateNumber(String tel_number);//校验手机号是否已经被使用
}

