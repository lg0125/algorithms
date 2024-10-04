package com.chris.algorithms.templates.sort.test20240916;

import java.util.Arrays;

public class InsertionSort {
    public static int[] sortArray(int[] nums) {
        insertionsort(nums);
        return nums;
    }

    private static void insertionsort(int[] nums) {
        int n = nums.length;
        // 外循环规定从第二个元素开始，将元素插入到已排好的数组中
        for(int i  = 1; i < n; ++i) {
            // 使用num来表示插入的元素，
            // 若直接用nums[i]表示，nums[j+1]操作会更改nums[i]的值
            int num = nums[i];
            int j = i-1;
            while(j >= 0 && nums[j] > num) {
                // 若插入的元素小，则将被比较的元素后移一位
                nums[j+1] = nums[j];
                j--;
            }
            // 此时的nums[j]代表着被插入元素左边相邻的那个元素
            nums[j+1] = num;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,9,2,8,3,7,4,6,5};
        System.out.println(Arrays.toString(sortArray(nums)));
    }
}
