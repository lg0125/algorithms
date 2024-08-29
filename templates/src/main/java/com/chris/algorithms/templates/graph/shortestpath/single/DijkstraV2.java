package com.chris.algorithms.templates.graph.shortestpath.single;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DijkstraV2 {
    // 邻接表
    int[] he; // 存储是某个节点所对应的边的集合（链表）的头结点
    int[] e; // 用于访问某一条边指向的节点
    int[] ne; // 用于是以链表的形式进行存边，该数组就是用于找到下一条边
    int[] w; // 用于记录某条边的权重为多少

    int idx; // 用来对边进行编号的

    int[] dist; // dist[x] = y 代表从「源点/起点」到 x 的最短距离为 y

    boolean[] vis; // 记录哪些点已经被更新过

    int INF = 0x3f3f3f3f;

    public DijkstraV2(int[][] info, int n) {
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
        vis     = new boolean[n];
        Arrays.fill(vis, false);
    }

    public int[] getShortestPath(int k) {
        // 只有起点最短距离为 0
        dist[k] = 0;

        // 使用「优先队列」存储所有可用于更新的点
        // 以 (点编号, 到起点的距离) 进行存储，优先弹出「最短距离」较小的点
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        queue.add(new int[]{k, 0});

        while (!queue.isEmpty()) {
            // 每次从「优先队列」中弹出
            int[] poll = queue.poll();
            int id = poll[0], step = poll[1];

            // 如果弹出的点被标记「已更新」，则跳过
            if (vis[id]) continue;

            // 标记该点「已更新」，并使用该点更新其他点的「最短距离」
            vis[id] = true;

            for (int i = he[id]; i != -1; i = ne[i]) {
                int j = e[i];

                if (dist[j] > dist[id] + w[i]) {
                    dist[j] = dist[id] + w[i];
                    queue.add(new int[]{j, dist[j]});
                }
            }
        }

        return dist;
    }
}
