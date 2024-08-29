package com.chris.algorithms.templates.graph.toposort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSortV2 {
    List<List<Integer>> edges; // 存储有向图
    int[] result; // 用数组来模拟栈，下标 n-1 为栈底，0 为栈顶
    int index; // 栈下标

    // DFS
    int[] visited; // 标记每个节点的状态：0=未搜索，1=搜索中，2=已完成
    boolean valid = true; // 判断有向图中是否有环

    // BFS
    int[] indeg; // 存储每个节点的入度
    Queue<Integer> queue;

    public TopologicalSortV2(int[][] info, int n) {
        // DFS
        visited = new int[n];
        // BFS
        indeg   = new int[n];
        queue   = new LinkedList<>();

        result  = new int[n];
        edges   = new ArrayList<>();
        for(int i = 0; i < n; ++i)
            edges.add(new ArrayList<>());
        for(int[] edge : info) {
            int u = edge[0], v = edge[1];

            edges.get(v).add(u);
            ++indeg[u];
        }

        for(int u = 0; u < n; ++u)
            if(indeg[u] == 0) queue.offer(u);
    }

    public int[] getTopologicalSortByBFS() {
        int n   = edges.size();
        index   = 0;

        while (!queue.isEmpty()) {
            // 从队首取出一个节点
            int u = queue.poll();
            // 放入答案中
            result[index++] = u;
            for (int v: edges.get(u)) {
                --indeg[v];
                // 如果相邻节点 v 的入度为 0，就可以选 v 对应的课程了
                if (indeg[v] == 0) queue.offer(v);
            }
        }

        if(index != n) return new int[0];
        return result;
    }

    public int[] getTopologicalSortByDFS() {
        int n = edges.size();
        index = n-1;

        // 每次挑选一个「未搜索」的节点，开始进行深度优先搜索
        for (int u = 0; u < n && valid; ++u)
            if(visited[u] == 0) dfs(u);

        if(!valid) return new int[0];
        return result; // 如果没有环，那么就有拓扑排序
    }

    private void dfs(int u) {
        // 将节点标记为「搜索中」
        visited[u] = 1;
        // 搜索其相邻节点
        // 只要发现有环，立刻停止搜索
        for (int v: edges.get(u)) {
            if(visited[v] == 0) { // 如果「未搜索」那么搜索相邻节点
                dfs(v);
                if (!valid) return;
            } else if(visited[v] == 1) {  // 如果「搜索中」说明找到了环
                valid = false;
                return;
            }
        }
        // 将节点标记为「已完成」
        visited[u] = 2;
        // 将节点入栈
        result[index--] = u;
    }
}
