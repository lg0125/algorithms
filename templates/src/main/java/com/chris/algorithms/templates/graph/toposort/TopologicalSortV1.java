package com.chris.algorithms.templates.graph.toposort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSortV1 {
    List<List<Integer>> edges;

    // DFS
    int[] visited;
    boolean valid = true;

    // BFS
    int[] indeg;
    Queue<Integer> queue;

    public TopologicalSortV1(int[][] info, int n) {
        visited = new int[n];
        indeg   = new int[n];

        edges = new ArrayList<>();
        for(int i = 0; i < n; ++i)
            edges.add(new ArrayList<>());
        for(int[] edge : info) {
            int u = edge[0], v = edge[1];

            edges.get(v).add(u);
            ++indeg[u];
        }

        queue = new LinkedList<>();
        for(int u = 0; u < n; ++u)
            if(indeg[u] == 0) queue.offer(u);
    }

    public boolean isValidByBFS(int n) {
        int visit = 0;
        while(!queue.isEmpty()) {
            ++visit;

            int u = queue.poll();
            for(int v : edges.get(u)) {
                --indeg[v];
                if(indeg[v] == 0) queue.offer(v);
            }
        }
        return visit == n;
    }

    public boolean isValidByDFS() {
        int n = edges.size();
        for(int u = 0; u < n && valid; ++u)
            if(visited[u] == 0) dfs(u);
        return valid;
    }

    private void dfs(int u) {
       visited[u] = 1;
       for(int v : edges.get(u)) {
           if(visited[v] == 0) {
               dfs(v);
               if(!valid) return;
           } else if(visited[v] == 1) {
               valid = false;
               return;
           }
       }
       visited[u] = 2;
    }
}
