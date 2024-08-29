package com.chris.algorithms.leetcode.array.prefixsum.subarray;

import java.util.HashMap;

/** 返回无序数组中累加和为给定值的子数组个数
 测试链接 : <a href="https://leetcode.cn/problems/subarray-sum-equals-k/">...</a>
 */
public class Lc560NumberOfSubarraySumEqualsTarget {
    public static class Solution {
        public static int subarraySum(int[] nums, int target) {
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);

            int ans = 0, n = nums.length;
            for (int i = 0, sum = 0; i < n; ++i) {
                // sum : 0...i前缀和
                sum += nums[i];

                ans += map.getOrDefault(sum - target, 0);

                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return ans;
        }
    }
}
