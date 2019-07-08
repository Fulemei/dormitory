package cn.itcast.dormitory.order.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.itcast.dormitory.jdbc.JdbcUtil;
import cn.itcast.dormitory.order.entity.Order;
import cn.itcast.dormitory.pager.PageBean;
import cn.itcast.dormitory.pager.PageConstants;
import cn.itcast.dormitory.sendemail.SendMail;
import cn.itcast.dormitory.user.entity.User;
import cn.itcast.dormitory.worker.entity.Worker;

public class OrderDaoImpl implements OrderDao {

	/**
	 * 查询订单状态
	 * 
	 * @param oid
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int findStatus(String oid) throws SQLException {
		JdbcUtil.getConnection();
		Order order = new Order();
		ResultSet rs = JdbcUtil.selectSql("select * from t_order where oid='" + oid + "'");
		while (rs.next()) {
			order.setStatus(rs.getInt("STATUS"));
		}
		JdbcUtil.freeAll();
		return order.getStatus();
	}

	/**
	 * 修改订单状态
	 * 
	 * @param oid
	 * @param status
	 * @throws Exception
	 * @throws SQLException
	 */
	@Override
	public boolean updateStatus(String oid, int status) throws Exception {
		boolean flag = false;
		int i = 0;
		JdbcUtil.getConnection();
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = format.format(date);
		System.out.println(time);
		if (status == 1) {
			i = JdbcUtil.addUpdDel(
					"update t_order set status='" + status + "', worktime='" + time + "'where oid = '" + oid + "'");
		} else if (status == 2) {
			i = JdbcUtil.addUpdDel(
					"update t_order set status='" + status + "', endtime='" + time + "'where oid = '" + oid + "'");
			Order order;
			try {
				order = load(oid);
				SendMail sm = new SendMail();
				sm.sendemail(order.getWorker().getWemail(), "完成", order);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (status == 3) {
			i = JdbcUtil.addUpdDel(
					"update t_order set status='" + status + "', endtime='" + time + "'where oid = '" + oid + "'");
			Order order;
			try {
				order = load(oid);
				SendMail sm = new SendMail();
				sm.sendemail(order.getWorker().getWemail(), "取消", order);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out
				.println("update t_order set status='" + status + "', endtime='" + time + "'where oid = '" + oid + "'");
		if (i > 0) {
			flag = true;
		}
		JdbcUtil.freeAll();
		return flag;
	}

	/**
	 * 加载订单
	 * 
	 * @param oid
	 * @return
	 * @throws SQLException
	 */
	public Order load(String oid) throws SQLException {
		JdbcUtil.getConnection();
		Order order = new Order();
		order.setOid(oid);
		/*
		 * 显示维修师傅和学生具体信息
		 */
		ResultSet rs = JdbcUtil.selectSql("select * from t_order where oid='" + oid + "'");
		while (rs.next()) {

			String sid = rs.getString("SID");
			String wid = rs.getString("WID");
//			String cid = rs.getString("CID");
			ResultSet rs2 = JdbcUtil.selectSql("select * from t_student where sid='" + sid + "'");
			while (rs2.next()) {
				User u = new User();
				u.setTel_number(rs.getString("SPHONE"));
				u.setName(rs2.getString("NAME"));
				order.setOwner(u);
			}
			ResultSet rs3 = JdbcUtil.selectSql("select * from t_worker where wid='" + wid + "'");
			while (rs3.next()) {
				Worker w = new Worker();
				w.setWname(rs3.getString("WNAME"));
				w.setWnumber(rs3.getString("WNUMBER"));
				order.setWorker(w);
			}
//			ResultSet rs4 = JdbcUtil.selectSql("select * from t_category where cid='"+cid+"'");
//			while(rs4.next()) {
//				Category c = new Category();
//				c.setCname(rs4.getString("CNAME"));
//				order.setCategory(c);
//			}
			order.setCid(rs.getString("CID"));
			order.setOid(rs.getString("OID"));
			order.setStatus(rs.getInt("STATUS"));
			order.setOrdertime(rs.getString("ORDERTIME"));
			order.setAddress(rs.getString("ADDRESS"));
			order.setOrdermsg(rs.getString("ORDERMSG"));
			order.setDescr(rs.getString("DESCR"));
			order.setEndtime(rs.getString("ENDTIME"));
			order.setWorktime(rs.getString("WORKTIME"));
			order.setPl(rs.getString("REMARK1"));
			order.setDj(rs.getString("REMARK"));
			order.setZt(rs.getInt("REMARK2"));
		}
		JdbcUtil.freeAll();
		return order;

	}

	/**
	 * 生成订单
	 * 
	 * @param order
	 * @throws Exception
	 */
	@Override
	public void add(Order order) throws Exception {
		JdbcUtil.getConnection();
		/*
		 * 根据分类匹配师傅
		 */
		System.out.println("----" + order);
		int cid = 5;
		String a = order.getCid();
		if ("01".equals(a)) {
			cid = 2;
		} else if ("02".equals(a)) {
			cid = 1;
		} else if ("03".equals(a) || "04".equals(a)) {
			cid = 3;
		} else if ("05".equals(a)) {
			cid = 4;
		}
		System.out.println("cid" + cid);
		ResultSet rs = JdbcUtil.selectSql("select * from t_worker where cid = '" + cid + "'");
		while (rs.next()) {
			Worker worker = new Worker();
			worker.setCid(cid);
			worker.setWid(rs.getString("WID"));
			worker.setWnumber(rs.getString("WNUMBER"));
			worker.setWname(rs.getString("WNAME"));
			worker.setWemail(rs.getString("WEMAIL"));
			order.setWorker(worker);
		}
		System.out.println("order" + order);
		String sql = "insert into t_order (oid,wname,sname,address,ordermsg,sphone,wphone,cid,wid,sid) values('"
				+ order.getOid() + "','" + order.getWorker().getWname() + "','" + order.getOwner().getName() + "','"
				+ order.getAddress() + "','" + order.getOrdermsg() + "','" + order.getOwner().getTel_number() + "','"
				+ order.getWorker().getWnumber() + "','" + cid + "','" + order.getWorker().getWid() + "','"
				+ order.getOwner().getSid() + "')";
		System.out.println("-------------" + sql);
		JdbcUtil.addUpdDel(sql);
		SendMail sm = new SendMail();
		Order o = load(order.getOid());
		sm.sendemail(order.getWorker().getWemail(), "新增", o);
		JdbcUtil.freeAll();
	}

	private PageBean<Order> findByCriteria(String wheresql, int pc) throws SQLException {
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
		String sql = "select count(*) from t_order " + wheresql;
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
		sql = "select * from (select t.*,rownum rn from (select * from t_order " + wheresql
				+ "order by ordertime desc)t where rownum <= " + max + " ) where rn > " + min;
		System.out.println("----2---" + sql);
		ResultSet rs = JdbcUtil.selectSql(sql);
		List<Order> beanList = new ArrayList<Order>();
		while (rs.next()) {
			Order order = new Order();
			order.setCid(rs.getString("CID"));
			order.setOid(rs.getString("OID"));
			order.setStatus(rs.getInt("STATUS"));
			order.setOrdermsg(rs.getString("ORDERMSG"));
			order.setOrdertime(rs.getString("ORDERTIME"));
			order.setAddress(rs.getString("ADDRESS"));
			order.setPl(rs.getString("REMARK1"));
			order.setDj(rs.getString("REMARK"));
			order.setZt(rs.getInt("REMARK2"));
			User u = new User();
			u.setName(rs.getString("SNAME"));
			u.setTel_number(rs.getString("SPHONE"));
			Worker w = new Worker();
			w.setWname(rs.getString("WNAME"));
			w.setWnumber(rs.getString("WPHONE"));
			order.setOwner(u);
			order.setWorker(w);
			System.out.println("order------" + order);
			beanList.add(order);
		}
		System.out.println("-----------" + beanList.size());
		JdbcUtil.freeAll();

		/*
		 * 5. 创建PageBean，设置参数
		 */
		PageBean<Order> pb = new PageBean<Order>();
		/*
		 * 其中PageBean没有url，这个任务由Servlet完成
		 */
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	/**
	 * 按用户查询订单
	 * 
	 * @param uid
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Order> findByUser(String sid, int pc) throws SQLException {
		String wheresql = "where sid='" + sid + "'";
		return findByCriteria(wheresql, pc);
	}

	public PageBean<Order> findByAdmin(String address, int pc) throws SQLException {
		String wheresql = "where address like'%" + address + "%'";
		return findByCriteria(wheresql, pc);
	}

	@Override
	public PageBean<Order> findAll(int pc) throws SQLException {
		String wheresql = "";
		return findByCriteria(wheresql, pc);
	}

	/**
	 * 根据维修工人查找订单
	 * 
	 * @throws SQLException
	 */
	@Override
	public PageBean<Order> findByWorker(String wid, int pc) throws SQLException {
		String wheresql = "where wid='" + wid + "'";
		return findByCriteria(wheresql, pc);
	}

	/**
	 * 根据状态查询订单
	 * 
	 * @throws SQLException
	 */
	@Override
	public List<Order> findByStatus(int status) throws SQLException {
		JdbcUtil.getConnection();
		List<Order> list = new ArrayList<Order>();
		ResultSet rs = JdbcUtil.selectSql("select * from t_order where status='" + status + "'");
		while (rs.next()) {
			Order order = new Order();
			String sid = rs.getString("SID");
			String wid = rs.getString("WID");
			String cid = rs.getString("CID");
			ResultSet rs2 = JdbcUtil.selectSql("select * from t_student where sid='" + sid + "'");
			while (rs2.next()) {
				User u = new User();
				u.setTel_number(rs2.getString("TEL_NUMBER"));
				u.setName(rs2.getString("NAME"));
				order.setOwner(u);
			}
			ResultSet rs3 = JdbcUtil.selectSql("select * from t_worker where wid='" + wid + "'");
			while (rs3.next()) {
				Worker w = new Worker();
				w.setWname(rs3.getString("WNAME"));
				w.setWnumber(rs3.getString("WNUMBER"));
				order.setWorker(w);
			}
//			ResultSet rs4 = JdbcUtil.selectSql("select * from t_category where cid='" + cid + "'");
//			while (rs4.next()) {
//				Category c = new Category();
//				c.setCname(rs4.getString("CNAME"));
//				order.setCategory(c);
//			}
			order.setCid(rs.getString("CID"));
			order.setOid(rs.getString("OID"));
			order.setStatus(rs.getInt("STATUS"));
			order.setOrdertime(rs.getString("ORDERTIME"));
			order.setAddress(rs.getString("ADDRESS"));
			list.add(order);
		}
		JdbcUtil.freeAll();
		return list;
	}

	@Override
	public PageBean<Order> findByDorimtory(String dor, int pc) throws SQLException {
		String wheresql = "where address like '" + dor + "%'";
		return findByCriteria(wheresql, pc);
	}

	@Override
	public boolean addPl(String pl, String dj, String oid) throws SQLException {
		boolean flag = false;
		JdbcUtil.getConnection();
		int i = JdbcUtil.addUpdDel(
				"update t_order set remark='" + dj + "', remark1='" + pl + "', remark2 = 1 where oid = '" + oid + "'");
		if (i > 0) {
			flag = true;
		}
		JdbcUtil.freeAll();
		return flag;
	}

}
