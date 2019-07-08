<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
	//判断用户是否登陆
	if(request.getSession().getAttribute("sessionUser")==null){
		//重定向到登录页
		response.sendRedirect("index.html");
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
	<title>公告详情</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="css/pintuer.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
	<script type="text/javascript" src="js/pintuer.js"></script>
	<script type="text/javascript" src="js/script.js"></script>
	<script type="text/javascript" src="js/layer.js"></script>
	<script type="text/javascript">
		function cancelRepair(id) {
			// 提交异步请求
			var postUrl = "OrderCancel" ;
			$.post(postUrl,{"oid":id},function(data){
				if(data.state){
					layer.msg("取消成功");
					//按钮变化
					$("#btn").attr("disabled", true);
					$("#btn").html("已取消");
					location.reload();
				}else{
					layer.msg(data.msg);
				}
			},"json");
		}
		</script>
		<script type="text/javascript">
		 function plRepair(form) {
			// 执行提交
			var postUrl = "OrderServlet" ;
			var param = $("#form1").serialize();
			// 提交异步请求
			$.post(postUrl,param,function(data){
				if(data.state){
					//执行跳转
					layer.msg("评论成功~");
				}else{
					layer.msg(data.msg);
				}
			},"json");
		}
		</script>
</head>
<body>
	<div class="container">
		<!-- 菜单 -->
	 	<%@include file="menu.jsp" %>
		<div class="contain float-right">
			<div class="repIfo">
				<h2 class="title">公告详情</h2>
				<table class="table">
					<tr>
						<td>标题</td>
						<td>${message.mtitle}</td>
					</tr>
					<tr>
						<td>发布日期</td>
						<td>${message.create_date}</td>
					</tr>
					<tr>
						<td>内容</td>
						<td>${message.message}</td>
					</tr>
				</table>
			</div>
			<br>
			
		</div>
	</div>
</body>
</html>