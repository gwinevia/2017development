// インクリメント演算子
#include <iostream>
using namespace std;

int main()
{
  int i = 1, j = 10;
  int x = ++i;
  int y = j++;
  cout << i << endl;
  cout << j << endl;
  cout << x << endl;
  cout << y << endl;
}

/*

変数 x, y に値をセットするとき、変数 i, j の値を +1 している
このため、i, j の値は 2 と 11 になる
x の値は演算子 ++ が i の前についているので、i の値を +1 してから、i の値を x に代入する → x の値は 2 
y の値は演算子 ++ が j の後ろに付いているので、j の値を y に代入してから j の値を +1 する → y の値は 10 

*/