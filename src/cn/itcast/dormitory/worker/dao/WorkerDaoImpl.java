package cn.itcast.dormitory.worker.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.dormitory.jdbc.JdbcUtil;
import cn.itcast.dormitory.worker.entity.Worker;


public class WorkerDaoImpl implements WorkerDao{
    /**
          * 删除工人信息
     */
	@Override
	public void delete(String wid) {
		JdbcUtil.getConnection();
		JdbcUtil.addUpdDel("delete from t_worker where wid='"+wid+"'");
		JdbcUtil.freeAll();
	}
    /**
          * 编辑工人信息
     */
	@Override
	public void edit(Worker worker) {
		JdbcUtil.getConnection();
		String sql = "update t_worker set wname ='"+worker.getWname()
			     +"' , wpassword ='"+worker.getWpassword()
			     +"' , wemail ='"+worker.getWemail()
			     +"' , wnumber ='"+worker.getWnumber()
			     +"' , cid ='"+worker.getCid()+"' where wid = '"+worker.getWid()+"'";
		JdbcUtil.addUpdDel(sql);
		JdbcUtil.freeAll();
	}
    /**
          * 按照工号查找信息
     */
	@Override
	public List<Worker> findByWid(String wid) throws SQLException {
		List<Worker> list = new ArrayList<Worker>();
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil.selectSql("select * from t_worker where wid='"+wid+"'");
		while(rs.next()) {
			Worker worker = new Worker();
			worker.setWid(wid);
			worker.setWname(rs.getString("WNAME"));
			worker.setCid(rs.getString("CID"));
			worker.setWpassword(rs.getString("WPASSWORD"));
			worker.setWnumber(rs.getString("WNUMBER"));
			worker.setWemail(rs.getString("WEMAIL"));
			list.add(worker);
		}
		return list;
	}
	 /**
 	 * 查询指定分类下工人的个数
 	 * @param cid
 	 * @return
 	 * @throws SQLException
 	 */
	@Override
	public int findWorkerCountByCategory(String cid) throws SQLException {
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil.selectSql("select count(*) as rec from t_worker where cid='"+cid+"'");  
		int rowCount = 0;
		while (rs.next()) {
		    rowCount = rs.getInt("rec");
		}
		return rowCount;
		
	}

	/**
	 * 按分类查询
	 * @param cid
	 * @param pc
	 * @return
	 * @throws SQLException 
	 */
	public List<Worker> findByCategory(String cid) throws SQLException {
		List<Worker> list = new ArrayList<Worker>();
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil.selectSql("select * from t_worker where cid='"+cid+"'");
		while(rs.next()) {
			Worker worker = new Worker();
			worker.setWid(rs.getString("WID"));
			worker.setWname(rs.getString("WNAME"));
			worker.setWpassword(rs.getString("wpassword"));
			worker.setCid(rs.getString("CID"));
			worker.setWnumber(rs.getString("WNUMBER"));
			worker.setWemail(rs.getString("WEMAIL"));
			list.add(worker);
		}
		return list;
	}
	public List<Worker> findAll() throws SQLException{
		List<Worker> list = new ArrayList<Worker>();
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil.selectSql("select * from t_worker ");
		while(rs.next()) {
			Worker worker = new Worker();
			worker.setWid(rs.getString("WID"));
			worker.setWname(rs.getString("WNAME"));
			worker.setWpassword(rs.getString("wpassword"));
			worker.setCid(rs.getString("CID"));
			worker.setWnumber(rs.getString("WNUMBER"));
			worker.setWemail(rs.getString("WEMAIL"));
			list.add(worker);
		}
		return list;
	}

	@Override
	public void add(Worker worker) {
		JdbcUtil.getConnection();
		JdbcUtil.addUpdDel("insert into t_worker values('"+worker.getWid()+"','"+worker.getWname()+"','"+worker.getWpassword()+"','"+worker.getCid()+"','"+worker.getWemail()+"','"+worker.getWnumber()+"')");
		JdbcUtil.freeAll();
	}

}
