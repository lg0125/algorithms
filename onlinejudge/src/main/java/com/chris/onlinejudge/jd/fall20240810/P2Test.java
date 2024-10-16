package com.chris.onlinejudge.jd.fall20240810;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P2Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<Long, Integer> cnt = new HashMap<>();

        int n   = sc.nextInt();
        long x  = sc.nextLong();

        long[] a = new long[n];
        for(int i = 0; i < n; ++i) {
            a[i] = sc.nextLong();
            cnt.put(a[i], cnt.getOrDefault(a[i], 0) + 1);
        }

        int ans = 0;
        for(int i = 0; i < n; ++i) {
            long y = x - a[i];
            ans += cnt.getOrDefault(y, 0);
        }
        System.out.println(ans);
    }
}
