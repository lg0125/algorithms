package com.chris.algorithms.templates.sort;

/**
 * 三指针
 */
public class QuickSortV3 {
    public static int[] sortArray(int[] nums) {
        quicksort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void quicksort(int[] nums, int l, int r) {
        if (l < r) {
            int pivot = nums[l];
            int lt = l, gt = r + 1;

            // 循环不变量：
            // all in [left + 1, lt] < pivot
            // all in [lt + 1, i) = pivot
            // all in [gt, right] > pivot
            int i = l + 1;
            while (i < gt) {
                if (nums[i] < pivot) {
                    lt++;
                    swap(nums, i, lt);

                    i++;
                } else if (nums[i] == pivot) {
                    i++;
                } else {
                    gt--;
                    swap(nums, i, gt);
                }
            }

            swap(nums, l, lt);

            // 注意这里，大大减少了两侧分治的区间
            quicksort(nums, l, lt - 1);
            quicksort(nums, gt, r);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
