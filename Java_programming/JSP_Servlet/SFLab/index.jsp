<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map, java.util.*,java.io.*,java.sql.*,java.text.*" %>
<%
	Map<String, String> map = (Map<String, String>)session.getAttribute( "login_user" );

	String userEmail = "";
	String userPassword = "";
	String hiddenLogin = "";
	String hiddenLogout = "";
	String id = "";
	String admin = "";
	
	if ( null == map ) {
		// ログアウト中.
		hiddenLogout = "hidden";
	} else {
		// ログイン中.
		userEmail = (String)map.get( "Email" );
		userPassword = (String)map.get( "Password" );
		id = (String)map.get("id");
		hiddenLogin = "hidden";
		String userName = (String)map.get("name");
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
        		<li <%= hiddenLogin %>><a href="./newuser">新規登録</a></li>
        		<li <%= hiddenLogout %>><a href="#">Menu</a>
        		<ul>
        			<li <%= hiddenLogout %>><a href="./mypage">マイページ</a></li>
        			<li <%= hiddenLogout %>><a href="./update">設定</a></li>
        			<% if(admin.equals("true")){ %>
        				<li <%= admin %>><a href="./member">メンバー管理</a></li>
	        		<%	} %>
        			<li <%= hiddenLogout %>><a href="./logout">ログアウト</a></li>
        		</ul>
        		</li>
          		<li <%= hiddenLogin %>><a href="./login">ログイン</a></li>
         	</ul>
       </nav>
       <table class="hometable">
		<tr>
<%
	Connection conn = null;
	Connection conn2 = null;
	String url = "jdbc:mysql://localhost/2017development";
	String user = "mmk";
	String password = "grqt58yj";

    Class.forName("com.mysql.jdbc.Driver").newInstance();
    conn = DriverManager.getConnection(url, user, password);
    conn2 = DriverManager.getConnection(url, user, password);
    Statement stmt = conn.createStatement();
    Statement stmt2 = conn.createStatement();
    
    String sql = "SELECT * FROM Test order by name";
    ResultSet rs = stmt.executeQuery(sql);
    
    
    while(rs.next()){
    	if(!rs.getString("name").equals("SFLab") && rs.getString("view").equals("ok")){
%>
			<td><%= rs.getString("name") %></td> 
			<%	
		    	String sql2 = "SELECT state FROM Log WHERE id=" + rs.getString("id") + " order by time desc";
		    	ResultSet rs2 = stmt2.executeQuery(sql2);
		    	String status  = "";
		    	while(rs2.next()){
		    		status = rs2.getString("state");
		    		break;
		    	}
		    	rs2.close();
		    	
		    	if(status.equals("in")){
		    		%>
		    		<td id="flag">在室</td>
		    		<td>退室</td>
		    		</tr>
		    		<%
		    	}else{
		    		%>
		    		<td>在室</td>
		    		<td id="flag">退室</td>
		    		</tr>
		    		<%		    		
		    	}
    	}
   }

    rs.close();
    stmt.close();
    stmt2.close();
    conn.close();
    conn2.close();
%>
	</table>
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