// 平均，分散，標準偏差を計算するプログラム
#include <stdio.h>
#include <math.h>

#define N 20

double sum(double data[N]);
double ave(double data[N]);
double var(double data[N],double ave);
double std(double var);

int main(void)
{
	double data[N] = {
		165.547,
		165.645,
		165.694,
		171.089,
		171.344,
		163.899,
		171.917,
		164.402,
		170.125,
		164.611,
		170.351,
		166.159,
		170.394,
		170.382,
		170.202,
		172.559,
		170.806,
		171.714,
		170.623,
		171.916
	};
	double a = 0.0; // 平均
	double v = 0.0;   // 分散
	double s = 0.0;   // 標準偏差

	a = ave(data);
	v = var(data,a);
	s = std(v);

	printf("平均：%.5f\n",a);
	printf("分散：%.5f\n",v);
	printf("標準偏差：%.5f\n",s);

	return 0;
}

// 合計値を計算
double sum(double data[N])
{
	int i;
	double sum = 0.0;

	for(i=0;i<N;i++){
		sum += data[i];
	}

	return sum;
}

// 平均値を計算
double ave(double data[N])
{
	double s = sum(data);
	return s/N;
}

// 分散を計算
double var(double data[N],double ave)
{
	int i;
	double v = 0.0;

	for(i=0;i<N;i++){
		v += (data[i] - ave)*(data[i] - ave);
	}

	return v/N;

}

// 標準偏差を計算
double std(double var)
{
	return sqrt(var);
}
















