package com.chris.algorithms.templates.graph.shortestpath.single;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Spfa {
    // 邻接表
    int[] he; // 存储是某个节点所对应的边的集合（链表）的头结点
    int[] e; // 用于访问某一条边指向的节点
    int[] ne; // 用于是以链表的形式进行存边，该数组就是用于找到下一条边
    int[] w; // 用于记录某条边的权重为多少

    int idx; // 用来对边进行编号的

    int[] dist; // dist[x] = y 代表从「源点/起点」到 x 的最短距离为 y

    boolean[] vis; // 记录哪些点已经被更新过

    int INF = 0x3f3f3f3f;

    public Spfa(int[][] info, int n) {
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

        // 使用「双端队列」存储，存储的是点编号
        Deque<Integer> queue = new ArrayDeque<>();

        // 将「源点/起点」进行入队，并标记「已入队」
        queue.addLast(k);
        vis[k] = true;

        while (!queue.isEmpty()) {
            // 每次从「双端队列」中取出，并标记「未入队」
            int poll = queue.pollFirst();
            vis[poll] = false;

            // 尝试使用该点，更新其他点的最短距离
            // 如果更新的点，本身「未入队」则加入队列中，并标记「已入队」
            for (int i = he[poll]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[poll] + w[i]) {
                    dist[j] = dist[poll] + w[i];

                    if (vis[j])
                        continue;

                    queue.addLast(j);
                    vis[j] = true;
                }
            }
        }

        return dist;
    }
}
