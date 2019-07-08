package cn.itcast.dormitory.user.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dormitory.pager.PageBean;
import cn.itcast.dormitory.user.entity.User;

public interface UserDao {
	public boolean login(String sid, String password);// 登录

	public boolean register(User user);// 注册

	public List<User> getUserAll();// 返回用户信息集合

	public boolean delete(String sid);// 根据id删除用户

	public boolean update(User user);

	public boolean ajaxValidateSid(String sid);// 校验用户名是否注册

	public boolean ajaxValidateEmail(String email);// 校验邮箱是否注册

	public boolean ajaxValidateNumber(String tel_number);// 校验手机号是否已经被使用

	public boolean updatePassword(String sid, String newPass, String password);

	public List<User> findBySid(String sid) throws SQLException;

	public PageBean<User> findBySid(String sid, int pc) throws SQLException;

	public PageBean<User> findAll(int pc) throws SQLException;
}
