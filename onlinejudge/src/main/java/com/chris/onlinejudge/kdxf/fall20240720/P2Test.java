package com.chris.onlinejudge.kdxf.fall20240720;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P2Test {
    private static final int MOD = 1_000_000_007;

    private static long qmi(long a, long b) {
        long res = 1 % MOD;
        while(b > 0) {
            if((b & 1) == 1)
                res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Map<Character, Integer> cnt = new HashMap<>();

        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        for(char c : s.toCharArray())
            cnt.put(c, cnt.getOrDefault(c, 0) + 1);

        int n = s.length();

        long res = 0;
        for (Map.Entry<Character, Integer> entry : cnt.entrySet()) {
            int r = entry.getValue();
            res = (res + (qmi(2, r) - 1) * qmi(2, n - r)) % MOD;
        }
        System.out.println(res);
    }
}
