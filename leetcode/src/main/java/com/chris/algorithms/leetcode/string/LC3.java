package com.chris.algorithms.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, find the length of the longest
 * substring without repeating characters.
 * <p>
 * "abcabcbb"
 * 3
 * "pwwkew"
 * 3
 */
public class LC3 {
    public static int lengthOfLongestSubstring(String str) {
        Map<Character, Integer> window = new HashMap<>();

        char[] s = str.toCharArray();
        int n = s.length;

        int res = 0;
        int l = 0, r = 0;
        while (r < n) {
            char ch1 = s[r++];
            window.put(ch1, window.getOrDefault(ch1, 0) + 1);

            while (window.get(ch1).compareTo(1) > 0) {
                char ch2 = s[l++];
                window.put(ch2, window.get(ch2) - 1);
            }

            res = Math.max(res, r - l);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
