package com.chris.onlinejudge.mihoyo.fall20240803;

/*
米小游和派蒙在进行一场游戏。游戏在一个基环树（点数与边数相等的无向简单连通图）上进行，定义图中一个点的度数为与其相连的边数，二人轮流进行以下操作：
选择图中一个度数为 1 的点，删除这个点以及与这个点相连的边。
图中有一个特殊的点 x ，删除了点 x 的玩家即获得胜利。
现在，由米小游先进行操作。在双方都采取最优策略的情况下，胜者是谁？


每个测试文件均包含多组测试数据。第一行输入一个整数 T(1<=T<=1000) 代表数据组数，每组测试数据描述如下：
第一行输入两个整数 n,x(3<=n<=10^5, 1<=x<=n) 表示图的点数及特殊点的编号。
此后 n 行，第 i 行输入两个整数 ui 和 vi (1<=vi,ui<=n ; ui!=vi) 表示树上第 i 条边连接节点 ui 和 vi 。保证图联通，没有重边。
除此之外，保证给定的边构成一个基环树，所有的 n 之和不超过 2*10^5 。


对于每一组测试数据，在一行上输出胜者的名字（ Xiaoyo 或 Pyrmont ）。特别地，若点 x 不可能被删除，请输出 Draw 。


3
4 2
1 2
1 3
1 4
3 4
5 2
1 2
1 3
1 4
3 4
2 5
3 1
1 2
1 3
2 3

Xiaoyo
Pyrmont
Draw

拓扑排序+GTO博弈论
所谓的基环树指的是：在一棵树的基础上加上一个环
以后大家看到这种：每个人都会按照最优策略进行选择，最后判断谁会获胜。
    这种字眼的时候，基本就可以确定是一个GTO（博弈论）的题目。
    基本的做题思路就是找到一个规律可以直接得出结论的。

对于这道题，有一个显而易见的结论，
如果x在环中，
    那么无论如何删点都不可能删的掉，因此必然是Draw。
如果点不在环中呢？
    我们可以考虑在删除x点之前（包括x），有多少个节点是可以删除的？假设这个值是cnt。
    如果cnt是偶数的话，那么Xiaoyo作为先选取的一方，一定是无法删除这个点的。因为双方的操作是对称的。
    反之，则是Pyrmont获胜。

因此大题思路与拓扑排序类似，
    不断地将度数为1的节点加入队列，
    记录在删除x节点之前最多可以访问的节点数（包括x节点）
    最后判断x的奇偶性即可

需要注意的是
    如果x节点是在环中的，那么我们永远无法遍历到这个节点，此时必然是Draw。
    如果x节点的度数初始值就是1，那么此时Xiaoyo获胜。
*/

import java.util.*;

public class P3 {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int T = sc.nextInt();
        while(T-- > 0)
            solve();
    }

    private static void solve() {
        int n = sc.nextInt();
        int x = sc.nextInt();

        // 邻接表
        List<List<Integer>> g = new ArrayList<>(n+1);
        for(int i = 0; i <= n; ++i)
            g.add(new ArrayList<>());

        // 无向图
        int[] in = new int[n+1];
        for(int i = 0; i < n; ++i) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            g.get(a).add(b);
            g.get(b).add(a);

            in[a]++;
            in[b]++;
        }

        Deque<Integer> q = new ArrayDeque<>();
        for(int u = 1; u <= n; ++u) {
            if(in[u] == 1) {
                if(u == x) {
                    System.out.println("Xiaoyo");
                    return;
                }
                q.add(u);
            }
        }

        int cnt = 0;
        boolean flag = false;
        while(!q.isEmpty()) {
            cnt++;

            int u = q.poll();
            if(u == x) {
                flag = true;
                continue;
            }

            for(int v : g.get(u)) {
                in[v]--;

                if(in[v] == 1)
                    q.offer(v);
            }
        }

        if (!flag) {
            System.out.println("Draw");
        } else if (cnt % 2 == 0) {
            System.out.println("Pyrmont");
        } else {
            System.out.println("Xiaoyo");
        }
    }
}
