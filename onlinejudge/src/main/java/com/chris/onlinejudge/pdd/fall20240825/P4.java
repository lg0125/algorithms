package com.chris.onlinejudge.pdd.fall20240825;

import java.util.Scanner;

/*
给定长度为n的01串，定义一次操作为，将整个字符串按顺序分为两部分，将两部分各自翻转后再按原顺序拼接。
提问，进行任意次的操作后，可以得到的最长的连续的01交替的子串有多长

例：原01串为01001，可以先将原串分为010和01两部分，分别翻转得到010和10,
按原顺序拼接后得到01010，此时最长的连续交替子串为01010，长度为5

第一行输入n 表示输入的01串的长度 2*10^6
第二行输入长度为n的01串

输出一个数字表示可能得到的最长的交替01子串的长度

5
10010

5

原字符串分为10和010，分别翻转得到01和010，拼接后为01010

可以将字符串看成一个环(首尾相连)，那么任意一次操作都可以在环上找到对应的子串，
于是就可以化环为链，在环上找最长的连续交替01串。

 */
public class P4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        String s = scanner.next();
        int ans = 1;

        s += s; // Convert the string into a circular string

        int tmp = 1;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                tmp = 1;
            } else {
                ++tmp;
                ans = Math.max(ans, tmp);
            }
        }

        System.out.println(Math.min(ans, n)); // Longest substring length should not exceed n
    }
}
