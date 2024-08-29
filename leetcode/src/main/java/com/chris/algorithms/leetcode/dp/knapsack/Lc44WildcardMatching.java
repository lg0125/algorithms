package com.chris.algorithms.leetcode.dp.knapsack;

/** 通配符匹配（和题目4高度相似，只是边界条件不同而已，而且更简单）
 给你字符串s、字符串p
 s中一定不含有'?'、'*'字符，p中可能含有'?'、'*'字符
 '?' 表示可以变成任意字符，数量1个
 '*' 表示可以匹配任何字符串
 请实现一个支持 '?' 和 '*' 的通配符匹配
 返回p的整个字符串能不能匹配出s的整个字符串
 测试链接 : <a href="https://leetcode.cn/problems/wildcard-matching/">...</a>
 */
public class Lc44WildcardMatching {
    public static class Solution {
        // 记忆化搜索
        public static boolean isMatch2(String str, String pat) {
            char[] s = str.toCharArray(), p = pat.toCharArray();

            int n = s.length, m = p.length;

            // dp[i][j] == 0，表示没算过
            // dp[i][j] == 1，表示算过，答案是true
            // dp[i][j] == 2，表示算过，答案是false
            int[][] dp = new int[n + 1][m + 1];

            return f2(s, p, 0, 0, dp);
        }

        public static boolean f2(char[] s, char[] p, int i, int j, int[][] dp) {
            if (dp[i][j] != 0)
                return dp[i][j] == 1;

            boolean ans;
            if (i == s.length) {
                if (j == p.length)
                    ans = true;
                else
                    ans = p[j] == '*' && f2(s, p, i, j + 1, dp);
            } else if (j == p.length) {
                ans = false;
            } else {
                if (p[j] != '*')
                    ans = (s[i] == p[j] || p[j] == '?') && f2(s, p, i + 1, j + 1, dp);
                else
                    ans = f2(s, p, i + 1, j, dp) || f2(s, p, i, j + 1, dp);
            }
            dp[i][j] = ans ? 1 : 2;
            return ans;
        }

        // 严格位置依赖的动态规划
        public static boolean isMatch3(String str, String pat) {
            char[] s = str.toCharArray(), p = pat.toCharArray();

            int n = s.length, m = p.length;

            boolean[][] dp = new boolean[n + 1][m + 1];

            dp[n][m] = true;
            for (int j = m - 1; j >= 0 && p[j] == '*'; j--)
                dp[n][j] = true;

            for (int i = n - 1; i >= 0; i--) {
                for (int j = m - 1; j >= 0; j--) {
                    if (p[j] != '*')
                        dp[i][j] = (s[i] == p[j] || p[j] == '?') && dp[i + 1][j + 1];
                    else
                        dp[i][j] = dp[i + 1][j] || dp[i][j + 1];
                }
            }

            return dp[0][0];
        }
    }
}
