package cn.itcast.dormitory.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.commons.CommonUtils;
import cn.itcast.dormitory.user.entity.User;
import cn.itcast.dormitory.user.service.UserService;
import cn.itcast.dormitory.user.service.UserServiceImpl;
import cn.itcast.servlet.BaseServlet;
import net.sf.json.JSONObject;

/**
 * 用户模块WEB层
 * 
 * @author qdmmy6
 *
 */
public class UserServlet extends BaseServlet {
	private UserService userService = new UserServiceImpl();

	/**
	 * ajax用户名是否注册校验
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateSid(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取用户名
		 */
		String loginname = req.getParameter("loginname");
		/*
		 * 2. 通过service得到校验结果
		 */
		boolean b = userService.ajaxValidateSid(loginname);
		/*
		 * 3. 发给客户端
		 */
		resp.getWriter().print(b);
		return null;
	}

	/**
	 * ajax Email是否注册校验
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateEmail(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取Email
		 */
		String email = req.getParameter("email");
		/*
		 * 2. 通过service得到校验结果
		 */
		boolean b = userService.ajaxValidateEmail(email);
		/*
		 * 3. 发给客户端
		 */
		resp.getWriter().print(b);
		return null;
	}

	/**
	 * ajax验证码是否正确校验
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateVerifyCode(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取输入框中的验证码
		 */
		String verifyCode = req.getParameter("verifyCode");
		/*
		 * 2. 获取图片上真实的校验码
		 */
		String vcode = (String) req.getSession().getAttribute("vCode");
		/*
		 * 3. 进行忽略大小写比较，得到结果
		 */
		boolean b = verifyCode.equalsIgnoreCase(vcode);
		/*
		 * 4. 发送给客户端
		 */
		resp.getWriter().print(b);
		return null;
	}

	/**
	 * 注册功能
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();
		/*
		 * 1. 封装表单数据到User对象
		 */
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		/*
		 * 2. 校验之, 如果校验失败，保存错误信息，返回到regist.jsp显示
		 */
		Map<String, String> errors = validateRegist(formUser, req.getSession());
		String msg = errors.get("msg");
		if (errors.size() > 0) {
			res.put("state", false);
			res.put("msg", msg);
			JSONObject jsonObject = JSONObject.fromObject(res);
			out.print(jsonObject.toString());
			out.flush();
			out.close();
		}
		/*
		 * 3. 使用service完成业务
		 */
		userService.register(formUser);
		/*
		 * ` 4. 保存成功信息，转发到msg.jsp显示！
		 */
		res.put("state", true);
		res.put("msg", "注册成功！");
		JSONObject jsonObject = JSONObject.fromObject(res);
		System.out.println("返回" + jsonObject.toString());
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	/*
	 * 注册校验 对表单的字段进行逐个校验，如果有错误，使用当前字段名称为key，错误信息为value，保存到map中 返回map
	 */
	private Map<String, String> validateRegist(User formUser, HttpSession session) {
		Map<String, String> errors = new HashMap<String, String>();
		/*
		 * 1. 校验登录名
		 */
		String sid = formUser.getSid();
		if (sid == null || sid.trim().isEmpty()) {
			errors.put("msg", "用户名不能为空！");
		} else if (sid.length() < 3 || sid.length() > 20) {
			errors.put("msg", "用户名长度必须在3~20之间！");
		} else if (!userService.ajaxValidateSid(sid)) {
			errors.put("msg", "用户名已被注册！");
		}

		/*
		 * 2. 校验登录密码
		 */
		String password = formUser.getPassword();
		if (password == null || password.trim().isEmpty()) {
			errors.put("msg", "密码不能为空！");
		} else if (password.length() < 3 || password.length() > 20) {
			errors.put("msg", "密码长度必须在3~20之间！");
		}

		/*
		 * 3. 确认密码校验
		 */
		String repass = formUser.getRepass();
		if (repass == null || repass.trim().isEmpty()) {
			errors.put("msg", "确认密码不能为空！");
		} else if (!repass.equals(repass)) {
			errors.put("msg", "两次输入不一致！");
		}

		return errors;
	}

	/**
	 * 修改信息
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();
		/*
		 * 1. 封装表单数据到user
		 */
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		User owner = (User) req.getSession().getAttribute("sessionUser");
		formUser.setSid(owner.getSid());
		/*
		 * 2. 校验
		 */
		String errors = validateUpdate(formUser, req.getSession());
		if (null != errors && !"".equals(errors)) {
			res.put("state", false);
			res.put("msg", errors);
			JSONObject jsonObject = JSONObject.fromObject(res);
			System.out.println("返回" + jsonObject.toString());
			out.print(jsonObject.toString());
			out.flush();
			out.close();
		}

		/*
		 * 3. 调用userService#login()方法
		 */
		boolean flag = userService.update(formUser);
		System.out.println("flag" + flag);
		/*
		 * 4. 开始判断
		 */
		if (!flag) {
			res.put("state", false);
			res.put("msg", "重新确认修改信息是否有误！");
		} else {
			res.put("state", true);
		}
		JSONObject jsonObject = JSONObject.fromObject(res);
		System.out.println("返回" + jsonObject.toString());
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	/**
	 * 修改密码
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();
		/*
		 * 1. 封装表单数据到user中 2. 从session中获取uid 3. 使用uid和表单中的oldPass和newPass来调用service方法 >
		 * 如果出现异常，保存异常信息到request中，转发到pwd.jsp 4. 保存成功信息到rquest中 5. 转发到msg.jsp
		 */
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		User user = (User) req.getSession().getAttribute("sessionUser");
		System.out.println(user.getSid() + "-------" + formUser.getNewPass());
		boolean flag = userService.updatePassword(user.getSid(), formUser.getNewPass(), formUser.getPassword());
		if (flag) {
			res.put("msg", "修改密码成功");
			res.put("state", true);
		} else {
			res.put("msg", "旧密码错误");// 保存异常信息到request
			res.put("state", false);// 为了回显
		}
		JSONObject jsonObject = JSONObject.fromObject(res);
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	/**
	 * 退出功能
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String quit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		return "r:/jsps/user/login.jsp";
	}

	/**
	 * 登录功能
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		/*
		 * 1. 封装表单数据到User 2. 校验表单数据 3. 使用service查询，得到User 4. 查看用户是否存在，如果不存在： *
		 * 保存错误信息：用户名或密码错误 * 保存用户数据：为了回显 * 转发到login.jsp 5. 如果存在，查看状态，如果状态为false： *
		 * 保存错误信息：您没有激活 * 保存表单数据：为了回显 * 转发到login.jsp 6. 登录成功： * 保存当前查询出的user到session中 *
		 * 保存当前用户的名称到cookie中，注意中文需要编码处理。
		 */
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		Map<String, Object> res = new HashMap<String, Object>();
		/*
		 * 1. 封装表单数据到user
		 */
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		/*
		 * 2. 校验
		 */
		String errors = validateLogin(formUser, req.getSession());
		if (null != errors && !"".equals(errors)) {
			res.put("state", false);
			res.put("msg", errors);
			JSONObject jsonObject = JSONObject.fromObject(res);
			System.out.println("返回" + jsonObject.toString());
			out.print(jsonObject.toString());
			out.flush();
			out.close();
		}

		/*
		 * 3. 调用userService#login()方法
		 */

		boolean flag = userService.login(formUser.getSid(), formUser.getPassword());
		System.out.println("flag" + flag);
		/*
		 * 4. 开始判断
		 */
		if (!flag) {
			res.put("state", false);
			res.put("msg", "用户名或密码错误！");
		} else {
			UserService userservice = new UserServiceImpl();
			List<User> list = userservice.findBySid(formUser.getSid());
			formUser.setDormitory(list.get(0).getDormitory());
			formUser.setName(list.get(0).getName());
			formUser.setClasses(list.get(0).getClasses());
			formUser.setTel_number(list.get(0).getTel_number());
			// 保存用户到session
			req.getSession().setAttribute("sessionUser", formUser);
			// 获取用户名保存到cookie中
			String sid = formUser.getSid();
			sid = URLEncoder.encode(sid, "utf-8");
			Cookie cookie = new Cookie("sid", sid);
			cookie.setMaxAge(60 * 60 * 24 * 10);// 保存10天
			resp.addCookie(cookie);
			res.put("state", true);
		}
		JSONObject jsonObject = JSONObject.fromObject(res);
		System.out.println("返回" + jsonObject.toString());
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	/*
	 * 登录校验方法，内容等你自己来完成
	 */
	private String validateLogin(User formUser, HttpSession session) {
		String error1 = "";
		String error2 = "";
		/*
		 * 1. 校验登录名
		 */
		String sid = formUser.getSid();
		if (sid == null || sid.trim().isEmpty()) {
			error1 = "用户名不能为空！";
		} else if (sid.length() < 3 || sid.length() > 20) {
			error1 = "用户名长度必须在3~20之间！";
		}
		StringBuffer errors1 = new StringBuffer(error1);
		/*
		 * 2. 校验登录密码
		 */
		String password = formUser.getPassword();
		if (password == null || password.trim().isEmpty()) {
			error2 = "密码不能为空！";
		} else if (password.length() < 3 || password.length() > 20) {
			error2 = "密码长度必须在3~20之间！";
		}
		String errors = errors1.append(error2).toString();
		return errors;
	}

	/*
	 * 修改信息校验方法
	 */
	private String validateUpdate(User formUser, HttpSession session) {
		String error1 = "";
		String error2 = "";
		/*
		 * 1. 校验登录名
		 */
		String tel_number = formUser.getTel_number();
		if (tel_number == null || tel_number.trim().isEmpty()) {
			error1 = "手机号不能为空！";
		} else if (tel_number.length() < 11) {
			error1 = "请输入正确的手机号!";
		}
		StringBuffer errors1 = new StringBuffer(error1);
		/*
		 * 2. 校验登录密码
		 */
		String dormitory = formUser.getDormitory();
		if (dormitory == null || dormitory.trim().isEmpty()) {
			error2 = "寝室信息不能为空！";
		} else if (dormitory.length() < 3 || dormitory.length() > 20) {
			error2 = "寝室信息格式不对！";
		}
		String errors = errors1.append(error2).toString();
		return errors;
	}
}
