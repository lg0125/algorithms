package com.chris.algorithms.leetcode.array.hot100;

/*
Given an integer array nums,
find the subarray with the largest sum, and return its sum.

nums = [-2,1,-3,4,-1,2,1,-5,4]
6
 */
public class LC53 {
    public static int maxSubArray(int[] nums) {
        int pre = 0, res = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            res = Math.max(res, pre);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
}
