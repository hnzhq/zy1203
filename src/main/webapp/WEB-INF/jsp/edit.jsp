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
<title>修改商品数据</title>
<link rel="stylesheet" type="text/css" href="assets/goods/add.css">
<script type="text/javascript">
	var error = "${error }";
	//商品是否到货选中不变
	var isarrival = "${params['isarrival'][0]}";
</script>
<!-- 引入jquery -->
<script type="text/javascript" language="javascript"
	src="assets/public/lib/jquery/jquery-3.6.0.min.js"></script>
<!-- 引入layer 用于弹出框-->
<script type="text/javascript" language="javascript"
	src="assets/public/lib/layer/layer.js"></script>
<script type="text/javascript" language="javascript" src="assets/public/lib/laydate/laydate.js"></script>
	

<!-- 引入js -->
<script type="text/javascript" language="javascript"
	src="assets/goods/edit.js"></script>
</head>
<body>
	<h1>修改秦山商品信息</h1>
	<div class="container">
		<fieldset>
			<legend>修改一个商品</legend>
			<form action="goods/list" method="post">
			<button type="submit">返回表格</button>
			</form>
			<form class="goods-form" action="goods/edit" method="post" enctype="mulitipart/form-data">
				<div>
					<label for="id"> 编号：</label> <input type="text" name="id"
						id="id" readonly="readonly" value="${good.id }">
				</div>
				<div>
					<label for="goodsId">商品号：</label> <input type="text" name="goodsId"
						id="goodsId" placeholder="输入商品号" value="${good.goodsId}">
				</div>
				<div>
					<label for="name">商品名称：</label> <input type="text" name="name"
						id="name" placeholder="输入商品名称" value="${good.name}">
				</div>
				<div>
					<label for="unit">商品单位：</label> <input type="text" name="unit"
						id="unit" placeholder="件/套/个..." value="${good.unit}">
				</div>
				 <div>
			<label for="inNum">进货数量：</label>
			<input type="text" name="inNum" id="inNum" placeholder="整数" value="${good.inNum}">
			</div>
			<div>
			<label for="outNum">销售数量：</label>
			<input type="text" name="outNum" id="outNum" placeholder="整数" value="${good.outNum}">
			</div>
			<div>
			<label for="price">单价：</label>
			<input type="text" name="price" id="price" placeholder="可保留两位小数" value="${good.price}">
			</div> 

				<div>
					<label for="place">产地：</label> <input type="text" name="place"
						id="place" value="${good.place}">
				</div>
				<div>
					<label for="staff">推销员：</label> <input type="text" name="staff"
						id="staff" value="${good.staff}">
				</div>
				<div>
					<label for="time">销售时间：</label> <input type="text" name="time"
						id="time" value="${good.time}" autocomplete="off">
				</div>
				<div class="arrival-wrapper">
					<label>是否到货：</label>
					<div>
						<input type="radio" id="no" name="isarrival" value="否"
							checked="checked"><label for="no">否</label>
					</div>
					<div>
						<input type="radio" id="yes" name="isarrival" value="是"><label
							for="yes">是</label>
					</div>
				</div>

				<div>
					<label></label>
					<button type="submit">提交</button>
					<button type="reset">重置</button>
				</div>

			</form>
		</fieldset>


	</div>
</body>
</html>