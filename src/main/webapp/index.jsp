<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--   引入标签库   -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 固定写法,获取应用上下文路径，可用于获取css的位置 -->
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<base href="${ctx }/">
<meta charset="UTF-8">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="assets/hello/hello.css">
</head>
<body style="background: url(assets/img/001.jpg) center center">
	<h1>秦山商品管理平台</h1>
	<div class="bod">
		<form action="goods/list" method="post">
			<div class="user">
				<label for="username">用户名：</label><input type="text" name="username"
					id="username" placeholder="请输入用户名">
			</div>
			<div class="pass">
				<label for="password">密码：</label><input type="password"
					name="password" id="password" placeholder="请输入密码">
			</div>
			<div class="sub">
				<button type="submit" class="but">登录</button>
			</div>

		</form>
	</div>



</body>
</html>