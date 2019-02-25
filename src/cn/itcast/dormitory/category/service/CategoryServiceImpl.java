package cn.itcast.dormitory.category.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dormitory.category.dao.CategoryDao;
import cn.itcast.dormitory.category.dao.CategoryDaoImpl;
import cn.itcast.dormitory.category.entity.Category;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao cd = new CategoryDaoImpl();

	@Override
	public List<Category> findAll() throws SQLException {
		return cd.findAll();
	}

	@Override
	public List<Category> findByParent(String pid) throws SQLException {
		return cd.findByParent(pid);
	}

	@Override
	public void add(Category category) {
		cd.add(category);
		
	}

	@Override
	public List<Category> findParents() throws SQLException {
		return cd.findParents();
	}

	@Override
	public void edit(Category category) {
		cd.edit(category);
	}

	@Override
	public int findChildrenCountByParent(String pid) throws SQLException {
		return cd.findChildrenCountByParent(pid);
	}

	@Override
	public void delete(String cid) {
		cd.delete(cid);
	}


}
