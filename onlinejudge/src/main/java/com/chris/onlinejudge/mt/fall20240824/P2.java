package com.chris.onlinejudge.mt.fall20240824;

import java.util.Arrays;
import java.util.Scanner;

public class P2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long[] arr = new long[3];
        arr[0] = sc.nextLong();
        arr[1] = sc.nextLong();
        arr[2] = sc.nextLong();

        long k = sc.nextLong();
        long mod = 1000000007;

        arr[0] %= mod;
        arr[1] %= mod;
        arr[2] %= mod;

        Arrays.sort(arr);

        long needed = (arr[2] - arr[0]) + (arr[2] - arr[1]);

        if (k >= needed) {
            k -= needed;
            arr[0] = arr[2];
            arr[1] = arr[2];

            arr[0] += k / 3;
            arr[1] += k / 3;
            arr[2] += k / 3;

            long remainder = k % 3;
            if (remainder == 1) {
                arr[2] += 1;
            } else if (remainder == 2) {
                arr[1] += 1;
                arr[2] += 1;
            }
        } else {
            if (k <= arr[1] - arr[0]) {
                arr[0] += k;
            } else {
                arr[0] = arr[1];
                arr[0] += k / 2 + k % 2;
                arr[1] += k / 2;
            }
        }

        long result = (((arr[0] % mod) * (arr[1] % mod) % mod) * (arr[2] % mod)) % mod;

        System.out.println(result);
    }
}
