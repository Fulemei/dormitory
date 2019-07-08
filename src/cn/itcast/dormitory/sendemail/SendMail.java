package cn.itcast.dormitory.sendemail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cn.itcast.dormitory.order.entity.Order;

public class SendMail {
	// 发件人地址
	public static String senderAddress = "13314903613@163.com";
	// 收件人地址
	public static String recipientAddress = "804718591@qq.com";
	// 发件人账户名
	public static String senderAccount = "13314903613@163.com";
	// 发件人账户密码
	public static String senderPassword = "123qweasdzxc";

	public void sendemail(String recipientAddress, String stats, Order order) throws Exception {
		// 1、连接邮件服务器的参数配置
		Properties props = new Properties();
		// 设置用户的认证方式
		props.setProperty("mail.smtp.auth", "true");
		// 设置传输协议
		props.setProperty("mail.transport.protocol", "smtp");
		// 设置发件人的SMTP服务器地址
		props.setProperty("mail.smtp.host", "smtp.163.com");
		// 2、创建定义整个应用程序所需的环境信息的 Session 对象
		Session session = Session.getInstance(props);
		// 设置调试信息在控制台打印出来
		session.setDebug(true);
		// 3、创建邮件的实例对象
		Message msg = getMimeMessage(session, stats, order);
		// 4、根据session对象获取邮件传输对象Transport
		Transport transport = session.getTransport();
		// 设置发件人的账户名和密码
		transport.connect(senderAccount, senderPassword);
		// 发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
		transport.sendMessage(msg, msg.getAllRecipients());

		// 如果只想发送给指定的人，可以如下写法
		// transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});

		// 5、关闭邮件连接
		transport.close();
		System.out.println("发送成功");
	}

	public static void main(String[] args) throws Exception {

	}

	/**
	 * 获得创建一封邮件的实例对象
	 * 
	 * @param session
	 * @return
	 * @throws MessagingException
	 * @throws AddressException
	 */
	public static MimeMessage getMimeMessage(Session session, String stats, Order order) throws Exception {
		// 创建一封邮件的实例对象
		MimeMessage msg = new MimeMessage(session);
		// 设置发件人地址
		msg.setFrom(new InternetAddress(senderAddress));
		/**
		 * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行 MimeMessage.RecipientType.TO:发送
		 * MimeMessage.RecipientType.CC：抄送 MimeMessage.RecipientType.BCC：密送
		 */
		msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipientAddress));
		// 设置邮件主题
		if (stats.equals("取消")) {
			msg.setSubject("您有一单报修已取消", "UTF-8");
			// 设置邮件正文
			msg.setContent("订单号：" + order.getOid() + ", 订单时间" + order.getOrdertime() + ", 订单地址" + order.getAddress()
					+ ", 订单描述：" + order.getOrdermsg() + ", 报修人联系方式" + order.getOwner().getTel_number() + "此订单已取消",
					"text/html;charset=UTF-8");
			// 设置邮件的发送时间,默认立即发送
			msg.setSentDate(new Date());
		} else if (stats.equals("完成")) {
			msg.setSubject("订单完成", "UTF-8");
			// 设置邮件正文
			msg.setContent(
					"订单号：" + order.getOid() + ", 订单时间" + order.getOrdertime() + ", 订单地址" + order.getAddress() + ", 订单描述"
							+ order.getOrdermsg() + ", 报修人联系方式" + order.getOwner().getTel_number() + "该订单已完成",
					"text/html;charset=UTF-8");
			// 设置邮件的发送时间,默认立即发送
			msg.setSentDate(new Date());
		} else {
			msg.setSubject("您有新的报修", "UTF-8");
			// 设置邮件正文
			msg.setContent("订单号：" + order.getOid() + ", 订单时间:" + order.getOrdertime() + ", 订单地址:" + order.getAddress()
					+ ", 订单描述：" + order.getOrdermsg() + ", 报修人联系方式：" + order.getOwner().getTel_number() + "请尽快维修此订单",
					"text/html;charset=UTF-8");
			// 设置邮件的发送时间,默认立即发送
			msg.setSentDate(new Date());
		}

		return msg;
	}

}