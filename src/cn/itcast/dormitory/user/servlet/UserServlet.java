package cn.itcast.dormitory.user.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.dormitory.user.dao.UserDao;
import cn.itcast.dormitory.user.dao.UserDaoImpl;
import cn.itcast.servlet.BaseServlet;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	   public String login(HttpServletRequest request, HttpServletResponse response)
	    		 throws ServletException,IOException, SQLException{
		    String sid = request.getParameter("sid");
	        String password = request.getParameter("password");
	        
	        UserDao ud = new UserDaoImpl();
	        if(!ud.login(sid, password)) {
	        	    request.setAttribute("msg", "账号或密码错误!");
	        	    request.setAttribute("errors", errors);
	    			return "f:/jsps/user/login.jsp";
	    		}

	    		}else{
	    			if(user.getDormitory()==null){
	    			   req.setAttribute("msg", "您还没有完善信息！");
	    			   req.setAttribute("user", formUser);
	   				   return "f:/jsps/user/udm.jsp";	
	    		   }
					
	    			req.getSession().setAttribute("sessionUser", user);
	    			String sid = user.getSid();
	    			Cookie cookie = new Cookie("sid",sid);
	    			cookie.setMaxAge(10*60*60*24);
	    			resp.addCookie(cookie);
	    		    return "r:/index.jsp";
	    		}
	     } 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String password = request.getParameter("password");
        
        UserDao ud = new UserDaoImpl();
        if(ud.login(sid, password)) {
        	request.setAttribute("message", "欢迎用户");
        	request.getRequestDispatcher("/success.jsp").forward(request, response);
        }else {
        	response.sendRedirect("/index.jsp");
        }
	}

}
