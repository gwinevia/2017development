<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sample1_to.jsp</title>
</head>
<body>
<h2>sample1_to.js</h2>
<p>session ID:<%= session.getId() %></p>
<p>foo:<%= session.getAttribute("foo") %></p>
<p>hoge:<%= session.getAttribute("hoge") %></p>
<h2>request Object</h2>
<p>Referer:<%= request.getHeader("referer") %></p>
<p><a href = "sample1_from.jsp">sample1_from.jsp</a></p>
</body>
</html>