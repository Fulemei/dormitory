package cn.itcast.dormitory.admin.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.dormitory.admin.admin.entity.Admin;
import cn.itcast.dormitory.jdbc.JdbcUtil;
import cn.itcast.dormitory.pager.PageBean;
import cn.itcast.dormitory.pager.PageConstants;

public class AdminDao {
	public boolean login(String adminid, String password) throws SQLException {
		boolean flag = false;
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil
				.selectSql("select * from t_admin where adminid='" + adminid + "'and password='" + password + "'");
		while (rs.next()) {

			flag = true;

		}
		JdbcUtil.freeAll();
		return flag;
	}

	public PageBean<Admin> findByAdminname(String adminname, int pc) throws SQLException {
		String wheresql = "where adminname like'%" + adminname + "%'";
		return findByCriteria(wheresql, pc);
	}

	private PageBean<Admin> findByCriteria(String wheresql, int pc) throws SQLException {
		JdbcUtil.getConnection();
		/*
		 * 1. 得到ps 2. 得到tr 3. 得到beanList 4. 创建PageBean，返回
		 */
		/*
		 * 1. 得到ps
		 */
		int ps = PageConstants.ORDER_PAGE_SIZE;// 每页记录数
		/*
		 * 2. 通过exprList来生成where子句
		 */

		/*
		 * 3. 总记录数
		 */
		int tr = 0;
		String sql = "select count(*) from t_admin " + wheresql;
		System.out.println("----1---" + sql);
		ResultSet rs1 = JdbcUtil.selectSql(sql);
		while (rs1.next()) {
			tr = rs1.getInt("COUNT(*)");
		}
		System.out.println(tr);
		/*
		 * 4. 得到beanList，即当前页记录
		 */
		int min = (pc - 1) * ps;
		int max = pc * ps;
		sql = "select * from (select t.*,rownum rn from (select * from t_admin " + wheresql
				+ "order by remark desc)t where rownum <= " + max + " ) where rn > " + min;
		System.out.println("----2---" + sql);
		ResultSet rs = JdbcUtil.selectSql(sql);
		List<Admin> beanList = new ArrayList<Admin>();
		while (rs.next()) {
			Admin admin = new Admin();
			admin.setAdminid(rs.getString("ADMINID"));
			admin.setAdminname(rs.getString("ADMINNAME"));
			admin.setPassword(rs.getString("PASSWORD"));
			admin.setRemark(rs.getInt("REMARK"));
			admin.setTel(rs.getString("TEL"));
			beanList.add(admin);
		}
		System.out.println("-----------" + beanList.size());
		JdbcUtil.freeAll();

		/*
		 * 5. 创建PageBean，设置参数
		 */
		PageBean<Admin> pb = new PageBean<Admin>();
		/*
		 * 其中PageBean没有url，这个任务由Servlet完成
		 */
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	public boolean add(Admin admin) {
		JdbcUtil.getConnection();
		boolean flag = false;
		String sql = "insert into t_admin values('" + admin.getAdminid() + "','" + admin.getPassword() + "','"
				+ admin.getAdminname() + "','" + admin.getRemark() + "','" + admin.getTel() + "')";
		System.out.println("111111111111" + sql);
		int i = JdbcUtil.addUpdDel(sql);
		JdbcUtil.freeAll();
		if (i > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean update(Admin admin) {
		JdbcUtil.getConnection();
		boolean flag = false;
		String sql = "update t_admin set  adminid ='" + admin.getAdminid() + "' , tel ='" + admin.getTel()
				+ "' , password ='" + admin.getPassword() + "' where adminname = '" + admin.getAdminname() + "'";
		System.out.println("gengxing----------" + sql);
		int i = JdbcUtil.addUpdDel(sql);
		JdbcUtil.freeAll();
		if (i > 0) {
			flag = true;
		}
		return flag;
	}

	public PageBean<Admin> findAll(int pc) throws SQLException {
		String wheresql = "";
		return findByCriteria(wheresql, pc);
	}

	public PageBean<Admin> findByAdminid(String adminid, int pc) throws SQLException {
		String wheresql = " where adminid = '" + adminid + "'";
		System.out.println(wheresql);
		return findByCriteria(wheresql, pc);
	}
}
