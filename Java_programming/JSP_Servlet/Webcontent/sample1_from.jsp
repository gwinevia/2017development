<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sample1_from.jsp</title>
</head>
<body>
<h2>sample1_from.jsp</h2>
<!-- セッションIDを取得 -->
<p>session ID:<%= session.getId() %></p>
<!-- セッションで値を設定 -->
<%
    session.setAttribute("foo","bar");
    session.setAttribute("hoge","fuga");
%>
<p><a href = "sample1_to.jsp">sample1_to.jsp</a></p>
</body>
</html>