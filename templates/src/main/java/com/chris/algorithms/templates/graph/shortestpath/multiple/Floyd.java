package com.chris.algorithms.templates.graph.shortestpath.multiple;

public class Floyd {
    // 邻接矩阵数组：w[a][b] = c 代表从 a 到 b 有权重为 c 的边
    int[][] w;

    int INF = 0x3f3f3f3f;

    public Floyd(int[][] info, int n) {
        // 初始化邻接矩阵
        w = new int[n][n];
        for(int u = 0; u < n; ++u)
            for(int v = 0; v < n; ++v)
                w[u][v] = w[v][u] = (u == v) ? 0 : INF;
        // 存图
        for(int[] edge : info) {
            int u = edge[0], v = edge[1], c = edge[2];

            w[u][v] = c;
        }
    }

    public int[][] getShortPath() {
        int n = w.length;
        // floyd 基本流程为三层循环：
        // 枚举中转点 - 枚举起点 - 枚举终点 - 松弛操作
        for (int p = 1; p <= n; p++)
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    w[i][j] = Math.min(w[i][j], w[i][p] + w[p][j]);
        return w;
    }
}
