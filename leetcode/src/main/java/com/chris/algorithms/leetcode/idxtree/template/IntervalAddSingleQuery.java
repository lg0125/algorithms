package com.chris.algorithms.leetcode.idxtree.template;

// 树状数组范围增加、单点查询模版
// 测试链接 : https://www.luogu.com.cn/problem/P3368
public class IntervalAddSingleQuery {
    public static int MAXN = 500002;

    // 树状数组不维护原数组的信息，维护原数组的差分信息
    // 注意下标一定从1开始，不从0开始
    public static int[] tree = new int[MAXN];

    public static int n, m;

    public static int lowbit(int i) {
        return i & -i;
    }

    public static void add(int i, int v) {
        while (i <= n) {
            tree[i] += v;
            i += lowbit(i);
        }
    }

    // 返回1~i范围累加和
    public static int sum(int i) {
        int ans = 0;
        while (i > 0) {
            ans += tree[i];
            i -= lowbit(i);
        }
        return ans;
    }
}
