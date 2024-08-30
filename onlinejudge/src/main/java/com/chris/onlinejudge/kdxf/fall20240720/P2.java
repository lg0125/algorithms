package com.chris.onlinejudge.kdxf.fall20240720;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P2 {
    static final int MOD = 1000000007;

    public static long qmi(long a, long b) {
        long res = 1 % MOD;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        Map<Character, Integer> mp = new HashMap<>();
        for (char ch : s.toCharArray()) {
            mp.put(ch, mp.getOrDefault(ch, 0) + 1);
        }

        long res = 0;
        int n = s.length();
        for (Map.Entry<Character, Integer> entry : mp.entrySet()) {
            int r = entry.getValue();
            res = (res + (qmi(2, r) - 1) * qmi(2, n - r)) % MOD;
        }

        System.out.println(res);
    }
}
