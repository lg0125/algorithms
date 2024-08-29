package com.chris.algorithms.leetcode.dp.d3;

/** 盈利计划(多维费用背包)
 集团里有 n 名员工，他们可以完成各种各样的工作创造利润
 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与
 如果成员参与了其中一项工作，就不能参与另一项工作
 工作的任何至少产生 minProfit 利润的子集称为 盈利计划
 并且工作的成员总数最多为 n
 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
 测试链接 : <a href="https://leetcode.cn/problems/profitable-schemes/">...</a>
 */
public class Lc879ProfitableSchemes {
    // n : 员工的额度，不能超
    // p : 利润的额度，不能少
    // group[i] : i号项目需要几个人
    // profit[i] : i号项目产生的利润
    // 返回能做到员工不能超过n，利润不能少于p的计划有多少个
    public static class Solution {
        public static int mod = 1000000007;

        // 记忆化搜素
        public static int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {
            int m = group.length;
            int[][][] dp = new int[m][n + 1][minProfit + 1];

            for (int i = 0; i < m; i++)
                for (int j = 0; j <= n; j++)
                    for (int k = 0; k <= minProfit; k++)
                        dp[i][j][k] = -1;

            return f2(group, profit, 0, n, minProfit, dp);
        }

        private static int f2(int[] g, int[] p, int i, int r, int s, int[][][] dp) {
            if (r <= 0)
                return s == 0 ? 1 : 0;
            if (i == g.length)
                return s == 0 ? 1 : 0;
            if (dp[i][r][s] != -1)
                return dp[i][r][s];

            int p1 = f2(g, p, i + 1, r, s, dp);

            int p2 = 0;
            if (g[i] <= r)
                p2 = f2(g, p, i + 1, r - g[i], Math.max(0, s - p[i]), dp);

            int ans = (p1 + p2) % mod;
            dp[i][r][s] = ans;
            return ans;
        }

        // dp + 空间压缩
        public static int profitableSchemes3(int n, int minProfit, int[] group, int[] profit) {
            // i = 没有工作的时候，i == g.length
            int[][] dp = new int[n + 1][minProfit + 1];

            for (int r = 0; r <= n; r++)
                dp[r][0] = 1;

            int m = group.length;
            for (int i = m - 1; i >= 0; i--) {
                for (int r = n; r >= 0; r--) {
                    for (int s = minProfit; s >= 0; s--) {
                        int p1 = dp[r][s];
                        int p2 = group[i] <= r ? dp[r - group[i]][Math.max(0, s - profit[i])] : 0;

                        dp[r][s] = (p1 + p2) % mod;
                    }
                }
            }

            return dp[n][minProfit];
        }
    }
}
