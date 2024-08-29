package com.chris.onlinejudge.ali.spring20240420;

import java.util.*;

/*
小杰在灵犀帝国已经休息许久了，现在回想起进入灵犀帝国的那—天都感到无比幸运。
在一个雷雨交加的晚上，小杰听到了悠扬的歌声。
小杰追寻着声音遇到了—位和蔼的老者，他笑着跟小杰说:“进入灵犀帝国的机会已经来临了。
"接着他给了一副地图给小杰，地图上有两个光点，一个是目前小杰身处的位置，一个是灵犀帝国所在的位置。
在这两个点之间还存在n-2个地点与m条路，每条路上都有一个能量值w。
小杰疑惑的看着老者，老者又说:“去找吧，找到那条最大能量与最小能量比值最小的路，你就能找到神秘的灵犀帝国。”

第—行—个正整数T(T<10)表示数据组数
第二行两个正整数，n和m
第三行两个正整数，s和t
第四行开始有m行，每行三个整数，x，y,w(表示x到y有—条能量值是w的路)

如果不存在到灵犀帝国的路输出"%%%",否则输出最小能量值比(要求最简分数)。

本题所有边均为无向边 对于30%的数据3<=n<= 50,0 <m <= 100,0 <w<10000对于100%的数据3<=n<= 200,0 <m <= 1000,0 <w<10000

3
5 5
1 5
1 2 1
1 3 6
2 3 2
2 5 5
2 4 10
8 7
1 7
1 2 1
2 5 1
2 3 1
2 4 1
3 4 1
5 6 1
7 8 1
10 12
3 10
1 2 2
1 3 6
1 6 3
2 7 5
2 6 1
2 5 3
3 6 5
3 7 7
5 6 6
6 9 9
7 9 11
9 10 13

3
%%%
13/7

可以使用枚举+并查集的想法，
    将所有的边按照权值从小到大排序，枚举边的权值（作为最小值），
    然后将大于它的边逐步加入并查集，并在每一步检查s和t的连通性
    如此以来，在枚举边权最小值的情况下，可以找到满足联通条件的最小最大权值。
注意输出的时候要求最简分数，使用gcd
*/
public class P5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int s = sc.nextInt();
            int t = sc.nextInt();

            List<int[]> g = new ArrayList<>();
            for (int i = 0; i < m; ++i) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();
                g.add(new int[]{u, v, w});
            }
            g.sort(Comparator.comparingInt(e -> e[2]));

            UnionFind uf = new UnionFind(n + 1);

            long a = -1, b = -1;
            for (int i = 0; i < m; ++i) {
                uf.clear();
                int j;
                for (j = i; j < m; ++j) {
                    uf.union(g.get(j)[0], g.get(j)[1]);
                    if (uf.find(s) == uf.find(t)) break;
                }
                if (j >= m) continue;
                if (a == -1 || a * g.get(i)[2] > b * g.get(j)[2]) {
                    a = g.get(j)[2];
                    b = g.get(i)[2];
                }
            }

            if (a == -1) {
                System.out.println("%%%");
            } else {
                long gcd = gcd(a, b);
                a /= gcd;
                b /= gcd;
                if (b == 1) {
                    System.out.println(a);
                } else {
                    System.out.println(a + "/" + b);
                }
            }
        }
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static class UnionFind {
        private final int[] parent;
        private final int[] rank;
        int n;

        public UnionFind(int _n) {
            n = _n;

            rank = new int[n];
            parent = new int[n];

            for(int i = 0; i < n; ++i) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int u) {
            return (parent[u] == u) ? u : (parent[u] = find(parent[u]));
        }

        public void union(int u, int v) {
            int ru = parent[u], rv = parent[v];

            if(ru == rv) return;

            if(rank[ru] < rank[rv]) {
                int tmp = ru;
                ru = rv;
                rv = tmp;
            }

            parent[rv] = ru;
            rank[ru] += rank[rv];
        }

        public void clear() {
            for(int i = 0; i < n; ++i) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
    }
}
