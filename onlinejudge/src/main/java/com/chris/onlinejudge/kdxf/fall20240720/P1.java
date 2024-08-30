package com.chris.onlinejudge.kdxf.fall20240720;

import java.util.Arrays;
import java.util.Scanner;

public class P1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        Arrays.sort(a);
        int mid = n / 2;
        int i = mid - (1 - (n % 2));
        int j = mid;

        StringBuilder result = new StringBuilder();
        while (i >= 0 && j < n) {
            result.append(a[i]).append(" ");
            if (i != j) {
                result.append(a[j]).append(" ");
            }
            i--;
            j++;
        }
        System.out.println(result.toString().trim());
    }
}
