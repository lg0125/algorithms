package com.chris.algorithms.templates.graph.shortestpath.single;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BellmanFordV1 {
    // 使用类进行存边
    List<Edge> edges;

    // dist[x] = y 代表从「源点/起点」到 x 的最短距离为 y
    int[] dist;

    int INF = 0x3f3f3f3f;

    public BellmanFordV1(int[][] info, int n) {
        dist = new int[n];
        Arrays.fill(dist, INF);

        edges = new ArrayList<>();
        for(int[] edge : info) {
            int u = edge[0], v = edge[1], w = edge[2];

            edges.add(new Edge(u, v, w));
        }
    }

    public int[] getShortestPath(int k) {
        int n = dist.length;

        // 只有起点最短距离为 0
        dist[k] = 0;

        // 迭代 n 次
        for (int p = 0; p < n; p++) {
            int[] prev = dist.clone();

            // 每次都使用上一次迭代的结果，执行松弛操作
            for(Edge edge : edges) {
                int u = edge.u, v = edge.v, w = edge.w;

                dist[v] = Math.min(dist[v], prev[u] + w);
            }
        }

        return dist;
    }


    public static class Edge {
        int u, v , w;
        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}
