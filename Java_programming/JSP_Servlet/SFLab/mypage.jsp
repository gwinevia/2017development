<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, java.util.*,java.io.*,java.sql.*,java.text.*" %>
<%
	Map<String, String> map = (Map<String, String>)session.getAttribute( "login_user" );
	String userEmail = (String)map.get( "Email" );;
	String userPassword = (String)map.get( "Password" );
	String id = (String)map.get("id");
	String userName = (String)map.get("name");
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
        		<li><a href="./logout">ログアウト</a></li>
        		<li><a href="./update">設定</a></li>
         	</ul>
       	</nav>
		<div>
  			<aside>
    			<section class="user_info">
      				<h1><%= userName %></h1>
    			</section>
    			<hr width=80%>
<%
	Connection conn = null;
	Connection conn2 = null;
	Map<String, String> map_db = (Map<String, String>)session.getAttribute( "db-info" );
	String url = (String)map_db.get("url");
	String user = (String)map_db.get("user");
	String password = (String)map_db.get("password");

    Class.forName("com.mysql.jdbc.Driver").newInstance();
    conn = DriverManager.getConnection(url, user, password);
    Statement stmt = conn.createStatement();
    
    String sql = "SELECT state FROM Log WHERE id=" + id + " order by time desc";
    ResultSet rs = stmt.executeQuery(sql);
    rs.next();
    
	if(rs.getString("state").equals("in")){
%>
				<form method="post" action="./mypage">
				    <input type="hidden" name="state" value="out">
        			<p class="mypage-b"><input type="button" name="state" value="在室" disabled id="mypage-submit">
        			<input type="submit" value="退室" id="mypage-submit"/></p>
    		 	</form>
<%
	}else {
%>
				<form method="post" action="./mypage">
					<input type="hidden" name="state" value="in">
    				<p class="mypage-b"><input type="submit" value="在室" id="mypage-submit"/>
    				<input type="button" name="state" value="退室" disabled id="mypage-submit"></p>
		 		</form>
<%	
	}
	
	rs.close();
	stmt.close();
    conn.close();
%>
		 	 </aside>
		</div>
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
	</body>
</html>