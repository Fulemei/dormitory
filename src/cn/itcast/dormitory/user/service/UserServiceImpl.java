package cn.itcast.dormitory.user.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dormitory.pager.PageBean;
import cn.itcast.dormitory.user.dao.UserDao;
import cn.itcast.dormitory.user.dao.UserDaoImpl;
import cn.itcast.dormitory.user.entity.User;

public class UserServiceImpl implements UserService {
	UserDao ud = new UserDaoImpl();

	@Override
	public boolean login(String sid, String password) {
		return ud.login(sid, password);
	}

	@Override
	public boolean register(User user) {
		return ud.register(user);
	}

	@Override
	public List<User> getUserAll() {
		return ud.getUserAll();
	}

	@Override
	public boolean delete(String sid) {
		return ud.delete(sid);
	}

	@Override
	public boolean update(User user) {
		return ud.update(user);
	}

	@Override
	public boolean ajaxValidateSid(String sid) {
		return ud.ajaxValidateSid(sid);
	}

	@Override
	public boolean ajaxValidateEmail(String email) {
		return ud.ajaxValidateEmail(email);
	}

	@Override
	public boolean ajaxValidateNumber(String tel_number) {
		return ud.ajaxValidateNumber(tel_number);
	}

	@Override
	public boolean updatePassword(String sid, String newPass, String password) {
		return ud.updatePassword(sid, newPass, password);

	}

	@Override
	public PageBean<User> findBySid(String sid, int pc) throws SQLException {
		return ud.findBySid(sid, pc);
	}

	@Override
	public PageBean<User> findAll(int pc) throws SQLException {
		return ud.findAll(pc);
	}

	public List<User> findBySid(String sid) throws SQLException {
		return ud.findBySid(sid);
	}
}
