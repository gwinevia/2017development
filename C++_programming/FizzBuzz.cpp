 /*
 	*	FizzBuzz問題
 	*	1から100までの値を表示するとき
 	*	3の倍数のときはFizzを
 	*	5 の倍数ときはBuzzを表示する
  */

#include <iostream>
using namespace std;

int main()
{
	int i = 1;
	int flag  =0;

	while(i <= 100){
		if(i % 3 == 0){
			cout << "Fizz";
			flag = 1;
		}

		if(i % 5 == 0){
			cout << "Buzz";
			flag = 1;
		}

		if(flag == 0){
			cout << i << endl;
		}else {
			flag = 0;
			cout << endl;
		}

		i++;
	}
}