// 数値積分で円周率を求める

#include <iostream>
#include <iomanip>
using namespace std;

int main()
{
  int n = 10000;
  double w = 1.0 / n;
  double s = 0.0;
  int i = 1;
  while (i <= n) {
    double x = (i - 0.5) * w;
    s += 4.0 / (1.0 + x * x);
    i++;
  }

  // etprecision() はヘッダ iomanip に定義されているマニピュレータ
  // 小数点以下の桁数を指定するときに使う(デフォルトは 5 に設定)
  cout << setprecision(16) << s * w << endl;
}