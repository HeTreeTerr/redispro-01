<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>树形数据实验</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引入css样式 -->
<link type="text/css" rel="styleSheet"  href="${ctx}/ztree/css/demo.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/ztree/css/zTreeStyle/zTreeStyle.css">

</head>
<body>
<h1>最简单的树 -- 简单 JSON 数据</h1>
<h6>[ 文件路径: core/simpleData.html ]</h6>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</div>
</body>
<!-- 引入js文件 -->
<script type="text/javascript" src="${ctx}/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ztree/js/jquery.ztree.core.js"></script>
<!--  <script type="text/javascript" src="../../../js/jquery.ztree.excheck.js"></script>
	  <script type="text/javascript" src="../../../js/jquery.ztree.exedit.js"></script>-->
<script type="text/javascript">
	/* 配置 */
	var setting = {	
			
	};
	var zNodes =[
		{ name:"父节点1 - 展开", open:true,
			children: [
				{ name:"父节点11 - 折叠",
					children: [
						{ name:"叶子节点111"},
						{ name:"叶子节点112"},
						{ name:"叶子节点113"},
						{ name:"叶子节点114"}
					]},
				{ name:"父节点12 - 折叠",
					children: [
						{ name:"叶子节点121"},
						{ name:"叶子节点122"},
						{ name:"叶子节点123"},
						{ name:"叶子节点124"}
					]},
				{ name:"父节点13 - 没有子节点", isParent:true}
			]},
		{ name:"父节点2 - 折叠",
			children: [
				{ name:"父节点21 - 展开", open:true,
					children: [
						{ name:"叶子节点211"},
						{ name:"叶子节点212"},
						{ name:"叶子节点213"},
						{ name:"叶子节点214"}
					]},
				{ name:"父节点22 - 折叠",
					children: [
						{ name:"叶子节点221"},
						{ name:"叶子节点222"},
						{ name:"叶子节点223"},
						{ name:"叶子节点224"}
					]},
				{ name:"父节点23 - 折叠",
					children: [
						{ name:"叶子节点231"},
						{ name:"叶子节点232"},
						{ name:"叶子节点233"},
						{ name:"叶子节点234"}
					]}
			]},
		{ name:"父节点3 - 没有子节点", isParent:true}

	];
	
	function ajaxNodes(){
		//发送ajax请求
		$.ajax({
			   url:projectName+"/ajaxEmps",
			   data:"pn="+pn,
			   dataType:"json",
			   type:"GET",
			   success:function(result){
				   //控制台打印
				   //console.log(result);
				   
				   // 1.解析并显示员工数据
				    build_emps_table(result);
				   // 2.解析并显示分页信息
				    build_info(result);
				   // 3.解析显示分页条信息
				   build_page_nav(result);
				  
			   }
		   });
		
	}

	$(function(){
		var zNodes2 = ajaxNodes();
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
	
</script>
</html>