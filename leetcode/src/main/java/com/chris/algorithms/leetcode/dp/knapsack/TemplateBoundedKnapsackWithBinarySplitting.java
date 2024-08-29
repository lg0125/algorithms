package com.chris.algorithms.leetcode.dp.knapsack;

import java.util.Arrays;

/** 多重背包通过二进制分组转化成01背包(模版)
 宝物筛选
 一共有n种货物, 背包容量为t
 每种货物的价值(v[i])、重量(w[i])、数量(c[i])都给出
 请返回选择货物不超过背包容量的情况下，能得到的最大的价值
 测试链接 : <a href="https://www.luogu.com.cn/problem/P1776">...</a>
 */
public class TemplateBoundedKnapsackWithBinarySplitting {
    public static class Solution {
        public static int MAXN = 1001;

        // 把每一种货物根据个数做二进制分组，去生成衍生商品
        // 衍生出来的每一种商品，价值放入v、重量放入w
        public static int[] v = new int[MAXN];
        public static int[] w = new int[MAXN];

        public static int n, t, m;

        // 01背包的空间压缩代码(模版)
        public static int compute() {
            int[] dp = new int[t + 1];

            for (int i = 1; i <= m; i++)
                for (int j = t; j >= w[i]; j--)
                    dp[j] = Math.max(
                            dp[j],
                            dp[j - w[i]] + v[i]
                    );

            return dp[t];
        }
    }
}
