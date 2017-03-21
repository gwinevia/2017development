<?php

	//入門
	//echo "hello from the TOP!";
	//echo "hello from the TOP! again";

	/*
		変数:データにつけるラベル
		データ型:
			- 文字列 string 
			- 数値 integer float
			- 論理値 boolean / true false
			- 配列
			- オブジェクト
			- null
	*/

	$msg = "hello from the TOP!";
	echo $msg;
	var_dump($msg);

	/*
		定数:変更されない値につけるラベル
	*/

	define("MY_EMAIL","sample@gmail.com");
	echo MY_EMAIL;

	var_dump(__LINE__);
	var_dump(__FILE__);
	var_dump(__DIR__);


?>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<title>Sample</title>
	</head>
	<body>
		<p>Hello World <?php echo " from PHP"; ?></p>
	</body>
</html>