package com.chris.algorithms.leetcode.array.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
Notice that the solution set must not contain duplicate triplets.

nums = [-1,0,1,2,-1,-4]
[[-1,-1,2],[-1,0,1]]
 */
public class LC15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        int n = nums.length;
        if(nums == null || n < 3)
            return ans;

        Arrays.sort(nums);

        for(int i = 0; i < n; ++i) {
            if(nums[i] > 0)
                break;
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if(sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));

                    while (l < r && nums[l + 1] == nums[l]) l++;
                    while (l < r && nums[r - 1] == nums[r]) r--;
                    l++;
                    r--;
                } else if(sum < 0) {
                    l++;
                } else if(sum > 0) {
                    r--;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }
}
