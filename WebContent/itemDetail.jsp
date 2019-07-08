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
	<title>报修详情</title>
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
				<h2 class="title">报修信息</h2>
				<table class="table">
					<tr>
						<td>姓名</td>
						<td>${order.owner.name}</td>
					</tr>
					<tr>
						<td>手机号码</td>
						<td>${order.owner.tel_number}</td>
					</tr>
					<tr>
						<td>地址</td>
						<td>${order.address}</td>
					</tr>
					<tr>
						<td>报修类型</td>
						<td>${order.cid}</td>
					</tr>
					<tr>
						<td>问题描述</td>
		            				<td>${order.ordermsg}</td>
					</tr>
				</table>
			</div>
			<br>
			<div class="ItemLogList">
				<h2 class="title">报修日志</h2>
				<ul class="list-unstyle list-striped">	 
						<table class="table">
					<tr>
						<td>订单完成时间</td>
						<td>${order.endtime}</td>
					</tr>
					<tr>
						<td>维修工人姓名</td>
						<td>${order.worker.wname}</td>
					</tr>
					<tr>
						<td>维修工人联系方式</td>
						<td>${order.worker.wnumber}</td>
					</tr>
					<c:if test="${order.zt==1}">
					<tr>
					    <td>维修评价等级</td>
					    <td>${order.dj}</td>
					</tr>
					<tr>
						<td>维修评价</td>
						<td>${order.pl}</td>
					</tr>
					</c:if>
				</table>
				</ul>
			</div>
			<c:if test="${order.zt==0}">
				<c:choose>
					<c:when test="${order.status==0}">
					    <div class="form-button">
						<button class="button" id="btn" style="width:100%;background-color:#E74C3C" type="submit" onClick="cancelRepair('${order.oid}');"> 取消订单</button>
						</div>
					</c:when>
					<c:when test="${order.status==1}">
					</c:when>
					<c:when test="${order.status==2}">
					<form id="form1" action="" method="post">
					<input type="hidden" name="method" value="pingjia"/>
					<div class="form-group">
					<div class="label">
						<label class="label"> 维修评价等级：</label>
					</div>
					<div class="field">
						<select class="input" name="dj">
							<option value="5">5分</option>
							<option value="4">4分</option>
							<option value="3">3分</option>
							<option value="2">2分</option>
							<option value="1">1分</option>
						</select>
					</div>
				</div>
					<div class="form-group">
					<div class="label">
						<label for="ordermsg"> 订单评价</label>
					</div>
					<div class="field">
						<textarea rows="4" class="input" id="pl" name="pl" data-validate="required:必填"></textarea>
					</div>
				</div>
				<div class="form-button">
					<button class="button" id="btn" style="width:100%;background-color:#E74C3C" type="submit" onClick="plRepair('#form1');"> 提交</button>
				</div>
			 	</form>
					</c:when>
					<c:when test="${order.status==3}">
					<div class="form-button">
					<button class="button" id="btn" disabled style="width:100%;background-color:#E74C3C" type="submit" onClick=""> 已取消</button>
					</div>
					</c:when>
					</c:choose>
					</c:if>
		</div>
	</div>
</body>
</html>