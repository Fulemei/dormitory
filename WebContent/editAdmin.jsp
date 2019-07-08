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
	<title>管理员用户管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="css/pintuer.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
	<script type="text/javascript" src="js/pintuer.js"></script>
	<script type="text/javascript" src="js/script.js"></script>
	<script type="text/javascript" src="js/layer.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
					//校验注册
					$("#form2").validate({
						submitHandler: function(form) {
							// 执行提交
							var postUrl = "AdminServlet" ;
							var param = $("#form2").serialize();
							// 提交异步请求
							$.post(postUrl,param,function(data){
								if(data.state){
									//执行跳转
									layer.msg("修改成功~");
								}else{
									layer.msg(data.msg);
								}
							},"json");
						}
					});
				});
	</script>
</head>
<body>
	<div class="container">
		<!-- 菜单 -->
	 	<%@include file="adminmenu.jsp" %>
			<div class="contain float-right">
			<h2 style="color: green;">管理员信息修改</h2>
				<c:forEach items="${pb.beanList }" var="admin">
				<form id="form2" action="" method="post">
			<input type="hidden" name="method" value="updateAdmin"/>
				<div class="form-group">
					<div class="label">
						<label for="adminname"> 管理员:</label>
					</div>
					<div class="label">
					<input type="text" class="input" id="adminname" name="adminname" size="12" data-validate="required:必填" value="${admin.adminname}" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="adminid"> 管理员登录账号:</label>
					</div>
					<div class="label">
						<input type="text" class="input" id="adminid" name="adminid" size="12" data-validate="required:必填" value="${admin.adminid}" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="password"> 管理员登录密码</label>
					</div>
					<div class="field">
						<input type="text" class="input" id="password" name="password" size="30" data-validate="required:必填" value="${admin.password}" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="tel"> 手机号码</label>
					</div>
					<div class="field">
						<input type="text" class="input" id="tel" name="tel" size="30" data-validate="required:必填,mobile:请输入正确手机号码" value="${admin.tel}" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="remark"> 工种</label>
					</div>
						<div class="field">
						    <select class="input" name="remark" >
							<option value="0">普通管理员</option>
							<option value="1">超级管理员</option>
						</select>
					</div>
				</div>
				<div class="form-button">
					<button class="button border-main0" style="width:100%;" type="submit"> 修改</button>
				</div>
				
			</form>
				</c:forEach>
		</div>

</body>
</html>