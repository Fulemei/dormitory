package cn.itcast.dormitory.category.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.itcast.dormitory.category.entity.Category;
public interface CategoryDao {
	public List<Category> findAll() throws SQLException;//返回所有分类
	public List<Category> findByParent(String pid) throws SQLException;//通过父类查找子类
	public void add(Category category);//添加分类
	public List<Category> findParents() throws SQLException;//获取所有父类分类,不带子类
	public void edit(Category category);//编辑分类
	public int findChildrenCountByParent(String pid) throws SQLException;//查看父分类下子类的数目
	public void delete(String cid);//删除分类
}
