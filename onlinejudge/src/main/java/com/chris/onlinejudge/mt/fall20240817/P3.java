package com.chris.onlinejudge.mt.fall20240817;

import java.util.Scanner;

/**
 第一行输入两个整数 n 和 k,代表数组长度和固定的整数(1 <= k <= 10^5; 1 <= n <= 1000; -10^5 <= ai <= 10^5)。
 第二行输入 n 个整数 a1, a2, ..., an(-10^5 <= ai <= 10^5)代表数组。

 在一行上输出一个整数表示答案

 6 2
 -1 -2 -3 1 2 3

 0

 小美会选择区间[4,6],数组变成{-1, -2, -3, 2, 4, 6}；
 小美会选择区间[1,3],数组变成{-2, -4, -6, 2, 4, 6}；
 数组总和为 0。

 动态规划
 定义dp[i][j][0]代表中连续子段的最小值
 定义dp[i][j][1]代表中连续子段的最大值
 可以采用二维循环求出dp数组
 之后, 枚举小美选择哪个子段，
 然后, 计算这个子段情况下小团所能达到的最小贡献，并取最大值即为最终答案
 */
public class P3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, k;
        n = sc.nextInt();
        k = sc.nextInt();

        long[] a            = new long[n + 1];
        long[] presum       = new long[n + 1];
        long[][][] dp       = new long[n + 1][n + 1][2];

        for (int i = 1; i <= n; i++) {
            a[i]        = sc.nextLong();
            presum[i]   = presum[i - 1] + a[i];
        }

        for (int seg = 1; seg <= n; seg++) {
            for (int start = 1; start + seg - 1 <= n; start++) {
                int end = start + seg - 1;

                if (seg == 1) {
                    dp[start][end][0] = dp[start][end][1] = a[start];
                } else {
                    dp[start][end][0] = Math.min(
                            Math.min(
                                    dp[start + 1][end][0],
                                    dp[start][end - 1][0]
                            ),
                            sum(presum, start, end)
                    );
                    dp[start][end][1] = Math.max(
                            Math.max(
                                    dp[start + 1][end][1],
                                    dp[start][end - 1][1]
                            ),
                            sum(presum, start, end)
                    );
                }
            }
        }

        long maxV = Long.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                long p1 = sum(presum, i, j) * (k - 1);
                long p2 = Math.min(
                        dp[i][j][0] * k * (k - 1),
                        dp[i][j][1] * k * (k - 1)
                );

                if (i != 1)
                    p2 = Math.min(
                            p2,
                            Math.min(
                                    dp[1][i - 1][0] * (k - 1),
                                    dp[1][i - 1][1] * (k - 1)
                            )
                    );
                if (j != n)
                    p2 = Math.min(
                            p2,
                            Math.min(
                                    dp[j + 1][n][0] * (k - 1),
                                    dp[j + 1][n][1] * (k - 1)
                            )
                    );

                maxV = Math.max(maxV, p1 + p2);
            }
        }

        System.out.println(sum(presum, 1, n) + maxV);
    }

    private static long sum(long[] prefixSum, int left, int right) {
        return prefixSum[right] - prefixSum[left - 1];
    }
}
