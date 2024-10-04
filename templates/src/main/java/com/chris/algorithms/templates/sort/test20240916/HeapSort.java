package com.chris.algorithms.templates.sort.test20240916;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] nums = new int[]{1,9,2,8,3,7,4,6,5};
        System.out.println(Arrays.toString(sortArray(nums)));
    }

    public static int[] sortArray(int[] nums) {
        heapsort(nums);
        return nums;
    }

    private static void heapsort(int[] nums) {

        buildMaxHeap(nums);

        int n = nums.length;
        for(int k = n-1; k >= 1; --k) {
            swap(nums, 0, k);

            maxHeapify(nums, 0, --n);
        }
    }

    private static void buildMaxHeap(int[] nums) {
        int n = nums.length;

        for(int k = n/2; k >= 0; --k)
            maxHeapify(nums, k, n);
    }

    private static void maxHeapify(int[] nums, int i, int n) {
        int l = i * 2 + 1;
        int r = i * 2 + 2;

        int lgt = i;
        if(l < n && nums[l] > nums[lgt])
            lgt = l;
        if(r < n && nums[r] > nums[lgt])
            lgt = r;

        if(lgt != i) {
            swap(nums, i, lgt);

            maxHeapify(nums, lgt, n);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
