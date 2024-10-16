package com.chris.algorithms.leetcode.linkedlist;

/*
Given an integer array nums and an integer k, return the kth largest element in the array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.

nums = [3,2,1,5,6,4], k = 2
5
nums = [3,2,3,1,2,4,5,5,6], k = 4
4
 */
public class LC215 {
    public static int findKthLargest(int[] nums, int k) {
        return quickselect(nums, 0, nums.length - 1, k);
    }

    private static int quickselect(int[] nums, int l, int r, int k) {
        int pivot = nums[r];

        int i = l - 1;
        for(int j = l; j <= r - 1; ++j) {
            if(nums[j] >= pivot) {
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);

        if((i + 1) == (k - 1))
            return nums[i + 1];
        else if((i + 1) < (k - 1))
            return quickselect(nums, (i + 1) + 1, r, k);
        else
            return quickselect(nums, l, (i + 1) - 1, k);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(nums, 4));
    }
}
