package com.chris.algorithms.leetcode.dp.d2;

/** 编辑距离
 给你两个单词 word1 和 word2
 请返回将 word1 转换成 word2 所使用的最少代价
 你可以对一个单词进行如下三种操作：
 插入一个字符，代价a
 删除一个字符，代价b
 替换一个字符，代价c
 测试链接 : <a href="https://leetcode.cn/problems/edit-distance/">...</a>
 */
public class Lc72EditDistance {
    public static class Solution {
        public static int minDistance(String word1, String word2) {
            return editDistance1(word1, word2, 1, 1, 1);
        }

        // 枚举小优化版
        // a : str1中插入1个字符的代价
        // b : str1中删除1个字符的代价
        // c : str1中改变1个字符的代价
        // 返回从str1转化成str2的最低代价
        private static int editDistance1(String str1, String str2, int a, int b, int c) {
            char[] s1 = str1.toCharArray(), s2 = str2.toCharArray();

            int n = s1.length, m = s2.length;
            // dp[i][j] :
            // s1[前缀长度为i]想变成s2[前缀长度为j]，至少付出多少代价
            int[][] dp = new int[n + 1][m + 1];

            for (int i = 1; i <= n; i++)
                dp[i][0] = i * b;
            for (int j = 1; j <= m; j++)
                dp[0][j] = j * a;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (s1[i - 1] == s2[j - 1])
                        dp[i][j] = dp[i - 1][j - 1];
                    else
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j] + b, dp[i][j - 1] + a), dp[i - 1][j - 1] + c);
                }
            }

            return dp[n][m];
        }

        // 空间压缩
        private static int editDistance2(String str1, String str2, int a, int b, int c) {
            char[] s1 = str1.toCharArray(), s2 = str2.toCharArray();

            int n = s1.length, m = s2.length;
            int[] dp = new int[m + 1];

            for (int j = 1; j <= m; j++)
                dp[j] = j * a;

            for (int i = 1, leftUp, backUp; i <= n; i++) {
                leftUp = (i - 1) * b;

                dp[0] = i * b;
                for (int j = 1; j <= m; j++) {
                    backUp = dp[j];

                    if (s1[i - 1] == s2[j - 1])
                        dp[j] = leftUp;
                    else
                        dp[j] = Math.min(Math.min(dp[j] + b, dp[j - 1] + a), leftUp + c);

                    leftUp = backUp;
                }
            }

            return dp[m];
        }
    }
}
