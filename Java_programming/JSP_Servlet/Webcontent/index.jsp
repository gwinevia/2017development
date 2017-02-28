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
		<title>藤田研究室</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/main.css">
	</head>
	<body>

	<header>
		<h1><a href="./" id="logo">藤田研究室</a></h1>
	</header>

	<main>
	    <nav class="navi">
        	<ul id="dropmenu">
        		<li <%= hiddenLogin %>><a href="#">新規登録</a></li>
        		<li <%= hiddenLogout %>><a href="#">マイページ</a></li>
          		<li <%= hiddenLogin %>><a href="./login">ログイン</a></li>
          		<li <%= hiddenLogout %>><a href="./logout">ログアウト</a></li>
         	</ul>
       </nav>
	</main>
	
	<footer>
  		<nav>
    		<ul>
      			<li class="footernavi"><a href="http://alpha.sf.cs.it-chiba.ac.jp/mediawiki/">SFwiki</a></li>
      			<li class="footernavi"><a href="http://alpha.sf.cs.it-chiba.ac.jp/gitbucket/">GitBucket</a></li>  
    		</ul>
  		</nav>
	</footer>
	</body>
</html>