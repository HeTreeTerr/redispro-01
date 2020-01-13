<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="导入excel">
<title>导入excel</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
</head>
<body>
 <form enctype="multipart/form-data" id="batchUpload"  action="excel/import" method="post" class="form-horizontal">    
        <button class="btn btn-success btn-xs" id="uploadEventBtn" style="height:26px;"  type="button" >择文件</button>  
        <input type="file" name="file"  style="width:0px;height:0px;" id="uploadEventFile">  
        <input id="uploadEventPath"  disabled="disabled"  type="text" placeholder="请择excel表" style="border: 1px solid #e6e6e6; height: 26px;width: 200px;" /> 
        <br/>                                          
        <button type="submit" class="btn btn-success btn-sm" >submit上传</button>  
    </form>  
    <button type="button" class="btn btn-success btn-sm"  onclick="user.uploadBtn()" >ajax上传</button> 
</body>
<!-- 引入js文件 -->
<script type="text/javascript" src="${ctx}/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ztree/js/ajaxfileupload.js"></script>
<script type="text/javascript">
var User = function() {
    this.init = function() {
        //模拟上传excel  
        $("#uploadEventBtn").unbind("click").bind("click", function() {
            $("#uploadEventFile").click();
        });
        $("#uploadEventFile").bind("change", function() {
            $("#uploadEventPath").attr("value",    $("#uploadEventFile").val());
        });
    };
    //点击上传钮  
    this.uploadBtn = function() {
        var uploadEventFile = $("#uploadEventFile").val();
        if (uploadEventFile == '') {
            alert("请择excel,再上传");
        } else if (uploadEventFile.lastIndexOf(".xls") < 0) {//可判断以.xls和.xlsx结尾的excel  
            alert("只能上传Excel文件");
        } else {
            var url = "excel/import1";
            user.sendAjaxRequest(url);
        }
    };
    
    this.sendAjaxRequest = function(url) {
    	$.ajaxFileUpload({
                url: url, //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: 'uploadEventFile', //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                	alert(data.message);
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                	alert(data.message);
                }
            }
    	)};
};


var user;
$(function() {
    user = new User();
    user.init();
});
</script>
</html>