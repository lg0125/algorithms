package com.chris.algorithms.templates.graph.shortestpath.single;

import java.util.Arrays;

public class DijkstraV1 {
    // 邻接矩阵数组：w[a][b] = c 代表从 a 到 b 有权重为 c 的边
    int[][] w;

    // dist[x] = y 代表从「源点/起点」到 x 的最短距离为 y
    int[] dist;

    // 记录哪些点已经被更新过
    boolean[] vis;

    int INF = 0x3f3f3f3f;

    public DijkstraV1(int[][] info, int n) {
        w = new int[n][n];
        // 初始化邻接矩阵
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                w[i][j] = w[j][i] = i == j ? 0 : INF;
        // 存图
        for(int[] edge : info) {
            int u = edge[0], v = edge[1], c = edge[2];
            w[u][v] = c;
        }

        // 起始先将所有的点标记为「未更新」
        vis = new boolean[n];
        Arrays.fill(vis, false);

        // 起始先将所有的点标记为「距离为正无穷」
        dist = new int[n];
        Arrays.fill(dist, INF);
    }

    public int[] getShortestPath(int k) {
        int n = w.length;

        // 只有起点最短距离为 0
        dist[k] = 0;

        // 迭代 n 次
        for (int p = 0; p < n; p++) {
            // 每次找到「最短距离最小」且「未被更新」的点 t
            int t = -1;
            for (int i = 0; i < n; i++)
                if (!vis[i] && (t == -1 || dist[i] < dist[t]))
                    t = i;

            // 标记点 t 为已更新
            vis[t] = true;

            // 用点 t 的「最小距离」更新其他点
            for (int i = 0; i < n; i++)
                dist[i] = Math.min(dist[i], dist[t] + w[t][i]);
        }

        return dist;
    }
}
