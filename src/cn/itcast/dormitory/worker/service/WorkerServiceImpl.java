package cn.itcast.dormitory.worker.service;

import java.sql.SQLException;

import cn.itcast.dormitory.pager.PageBean;
import cn.itcast.dormitory.worker.dao.WorkerDao;
import cn.itcast.dormitory.worker.dao.WorkerDaoImpl;
import cn.itcast.dormitory.worker.entity.Worker;

public class WorkerServiceImpl implements WorkerService {
	WorkerDao wd = new WorkerDaoImpl();

	@Override
	public boolean delete(String wid) {
		return wd.delete(wid);

	}

	@Override
	public boolean edit(Worker worker) {
		return wd.edit(worker);

	}

	@Override
	public PageBean<Worker> findByWid(String wid, int pc) throws SQLException {
		return wd.findByWid(wid, pc);
	}

	@Override
	public int findWorkerCountByCategory(String cid) throws SQLException {
		return wd.findWorkerCountByCategory(cid);
	}

	@Override
	public PageBean<Worker> findByCategory(String cid, int pc) throws SQLException {
		return wd.findByCategory(cid, pc);
	}

	@Override
	public PageBean<Worker> findAll(int pc) throws SQLException {
		return wd.findAll(pc);
	}

	@Override
	public boolean add(Worker worker) {
		return wd.add(worker);
	}

	@Override
	public boolean checkWid(String wid) {
		return wd.checkWid(wid);
	}

	@Override
	public boolean ajaxValidateEmail(String email) {
		return wd.ajaxValidateEmail(email);
	}

}
