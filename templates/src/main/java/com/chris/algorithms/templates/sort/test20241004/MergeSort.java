package com.chris.algorithms.templates.sort.test20241004;

import java.util.Arrays;

public class MergeSort {
    private static int[] aux;

    public static void sortArray(int[] nums) {
        aux = new int[nums.length];

        mergesort(nums, 0, nums.length - 1);
    }

    private static void mergesort(int[] nums, int l, int r) {
        // [l, r]
        if(l >= r)
            return;

        int m = l + (r - l) / 2;
        mergesort(nums, l, m);        // [l, m]
        mergesort(nums, m + 1, r); //  [m + 1, r]

        int cnt = 0;
        int i = l, j = m + 1;
        while (i <= m && j <= r) {
            if(nums[i] <= nums[j])
                aux[cnt++] = nums[i++];
            else
                aux[cnt++] = nums[j++];
        }
        while (i <= m) aux[cnt++] = nums[i++];
        while (j <= r) aux[cnt++] = nums[j++];

        System.arraycopy(
                aux, 0,
                nums, l,
                r - l + 1
        );
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 9, 2, 8, 3, 7, 4, 6, 5, 5};
        sortArray(nums);
        System.out.println(Arrays.toString(nums));
    }
}
