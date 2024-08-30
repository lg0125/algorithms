package com.chris.onlinejudge.jd.fall20240824;

import java.util.Scanner;

/*
给你一个整数，请你判断0～N之间有多少个数是100的正整数倍。
输入描述：输入的第一行给出一个整数N
输出描述：输出0~N之间有多少个数是100的整数倍。

2000

20

答案为N整除100，注意负数
 */
public class P1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();

        int n = s.length();
        if(s.charAt(0) == '-') {
            System.out.println("0");
        } else {
            if(s.length() > 2) {
                System.out.println(s.substring(0, s.length() - 2));
            } else {
                System.out.println("0");
            }
        }
    }
}
