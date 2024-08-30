package com.chris.onlinejudge.kdxf.fall20240713;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int val : a) {
            countMap.put(val, countMap.getOrDefault(val, 0) + 1);
        }

        int ans = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() <= k) {
                ans = Math.min(ans, entry.getKey());
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
