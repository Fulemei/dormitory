package cn.itcast.dormitory.category.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cn.itcast.dormitory.category.entity.Category;
import cn.itcast.dormitory.jdbc.JdbcUtil;

public class CategoryDaoImpl implements CategoryDao{

	/**
	 * 返回所有分类
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public List<Category> findAll() throws SQLException {
		    List<Category> parents = new ArrayList<Category>();
			JdbcUtil.getConnection();
			ResultSet rs = JdbcUtil.selectSql("select * from t_category where pid is null");
			while(rs.next()) {
				Category category =new Category();
				category.setCid(rs.getString("CID"));
				category.setCname(rs.getString("CNAME"));
				category.setDesc(rs.getString("DESCR"));
				parents.add(category);
			}
			for(Category parent : parents) {
				// 查询出当前父分类的所有子分类
				List<Category> children = findByParent(parent.getCid());
				// 设置给父分类
				parent.setChildren(children);
			}
			JdbcUtil.freeAll();
		return parents;
	}
    /**
     * 通过父分类查找子分类
     * @throws SQLException 
     */
	@Override
	public List<Category> findByParent(String pid) throws SQLException {
		List<Category> list = new ArrayList<Category>();
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil.selectSql("select * from t_category where pid='"+pid+"'");
		while(rs.next()) {
			Category category =new Category();
			category.setCid(rs.getString("CID"));
			category.setCname(rs.getString("CNAME"));
			category.setDesc(rs.getString("DESCR"));
			list.add(category);
		}
		JdbcUtil.freeAll();
		return list;
	}
	/**
	 * 添加分类
	 * @param category
	 * @throws SQLException 
	 */
	@Override
	public void add(Category category) {
		JdbcUtil.getConnection();
		/*
		 * 因为一级分类，没有parent，而二级分类有！
		 * 我们这个方法，要兼容两次分类，所以需要判断
		 */
		int orderby =1;
		String pid = null;//一级分类
		if(category.getParent() != null) {
			System.out.println("------------");
			pid = category.getParent().getCid();
			System.out.println(pid);
		}
		JdbcUtil.addUpdDel("insert into t_category values('"+category.getCid()+"','"+category.getCname()+"','"+pid+"','"+category.getDesc()+"','"+orderby+"')");
		JdbcUtil.freeAll();
	}
	
	/**
	 * 获取所有父分类，但不带子分类的！
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Category> findParents() throws SQLException {
		 List<Category> parents = new ArrayList<Category>();
			JdbcUtil.getConnection();
			ResultSet rs = JdbcUtil.selectSql("select * from t_category where pid is null");
			while(rs.next()) {
				Category category =new Category();
				category.setCid(rs.getString("CID"));
				category.setCname(rs.getString("CNAME"));
				category.setDesc(rs.getString("DESCR"));
				parents.add(category);
			}
			return parents;
	}
	
	
	/**
	 * 查询指定父分类下子分类的个数
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int findChildrenCountByParent(String pid) throws SQLException {
		JdbcUtil.getConnection();
		ResultSet rs = JdbcUtil.selectSql("select count(*) as rec from t_category where pid='"+pid+"'");  
		int rowCount = 0;
		while (rs.next()) {
		    rowCount = rs.getInt("rec");
		}
		return rowCount;
		
	}
	/**
	 * 删除分类
	 * @param cid
	 * @throws SQLException 
	 */
	@Override
	public void delete(String cid) {
		JdbcUtil.getConnection();
		JdbcUtil.addUpdDel("delete from t_category where cid='"+cid+"'");
		JdbcUtil.freeAll();
	}
	/**
	 * 修改分类
	 * 即可修改一级分类，也可修改二级分类
	 * @param category
	 * @throws SQLException 
	 */
	@Override
	public void edit(Category category) {
			JdbcUtil.getConnection();
			String pid = null;
			if(category.getParent() != null) {
				pid = category.getParent().getCid();
			}
			String sql = "update t_category set cname ='"+category.getCname()
				     +"' , pid ='"+pid
				     +"' , descr ='"+category.getDesc()+"' where cid = '"+category.getCid()+"'";
			JdbcUtil.addUpdDel(sql);
			JdbcUtil.freeAll();
		}
	
	}


