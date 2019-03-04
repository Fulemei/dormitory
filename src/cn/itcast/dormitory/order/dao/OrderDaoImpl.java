package cn.itcast.dormitory.order.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.itcast.dormitory.category.entity.Category;
import cn.itcast.dormitory.jdbc.JdbcUtil;
import cn.itcast.dormitory.order.entity.Order;
import cn.itcast.dormitory.user.entity.User;
import cn.itcast.dormitory.worker.entity.Worker;

public class OrderDaoImpl implements OrderDao{

	
	/**
	 * 查询订单状态
	 * @param oid
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int findStatus(String oid) throws SQLException {
		JdbcUtil.getConnection();
		Order order = new Order();
		ResultSet rs = JdbcUtil.selectSql("select * from t_order where oid='"+oid+"'");
		while(rs.next()) {
        	order.setStatus(rs.getInt("STATUS")); 
        }
		JdbcUtil.freeAll();
		return order.getStatus();
	}

	/**
	 * 修改订单状态
	 * @param oid
	 * @param status
	 * @throws SQLException
	 */
	@Override
	public void updateStatus(String oid, int status) {
		JdbcUtil.getConnection();
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		System.out.println(time);
		if(status==1) {
			JdbcUtil.addUpdDel("update t_order set status='"+status+"', worktime='"+time+"'where oid = '"+oid+"'");
		}
		if(status==2) {
			JdbcUtil.addUpdDel("update t_order set status='"+status+"', endtime='"+time+"'where oid = '"+oid+"'");
		}
		JdbcUtil.freeAll();
	}

	
	/**
	 * 加载订单
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
		ResultSet rs = JdbcUtil.selectSql("select * from t_order where oid='"+oid+"'");
		while(rs.next()) {
		
			String sid = rs.getString("SID");
			String wid = rs.getString("WID");
			String cid = rs.getString("CID");
			ResultSet rs2 = JdbcUtil.selectSql("select * from t_student where sid='"+sid+"'");
			while(rs2.next()) {
				User u = new User();
				u.setTel_number(rs2.getString("TEL_NUMBER"));
				u.setName(rs2.getString("NAME"));
				order.setOwner(u);
			}
			ResultSet rs3 = JdbcUtil.selectSql("select * from t_worker where wid='"+wid+"'");
			while(rs3.next()) {
				Worker w = new Worker();
				w.setWname(rs3.getString("WNAME"));
				w.setWnumber(rs3.getString("WNUMBER"));
				order.setWorker(w);
			}
			ResultSet rs4 = JdbcUtil.selectSql("select * from t_category where cid='"+cid+"'");
			while(rs4.next()) {
				Category c = new Category();
				c.setCname(rs4.getString("CNAME"));
				order.setCategory(c);
			}
			order.setOid(rs.getString("OID"));
			order.setStatus(rs.getInt("STATUS")); 
			order.setOrdertime(rs.getString("ORDERTIME"));
			order.setAddress(rs.getString("ADDRESS"));
		}
		JdbcUtil.freeAll();
		return order;
		
	}
	/**
	 * 生成订单
	 * @param order
	 * @throws SQLException 
	 */
	@Override
	public void add(Order order) throws SQLException {
		 JdbcUtil.getConnection();
		 /*
		  * 根据分类匹配师傅
		  */
		 String cid = order.getCategory().getCid();
		 String c = cid.substring(0, 2);
		 ResultSet rs = JdbcUtil.selectSql("select * from t_worker where cid = '"+c+"'");
		 while(rs.next()) {
			 Worker worker = new Worker();
			 worker.getCategory().setCid(cid);
			 worker.setWid(rs.getString("WID"));
			 worker.setWnumber(rs.getString("WNUMBER"));
			 worker.setWname(rs.getString("WNAME"));
			 worker.setWemail(rs.getString("WEMAIL"));
			 order.setWorker(worker);
		 }
		 order.getWorker().getWid();
		 String sql = "insert into t_order (wid,sid,address,ordermsg,cid) values('"+order.getWorker().getWid()+
				 													"','"+order.getOwner().getSid()+
				 													"','"+order.getAddress()+
				 													"','"+order.getOrdermsg()+
				 													"','"+cid+"')";
		 JdbcUtil.addUpdDel(sql);
		 JdbcUtil.freeAll();
	}

	/**
	 * 按用户查询订单
	 * @param uid
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public List<Order> findByUser(String sid) throws SQLException {
		JdbcUtil.getConnection();
		List<Order> list = new ArrayList<Order>();
		ResultSet rs = JdbcUtil.selectSql("select * from t_order where sid='"+sid+"'");
		while(rs.next()) {
			Order order = new Order();
			String wid = rs.getString("WID");
			String cid = rs.getString("CID");
			ResultSet rs2 = JdbcUtil.selectSql("select * from t_student where sid='"+sid+"'");
			while(rs2.next()) {
				User u = new User();
				u.setTel_number(rs2.getString("TEL_NUMBER"));
				u.setName(rs2.getString("NAME"));
				order.setOwner(u);
			}
			ResultSet rs3 = JdbcUtil.selectSql("select * from t_worker where wid='"+wid+"'");
			while(rs3.next()) {
				Worker w = new Worker();
				w.setWname(rs3.getString("WNAME"));
				w.setWnumber(rs3.getString("WNUMBER"));
				order.setWorker(w);
			}
			ResultSet rs4 = JdbcUtil.selectSql("select * from t_category where cid='"+cid+"'");
			while(rs4.next()) {
				Category c = new Category();
				c.setCname(rs4.getString("CNAME"));
				order.setCategory(c);
			}
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
	public List<Order> findAll() throws SQLException {
		JdbcUtil.getConnection();
		List<Order> list = new ArrayList<Order>();
		ResultSet rs = JdbcUtil.selectSql("select * from t_order");
		while(rs.next()) {
			Order order = new Order();
			String sid = rs.getString("SID");
			String wid = rs.getString("WID");
			String cid = rs.getString("CID");
			ResultSet rs2 = JdbcUtil.selectSql("select * from t_student where sid='"+sid+"'");
			while(rs2.next()) {
				User u = new User();
				u.setTel_number(rs2.getString("TEL_NUMBER"));
				u.setName(rs2.getString("NAME"));
				order.setOwner(u);
			}
			ResultSet rs3 = JdbcUtil.selectSql("select * from t_worker where wid='"+wid+"'");
			while(rs3.next()) {
				Worker w = new Worker();
				w.setWname(rs3.getString("WNAME"));
				w.setWnumber(rs3.getString("WNUMBER"));
				order.setWorker(w);
			}
			ResultSet rs4 = JdbcUtil.selectSql("select * from t_category where cid='"+cid+"'");
			while(rs4.next()) {
				Category c = new Category();
				c.setCname(rs4.getString("CNAME"));
				order.setCategory(c);
			}
			order.setOid(rs.getString("OID"));
			order.setStatus(rs.getInt("STATUS")); 
			order.setOrdertime(rs.getString("ORDERTIME"));
			order.setAddress(rs.getString("ADDRESS"));
			list.add(order);
		}
		JdbcUtil.freeAll();
		return list;
	}
	
    /**
     * 根据维修工人查找订单
     * @throws SQLException 
     */
	@Override
	public List<Order> findByWorker(String wid) throws SQLException {
		JdbcUtil.getConnection();
		List<Order> list = new ArrayList<Order>();
		ResultSet rs = JdbcUtil.selectSql("select * from t_order where wid='"+wid+"'");
		while(rs.next()) {
			Order order = new Order();
			String sid = rs.getString("SID");
			String cid = rs.getString("CID");
			ResultSet rs2 = JdbcUtil.selectSql("select * from t_student where sid='"+sid+"'");
			while(rs2.next()) {
				User u = new User();
				u.setTel_number(rs2.getString("TEL_NUMBER"));
				u.setName(rs2.getString("NAME"));
				order.setOwner(u);
			}
			ResultSet rs3 = JdbcUtil.selectSql("select * from t_worker where wid='"+wid+"'");
			while(rs3.next()) {
				Worker w = new Worker();
				w.setWname(rs3.getString("WNAME"));
				w.setWnumber(rs3.getString("WNUMBER"));
				order.setWorker(w);
			}
			ResultSet rs4 = JdbcUtil.selectSql("select * from t_category where cid='"+cid+"'");
			while(rs4.next()) {
				Category c = new Category();
				c.setCname(rs4.getString("CNAME"));
				order.setCategory(c);
			}
			order.setOid(rs.getString("OID"));
			order.setStatus(rs.getInt("STATUS")); 
			order.setOrdertime(rs.getString("ORDERTIME"));
			order.setAddress(rs.getString("ADDRESS"));
			list.add(order);
		}
		JdbcUtil.freeAll();
		return list;
	}
    /**
     * 根据状态查询订单
     * @throws SQLException 
     */
	@Override
	public List<Order> findByStatus(int status) throws SQLException {
		JdbcUtil.getConnection();
		List<Order> list = new ArrayList<Order>();
		ResultSet rs = JdbcUtil.selectSql("select * from t_order where status='"+status+"'");
		while(rs.next()) {
			Order order = new Order();
			String sid = rs.getString("SID");
			String wid = rs.getString("WID");
			String cid = rs.getString("CID");
			ResultSet rs2 = JdbcUtil.selectSql("select * from t_student where sid='"+sid+"'");
			while(rs2.next()) {
				User u = new User();
				u.setTel_number(rs2.getString("TEL_NUMBER"));
				u.setName(rs2.getString("NAME"));
				order.setOwner(u);
			}
			ResultSet rs3 = JdbcUtil.selectSql("select * from t_worker where wid='"+wid+"'");
			while(rs3.next()) {
				Worker w = new Worker();
				w.setWname(rs3.getString("WNAME"));
				w.setWnumber(rs3.getString("WNUMBER"));
				order.setWorker(w);
			}
			ResultSet rs4 = JdbcUtil.selectSql("select * from t_category where cid='"+cid+"'");
			while(rs4.next()) {
				Category c = new Category();
				c.setCname(rs4.getString("CNAME"));
				order.setCategory(c);
			}
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
	public List<Order> findByDorimtory(String dor) throws SQLException {
		JdbcUtil.getConnection();
		List<Order> list = new ArrayList<Order>();
		ResultSet rs = JdbcUtil.selectSql("select * from t_order where address like '"+dor+"%'");
		while(rs.next()) {
			Order order = new Order();
			String sid = rs.getString("SID");
			String wid = rs.getString("WID");
			String cid = rs.getString("CID");
			ResultSet rs2 = JdbcUtil.selectSql("select * from t_student where sid='"+sid+"'");
			while(rs2.next()) {
				User u = new User();
				u.setTel_number(rs2.getString("TEL_NUMBER"));
				u.setName(rs2.getString("NAME"));
				order.setOwner(u);
			}
			ResultSet rs3 = JdbcUtil.selectSql("select * from t_worker where wid='"+wid+"'");
			while(rs3.next()) {
				Worker w = new Worker();
				w.setWname(rs3.getString("WNAME"));
				w.setWnumber(rs3.getString("WNUMBER"));
				order.setWorker(w);
			}
			ResultSet rs4 = JdbcUtil.selectSql("select * from t_category where cid='"+cid+"'");
			while(rs4.next()) {
				Category c = new Category();
				c.setCname(rs4.getString("CNAME"));
				order.setCategory(c);
			}
			order.setOid(rs.getString("OID"));
			order.setStatus(rs.getInt("STATUS")); 
			order.setOrdertime(rs.getString("ORDERTIME"));
			order.setAddress(rs.getString("ADDRESS"));
			order.setOrdermsg(rs.getString("ORDERMSG"));
			list.add(order);
		}
		JdbcUtil.freeAll();
		return list;
	}
}
