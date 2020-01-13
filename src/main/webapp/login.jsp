<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<%
    pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
</head>
<body>
	<form action="${APP_PATH}/login" method="post">
		用户名：<input type="text" name="username" /><br/>
		验证码：<input type="text" name="valcode" /><br/>
		密码：<input type="password" name="password" /><br/>
		<input type="submit" value="登录">
	</form>
</body>
</html>
