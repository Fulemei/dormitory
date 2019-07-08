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
<style type="text/css">
table.dataintable {
	margin-top:15px;
	border-collapse:collapse;
	border:1px solid #aaa;
	width:100%;
	}
 
table.dataintable th {
	vertical-align:baseline;
	padding:5px 15px 5px 6px;
	background-color:#e33;
	border:1px solid #e33;
	text-align:left;
	color:#fff;
	}
 
table.dataintable td {
	vertical-align:text-top;
	padding:6px 15px 6px 6px;
	border:1px solid #aaa;
	}
 
table.dataintable tr:nth-child(odd) {
	background-color:#F5F5F5;
}
 
table.dataintable tr:nth-child(even) {
	background-color:#fff;
}
</style>
	<div class="container">
		<!-- 菜单 -->
	 	<%@include file="adminmenu.jsp" %>
		<div class="contain float-right">
			<h2 style="color: green;">学生信息</h2>
			<form action ="UserListServlet" method="get">
				输入学号：<input type="text" name="sid"/>
			<input type="submit" value="查询" />
			</form>
			<ul class="nav nav-main list">
			<table class="dataintable">
			<tr>
  			<th>学号</th>
  			<th>姓名</th>
  			<th>班级</th>
  			<th>寝室号</th>
  			<th>联系方式</th>
  			<th>性别</th>
			</tr>
 
<c:forEach items="${pb.beanList}" var="stu">  
    <tr>  
        <td><b>${stu.getSid()}</b></td>  	
		<td>${stu.getName()}</td>  
		<td>${stu.getClasses()}</td>
        <td>${stu.getDormitory()}</td>  
        <td>${stu.getTel_number()}</td> 
        <c:if test="${stu.sex == 1 }"> 
        <td>男</td> 
        </c:if>
        <c:if test="${stu.sex == 0 }"> 
        <td>女</td> 
        </c:if>
    </tr>  
</c:forEach> 
</table>
			</ul>
				<%@include file="/jsps/pager/pager.jsp" %>
			
		</div>
	</div>
	
</body>
</html>


