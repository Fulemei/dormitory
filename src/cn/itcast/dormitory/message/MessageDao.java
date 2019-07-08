package cn.itcast.dormitory.message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.dormitory.jdbc.JdbcUtil;
import cn.itcast.dormitory.pager.PageBean;
import cn.itcast.dormitory.pager.PageConstants;

public class MessageDao {
	public boolean add(Message m) {
		boolean flag = false;
		JdbcUtil.getConnection();
		String sql = "insert into t_message values('" + m.getMid() + "','" + m.getMtitle() + "','" + m.getMessage()
				+ "','" + m.getCreate_date() + "','1')";
		System.out.println(sql);
		int i = JdbcUtil.addUpdDel(sql);
		JdbcUtil.freeAll();
		if (i > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean delete(String mid) {
		boolean flag = false;
		JdbcUtil.getConnection();
		String sql = "update  t_message set remark = '0' where mid = '" + mid + "'";
		int i = JdbcUtil.addUpdDel(sql);
		JdbcUtil.freeAll();
		if (i > 0) {
			flag = true;
		}
		return flag;
	}

	private PageBean<Message> findByCriteria(String wheresql, int pc) throws SQLException {
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
		String sql = "select count(*) from t_message " + wheresql;
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
		sql = "select * from (select t.*,rownum rn from (select * from t_message " + wheresql
				+ "order by create_date desc)t where rownum <= " + max + " ) where rn > " + min;
		System.out.println("----2---" + sql);
		ResultSet rs = JdbcUtil.selectSql(sql);
		List<Message> beanList = new ArrayList<Message>();
		while (rs.next()) {
			Message m = new Message();
			m.setMid(rs.getString("MID"));
			m.setMtitle(rs.getString("MTITLE"));
			m.setMessage(rs.getString("MESSAGE"));
			m.setCreate_date(rs.getString("CREATE_DATE"));
			m.setRemark(rs.getString("REMARK"));
			beanList.add(m);
		}
		System.out.println("-----------" + beanList.size());
		JdbcUtil.freeAll();

		/*
		 * 5. 创建PageBean，设置参数
		 */
		PageBean<Message> pb = new PageBean<Message>();
		/*
		 * 其中PageBean没有url，这个任务由Servlet完成
		 */
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	public PageBean<Message> findAll(int pc) throws SQLException {
		String wheresql = "where remark = '1'";
		return findByCriteria(wheresql, pc);
	}

	public Message findByMid(String mid) throws SQLException {
		JdbcUtil.getConnection();
		Message m = new Message();
		String sql = "select * from t_message where mid ='" + mid + "'";
		ResultSet rs = JdbcUtil.selectSql(sql);
		while (rs.next()) {
			m.setMid(rs.getString("MID"));
			m.setMtitle(rs.getString("MTITLE"));
			m.setMessage(rs.getString("MESSAGE"));
			m.setCreate_date(rs.getString("CREATE_DATE"));
			m.setRemark(rs.getString("REMARK"));
		}
		JdbcUtil.freeAll();
		return m;
	}
}
