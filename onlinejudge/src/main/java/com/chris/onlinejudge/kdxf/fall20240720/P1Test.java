package com.chris.onlinejudge.kdxf.fall20240720;

import java.util.Arrays;
import java.util.Scanner;

public class P1Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long[] a = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextLong();
        Arrays.sort(a);

        int mid = n / 2;
        int i   = mid - (1 - (n % 2));
        int j   = mid;

        StringBuilder res = new StringBuilder();
        while (i >= 0 && j < n) {
            res.append(a[i]).append(" ");
            if (i != j)
                res.append(a[j]).append(" ");
            i--;
            j++;
        }
        System.out.println(res.toString().trim());
    }
}
