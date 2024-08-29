package com.chris.algorithms.templates.sort;

public class MergeSort {
    private static int[] tmp;

    public static int[] sortArray(int[] nums) {
        tmp = new int[nums.length];

        mergeSort(nums, 0, nums.length - 1);

        return nums;
    }

    private static void mergeSort(int[] nums, int l, int r) {
        if (l >= r) return;

        int mid = l + (r - l) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);

        int i = l, j = mid + 1, cnt = 0;
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j])
                tmp[cnt++] = nums[i++];
            else
                tmp[cnt++] = nums[j++];
        }
        while (i <= mid)
            tmp[cnt++] = nums[i++];
        while (j <= r)
            tmp[cnt++] = nums[j++];

        if (r - l + 1 >= 0)
            System.arraycopy(
                    tmp, 0,
                    nums, l,
                    r - l + 1
            );
    }
}
