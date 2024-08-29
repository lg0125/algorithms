package com.chris.algorithms.leetcode.dp.d3;

/** 一和零(多维费用背包)
 给你一个二进制字符串数组 strs 和两个整数 m 和 n
 请你找出并返回 strs 的最大子集的长度
 该子集中 最多 有 m 个 0 和 n 个 1
 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集
 测试链接 : <a href="https://leetcode.cn/problems/ones-and-zeroes/">...</a>
 */
public class Lc474OnesAndZeroes {
    public static class Solution {
        public static int zeros, ones;

        // 统计一个字符串中0的1的数量
        // 0的数量赋值给全局变量zeros
        // 1的数量赋值给全局变量ones
        private static void zerosAndOnes(String str) {
            zeros = 0;
            ones = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }
        }

        // 记忆化搜索
        public static int findMaxForm2(String[] strs, int m, int n) {
            int[][][] dp = new int[strs.length][m + 1][n + 1];
            for (int i = 0; i < strs.length; i++)
                for (int j = 0; j <= m; j++)
                    for (int k = 0; k <= n; k++)
                        dp[i][j][k] = -1;

            return f2(strs, 0, m, n, dp);
        }

        private static int f2(String[] strs, int i, int j, int k, int[][][] dp) {
            if (i == strs.length)
                return 0;
            if (dp[i][j][k] != -1)
                return dp[i][j][k];

            int p1 = f2(strs, i + 1, j, k, dp), p2 = 0;

            zerosAndOnes(strs[i]);

            if (zeros <= j && ones <= k)
                p2 = 1 + f2(strs, i + 1, j - zeros, k - ones, dp);

            int ans = Math.max(p1, p2);
            dp[i][j][k] = ans;
            return ans;
        }

        // dp
        public static int findMaxForm3(String[] strs, int m, int n) {
            int len = strs.length;
            int[][][] dp = new int[len + 1][m + 1][n + 1];

            for (int i = len - 1; i >= 0; i--) {
                zerosAndOnes(strs[i]);

                for (int j = 0, p1, p2; j <= m; j++) {
                    for (int k = 0; k <= n; k++) {
                        p1 = dp[i + 1][j][k];

                        p2 = 0;
                        if (zeros <= j && ones <= k)
                            p2 = 1 + dp[i + 1][j - zeros][k - ones];

                        dp[i][j][k] = Math.max(p1, p2);
                    }
                }
            }

            return dp[0][m][n];
        }

        // dp + 空间压缩
        public static int findMaxForm4(String[] strs, int m, int n) {
            // 代表i == len
            int[][] dp = new int[m + 1][n + 1];

            for (String s : strs) {
                // 每个字符串逐渐遍历即可
                // 更新每一层的表
                // 和之前的遍历没有区别
                zerosAndOnes(s);

                for (int j = m; j >= zeros; j--)
                    for (int k = n; k >= ones; k--)
                        dp[j][k] = Math.max(dp[j][k], 1 + dp[j - zeros][k - ones]);
            }

            return dp[m][n];
        }
    }
}
