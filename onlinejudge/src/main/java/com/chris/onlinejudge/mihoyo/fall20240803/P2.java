package com.chris.onlinejudge.mihoyo.fall20240803;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
商店里有 n个商品，分别编号为 1~n ，每个商品都有一个价值 vali和体积 wi，米小游有一个有一个 m 容量的背包，他能够装得下任意多个体积之和不超过 m 的商品。
米小游认为有些东西一起购买会带来灾难，比如可莉的角色立牌和蹦蹦炸弹的小手办，所以他设定了 k组互斥关系，每组关系给定两个数字 a,b，表示编号为 a 的商品和编号为 b的商品不能同时购买。
米小游希望装下的物品的价值之和最大，请你帮帮他求出最大价值。

第一行输入三个整数 n,m,k(1<=n<=15;1<=m<=10^9;0<=k<=15)表示商品数量、背包容量和互斥关系数量。
接下来 n行，每行输入两个整数 wi,vali(1<=wi,vali<=10^9) 表示每个物品的体积和价值。
接下来 k行，每行输入两个整数 a,b(1<=a,b<=n,a≠b)，描述一组互斥关系。

在一行上输出一个整数表示答案。

3 100 2
15 19
20 30
15 19
1 2
2 3

38

3 20 2
15 19
20 30
15 19
1 2
2 3

30

暴力枚举+哈希表
首先观察题目中的n只有15，因此可以直接使用回溯来解。回溯的思路如下：
    每个物品有2种选择：选或者不选。无论什么情况下都可以不选择。关键是何时可以选择呢？
    必须保证当前的背包容量是足够的，并且已经选择的物品并不会与当前物品出现互斥关系，这个可以使用哈希来进行快速判断。
    不断更新所有可能的答案即可。
*/
public class P2 {
    private static int n, m, k;
    private static int[][] stocks; // {weight, value}
    private static Set<Integer>[] mutexes;

    private static int res = 0;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        stocks = new int[n][2];
        for(int i = 0; i < n; ++i) {
            stocks[i][0] = sc.nextInt(); // w
            stocks[i][1] = sc.nextInt(); // v
        }

        mutexes = new HashSet[n];
        for(int i = 0; i < n; ++i)
            mutexes[i] = new HashSet<>();
        for(int i = 0; i < k; ++i) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            mutexes[a].add(b);
            mutexes[b].add(a);
        }

        backtrack(0, 0, 0, new HashSet<>());

        System.out.println(res);
    }

    private static void backtrack(int i, int curValue, int curWeight, Set<Integer> track) {
        if(i >= n) {
            res = Math.max(res, curValue);
            return;
        }

        backtrack(i+1, curValue, curWeight, track);

        if(check(i, track) && curWeight + stocks[i][0] <= m) {
            track.add(i);

            backtrack(i+1, curValue + stocks[i][1], curWeight + stocks[i][0], track);

            track.remove(i);
        }
    }

    private static boolean check(int i, Set<Integer> track) {
        for(int v : track)
            if(mutexes[v].contains(i))
                return false;
        return true;
    }
}
