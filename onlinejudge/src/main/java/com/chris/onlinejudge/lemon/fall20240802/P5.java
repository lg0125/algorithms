package com.chris.onlinejudge.lemon.fall20240802;

import java.util.*;

/**
 给定一个正整数 s 和 n 个正整数 , 求有多少种组合的和为 s ?
 数值相同的两个数视为不同的两个数。

 第一行两个整数 n，s，含义如题所述；
 第二行 n 个整数。
 1<=n<=30,1<=s<=900,1<=wi<=s

 输出一个整数表示答案。特别地，如果没有合法方案，输出0

10 5
1 1 1 1 1 1 1 1 1 1

 252

 C(10, 5) = 252

 动态规划。

 核心是一个背包问题，每个元素2种选择：选和不选。
 状态定义：f[i] [j] 考虑前i个元素，凑出和为j的元素有多少种组合。
 状态转移：f[i] [j] = f[i-1] [j] + f[i-1] [j-a[i]]
 */
public class P5 {
    private static int n, s;
    private static int[] w;

    private static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        s = sc.nextInt();

        w = new int[n];
        for(int i = 0; i < n; ++i)
            w[i] = sc.nextInt();

        dp = new int[n][n];
        for(int i = 0; i < n; ++i)
            for(int j = 0; j < n; ++j)
                dp[i][j] = -1;

        System.out.println(f(0, 0));
    }

    private static int f(int i, int j) {
        if(i >= n || j > s)
            return (j == s) ? 1 : 0;
        if(dp[i][j] != -1)
            return dp[i][j];

        int ans = f(i+1, j) + f(i+1, j+w[i]);
        dp[i][j] = ans;
        return ans;
    }
}
