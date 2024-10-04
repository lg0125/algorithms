package com.chris.algorithms.templates.sort.test20240916;

import java.util.Arrays;

public class MergeSort {

    private static int[] tmp;

    public static int[] sortArray(int[] nums) {
        tmp = new int[nums.length];

        mergesort(nums, 0 ,nums.length-1);

        return nums;
    }

    private static void mergesort(int[] nums, int l, int r) {
        if(l < r) {

            int mid = l + (r - l) / 2;
            mergesort(nums, 0, mid);
            mergesort(nums, mid + 1, r);

            int cnt = 0;
            int i = l, j = mid + 1;
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

    public static void main(String[] args) {
        int[] nums = new int[]{1,9,2,8,3,7,4,6,5};
        System.out.println(Arrays.toString(sortArray(nums)));
    }
}
