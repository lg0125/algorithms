package com.chris.onlinejudge.mihoyo.fall20240803;

import java.util.Scanner;

/*
米小游有一个长度为 n 的数组，其中第 i 个元素为 ai。现在定义数组的价值是最大的相邻数字的乘积。
例如数组为 [3,5,1,2] ，相邻元素的乘积分别是 35=15,51=5和1*2=2 ，则数组的价值是这些数字中的最大值，即 15。
现在米小游想要任选数组中的某两个相邻的元素进行交换（你必须使用这次交换机会），他想知道最大可以将数组的价值更改为多少？

第一行输入一个整数 n(2<=n<=10^5)  表示数组的长度。 第二行输入 n 个整数 a1,a2,.....,an (1<=ai<=10^5) 表示数组中的值。

在一行上输出一个整数表示答案。

3
3 1 10

30
*/
public class P1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long[] a = new long[n];
        for(int i = 0; i < n; ++i)
            a[i] = sc.nextLong();

        long res = -1;
        for(int i = 0; i < n-1; ++i)
            res = Math.max(res, a[i] * a[i+1]);
        for(int i = 0; i < n-1; ++i) {
            long left   = (i > 0) ? a[i-1] * a[i+1] : 0;
            long right  = (i < n-2) ? a[i+1] * a[i+2] : 0;
            res = Math.max(res, Math.max(left, right));
        }
        System.out.println(res);
    }
}
