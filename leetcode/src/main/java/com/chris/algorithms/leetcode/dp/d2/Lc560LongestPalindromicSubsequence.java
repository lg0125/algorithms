package com.chris.algorithms.leetcode.dp.d2;

/** 最长回文子序列
 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度
 测试链接 : <a href="https://leetcode.cn/problems/longest-palindromic-subsequence/">...</a>
 */
public class Lc560LongestPalindromicSubsequence {
    public static class Solution {
        // 记忆化搜素
        public static int longestPalindromeSubseq2(String str) {
            char[] s = str.toCharArray();

            int n = s.length;
            int[][] dp = new int[n][n];

            return f2(s, 0, n - 1, dp);
        }

        private static int f2(char[] s, int l, int r, int[][] dp) {
            if (l == r)
                return 1;
            if (l + 1 == r)
                return s[l] == s[r] ? 2 : 1;
            if (dp[l][r] != 0)
                return dp[l][r];

            int ans;
            if (s[l] == s[r])
                ans = 2 + f2(s, l + 1, r - 1, dp);
            else
                ans = Math.max(f2(s, l + 1, r, dp), f2(s, l, r - 1, dp));
            dp[l][r] = ans;
            return ans;
        }

        // 严格位置依赖的动态规划
        public static int longestPalindromeSubseq3(String str) {
            char[] s = str.toCharArray();

            int n = s.length;
            int[][] dp = new int[n][n];

            for (int l = n - 1; l >= 0; l--) {
                dp[l][l] = 1;

                if (l + 1 < n)
                    dp[l][l + 1] = s[l] == s[l + 1] ? 2 : 1;

                for (int r = l + 2; r < n; r++) {
                    if (s[l] == s[r])
                        dp[l][r] = 2 + dp[l + 1][r - 1];
                    else
                        dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
                }
            }

            return dp[0][n - 1];
        }

        // 严格位置依赖的动态规划 + 空间压缩
        public static int longestPalindromeSubseq4(String str) {
            char[] s = str.toCharArray();

            int n = s.length;
            int[] dp = new int[n];

            for (int l = n - 1, leftDown = 0, backup; l >= 0; l--) {
                // dp[l] : 想象中的dp[l][l]
                dp[l] = 1;

                if (l + 1 < n) {
                    leftDown = dp[l + 1];
                    // dp[l+1] : 想象中的dp[l][l+1]
                    dp[l + 1] = s[l] == s[l + 1] ? 2 : 1;
                }

                for (int r = l + 2; r < n; r++) {
                    backup = dp[r];

                    if (s[l] == s[r])
                        dp[r] = 2 + leftDown;
                    else
                        dp[r] = Math.max(dp[r], dp[r - 1]);

                    leftDown = backup;
                }
            }

            return dp[n - 1];
        }
    }
}
