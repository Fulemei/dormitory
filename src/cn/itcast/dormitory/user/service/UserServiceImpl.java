package cn.itcast.dormitory.user.service;

import java.util.List;

import cn.itcast.dormitory.user.dao.UserDao;
import cn.itcast.dormitory.user.dao.UserDaoImpl;
import cn.itcast.dormitory.user.entity.User;

public class UserServiceImpl implements UserService{
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
	public boolean update(String sid, String name, String password, String email, String dormitory, String tel_number,
			String classes) {
		return ud.update(sid, name, password, email, dormitory, tel_number, classes);
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

}
