package com.chris.algorithms.leetcode.array.prefixsum.d1;

/** 航班预订统计
 这里有n个航班，它们分别从 1 到 n 进行编号。
 有一份航班预订表bookings ，
 表中第i条预订记录bookings[i] = [firsti, lasti, seatsi]
 意味着在从 firsti到 lasti
 （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi个座位。
 请你返回一个长度为 n 的数组answer，里面的元素是每个航班预定的座位总数。
 测试链接 : <a href="https://leetcode.cn/problems/corporate-flight-bookings/">...</a>
 */
public class Lc1109CorporateFlightBookings {
    public static class Solution {
        public static int[] corpFlightBookings(int[][] bookings, int n) {
            int[] cnt = new int[n + 2];
            // 设置差分数组，每一个操作对应两个设置
            for(int[] book : bookings) {
                cnt[book[0]]        += book[2];
                cnt[book[1] + 1]    -= book[2];
            }
            // 加工前缀和
            for (int i = 1; i < cnt.length; i++)
                cnt[i] += cnt[i - 1];

            int[] ans = new int[n];
            System.arraycopy(cnt, 1, ans, 0, n);
            return ans;
        }
    }
}
