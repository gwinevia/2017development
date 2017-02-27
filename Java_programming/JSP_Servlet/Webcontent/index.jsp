<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map" %>
<%
	Map<String, String> map = (Map<String, String>)session.getAttribute( "login_user" );

	String userEmail = "";
	String userPassword = "";
	String hiddenLogin = "";
	String hiddenLogout = "";
	if ( null == map ) {
	// ログアウト中.
	hiddenLogout = "hidden";
	} else {
	// ログイン中.
	userEmail = (String)map.get( "Email" );
	userPassword = (String)map.get( "Password" );
	hiddenLogin = "hidden";
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>TestLogin</title>
	</head>
	<body>

	<header>
		<h1>TestLogin</h1>
		<p>トップページ</p>
	</header>

	<main>
		<div <%= hiddenLogin %>>
			<p><a href="./login">ログイン</a></p>
		</div>
		<div <%= hiddenLogout %>>
			<p>E-MAIL : <%= userEmail %></p>
			<p>PASSWORD : <%= userPassword %></p>
			<p><a href="./logout">ログアウト</a></p>
		</div>
	</main>
	</body>
</html>