package com.chris.algorithms.templates.sort;

/**
 * 双指针
 */
public class QuickSortV2 {
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
        int pivot = nums[l];
        int lt = l + 1, gt = r;

        // 循环不变量：
        // all in [left + 1, lt) <= pivot
        // all in (gt, right] >= pivot
        while (true) {
            while(lt <= r &&  nums[lt] < pivot) ++lt;
            while(gt > l && nums[gt] > pivot) --gt;

            if(lt >= gt) break;

            // 细节：相等的元素通过交换，等概率分到数组的两边
            swap(nums, lt, gt);
            ++lt;
            --gt;
        }

        swap(nums, l, gt);
        return gt;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
