<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
	//判断用户是否登陆
	if(request.getSession().getAttribute("sessionUser")==null){
		//重定向到登录页
		response.sendRedirect("login.html");
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
	<title>我要报修</title>
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
					//校验登陆
					$("#form2").validate({
						submitHandler: function(form) {
							// 执行提交
							var postUrl = "AdminServlet" ;
							var param = $("#form2").serialize();
							// 提交异步请求
							$.post(postUrl,param,function(data){
								if(data.state){
									//执行跳转
									layer.msg("新增成功~");
									window.location.href='AdminList'
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
			<h2 class="title">新增管理员</h2>
							<form id="form2" action="" method="post">
			<input type="hidden" name="method" value="addAdmin"/>
				<div class="form-group">
					<div class="label">
						<label for="adminname"> 管理员：</label>
					</div>
					<div class="label">
					<input type="text" class="input" id="adminname" name="adminname" size="12" data-validate="required:必填" placeholder="管理员" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="adminid"> 管理员登录账号：</label>
					</div>
					<div class="label">
						<input type="text" class="input" id="adminid" name="adminid" size="12" data-validate="required:必填" placeholder="登录账号" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="password"> 管理员登录密码：</label>
					</div>
					<div class="field">
						<input type="text" class="input" id="password" name="password" size="30" data-validate="required:必填" placeholder="登录密码" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="tel"> 手机号码：</label>
					</div>
					<div class="field">
						<input type="text" class="input" id="tel" name="tel" size="30" data-validate="required:必填,mobile:请输入正确手机号码" placeholder="手机号码" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="remark"> 管理员等级：</label>
					</div>
						<div class="field">
						    <select class="input" name="remark" >
							<option value="0">普通管理员</option>
							<option value="1">超级管理员</option>
						</select>
					</div>
				</div>
				<div class="form-button">
					<button class="button border-main0" style="width:100%;" type="submit"> 提交</button>
				</div>
				
			</form>
		</div>
	</div>

</body>
</html>