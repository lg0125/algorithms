package com.chris.onlinejudge.ali.spring20240407;

import java.util.Scanner;

/**
 小红定义一个矩阵的权值为:该矩阵有多少2*2的矩形区域满足:区域内四个元素之和恰好等于2。
 现在小红想知道,所有n行m列的01矩阵,它们的权值之和等于多少?
 结果对1e+7取模

 输入两个正整数n,m,用空格隔开
 输出—个整数,代表所有n行m列的01矩阵的权值之和

 输入 2 2 输出 6 说明 2行2列的01矩阵共有16种,其中有6种的权值为1,其余权值为0

 统计矩形中有多少个2*2小矩形，每个小矩形有6中符合条件的方案数，剩余的元素可以任取0和1，
 因此6 * 符合条件的小矩形数目 * pow(2, 矩形面积-4) % mod即可
 */
public class P3 {
    private static final long MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong(), m = sc.nextLong();

        long a = n * m, b = (n - 1) * (m - 1);
        long ans = 6 * b % MOD * pow(2, a - 4) % MOD;
        System.out.println(ans);
    }

    private static long pow(long x, long n) {
        if (n == 0)
            return 1;
        if ((n & 1) == 1)
            return x * pow(x, n - 1) % MOD;

        long tmp = pow(x, n / 2);
        return tmp * tmp % MOD;
    }
}
