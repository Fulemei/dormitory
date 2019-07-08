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
					$("#form1").validate({
						submitHandler: function(form) {
							// 执行提交
							var postUrl = "OrderServlet" ;
							var param = $("#form1").serialize();
							// 提交异步请求
							$.post(postUrl,param,function(data){
								if(data.state){
									//执行跳转
									layer.msg("报修成功~");
									window.location.href='OrderListServlet'
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
			<h2 class="title">报修信息填写</h2>
			<form id="form1" action="" method="post">
			<input type="hidden" name="method" value="admincreateOrder"/>
				<div class="form-group">
					<div class="label">
						<label for="name"> 姓名</label>
					</div>
					<div class="field">
						<input type="text" class="input" id="name" name="name" size="40" data-validate="required:必填" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="phone"> 手机号码</label>
					</div>
					<div class="field">
						<input type="text" class="input" id="tel_number" name="tel_number" size="30" data-validate="required:必填,mobile:请输入正确手机号码" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="address"> 地址</label>
					</div>
					<div class="field">
						<textarea rows="2" class="input" id="address" name="address" data-validate="required:必填"></textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label class="label"> 报修类型：</label>
					</div>
					<div class="field">
						<select class="input" name="cid">
							<option value="01">电路</option>
							<option value="02">水路</option>
							<option value="03">移动网络</option>
							<option value="04">电信网络</option>
							<option value="05">门窗</option>
							<option value="06">空调</option>
							<option value="07">电脑</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="ordermsg"> 问题描述</label>
					</div>
					<div class="field">
						<textarea rows="4" class="input" id="ordermsg" name="ordermsg" data-validate="required:必填"></textarea>
					</div>
				</div>
				<div class="form-button">
					<button class="button border-main" style="width:100%;" type="submit"> 提交</button>
				</div>
			</form>
		</div>
	</div>

</body>
</html>