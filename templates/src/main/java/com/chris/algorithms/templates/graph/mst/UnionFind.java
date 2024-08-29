package com.chris.algorithms.templates.graph.mst;

import java.util.Arrays;

public class UnionFind {
    int components;
    int[] parent;
    int[] rank;

    public UnionFind(int n) {
        components  = n;
        parent      = new int[n];
        rank        = new int[n];
        Arrays.fill(rank, 1);

        for(int u = 0; u < n; ++u)
            parent[u] = u;
    }

    public int find(int u) {
        return parent[u] == u ? u : (parent[u] = find(parent[u]));
    }

    public boolean union(int u, int v) {
        int rootU = find(u), rootV = find(v);

        if(rootU == rootV) return false;

        if(rank[rootU] < rank[rootV]) {
            int tmp = rootU;
            rootU = rootV;
            rootV = tmp;
        }

        parent[rootV] = rootU;
        rank[rootU] += rank[rootV];
        --components;
        return true;
    }

    public int getComponents() { return components; }

    public boolean isConnected(int u, int v) {
        return find(u) == find(v);
    }
}
