package com.chris.algorithms.leetcode.dp.d2;

/** 不同的子序列
 给你两个字符串s和t ，统计并返回在s的子序列中t出现的个数
 答案对1000000007取余
 测试链接 : <a href="https://leetcode.cn/problems/distinct-subsequences/">...</a>
 */
public class Lc115DistinctSubsequences {
    public static class Solution {
        public static int numDistinct1(String str, String target) {
            char[] s = str.toCharArray(), t = target.toCharArray();

            int n = s.length, m = t.length;
            // dp[i][j] :
            // s[前缀长度为i]的所有子序列中，有多少个子序列等于t[前缀长度为j]
            int[][] dp = new int[n + 1][m + 1];

            for (int i = 0; i <= n; i++)
                dp[i][0] = 1;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    dp[i][j] = dp[i - 1][j];

                    if (s[i - 1] == t[j - 1])
                        dp[i][j] += dp[i - 1][j - 1];
                }
            }

            return dp[n][m];
        }

        public static int numDistinct2(String str, String target) {

            char[] s = str.toCharArray(), t = target.toCharArray();

            int n = s.length, m = t.length, mod = 1000000007;

            int[] dp = new int[m + 1];

            dp[0] = 1;

            for (int i = 1; i <= n; i++)
                for (int j = m; j >= 1; j--)
                    if (s[i - 1] == t[j - 1])
                        dp[j] = (dp[j] + dp[j - 1]) % mod;

            return dp[m];
        }
    }
}
