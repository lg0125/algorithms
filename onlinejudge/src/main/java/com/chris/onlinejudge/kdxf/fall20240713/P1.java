package com.chris.onlinejudge.kdxf.fall20240713;

import java.util.Scanner;

public class P1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();

        for (int t = 0; t < T; t++) {
            int n = sc.nextInt();
            sc.nextLine();
            String s = sc.nextLine();

            int zero = -1, one = -1;
            int[] ans = new int[n];

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '1') {
                    ans[i] = zero;
                    one = i + 1;
                } else {
                    ans[i] = one;
                    zero = i + 1;
                }
            }

            for (int i = 0; i < n; i++) {
                System.out.print(ans[i]);
                if (i < n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
