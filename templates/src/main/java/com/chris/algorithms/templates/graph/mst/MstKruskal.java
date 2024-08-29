package com.chris.algorithms.templates.graph.mst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MstKruskal {
    List<Edge> edges;

    UnionFind uf;

    public MstKruskal(int[][] info, int n) {
        uf = new UnionFind(n);

        edges = new ArrayList<>();
        for (int u = 0; u < n; u++)
            for (int v = u + 1; v < n; v++)
                edges.add(new Edge(u, v, info[u][v]));
        edges.sort(Comparator.comparingInt(e -> e.w));
    }

    public List<Edge> getMST() {
        List<Edge> mst = new ArrayList<>();
        int cnt = 1, n = edges.size();
        for (Edge edge : edges) {
            int u = edge.u, v = edge.v, w = edge.w;
            if(uf.union(u, v)) {
                mst.add(new Edge(u, v, w));

                ++cnt;
                if(cnt == n) break;
            }
        }
        return mst;
    }

    public static class Edge {
        int u, v;
        int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}
