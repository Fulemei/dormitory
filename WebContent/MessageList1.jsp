<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	//判断用户是否登陆
	if(request.getSession().getAttribute("sessionUser")==null){
		//重定向到登录页
		response.sendRedirect("adminlogin.html");
	}
%>
<% 
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href=" <%=basePath%>"> 
	<title>公告列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="css/pintuer.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" />
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
	<script type="text/javascript" src="js/pintuer.js"></script>
	<script type="text/javascript" src="js/script.js"></script>
	<script type="text/javascript" src="js/layer.js"></script>
</head>
<body>
	<div class="container">
		<!-- 菜单 -->
	 	<%@include file="adminmenu.jsp" %>
		<div class="contain float-right">
		<div class="button-group border-blue float-right psw">
				<button class="button icon-key active" onclick="window.location.href='addMessage.jsp'">新增公告</button>
			</div>
			<h2 style="color: green;">公告列表</h2>
			<ul class="nav nav-main list">
				<c:forEach items="${pb.beanList }" var="msg">
							<div>
								<a href="LoadMessage?mid=${msg.mid}">
									<li>${msg.mtitle }</li>
									<li>${msg.create_date}</li>
								</a>
								<div class="button-group border-red float-right">
									<button type="button" class="button active rec-btn float-right" id="${msg.mid}" onclick="attFob('${msg.mid}')" > 删除</button>
								</div>
							</div>
				</c:forEach>
			</ul>
				<%@include file="/jsps/pager/pager.jsp" %>
		</div>
	</div>
	<script>
		function attFob(id) {
			// 提交异步请求
			var postUrl = "DeleteMessage" ;
			$.post(postUrl,{"mid":id},function(data){
				if(data.state){
					layer.msg("已删除");
					//按钮变化
					window.location.href='MessageList'
				}else{
					layer.msg(data.msg);
				}
			},"json");
		}
	</script>
</body>
</html>