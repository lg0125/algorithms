package com.chris.algorithms.leetcode.array.twopointers;

import java.util.Arrays;

/** 救生艇
 给定数组 people
 people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit
 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit
 返回 承载所有人所需的最小船数
 测试链接 : <a href="https://leetcode.cn/problems/boats-to-save-people/">...</a>
 */
public class Lc881BoatsToSavePeople {
    public static class Solution {
        // 时间复杂度O(n * log n)，因为有排序，额外空间复杂度O(1)
        public static int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people);

            int l = 0, r = people.length - 1;

            int ans = 0, sum = 0;
            while (l <= r) {
                sum = l == r ? people[l] : people[l] + people[r];

                if (sum > limit) {
                    r--;
                } else {
                    l++;
                    r--;
                }

                ans++;
            }
            return ans;
        }
    }
}
