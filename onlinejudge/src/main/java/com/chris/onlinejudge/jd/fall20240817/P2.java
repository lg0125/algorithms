package com.chris.onlinejudge.jd.fall20240817;

import java.util.Scanner;

public class P2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        String sn = sc.next();
        String sm = sc.next();

        int res = m + n;
        for (int j = -m; j < n; j++) { // Enumerate the starting point to be matched
            int idxm        = j;
            int cnt         = 0; // cnt represents the length less than or equal to 3 after the two strings overlap
            boolean flag    = true;
            int idxn        = Math.max(j, 0); // Starting point for matching the sn string

            for (int i = j; i < j + m; i++) { // Matching the sm string
                if (i < 0)
                    continue;
                if (idxn >= n)
                    break;
                if (sn.charAt(idxn) - '0' + sm.charAt(i - j) - '0' > 3) {
                    flag = false;
                    break;
                }

                idxn++;
                cnt++;
            }
            if (flag)
                res = Math.min(res, m + n - cnt);
        }
        System.out.println(res);
    }
}
