package cn.itcast.dormitory.worker.service;

import java.sql.SQLException;

import cn.itcast.dormitory.pager.PageBean;
import cn.itcast.dormitory.worker.entity.Worker;

public interface WorkerService {
	public boolean delete(String wid);// 删除工人信息

	public boolean edit(Worker worker);// 编辑工人信息

	public PageBean<Worker> findByWid(String wid, int pc) throws SQLException;// 按工号查找

	public int findWorkerCountByCategory(String cid) throws SQLException;// 查找指定分类下工人的个数

	public PageBean<Worker> findByCategory(String cid, int pc) throws SQLException;// 按分类查询 01.管道 02.电工 03.暖气 04.网络
																					// 05.主体

	public PageBean<Worker> findAll(int pc) throws SQLException;// 查看工人名单

	public boolean add(Worker worker);// 添加工人

	public boolean checkWid(String wid);

	boolean ajaxValidateEmail(String email);

}
