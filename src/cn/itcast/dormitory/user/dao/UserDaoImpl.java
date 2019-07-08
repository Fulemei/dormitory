package cn.itcast.dormitory.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.dormitory.jdbc.JdbcUtil;
import cn.itcast.dormitory.pager.PageBean;
import cn.itcast.dormitory.pager.PageConstants;
import cn.itcast.dormitory.user.entity.User;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean login(String sid, String password) {
		boolean flag = false;
		try {
			JdbcUtil.getConnection();
			ResultSet rs = JdbcUtil
					.selectSql("select * from t_student where sid='" + sid + "' and password='" + password + "'");
			while (rs.next()) {

				flag = true;

			}
			JdbcUtil.freeAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean register(User user) {
		boolean flag = false;
		JdbcUtil.getConnection();
		int i = JdbcUtil.addUpdDel("insert into t_student values('" + user.getSid() + "','" + user.getEmail() + "','"
				+ user.getName() + "','" + user.getPassword() + "','" + user.getDormitory() + "','"
				+ user.getTel_number() + "','" + user.getClasses() + "','" + user.getSex() + "')");
		System.out.println("insert into t_student values('" + user.getSid() + "','" + user.getEmail() + "','"
				+ user.getName() + "','" + user.getPassword() + "','" + user.getDormitory() + "','"
				+ user.getTel_number() + "','" + user.getClasses() + "','" + user.getSex() + "')");
		JdbcUtil.freeAll();
		if (i > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<User> getUserAll() {
		List<User> list = new ArrayList<User>();
		try {
			JdbcUtil.getConnection();
			ResultSet rs = JdbcUtil.selectSql("select * from t_student");
			while (rs.next()) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(String sid) {
		boolean flag = false;
		JdbcUtil.getConnection();
		int i = JdbcUtil.addUpdDel("delete from t_student where sid ='" + sid + "'");
		if (i > 0) {
			flag = true;
		}
		JdbcUtil.freeAll();
		return flag;
	}

	@Override
	public boolean update(User user) {
		boolean flag = false;
		JdbcUtil.getConnection();
		String sql = "update t_student set  dormitory ='" + user.getDormitory() + "' , tel_number ='"
				+ user.getTel_number() + "' , class ='" + user.getClasses() + "' where sid = '" + user.getSid() + "'";
		System.out.println(sql);
		int i = JdbcUtil.addUpdDel(sql);
		if (i > 0) {
			flag = true;
		}
		JdbcUtil.freeAll();
		return flag;
	}

	@Override
	public boolean ajaxValidateSid(String sid) {
		boolean flag = false;
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil.selectSql("select * from t_student where sid='" + sid + "'");
		if (rs != null) {
			flag = true;
		}
		JdbcUtil.freeAll();
		return flag;
	}

	@Override
	public boolean ajaxValidateEmail(String email) {
		boolean flag = false;
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil.selectSql("select * from t_student where email='" + email + "'");
		if (rs != null) {
			flag = true;
		}
		JdbcUtil.freeAll();
		return flag;
	}

	@Override
	public boolean ajaxValidateNumber(String tel_number) {
		boolean flag = false;
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil.selectSql("select * from t_student where tel_number='" + tel_number + "'");
		if (rs != null) {
			flag = true;
		}
		JdbcUtil.freeAll();
		return flag;
	}

	@Override
	public boolean updatePassword(String sid, String newPass, String password) {
		boolean flag = false;
		JdbcUtil.getConnection();
		System.out.println("update t_student set password='" + newPass + "' where sid ='" + sid + "' and password ='"
				+ password + "'");
		int i = JdbcUtil.addUpdDel("update t_student set password='" + newPass + "' where sid ='" + sid
				+ "' and password ='" + password + "'");
		System.out.println("i" + i);
		System.out.println("update t_student set password='" + newPass + "' where sid ='" + sid + "' and password ='"
				+ password + "'");
		if (i > 0) {
			flag = true;
		}
		JdbcUtil.freeAll();
		System.out.println("flag" + flag);
		return flag;
	}

	public PageBean<User> findBySid(String sid, int pc) throws SQLException {
		String wheresql = "where sid='" + sid + "'";
		return findByCriteria(wheresql, pc);
	}

	private PageBean<User> findByCriteria(String wheresql, int pc) throws SQLException {
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
		String sql = "select count(*) from t_student " + wheresql;
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
		sql = "select * from (select t.*,rownum rn from (select * from t_student " + wheresql
				+ "order by sid desc)t where rownum <= " + max + " ) where rn > " + min;
		System.out.println("----2---" + sql);
		ResultSet rs = JdbcUtil.selectSql(sql);
		List<User> beanList = new ArrayList<User>();
		while (rs.next()) {
			User user = new User();
			user.setSid(rs.getString("SID"));
			user.setName(rs.getString("NAME"));
			user.setClasses(rs.getString("CLASS"));
			user.setDormitory(rs.getString("DORMITORY"));
			user.setTel_number(rs.getString("TEL_NUMBER"));
			user.setSex(rs.getInt("SEX"));
			beanList.add(user);
		}
		System.out.println("-----------" + beanList.size());
		JdbcUtil.freeAll();

		/*
		 * 5. 创建PageBean，设置参数
		 */
		PageBean<User> pb = new PageBean<User>();
		/*
		 * 其中PageBean没有url，这个任务由Servlet完成
		 */
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	@Override
	public PageBean<User> findAll(int pc) throws SQLException {
		String wheresql = "";
		return findByCriteria(wheresql, pc);
	}

	public List<User> findBySid(String sid) throws SQLException {
		JdbcUtil.getConnection();
		List<User> list = new ArrayList<User>();
		ResultSet rs = JdbcUtil.selectSql("select * from t_student where sid='" + sid + "'");
		while (rs.next()) {

			User user = new User();
			user.setSid(sid);
			user.setName(rs.getString("name"));
			user.setDormitory(rs.getString("dormitory"));
			user.setEmail(rs.getString("email"));
			user.setTel_number(rs.getString("tel_number"));
			user.setPassword(rs.getString("password"));
			user.setClasses(rs.getString("class"));
			list.add(user);
		}
		JdbcUtil.freeAll();
		return list;
	}

}
