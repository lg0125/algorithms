package com.chris.onlinejudge.ali.spring20240506;

import java.util.Scanner;

/*
小红有一个数组，她想知道这个数组有多少个子序列的异或和为0。

第一行，一个 n，表示输入元素的种类，n在1到20之间
第二行输入 n 个整数表示数组有ai个数字

长度为n的、仅由小写字母组成的所有字符串的权值之和，答案对1e9+7取模。

3
1 2 1

3
数组为[1 2 2 3]
满足要求的子序列有：[1 2 3]、[1 2 3]、[2 2]三个

由于n的取值范围是1至20， 可以用二进制枚举每一种数字是选还是不选，
每种数字取偶数个和取奇数个的可能性都是2^ai-1，由于ai很大，这里要用快速幂求。
最后记得减去所有数字都不选的情况（1种）
 */
public class P3 {
    private static final long MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] a = new int[n+1];
        for(int i = 0; i < n; ++i)
            a[i] = sc.nextInt();

        long res = 0;
        for (long i = 0; i < (1L << n); i++) {
            long ans = 0;
            for (int j = 0; j < n; j++)
                if ((i >> j & 1) == 1)
                    ans ^= (j + 1);

            long t = 1;
            if (ans == 0) {
                for (int k = 1; k <= n; k++)
                    t = (t * pow(2L, a[k] - 1)) % MOD;
                res = (res + t) % MOD;
            }
        }
        System.out.println((res - 1 + MOD) % MOD);
    }

    private static long pow(long x, long n) {
        long res = 1;
        while(n > 0) {
            if ((n & 1) == 1)
                res = res * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }
}
