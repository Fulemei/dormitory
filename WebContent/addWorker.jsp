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
							var postUrl = "WorkerServlet" ;
							var param = $("#form2").serialize();
							// 提交异步请求
							$.post(postUrl,param,function(data){
								if(data.state){
									//执行跳转
									layer.msg("新增成功~");
									window.location.href='AllWorkerServlet'
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
			<h2 class="title">工人信息填写</h2>
			<form id="form2" method="post" class="form form-auto">
					<input type="hidden" name="method" value="addWorker"/>
								<div class="form-group">
									<div class="label">
										<label for="wid"> 工号</label>
									</div>
									<div class="field">
										<input type="text" class="input" id="sid" name="wid" data-validate="required:必填" placeholder="工号（请勿重复）"/>
									</div>
								</div>
								<div class="form-group">
									<div class="label">
										<label for="username"> 姓名</label>
									</div>
									<div class="field">
										<input type="text" class="input" id="wname" name="wname" data-validate="required:必填" placeholder="正确的姓名"/>
									</div>
								</div>
								<div class="form-group">
									<div class="label">
										<label for="cid"> 工种</label>
									</div>
									<div class="field">
						    <select class="input" name="cid">
							<option value="1">水工</option>
							<option value="2">电工</option>
							<option value="3">网络工</option>
							<option value="4">主体</option>
							<option value="5">其它</option>
						</select>
					</div>
								</div>
									<div class="form-group">
									<div class="label">
										<label for="classes"> 邮箱</label>
									</div>
									<div class="field">
										<input type="text" class="input" id="wemail" name="wemail" data-validate="required:必填,email:请输入正确的邮箱" placeholder="邮箱地址"/>
									</div>
								</div>
								<div class="form-group">
									<div class="label">
										<label for="mobile"> 联系方式</label>
									</div>
									<div class="field">
										<input type="text" class="input" id="wnumber" name="wnumber" data-validate="required:必填,mobile:请输入正确的手机号" placeholder="手机号码"/>
									</div>
								</div>
								<div class="form-button ">
									<button class="button border-sub regBtn" name="reg" type="submit">提交</button>
								</div>
							</form>
		</div>
	</div>

</body>
</html>