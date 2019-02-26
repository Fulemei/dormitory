package cn.itcast.dormitory.worker.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dormitory.worker.entity.Worker;


public interface WorkerDao {
	public void delete(String wid);//删除工人信息
	public void edit(Worker worker);//编辑工人信息
	public List<Worker> findByWid(String wid) throws SQLException;//按工号查找
	public int findWorkerCountByCategory(String cid) throws SQLException;//查找指定分类下工人的个数
	public List<Worker> findByCategory(String cid) throws SQLException;//按分类查询  01.管道  02.电工  03.暖气  04.网络  05.主体
	public List<Worker> findAll() throws SQLException;//查看工人名单
	public void add(Worker worker);//添加工人
}
