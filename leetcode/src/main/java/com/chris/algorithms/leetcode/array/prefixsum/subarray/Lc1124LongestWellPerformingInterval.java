package com.chris.algorithms.leetcode.array.prefixsum.subarray;

import java.util.HashMap;

/** 表现良好的最长时间段
 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数
 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是 劳累的一天
 所谓 表现良好的时间段 ，意味在这段时间内，「劳累的天数」是严格 大于 不劳累的天数
 请你返回 表现良好时间段 的最大长度
 测试链接 : <a href="https://leetcode.cn/problems/longest-well-performing-interval/">...</a>
 */
public class Lc1124LongestWellPerformingInterval {
    public static class Solution {
        public static int longestWPI(int[] hours) {
            HashMap<Integer, Integer> map = new HashMap<>(); // 某个前缀和，最早出现的位置
            map.put(0, -1); // 0这个前缀和，最早出现在-1，一个数也没有的时候

            int ans = 0, n = hours.length;
            for (int i = 0, sum = 0; i < n; ++i) {
                sum += hours[i] > 8 ? 1 : -1;

                if (sum > 0)
                    ans = i + 1;
                else if (map.containsKey(sum - 1))  // sum <= 0
                    ans = Math.max(ans, i - map.get(sum - 1));

                if (!map.containsKey(sum))
                    map.put(sum, i);
            }
            return ans;
        }
    }
}
