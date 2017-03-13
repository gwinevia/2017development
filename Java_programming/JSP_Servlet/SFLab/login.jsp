<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        		<li><a href="./newuser">新規登録</a></li>
         	</ul>
       </nav>

		<div id="form">
    		<p class="form-title">Login</p>
    		<form method="post" action="./login">
        		<p>Email</p>
        		<p class="mail"><input type="email" name="email" placeholder="E-MAIL"　required autofocus /></p>
        		<p>Password</p>
        		<p class="pass"><input type="password" name="password" placeholder="PASSWORD"　required /></p>
        		<p class="submit"><input type="submit" value="ログイン"/></p>
    		</form>
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