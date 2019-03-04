package cn.itcast.dormitory.sendemail;

import javax.mail.PasswordAuthentication;

public class MailAuthenticator {
	 public static String USERNAME = "804718591@qq.com"; 
	 public static String PASSWORD = "fulemei1997"; 
	 public MailAuthenticator() { 
		 
	 }
	 protected PasswordAuthentication getPasswordAuthentication() { 
		 return new PasswordAuthentication(USERNAME, PASSWORD);
		 }

}
