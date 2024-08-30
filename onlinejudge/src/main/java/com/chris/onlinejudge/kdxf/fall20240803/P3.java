package com.chris.onlinejudge.kdxf.fall20240803;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 ffmin[i]表示以i为根节点，满足父节点小于等于子节点的深度，
 ffmax[i]表示以i为根节点，满足父节点大于等于子节点的深度。
 最长链路无非就是2种情况：要
 么是以节点i为中心，找到节点i为根的两条长子链；
 要么是以节点i为边界，找出节点i为根的一条最长子链。
 */
public class P3 {
    private static class Pair {
        long first, second;
        Pair(long first, long second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        Pair[] f = new Pair[n];
        Pair[] g = new Pair[n];
        for (int i = 0; i < n; i++) {
            f[i] = new Pair(1, 1);
            g[i] = new Pair(1, 1);
        }
        dfs(0, -1, adj, a, f, g);
        long ans = 1;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, Math.max(f[i].first + g[i].first - 1, f[i].second + g[i].second - 1));
        }
        System.out.println(ans);
    }

    static void dfs(int u, int p, List<List<Integer>> adj, int[] a, Pair[] f, Pair[] g) {
        for (int v : adj.get(u)) {
            if (v == p) continue;
            dfs(v, u, adj, a, f, g);
            if (a[v] >= a[u]) {
                if (f[v].first + 1 >= f[u].first) {
                    g[u].first = f[u].first;
                    f[u].first = f[v].first + 1;
                } else {
                    g[u].first = Math.max(f[v].first + 1, g[u].first);
                }
            }
            if (a[v] <= a[u]) {
                if (f[v].second + 1 >= f[u].second) {
                    g[u].second = f[u].second;
                    f[u].second = f[v].second + 1;
                } else {
                    g[u].second = Math.max(f[v].second + 1, g[u].second);
                }
            }
        }
    }
}
