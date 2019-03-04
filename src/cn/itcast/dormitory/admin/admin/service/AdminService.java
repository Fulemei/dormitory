package cn.itcast.dormitory.admin.admin.service;

import java.sql.SQLException;

import cn.itcast.dormitory.admin.admin.dao.AdminDao;
import cn.itcast.dormitory.admin.admin.entity.Admin;

/**
 * 登录功能
 * @author Administrator
 *
 */
public class AdminService {
        public boolean login(Admin admin) throws SQLException {
        	AdminDao ad = new  AdminDao();
        	return ad.login(admin.getAdminid(), admin.getPassword());
        }
}
