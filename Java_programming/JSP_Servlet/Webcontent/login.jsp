<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>TestLogin</title>
	</head>
	<body>

	<header>
		<h1>TestLogin</h1>
		<p>ログインフォーム</p>
	</header>

	<main>
		<div>
			<form method="post" action="./login">
				<p><input type="email" name="email" placeholder="E-MAIL" required autofocus></p>
				<p><input type="password" name="password" placeholder="PASSWORD" required></p>
				<p><input type="submit" value="ログイン"></p>
			</form>
		</div>
	</main>
	</body>
</html>