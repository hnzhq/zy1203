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
<title>秦山商品批发详情表</title>
<link rel="stylesheet" type="text/css" href="assets/goods/list.css">
<!-- 引入jquery -->
<script type="text/javascript" language="javascript"
	src="assets/public/lib/jquery/jquery-3.6.0.min.js"></script>
<!-- 引入layer 用于弹出框-->
<script type="text/javascript" language="javascript"
	src="assets/public/lib/layer/layer.js"></script>
<script type="text/javascript" language="javascript" src="assets/public/lib/laydate/laydate.js"></script>
	

<script type="text/javascript">
	var ctx = "${ctx}";//客户端可看应用上下文
	var pageNo = "${pageNo}";//当前页
	var pages = "${pages}";//总页数
	var pageSize = "${pi.pageSize}";//页面大小
	var error = "${error}";//页面错误信息
</script>
<script type="text/javascript" language="javascript"
	src="assets/goods/list.js"></script>
</head>
<body>

	<div class="container">
		<h1>秦山商品批发详情表</h1>
		<!-- 创建表单区域，可以向后台提交数据 -->
		<div class="search-form">
			<fieldset>
				<legend>查询条件</legend>
				<form action="goods/list" method="post">
					<div>
						<label for="id">编号：</label><input type="text" name="id" id="id"
							placeholder="请输入编号" value="${gsb.id }">
					</div>
					<div>
						<label for="goodsId">商品号：</label><input type="text" name="goodsId"
							id="goodsId" placeholder="请输入商品号" value="${gsb.goodsId }">
					</div>
					<div>
						<label for="name">商品名：</label><input type="text" name="name"
							id="name" placeholder="请输入商品名" value="${gsb.name }">
					</div>


					<div>
						<label for="timeRange">出货时间：</label><input type="text"
							name="timeRange" id="timeRange" placeholder="请输入出货时间范围" autocomplete="off" value="${gsb.timeRange }">
					</div>
					<div>
						<label for="staff">推销员：</label><input type="text" name="staff"
							id="staff" placeholder="请输入推销员" value="${gsb.staff }">
					</div>
					<div>
						<label for="isarrival">是否到货：</label><input type="text" name="isarrival"
							id="isarrival" placeholder="请输入是否到货" >
					</div>

					<!-- 页码 -->
					<input type="hidden" name="pageNo" />
					<!-- 页面大小 -->
					<input type="hidden" name="pageSize" value="${pi.pageSize }" />
				</form>
			</fieldset>
		</div>


		<!-- 删除表单功能 -->
		<div class="delete-form">
			<form action="goods/delete" method="post">
				<!-- 批量删除操作 -->
				<input type="hidden" name="deleteIds" />
			</form>
		</div>




		<!-- 按钮 -->
		<div class="op">
		
			
			<ul>
			
				<li><a href="javascript:void(0)" class="add">添加</a></li>
				<li><a href="javascript:void(0)" class="edit">修改</a></li>
				<li><a href="javascript:void(0)" class="search">查询</a></li>
				<li><a href="javascript:void(0)" class="del">删除</a></li>
				<li><a href="javascript:void(0)" class="ajax-del">删除</a></li>

			</ul>
		</div>
		<div class="data">
			<table class="tab">
				<thead>
					<tr>
						<th><input type="checkbox" id="checkall"></th>
						<th>编号</th>
						<th>商品号</th>
						<th>商品名称</th>
						<th>单位</th>
						<th>进货数量</th>
						<th>销售数量</th>
						<th>单价</th>
						<th>产地</th>
						<th>推销员</th>
						<th>销售时间</th>
						<th>商品是否到货</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${goods1 }" var="goo">
						<tr>
							<td><input type="checkbox"></td>
							<td>${goo.id }</td>
							<td>${goo.goodsId }</td>
							<td>${goo.name }</td>
							<td>${goo.unit }</td>
							<td>${goo.inNum }</td>
							<td>${goo.outNum }</td>
							<td>${goo.price }</td>
							<td>${goo.place }</td>
							<td>${goo.staff }</td>
							<td>${goo.time }</td>
							<td>${goo.isArrival }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 分页 -->
	<div class="paginate">
		<span class="first"><a>首页</a></span> <span class="prev"><a>上一页</a></span>

		<!-- 数字页码 -->
		<ul>
			<c:forEach begin="${pi.navItemStart }" end="${pi.navItemEnd }"
				var="p">
				<c:choose>
					<c:when test="${p == pageNo }">
						<li class="current"><a href="javascript:void(0)">${p }</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:void(0)">${p }</a></li>
					</c:otherwise>
				</c:choose>

			</c:forEach>
		</ul>

		<span class="next"><a>下一页</a></span> <span class="last"><a>尾页</a></span>
		<select>
			<option value="8">8</option>
			<option value="12">12</option>
			<option value="17">17</option>
			<option value="20">20</option>
			<option value="30">30</option>

		</select>
	</div>
	</div>



</body>
</html>