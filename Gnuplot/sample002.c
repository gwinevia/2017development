// 
// ランダムウォークのデータを出力する
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define n 50000         //最大繰り返し回数

int main()
{
    int i, j;
    int random_num;

    FILE *output;
    char str[100];
    double x[n],y[n];

    output = fopen("sample002.dat","w");

    srand((unsigned) time(NULL));

    x[0] = 0.0;
    y[0] = 0.0;

    for( i = 1; i < n; i++) {

        x[i] = x[i-1];
        y[i] = y[i-1];

        //乱数の数字の範囲によって４つの方向のどちらかへ移動
        random_num = rand()%4; 
        switch(random_num){
            case 0:
                x[i] = x[i-1] - 1.0;
                break;
            case 1:
                x[i] = x[i-1] + 1.0;
                break;
            case 2:
                y[i] = y[i-1] - 1.0;
                break;
            case 3:
                y[i] = y[i-1] + 1.0;
                break;
        }

        // 反射壁
        if( x[i-1] == 40 ) {
            x[i] = 39;
            y[i] = y[i-1];
        } else if( x[i-1] == -40 ) {
            x[i] = -39;
            y[i] = y[i-1];
        } else if( y[i-1] == 40 ) {
            x[i] = x[i-1];
            y[i] = 39;
        } else if( y[i-1] == -40 ) {
            x[i] = x[i-1];
            y[i] = -39;
        }

        // 500回ごとにファイルへ出力
        if( i%500 == 0) {
            for(j = 0; j <= i; j++) {
                fprintf(output,"%f %f\n",x[j],y[j]);
            }

            // 2行空白(gnuplotでの解析用)
            fprintf(output,"\n\n");
        }
    }

    fclose(output);

    return 0;
}