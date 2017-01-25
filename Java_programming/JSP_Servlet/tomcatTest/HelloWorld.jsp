<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String strServlet = (String) request.getAttribute("fromServlet");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head><title>JSP Test</title></head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<body>
		<form action="Sample.jsp" method="post">
		<table>
    		<tr>
        		<th colspan="2">名前</th>
    		</tr>
    		<tr>
        		<th>姓</th><td><input type="text" id="family" name="family"/></td>
    		</tr>
    		<tr>
        		<th>名</th><td><input type="text" id="name" name="name"/></td>
    		</tr>
		</table>
		<br/>
		<p><b>性別</b>
		<input type="radio" id="sex" name="sex" value="男" checked>男
		<input type="radio" id="sex" name="sex" value="女" >女
		</p>
		<br/>
		<b>年代</b><br/>
		<% String age[] = {"10歳未満","10代","20代","30代","40代以上"}; %>
		<select id="age_id" name="age_id">
		<%for(int i = 0; i < age.length ; i++){ %>
    	<option value="<%= i %>"><%= age[i] %></option>
		<%} %>
		</select>
		<br/>
		<br/>
		<input type="submit" id="送信" name="送信" value="送信" />
		</form>
	</body>
</html>