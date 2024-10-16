package com.chris.onlinejudge.jd.fall20240810;

import java.util.Scanner;

public class P3Test {

    private static long f(long x) {
        long cnt = 0;
        while(x > 0) {
            if((x & 1) == 1)
                cnt++;
            x >>= 1;
            cnt++;
        }
        return cnt - 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while(T-- > 0) {
            int n = sc.nextInt();

            long[] b = new long[n];
            for(int i = 0; i < n; ++i)
                b[i] = sc.nextLong();

            long[] cnt = new long[n];
            for(int i = 0; i < n; ++i)
                cnt[i] = f(b[i]);

            long ans = 0;
            for(int i = 1; i < n; ++i)
                if (cnt[i] > cnt[i - 1])
                    ans += cnt[i] - cnt[i - 1];
            System.out.println(ans + cnt[0]);
        }
    }
}
