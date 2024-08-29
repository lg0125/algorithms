package com.chris.algorithms.leetcode.dp.knapsack;

import java.util.Arrays;

/** 01背包(模版)
 给定一个正数t，表示背包的容量
 有m个货物，每个货物可以选择一次
 每个货物有自己的体积costs[i]和价值values[i]
 返回在不超过总容量的情况下，怎么挑选货物能达到价值最大
 返回最大的价值
 测试链接 : <a href="https://www.luogu.com.cn/problem/P1048">...</a>
 */
public class Template01Knapsack {
    public static class Solution {
        public static int MAX_M = 101;
        public static int MAX_T = 1001;

        public static int[] cost = new int[MAX_M];

        public static int[] val = new int[MAX_M];

        public static int t, n;

        // 严格位置依赖的动态规划
        // n个物品编号1~n，第i号物品的花费cost[i]、价值val[i]
        // cost、val数组是全局变量，已经把数据读入了
        public static int compute1() {
            int[][] dp = new int[n + 1][t + 1];

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= t; j++) {
                    // 不要i号物品
                    dp[i][j] = dp[i - 1][j];

                    if (j - cost[i] >= 0)
                        // 要i号物品
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cost[i]] + val[i]);
                }
            }

            return dp[n][t];
        }

        // 空间压缩
        public static int compute2() {
            int[] dp = new int[t + 1];

            for (int i = 1; i <= n; i++)
                for (int j = t; j >= cost[i]; j--)
                    dp[j] = Math.max(dp[j], dp[j - cost[i]] + val[i]);

            return dp[t];
        }
    }
}
