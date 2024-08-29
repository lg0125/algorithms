package com.chris.algorithms.leetcode.dp.knapsack;

/** 完全背包(模版)
 给定一个正数t，表示背包的容量
 有m种货物，每种货物可以选择任意个
 每种货物都有体积costs[i]和价值values[i]
 返回在不超过总容量的情况下，怎么挑选货物能达到价值最大
 返回最大的价值
 测试链接 : <a href="https://www.luogu.com.cn/problem/P1616">...</a>
 */
public class TemplateUnboundedKnapsack {
    public static class Solution {
        public static int MAX_M = 10001;

        public static int[] cost = new int[MAX_M];
        public static int[] val = new int[MAX_M];

        public static int t, m;

        // 严格位置依赖的动态规划
        // 会空间不够，导致无法通过全部测试用例
        public static long compute1() {
            // dp[0][.....] = 0
            int[][] dp = new int[m + 1][t + 1];

            for (int i = 1; i <= m; i++) {
                for (int j = 0; j <= t; j++) {
                    dp[i][j] = dp[i - 1][j];

                    if (j - cost[i] >= 0)
                        dp[i][j] = Math.max(
                                dp[i][j],
                                dp[i][j - cost[i]] + val[i]
                        );
                }
            }

            return dp[m][t];
        }

        // 空间压缩
        // 可以通过全部测试用例
        public static long compute2() {
            int[] dp = new int[t + 1];


            for (int i = 1; i <= m; i++)
                for (int j = cost[i]; j <= t; j++)
                    dp[j] = Math.max(dp[j], dp[j - cost[i]] + val[i]);


            return dp[t];
        }
    }
}
