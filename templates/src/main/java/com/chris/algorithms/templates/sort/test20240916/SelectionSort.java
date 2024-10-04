package com.chris.algorithms.templates.sort.test20240916;

import java.util.Arrays;

public class SelectionSort {
    public static int[] sortArray(int[] nums) {
        selectionsort(nums);
        return nums;
    }

    private static void selectionsort(int[] nums) {
        int n = nums.length;
        // i不需要 = 数组最尾部元素，因为后面没有值对比
        for (int i = 0; i < n - 1; ++i) {
            // 设置每次循环的起始点为最小/大值
            int min = nums[i];
            // 记录下最小/大值的下标
            int index = i;
            // 哨兵，记录是否找到最值，默认false
            boolean isSwap = false;
            for (int j = i + 1; j < nums.length; j++) {
                // 升序排序>, 降序排序<
                if (min > nums[j]) {
                    min = nums[j];
                    index = j;
                    // 找到最值，设置为true
                    isSwap = true;
                }
            }
            if (isSwap){
                // 一轮对比完成后，将默认的最小值赋予到找到的最值下标位置
                nums[index] = nums[i];
                // 把找到的真实最值放到前面
                nums[i] = min;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,9,2,8,3,7,4,6,5};
        System.out.println(Arrays.toString(sortArray(nums)));
    }
}
