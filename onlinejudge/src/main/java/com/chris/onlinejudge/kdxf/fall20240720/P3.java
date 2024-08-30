package com.chris.onlinejudge.kdxf.fall20240720;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int t = 0; t < T; t++) {
            int n = scanner.nextInt();
            List<Integer> v = new ArrayList<>();
            for (int i = 31; i >= 0; i--) {
                if ((n >> i & 1) == 1) {
                    v.add(1 << i);
                }
            }
            System.out.println(v.size());
            for (int num : v) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
