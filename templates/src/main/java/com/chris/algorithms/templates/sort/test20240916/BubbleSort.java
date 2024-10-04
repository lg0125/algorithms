package com.chris.algorithms.templates.sort.test20240916;

import java.util.Arrays;

public class BubbleSort {
    public static int[] sortArray(int[] nums) {
        bubblesort(nums);
        return nums;
    }

    private static void bubblesort(int[] nums) {
        int n = nums.length;
        // 外层循环控制轮数，每一轮都会将一个最大元素冒泡到最右侧
        for(int i = 0; i < n-1; ++i)
            // 内层循环负责相邻元素的比较和交换
            // 注意内层循环每轮都会将一个未排序部分的最大元素浮动到最右侧
            for(int j = 0; j < n-i-1; ++j)
                // 比较相邻元素，如果顺序错误则交换
                if(nums[j] > nums[j+1]) {
                    // 交换array[j]和array[j + 1]
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,9,2,8,3,7,4,6,5};
        System.out.println(Arrays.toString(sortArray(nums)));
    }
}
