<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, java.util.*,java.io.*,java.sql.*,java.text.*" %>
<%
	Map<String, String> map = (Map<String, String>)session.getAttribute( "login_user" );
	String userEmail = "";
	String userPassword = "";
	String id = "";
	String userName = "";
	String admin = "";
	String image = "";
	String hiddenLogin = "";
	String hiddenLogout = "";
	
	if ( null == map ) {
		// ログアウト中.
		hiddenLogout = "hidden";
	} else {
		// ログイン中.
		userEmail = (String)map.get( "Email" );
		userPassword = (String)map.get( "Password" );
		id = (String)map.get("id");
		hiddenLogin = "hidden";
		userName = (String)map.get("name");
		image = "./uploaded/" + (String)map.get("image");
		
		if(userName.equals("SFLab")){
			admin = "true";
		}
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
        		<li><a href="#">Menu</a>
        		<ul>
        			<li <%= hiddenLogout %>><a href="./mypage">マイページ</a></li>
        			<li <%= hiddenLogout %>><a href="./update">設定</a></li>
        			<li><a href="./log">作業ログ</a></li>
        			<% if(admin.equals("true")){ %>
        				<li <%= admin %>><a href="./member">メンバー管理</a></li>
	        		<%	} %>
	        		<li <%= hiddenLogin %>><a href="./newuser">新規登録</a></li>
	        		<li <%= hiddenLogin %>><a href="./login">ログイン</a></li>
        			<li <%= hiddenLogout %>><a href="./logout">ログアウト</a></li>
        		</ul>
        		</li>
         	</ul>
       </nav>
		<div>
<%
	if(hiddenLogin.equals("hidden") && !admin.equals("true")){
%>
  			<aside>
    			<section class="user_info">
    				<img src="<%= image %>">
      				<h1><%= userName %></h1>
    			</section>
    			<hr width=80%>
<%

		Connection conn = null;
		Connection conn2 = null;
		String url = "jdbc:mysql://localhost/sflab";
		String user = "mmk";
		String password = "grqt58yj";

    	Class.forName("com.mysql.jdbc.Driver").newInstance();
    	conn = DriverManager.getConnection(url, user, password);
    	Statement stmt = conn.createStatement();
    	conn2 = DriverManager.getConnection(url, user, password);
    	Statement stmt2 = conn2.createStatement();
 
    	String sql2 = "";
    	ResultSet rs2;

    	String sql = "SELECT * FROM Tweet order by time desc";
		ResultSet rs = stmt.executeQuery(sql);
		
		int len = 0;
		String time  ="";
		
		rs.last();
		int number_of_row = rs.getRow();
		rs.beforeFirst();

%>
			<form method="post" action="./log">
				<textarea name="tweet" class="textarea" col="30" rows="5" placeholder="例)ここはラボ"></textarea>
				<p class="mypage-b"><input type="submit" value="送信" id="mypage-submit"/></p>
			</form>
		 	</aside>
		</div>
		
		<aside class="Logform">
			<h3>作業ログ(<%= number_of_row %>)</h3>
		    <hr width="80%" color="#ccc" align="left">
			<ol class="microposts">
<%
		while(rs.next()){
			time = rs.getString("time");
			len = time.length();
			time = time.substring(0,len-2);
			sql2 = "SELECT * FROM Member where id=" +  rs.getString("id");
			rs2 = stmt2.executeQuery(sql2);
			rs2.next();
			
%>
			<li>
			<span class="image"><img src="./uploaded/<%= rs2.getString("image") %>" width="40px" height="40px"></span>
			<span class="user"><%= rs2.getString("name") %></span>
  			<span class="content"><%= rs.getString("tweet") %></span>
  			<span class="timestamp"><%= time %></span>
			</li>
<%		
		}
	}
%>
			</ol>
		</aside>
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