package com.chris.onlinejudge.mihoyo.spring20240310;

import java.util.*;

/**
 仙舟罗浮上有一棵建木，据说服下建木生成的神实就可以得到“无尽形寿”的身体，蜕变为长生种。米小游是短生种，因此她很想找到神实。
 建木是一棵有 n 个节点的有根树，节点编号为1-n ，根节点为x 。
 对于编号为i 的节点f[i] 表示以i 为根的子树中，节点编号是i 的因子的节点个数。
 建木上神实的总数就是 Σf[i] ，米小游想知道建木上神实的总数是多少。

 第一行包含两个整数 n,x(1<=x<=n<=5*10^5)，表示树的节点个数，根节点编号。
 接下来n-1 行，每行两个整数 u, v(1<=u,v<=n) ，表示一条u 到v 的树边。
 数据保证一定是一棵树。

 输出包含一个整数，表示建木上神实的总数。

4 4
1 2
4 3
2 4

 7

 说明
 以节点4 为根的子树的节点有 [1,2,3,4] ，其中[1,2,4] 是4的因子f[4]=3。
 以节点 2 为根的子树的节点有 [1,2] ，其中[1,2] 是2的因子f[2]=2。
 以节点1 为根的子树的节点有[1] ，其中 [1] 是1的因子，f[1]=1。
 以节点3 为根的子树的节点有[3] ，其中[3] 是3的因子f[3]=1。
 3+2+1+1=7

 树上的回溯
 计算每一个节点的贡献值即可。每个节点贡献值取决于上层所有的父节点的因子数量，
 比如当前是2，那么父节点有x个数字是包含因子2的，那么该节点的贡献是x
 注意，对于每次增加了节点的因子是时候，递归结束需要回溯
 遍历树的复杂度是O(n)，求因子是复杂度是O(n^1/2)，于是复杂度是(n^3/2)
 */
public class P3 {
    public static Map<Integer, List<Integer>> g = new HashMap<>();
    public static Map<Integer, Integer> cnt = new HashMap<>();

    public static long ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), x = sc.nextInt();

        for(int i = 1; i <= n; ++i)
            g.put(i, new ArrayList<>());
        for(int i = 1; i <= n-1; ++i) {
            int u = sc.nextInt(), v = sc.nextInt();

            g.get(u).add(v);
            g.get(v).add(u);
        }

        dfs(x, -1);

        System.out.println(ans);
    }

    private static void dfs(int u, int fa) {
        Set<Integer> set = divisors(u);

        for(int div : set)
            cnt.put(div, cnt.getOrDefault(div, 0) + 1);

        ans += cnt.getOrDefault(u, 0);
        for(int v : g.get(u))
            if(v != fa) dfs(v, u);

        for(int div : set)
            cnt.put(div, cnt.get(div) - 1);
    }

    private static Set<Integer> divisors(int num) {
        Set<Integer> res = new HashSet<>();
        for(int i = 1; i * i <= num; ++i) {
            if(num % i == 0) {
                res.add(i);
                res.add(num / i);
            }
        }
        return res;
    }
}
