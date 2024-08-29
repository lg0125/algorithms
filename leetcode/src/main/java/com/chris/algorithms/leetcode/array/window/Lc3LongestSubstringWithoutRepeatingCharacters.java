package com.chris.algorithms.leetcode.array.window;

import java.util.Arrays;

/** 无重复字符的最长子串
 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 测试链接 : <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/">...</a>
 */
public class Lc3LongestSubstringWithoutRepeatingCharacters {
    public static class Solution {
        public static int lengthOfLongestSubstring(String str) {
            // char -> int -> 0 ~ 255
            // 每一种字符上次出现的位置
            int[] last = new int[256];
            // 所有字符都没有上次出现的位置
            Arrays.fill(last, -1);

            int n = str.length();
            char[] s = str.toCharArray();
            // 不含有重复字符的 最长子串 的长度
            int ans = 0;
            for (int l = 0, r = 0; r < n; r++) {
                l   = Math.max(l, last[s[r]] + 1);
                ans = Math.max(ans, r - l + 1);

                // 更新当前字符上一次出现的位置
                last[s[r]] = r;
            }
            return ans;
        }
    }
}
