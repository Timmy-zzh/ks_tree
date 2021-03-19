#include <stdio.h>


int global_init_var = 84;   // 全局变量-已初始化
int global_uninit_var;      // 全局变量-未初始化

void func1(int i){
    printf("%d/n",i);
}

int main(){

    static int static_var = 85;     //静态变量--已初始化
    static int static_var2;         //静态变量--未初始化
    //局部变量
    int a = 1;
    int b ;

    func1(static_var+static_var2+a+b);

    printf("main - end!\n");
    return 0;
}