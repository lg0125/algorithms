package com.chris.algorithms.leetcode.array.prefixsum.subarray;

/** 利用前缀和快速得到区域累加和
 测试链接 : <a href="https://leetcode.cn/problems/range-sum-query-immutable/">...</a>
 */
public class Lc303PrefixSumArray {
    public static class NumArray {
        public int[] sum;

        public NumArray(int[] nums) {
            int n = nums.length;

            sum = new int[n + 1];
            for(int i = 1; i <= n; ++i)
                sum[i] = sum[i-1] + nums[i-1];
        }

        public int sumRange(int left, int right) {
            return sum[right + 1] - sum[left];
        }
    }
}
