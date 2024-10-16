package com.chris.algorithms.templates.sort.test20241004;

import java.util.Arrays;

public class HeapSort {
    public static void sortArray(int[] nums) {
        heapsort(nums);
    }

    private static void heapsort(int[] nums) {
        buildMaxHeap(nums);

        for(int j = nums.length - 1; j >= 1; --j) {
            swap(nums, 0, j);

            maxHeapify(nums, 0, j);
        }
    }

    private static void buildMaxHeap(int[] nums) {
        int n = nums.length;
        for(int i = n / 2; i >= 0; --i)
            maxHeapify(nums, i, n);
    }

    private static void maxHeapify(int[] nums, int start, int end) {
        // [start, end)
        int l = start * 2 + 1, r = start * 2 + 2;

        int lgt = start;
        if(l < end && nums[l] > nums[lgt])
            lgt = l;
        if(r < end && nums[r] > nums[lgt])
            lgt = r;

        if(lgt != start) {
            swap(nums, lgt, start);

            maxHeapify(nums, lgt, end);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 9, 2, 8, 3, 7, 4, 6, 5, 5};
        sortArray(nums);
        System.out.println(Arrays.toString(nums));
    }
}
