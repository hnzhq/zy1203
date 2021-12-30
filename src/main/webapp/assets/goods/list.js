$(function() {
	//如果有错误就显示
	if (error) {
		layer.alert(error);
	}
	//时间范围
	laydate.render({
		elem: "#timeRange",
		range: true
	});
	//页面大小下拉框赋值固定
	$(".paginate>select").val(pageSize);



	//分页条相关事件处理
	$(".paginate>span").click(function() {
		var me = $(this);
		if (me.is(".first")) {//首页
			$(".search-form [name=pageNo]").val(1);
		} else if (me.is(".last")) {//尾页
			//总页数
			$(".search-form [name=pageNo]").val(parseInt(pages));
		} else if (me.is(".prev")) {//上一页
			//当前页-1
			pageNo = parseInt(pageNo) - 1;
			if (pageNo < 1) {
				pageNo = 1;
			}
			$(".search-form [name=pageNo]").val(pageNo);

		} else if (me.is(".next")) {//下一页
			//当前页+1
			pageNo = parseInt(pageNo) + 1;
			if (pageNo > +pages) {
				pageNo = pages;
			}
			$(".search-form [name=pageNo]").val(pageNo);
			console.log("aaaaaaa");

		}

		//提交表单
		$(".search-form form").submit();
	});


	//数字页码点击事件
	$(".paginate>ul>li").click(function() {
		var p = $(this).children("a").text();
		$(".search-form [name=pageNo]").val(p);
		//提交表单
		$(".search-form form").submit();
	});

	//页面大小下拉框改变事件
	$(".paginate select").change(function() {
		var ps = $(this).val();
		console.log(ps);
		$(".search-form form>[name=pageSize]").val(ps);
		//提交表单
		$(".search-form form").submit();
	});

	//表格全选与取消全选
	$("#checkall").click(function() {
		var checked = $(this).prop("checked");
		//让表格的选项与总选项一致
		$(".tab tr>td>:checkbox").prop("checked", checked);
	});

	//表单删除事件,删除按钮点击事件
	$(".del").click(function() {
		//:checked：表示被选中的复选框;找到复选框所在的td，再找下层td即id
		var checked = $(".tab tr>td>:checked").parent().next();
		//console.log(jqs.length);
		var ids = [];
		//方法一
		for (var i = 0; i < checked.length; i++) {
			//将被选中复选框的id放入ids里面
			ids.push(checked.eq(i).text());
		}
		//方法二,each表示对每一个数进行循环
		/*checked.each(function(){
			ids.push($(this).text());
		});*/
		
		//校验，给用户删除提示
		if (ids.length === 0) {
			//前端为空判断
			layer.alert("您未选中商品，请选中要删除的记录");
		} else {
			layer.confirm("是否确定删除数据", function(index) {

				//将选中的id传给表单,join将元素用”，“进行拼接
				$(".delete-form [name=deleteIds]").val(ids.join(","));
				//提交表单
				$(".delete-form form").submit();


				//关闭对话框,点确定时可以退出
				layer.close(index);
			});
		}

	});
	
	//点击ajax删除按键
	//表单删除事件,删除按钮点击事件
	$(".ajax-del").click(function() {
		//:checked：表示被选中的复选框;找到复选框所在的td，再找下层td即id
		var checked = $(".tab tr>td>:checked").parent().next();
		//console.log(jqs.length);
		var ids = [];
		//方法一
		for (var i = 0; i < checked.length; i++) {
			//将被选中复选框的id放入ids里面
			ids.push(checked.eq(i).text());
		}
		//方法二,each表示对每一个数进行循环
		/*checked.each(function(){
			ids.push($(this).text());
		});*/
		
		//校验，给用户删除提示
		if (ids.length === 0) {
			//前端为空判断
			layer.alert("您未选中商品，请选中要删除的记录");
		} else {
			layer.confirm("是否确定删除数据", function(index) {

				//ajax不需要表单提交
				//使用jQuery发送ajax请求
				$.ajax({
					
					//请求路径；使用应用上下文保证请求路径正确
					url:ctx + "/goods/ajaxDelete",
					//请求类型(使用post方法)
					method:"post",
					//数据
					data:{
						ids: ids
					},
					dataType:"json",
					traditional: true,
					//请求成功后的回调函数，servlet响应的json数据
					success: function(resp){
						/*console.log("请求成功");
						console.log(resp);*/
						if(resp.success){
							layer.msg("成功删除"+resp.rows+"条记录");
							
							checked.parent().remove();
							
						}else{
							layer.alert(error || "删除失败");
						}
					}
				});
				//关闭对话框,点确定时可以退出
				layer.close(index);
			});
		}

	});
	
	//添加商品事件
	$(".add").click(function(){
		//跳转到新页面
		location.href = ctx + "/goods/add";
	});
	
	//修改商品数据
	$(".edit").click(function(){
		var ids = [];
		var checked = $(".tab tr>td>:checked").parent().next();//类数组

		checked.each(function() {
			ids.push($(this).text());
		});

		//校验
		if (ids.length === 0) {
			layer.alert("请选中要修改的记录");
		} else if (ids.length > 1) {
			layer.alert("只能对一条记录进行修改");
		} else {
			var id = ids.pop();
			location.href = ctx + "/goods/edit?id=" + id;
		}
	});
	
	//查询数据事件
	$(".search").click(function(){
		$(".search-form form").submit();
	});

});