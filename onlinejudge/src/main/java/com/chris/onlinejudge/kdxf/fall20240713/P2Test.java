package com.chris.onlinejudge.kdxf.fall20240713;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P2Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), k = sc.nextInt();

        Map<Long, Integer> cnt = new HashMap<>();
        for(int i = 0; i < n; ++i) {
            long a = sc.nextLong();
            cnt.put(a, cnt.getOrDefault(a, 0) + 1);
        }

        long ans = Long.MAX_VALUE;
        for (Map.Entry<Long, Integer> entry : cnt.entrySet())
            if(entry.getValue() <= k)
                ans = Math.min(ans, entry.getKey());
        System.out.println(ans == Long.MAX_VALUE ? -1 : ans);
    }
}
