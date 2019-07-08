package cn.itcast.dormitory.admin.admin.service;

import java.sql.SQLException;

import cn.itcast.dormitory.admin.admin.dao.AdminDao;
import cn.itcast.dormitory.admin.admin.entity.Admin;
import cn.itcast.dormitory.pager.PageBean;

/**
 * 登录功能
 * 
 * @author Administrator
 *
 */
public class AdminService {
	AdminDao ad = new AdminDao();

	public boolean login(Admin admin) throws SQLException {
		return ad.login(admin.getAdminid(), admin.getPassword());
	}

	public PageBean<Admin> findByAdminname(String adminname, int pc) throws SQLException {
		return ad.findByAdminname(adminname, pc);
	}

	public boolean add(Admin admin) {
		return ad.add(admin);
	}

	public boolean update(Admin admin) {
		return ad.update(admin);
	}

	public PageBean<Admin> findAll(int pc) throws SQLException {
		return ad.findAll(pc);
	}

	public PageBean<Admin> findByAdminid(String adminid, int pc) throws SQLException {
		return ad.findByAdminid(adminid, pc);
	}
}
