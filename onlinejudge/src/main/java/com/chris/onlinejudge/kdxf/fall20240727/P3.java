package com.chris.onlinejudge.kdxf.fall20240727;

import java.util.Scanner;

/**
 异或和

 给定两个整数 n和 m ，询问满足如下条件的序列 a 的数量：
    序列 a的长度为 n ；
    序列 a的值均大于等于 0 且小于等于 m ，形式化的说，0<=ai<=m(1<=i<=m)；
    a1<=a2<=a3<=...<=an，表示序列 a是一个非递减序列；
    a1^a2^a3^....^an，表示序列 a 所有元素的异或值为 m 。
 在一行上输入两个整数 n,m(1<=n<=300;0<=m<=300)表示序列的长度和序列的取值范围
 在一行上输出一个整数，表示满足条件的不同的序列 a的数量；由于答案可能很大，请将最后的结果对 10^9+7取模

 2 3

 2
 其中序列 [0,3] 和 [1,2] 满足条件

 4 7

 40

 200 200

 391022064

 动态规划+前缀和优化。
 f[i][j][k]表示考虑前i个数字，最后一个数字j，异或和为k的方案数。
 转移为：f[i][j][k] += f[i-1][l][k^l]，其中，l满足<=j
 观察发现，f[i]维度的状态依赖于f[i-1]中的某一段区间，
    因此我们可以用前缀和来维护这一段区间，使得中间累加dp状态的操作变为O(1)。
 这样复杂度是O(nmm)，符合要求
 */
public class P3 {
    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        // f[i][k][l] represents the number of schemes ending with l and sum equals to k for the first i numbers
        long[][][] f = new long[n + 1][2 * m + 1][m + 1];
        f[0][0][0] = 1;

        for (int i = 1; i <= n; ++i) {
            long[][] pre = new long[2 * m + 1][m + 1];
            for (int t = 0; t <= 2 * m; ++t) {
                for (int p = 0; p <= m; ++p) {
                    pre[t][p] = f[i - 1][t][p];

                    if (p != 0)
                        pre[t][p] = (pre[t][p] + pre[t][p - 1]) % MOD;
                }
            }

            for (int k = 0; k <= 2 * m; ++k)
                for (int l = 0; l <= m && (k ^ l) <= 2 * m; ++l)
                    f[i][k][l] = (f[i][k][l] + pre[k ^ l][l]) % MOD;
        }

        long ans = 0;
        for (int i = 0; i <= m; ++i)
            ans = (ans + f[n][m][i]) % MOD;

        System.out.println(ans);
    }
}
