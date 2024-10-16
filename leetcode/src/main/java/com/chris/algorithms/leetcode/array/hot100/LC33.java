package com.chris.algorithms.leetcode.array.hot100;

/*
There is an integer array nums sorted in ascending order (with distinct values).
Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
You must write an algorithm with O(log n) runtime complexity.

nums = [4,5,6,7,0,1,2], target = 0
4
 */
public class LC33 {
    public static int search(int[] nums, int target) {
        int n = nums.length;
        if(n == 0)
            return -1;
        if(n == 1)
            return target == nums[0] ? 0 : -1;
        int l = 0, r = n - 1;
        while(l <= r) {
            int m = l + (r - l) / 2;

            if(target == nums[m])
                return m;

            if(nums[0] <= nums[m]) {
                if(nums[0] <= target && target < nums[m])
                    r = m - 1;
                else
                    l = m + 1;
            } else {
                if(nums[m] < target && target <= nums[n-1])
                    l = m + 1;
                else
                    r = m - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        System.out.println(search(nums, 0));
    }
}
