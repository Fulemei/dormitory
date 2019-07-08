package cn.itcast.dormitory.worker.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.dormitory.jdbc.JdbcUtil;
import cn.itcast.dormitory.pager.PageBean;
import cn.itcast.dormitory.pager.PageConstants;
import cn.itcast.dormitory.worker.entity.Worker;

public class WorkerDaoImpl implements WorkerDao {
	/**
	 * 删除工人信息
	 */
	@Override
	public boolean delete(String wid) {
		boolean flag = false;
		JdbcUtil.getConnection();
		int i = JdbcUtil.addUpdDel("delete from t_worker where wid ='" + wid + "'");
		if (i > 0) {
			flag = true;
		}
		JdbcUtil.freeAll();
		return flag;
	}

	/**
	 * 编辑工人信息
	 */
	@Override
	public boolean edit(Worker worker) {
		boolean flag = false;
		JdbcUtil.getConnection();
		String sql = "update t_worker set wname ='" + worker.getWname() + "' , wpassword ='" + worker.getWpassword()
				+ "' , wemail ='" + worker.getWemail() + "' , wnumber ='" + worker.getWnumber() + "' , cid ='"
				+ worker.getCid() + "' where wid = '" + worker.getWid() + "'";
		int i = JdbcUtil.addUpdDel(sql);
		JdbcUtil.freeAll();
		if (i > 0) {
			flag = true;
		}
		JdbcUtil.freeAll();
		return flag;
	}

	/**
	 * 按照工号查找信息
	 */
	@Override
	public PageBean<Worker> findByWid(String wid, int pc) throws SQLException {
		String wheresql = "where wid='" + wid + "'";
		return findByCriteria(pc, wheresql);
	}

	/**
	 * 查询指定分类下工人的个数
	 * 
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int findWorkerCountByCategory(String cid) throws SQLException {
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil.selectSql("select count(*) as rec from t_worker where cid='" + cid + "'");
		int rowCount = 0;
		while (rs.next()) {
			rowCount = rs.getInt("rec");
		}
		return rowCount;

	}

	/**
	 * 按分类查询
	 * 
	 * @param cid
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Worker> findByCriteria(int pc, String wheresql) throws SQLException {
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
		String sql = "select count(*) from t_worker " + wheresql;
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
		sql = "select * from (select t.*,rownum rn from (select * from t_worker " + wheresql + ")t where rownum <= "
				+ max + " ) where rn > " + min;
		System.out.println("----2---" + sql);
		ResultSet rs = JdbcUtil.selectSql(sql);
		List<Worker> beanList = new ArrayList<Worker>();
		while (rs.next()) {
			Worker worker = new Worker();
			worker.setCid(rs.getInt("CID"));
			worker.setWid(rs.getString("WID"));
			worker.setWemail(rs.getString("WEMAIL"));
			worker.setWname(rs.getString("WNAME"));
			worker.setWnumber(rs.getString("WNUMBER"));
			System.out.println("worker-------" + worker);
			beanList.add(worker);
		}
		System.out.println("-----------" + beanList.size());
		JdbcUtil.freeAll();

		/*
		 * 5. 创建PageBean，设置参数
		 */
		PageBean<Worker> pb = new PageBean<Worker>();
		/*
		 * 其中PageBean没有url，这个任务由Servlet完成
		 */
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	public PageBean<Worker> findAll(int pc) throws SQLException {
		String wheresql = "";
		return findByCriteria(pc, wheresql);
	}

	@Override
	public boolean add(Worker worker) {
		boolean flag = false;
		JdbcUtil.getConnection();
		int i = JdbcUtil.addUpdDel("insert into t_worker values('" + worker.getWid() + "','" + worker.getWname() + "','"
				+ worker.getWpassword() + "','" + worker.getCid() + "','" + worker.getWemail() + "','"
				+ worker.getWnumber() + "')");
		System.out.println("insert into t_worker values('" + worker.getWid() + "','" + worker.getWname() + "','"
				+ worker.getWpassword() + "','" + worker.getCid() + "','" + worker.getWemail() + "','"
				+ worker.getWnumber() + "')");
		JdbcUtil.freeAll();
		if (i > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public PageBean<Worker> findByCategory(String cid, int pc) throws SQLException {
		String wheresql = "where cid='" + cid + "'";
		return findByCriteria(pc, wheresql);
	}

	public boolean checkWid(String wid) {
		boolean flag = false;
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil.selectSql("select * from t_worker where wid='" + wid + "'");
		if (rs != null) {
			flag = true;
		}
		JdbcUtil.freeAll();
		return flag;
	}

	public boolean ajaxValidateEmail(String email) {
		boolean flag = false;
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil.selectSql("select * from t_worker where wemail='" + email + "'");
		if (rs != null) {
			flag = true;
		}
		JdbcUtil.freeAll();
		return flag;
	}
}
