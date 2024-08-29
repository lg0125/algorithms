package com.chris.algorithms.templates.sort;

/**
 * 单指针
 */
public class QuickSortV1 {
    public static int[] sortArray(int[] nums) {
        quicksort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void quicksort(int[] nums, int l, int r) {
        if (l < r) {
            int pos = partition(nums, l, r);

            quicksort(nums, l, pos - 1);
            quicksort(nums, pos + 1, r);
        }
    }

    private static int partition(int[] nums, int l, int r) {
        int pivot = nums[r];

        int i = l - 1;
        for (int j = l; j <= r - 1; ++j) {
            if (nums[j] <= pivot) {
                i = i + 1;
                swap(nums, i, j);
            }
        }

        swap(nums, i + 1, r);

        return i + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
