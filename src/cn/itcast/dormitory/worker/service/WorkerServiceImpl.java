package cn.itcast.dormitory.worker.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dormitory.worker.dao.WorkerDao;
import cn.itcast.dormitory.worker.dao.WorkerDaoImpl;
import cn.itcast.dormitory.worker.entity.Worker;

public class WorkerServiceImpl implements WorkerService{
	WorkerDao wd = new WorkerDaoImpl();
	@Override
	public void delete(String wid) {
		wd.delete(wid);
		
	}

	@Override
	public void edit(Worker worker) {
		wd.edit(worker);
		
	}

	@Override
	public List<Worker> findByWid(String wid) throws SQLException {
		return wd.findByWid(wid);
	}

	@Override
	public int findWorkerCountByCategory(String cid) throws SQLException {
		return wd.findWorkerCountByCategory(cid);
	}

	@Override
	public List<Worker> findByCategory(String cid) throws SQLException {
		return wd.findByCategory(cid);
	}

	@Override
	public List<Worker> findAll() throws SQLException {
		return wd.findAll();
	}

	@Override
	public void add(Worker worker) {
		wd.add(worker);
	}

}
