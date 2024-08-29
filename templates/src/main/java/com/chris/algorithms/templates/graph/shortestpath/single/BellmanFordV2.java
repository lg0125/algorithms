package com.chris.algorithms.templates.graph.shortestpath.single;

import java.util.Arrays;

public class BellmanFordV2 {
    // 邻接表
    int[] he; // 存储是某个节点所对应的边的集合（链表）的头结点
    int[] e; // 用于访问某一条边指向的节点
    int[] ne; // 用于是以链表的形式进行存边，该数组就是用于找到下一条边
    int[] w; // 用于记录某条边的权重为多少

    int idx; // 用来对边进行编号的

    int[] dist; // dist[x] = y 代表从「源点/起点」到 x 的最短距离为 y

    int INF = 0x3f3f3f3f;

    public BellmanFordV2(int[][] info, int n) {
        e       = new int[n * (n-1) / 2];
        ne      = new int[n * (n-1) / 2];
        w       = new int[n * (n-1) / 2];
        he      = new int[n];
        // 初始化链表头
        Arrays.fill(he, -1);
        // 存图
        for(int[] edge : info) {
            int u = edge[0], v = edge[1], c = edge[2];

            e[idx]  = v;
            ne[idx] = he[u];
            he[u]   = idx;
            w[idx]  = c;
            ++idx;
        }

        dist    = new int[n];
        Arrays.fill(dist, INF);
    }

    public int[] getShortestPath(int k) {
        int n  = dist.length;

        // 只有起点最短距离为 0
        dist[k] = 0;

        // 迭代 n 次
        for (int p = 1; p <= n; p++) {
            int[] prev = dist.clone();

            // 每次都使用上一次迭代的结果，执行松弛操作
            for (int a = 1; a <= n; a++) {
                for (int i = he[a]; i != -1; i = ne[i]) {
                    int b = e[i];
                    dist[b] = Math.min(dist[b], prev[a] + w[i]);
                }
            }
        }

        return dist;
    }
}
