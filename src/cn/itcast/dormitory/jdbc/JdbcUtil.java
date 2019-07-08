package cn.itcast.dormitory.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

	// 定义连接数据库的四个属性
	// 定义用户名
//	private static final String username = "guigu";
	// 定义密码
//	private static final String password = "guigu";
	// 定义路径
//	private static final String url = "jdbc:oracle:thin:@192.168.118.5:1521:ORCL";
	// 定义驱动
//	private static final String driver = "oracle.jdbc.driver.OracleDriver";

	// 定义用户名
	private static String username;
	// 定义密码
	private static String password;
	// 定义路径
	private static String url;
	// 定义驱动
	private static String driver;

	static {
		try {
			Properties properties = new Properties();
			// 通过类加载器 把指定的文件通过流的形式加载进来
			InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("Jdbc.properties");

			properties.load(in);
			username = properties.getProperty("jdbc.username");
			password = properties.getProperty("jdbc.password");
			url = properties.getProperty("jdbc.url");
			driver = properties.getProperty("jdbc.driver");
			System.out.println(username);
			System.out.println(password);
			System.out.println(url);
			System.out.println(driver);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static Connection conn = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;

	/**
	 * 获取Connection连接
	 */
	public static void getConnection() {
		// 建立与数据局之间的连接

		try {
			// 加载驱动
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int addUpdDel(String sql) {
		int i = 0;
		try {
			ps = conn.prepareStatement(sql);
			i = ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("sql数据库增删改异常");
			e.printStackTrace();
		}

		return i;
	}

	public static ResultSet selectSql(String sql) {
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			conn.commit();
		} catch (SQLException e) {
			System.out.println("数据库查询异常");
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 统一关闭资源
	 * 
	 */
	public static void freeAll() {

		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("sql数据库关闭异常");
			e.printStackTrace();
		}

	}

}
