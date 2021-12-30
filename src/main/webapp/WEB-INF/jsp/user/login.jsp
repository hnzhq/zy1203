<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--   引入标签库   -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 固定写法,获取应用上下文路径，可用于获取css的位置 -->
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<!-- 标记，整个页面的所有路径都基于这一个相对路径 -->
<base href="${ctx }/">
<meta charset="UTF-8">
<title>阿达e</title>
<link rel="stylesheet" type="text/css" href="assets/user/login.css">
<link rel="stylesheet" type="text/css" href="assets/user/bootstrap.min.css">
<!-- 引入jquery -->
<script type="text/javascript" language="javascript"
	src="assets/public/lib/jquery/jquery-3.6.0.min.js"></script>
<!-- 引入layer 用于弹出框-->
<script type="text/javascript" language="javascript"
	src="assets/public/lib/layer/layer.js"></script>
<script type="text/javascript">
	var error = "${error}";//页面错误信息
</script>
<script type="text/javascript" language="javascript"
	src="assets/user/login.js"></script>
</head>
<body style="background: url(assets/img/002.jpg) center center">
	<div style="height: 1px;"></div>
	<div class="login">
		<header>
			<h1>登陆</h1>
		</header>
		<div class="sr">
			<form action="user/login" method="post">
				<div class="name">
					<label> <i class="sublist-icon glyphicon glyphicon-user"></i>
					</label> <input type="text" name="username" placeholder="这里输入登录名" class="name_inp">
				</div>
				<div class="name">
					<label> <i class="sublist-icon glyphicon glyphicon-pencil"></i>
					</label> <input type="text" name="password" placeholder="这里输入登录密码" class="name_inp">
				</div>
				<button class="dl" type="submit">登陆</button>
				
			</form>
		</div>
	</div>

	<div
		style="text-align: center; margin: -150px 0; font: normal 14px/24px 'MicroSoft YaHei'; color: #ffffff">
		<p>适用浏览器：360、FireFox、Chrome、Opera、傲游、搜狗、世界之窗. 不支持Safari、IE8及以下浏览器。</p>
		<p>
			来源：<a href="http://www.baidu.com/" >特别提供</a>
		</p>
	</div>
</body>
</html>