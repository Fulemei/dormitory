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
	<title>报修列表</title>
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
			<h2 style="color: green;">报修列表</h2>
			<form action ="findByWid" method="get">
				输入工号：<input type="text" name="wid"/>
				<input type="submit" value="查询" />
			</form>
			<ul class="nav nav-main list">
				<c:forEach items="${pb.beanList }" var="order">
					<c:choose>
						<c:when test="${order.status==0}">
							<div>
								<a href="adminLoadOrder?itemId=${order.oid}">
									<li>${order.ordertime}</li>
									<li>${order.ordermsg}</li>
								</a>
								<div class="button-group border-red float-right">
									<button type="button" class="button active rec-btn float-right" id="${order.oid}" onclick="attFob('${order.oid}')" > 待受理</button>
								</div>
							</div>
						</c:when>
						<c:when test="${order.status==1}">
							<div>
								<a href="adminLoadOrder?itemId=${order.oid}">
									<li>${order.ordertime}</li>
									<li>${order.ordermsg}</li>
								</a>
								<div class="button-group border-red float-right">
									<button type="button"  disabled class="button active rec-btn float-right" id="${order.oid}" onclick="attFob('${order.oid}')" > 已受理</button>
								</div>
							</div>
						</c:when>
						<c:when test="${order.status==2}">
							<div>
								<a href="adminLoadOrder?itemId=${order.oid}">
									<li>${order.ordertime}</li>
									<li>${order.ordermsg}</li>
								</a>
								<div class="button-group border-red float-right">
									<button type="button" disabled class="button active rec-btn float-right" id="${order.oid}" onclick="attFob('${order.oid}')" > 报修完成</button>
								</div>
							</div>
						</c:when>
						<c:when test="${order.status==3}">
							<div>
								<a href="LoadOrder?itemId=${order.oid}">
									<li>${order.ordertime}</li>
									<li>${order.ordermsg}</li>
								</a>
								<div class="button-group border-red float-right">
									<button type="button" disabled class="button active rec-btn float-right" id="${order.oid}"> 已取消</button>
								</div>
							</div>
						</c:when>
					</c:choose>
				</c:forEach>
			</ul>
				<%@include file="/jsps/pager/pager.jsp" %>
		</div>
	</div>
	<script>
		function attFob(id) {
			// 提交异步请求
			var postUrl = "DoneServlet" ;
			$.post(postUrl,{"oid":id},function(data){
				if(data.state){
					layer.msg("状态已变更");
					//按钮变化
					$("#"+id).attr("disabled", true);
					$("#"+id).html("已受理");
				}else{
					layer.msg(data.msg);
				}
			},"json");
		}
	</script>
</body>
</html>