package cn.itcast.dormitory.message;

import java.sql.SQLException;

import cn.itcast.dormitory.pager.PageBean;

public class MessageService {
	MessageDao md = new MessageDao();

	public boolean addMessage(Message m) {
		return md.add(m);
	}

	public boolean delete(String mid) {
		return md.delete(mid);
	}

	public PageBean<Message> findAll(int pc) throws SQLException {
		return md.findAll(pc);
	}

	public Message findByMid(String mid) throws SQLException {
		return md.findByMid(mid);
	}
}
