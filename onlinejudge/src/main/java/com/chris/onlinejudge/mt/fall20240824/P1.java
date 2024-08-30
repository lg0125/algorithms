package com.chris.onlinejudge.mt.fall20240824;

import java.util.Scanner;

public class P1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long a = sc.nextLong();
        long b = sc.nextLong();
        long c = sc.nextLong();
        long d = sc.nextLong();

        int n = sc.nextInt();

        long[] X = new long[n];
        long[] Y = new long[n];

        long ans = 0;
        int idx = -1;
        long mx = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            X[i] = sc.nextLong();
            Y[i] = sc.nextLong();
            long x = X[i];
            long y = Y[i];

            long t = Math.abs(x - c) + Math.abs(y - d);
            long dist = Math.abs(x - a) + Math.abs(y - b) - t;

            if (idx == -1 || dist < mx) {
                idx = i;
                mx = dist;
            }
        }

        for (int i = 0; i < n; i++) {
            long x = X[i];
            long y = Y[i];
            ans += 2 * (Math.abs(x - c) + Math.abs(y - d));
        }

        ans += mx;
        System.out.println(ans);
    }
}
