<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, java.util.*,java.io.*,java.sql.*,java.text.*" %>
<%
	Map<String, String> map = (Map<String, String>)session.getAttribute( "login_user" );
	Map<String, String> dbinfo = (Map<String, String>)session.getAttribute( "db_info" );
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
		<h1><a href="./home" id="logo">藤田研究室</a></h1>
	</header>

	<main>
		<nav class="navi">
        	<ul id="dropmenu">
        		<li><a href="#">Menu</a>
        		<ul>
        			<li><a href="./mypage">マイページ</a></li>
        			<li><a href="./update">設定</a></li>
        			<li><a href="./log">作業ログ</a></li>
        			<li><a href="./logout">ログアウト</a></li>
         		</ul>
         		</li>
         	</ul>
       	</nav>
       	
       	<form method="post" action="./member">
       	<table class="hometable2">
		<tr>
<%
	Connection conn = null;
	String url = (String)dbinfo.get("url");
	String user = (String)dbinfo.get("user");
	String password = (String)dbinfo.get("password");

    Class.forName("org.postgresql.Driver").newInstance();
    conn = DriverManager.getConnection(url, user, password);
    Statement stmt = conn.createStatement();
    
    String sql = "SELECT * FROM Member order by name";
    ResultSet rs = stmt.executeQuery(sql);
    
    
    while(rs.next()){
    	if(!rs.getString("name").equals("SFLab") && rs.getString("view").equals("ok")){ 
%>
    		<td width="110px" id="flag">表示</td>
    		<td width="130px"><input type="checkbox" name="view" value="<%= rs.getString("id")%>">表示しない</td>
    		<td><%= rs.getString("name") %></td> 
    		</tr>
<%
    	}
    }
 
    rs.close();
    rs = stmt.executeQuery(sql);
    
    while(rs.next()){
    	if(!rs.getString("name").equals("SFLab") && rs.getString("view").equals("no")){ 
%>
    		<td width="110px"><input type="checkbox" name="view" value="<%= rs.getString("id")%>">表示</td>
    		<td width="130px"  id="flag">表示しない</td>
    		<td><%= rs.getString("name") %></td> 
    		</tr> 
<%
    	}
    }
    
    rs.close();
    stmt.close();
    conn.close();
   
%>
	</table>
	<p class="mypage-m"><input type="submit" value="更新" id="mypage-submit"></p>
	</form>
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