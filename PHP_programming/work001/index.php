<?php

	/*
		整数値の演算
		+ - * / % **
	*/

		$x = 10 % 3;
		$y = 30.2 / 4;
		var_dump($x);
		var_dump($y);

		//単項演算子 ++ --
		$z = 5;
		$z++;
		var_dump($z);
		$z--;
		var_dump($z);

		//代入を伴う演算子
		$s = 5;
		//$s = $s * 2;
		$s *= 2;
		var_dump($s);

?>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<title>Sample</title>
	</head>
	<body>
	</body>
</html>