$(function(){
	if(error){
		layer.alert(error);
	}
	
	//根据后台的实现选中
	if (isarrival) {
		$(".goods-form .arrival-wrapper :radio[value=" + isarrival + "]").prop("checked", true);
	}


	laydate.render({
		elem: "#time"
	});
});