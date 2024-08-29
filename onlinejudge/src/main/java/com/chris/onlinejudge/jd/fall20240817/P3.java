package com.chris.onlinejudge.jd.fall20240817;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P3 {
    private static final int INF = 20220201;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int a = sc.nextInt();

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int v = sc.nextInt();
            int u = sc.nextInt();
            int w = sc.nextInt();
            graph.get(v).add(new int[]{u, w});
        }

        int[][] dp      = new int[n + 1][a + 1];
        boolean[][] st  = new boolean[n + 1][a + 1];

        dp[1][0] = 1;

        for (int i = 0; i < a; i++)
            for (int u = 1; u <= n; u++)
                if (dp[u][i] > 0 || st[u][i]) {
                    for (int[] pair : graph.get(u)) {
                        int v = pair[0], w = pair[1];

                        if (i + w <= a) {
                            dp[v][i + w] = (dp[v][i + w] + dp[u][i]) % INF;

                            if (dp[v][i + w] + dp[u][i] >= INF)
                                st[v][i + w] = true;
                            if (st[u][i])
                                st[v][i + w] = true;
                        }
                    }
                }

        if (st[n][a])
            System.out.println("All roads lead to Home!");
        System.out.println(dp[n][a]);

    }
}
