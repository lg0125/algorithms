package com.chris.algorithms.leetcode.dp.bit;

/** 最大为N的数字组合
 给定一个按 非递减顺序 排列的数字数组 digits
 已知digits一定不包含'0'，可能包含'1' ~ '9'，且无重复字符
 你可以用任意次数 digits[i] 来写的数字
 例如，如果 digits = ['1','3','5']
 我们可以写数字，如 '13', '551', 和 '1351315'
 返回 可以生成的小于或等于给定整数 n 的正整数的个数
 测试链接 : <a href="https://leetcode.cn/problems/numbers-at-most-n-given-digit-set/">...</a>
 */
public class Lc902NumbersAtMostGivenDigitSet {
    public static class Solution {
        public static int atMostNGivenDigitSet2(String[] strs, int num) {
            int m = strs.length;

            int[] digits = new int[m];
            for (int i = 0; i < m; i++)
                digits[i] = Integer.parseInt(strs[i]);

            int len = 1;
            int offset = 1;
            int tmp = num / 10;
            while (tmp > 0) {
                tmp /= 10;
                len++;
                offset *= 10;
            }

            // cnt[i] : 已知前缀比num小，剩下i位没有确定，请问前缀确定的情况下，一共有多少种数字排列
            // cnt[0] = 1，表示后续已经没有了，前缀的状况都已确定，那么就是1种
            // cnt[1] = m
            // cnt[2] = m * m
            // cnt[3] = m * m * m
            // ...
            int[] cnt = new int[len];
            cnt[0] = 1;

            int ans = 0;
            for (int i = m, k = 1; k < len; k++, i *= m) {
                cnt[k] = i;
                ans += i;
            }
            return ans + f2(digits, cnt, num, offset, len);
        }

        // offset是辅助变量，由len确定，方便提取num中某一位数字
        // 还剩下len位没有决定，之前的位和num一样
        // 返回最终<=num的可能性有多少种
        public static int f2(int[] digits, int[] cnt, int num, int offset, int len) {
            if (len == 0)
                // num自己
                return 1;

            // cur是num当前位的数字
            int cur = (num / offset) % 10;
            int ans = 0;
            for (int i : digits) {
                if (i < cur)
                    ans += cnt[len - 1];
                else if (i == cur)
                    ans += f2(digits, cnt, num, offset / 10, len - 1);
                else
                    break;
            }
            return ans;
        }
    }
}
