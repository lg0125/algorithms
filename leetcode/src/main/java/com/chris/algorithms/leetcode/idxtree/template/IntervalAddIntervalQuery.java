package com.chris.algorithms.leetcode.idxtree.template;

// 树状数组范围增加、范围查询模版
// 测试链接 : https://www.luogu.com.cn/problem/P3372
public class IntervalAddIntervalQuery {
    public static int MAXN = 100001;

    // 维护原始数组的差分信息：Di
    public static long[] info1 = new long[MAXN];

    // 维护原始数组的差分加工信息：(i-1) * Di
    public static long[] info2 = new long[MAXN];

    public static int n, m;

    public static int lowbit(int i) {
        return i & -i;
    }

    public static void add(long[] tree, int i, long v) {
        while (i <= n) {
            tree[i] += v;
            i += lowbit(i);
        }
    }

    public static long sum(long[] tree, int i) {
        long ans = 0;
        while (i > 0) {
            ans += tree[i];
            i -= lowbit(i);
        }
        return ans;
    }

    // 原始数组中[l..r]每个数值+v
    public static void add(int l, int r, long v) {
        add(info1, l, v);
        add(info1, r + 1, -v);
        add(info2, l, (l - 1) * v);
        add(info2, r + 1, -(r * v));
    }

    // 原始数组中[l..r]范围上的累加和
    public static long range(int l, int r) {
        return sum(info1, r) * r - sum(info2, r) - sum(info1, l - 1) * (l - 1) + sum(info2, l - 1);
    }
}
