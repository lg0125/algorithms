package com.chris.onlinejudge.jd.fall20240817;

import java.util.Scanner;

public class P1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a   = sc.nextInt();
        int b   = sc.nextInt();
        int c   = b - a;
        int sum = (c + 1) * c / 2;
        System.out.println(sum - b);
    }
}
