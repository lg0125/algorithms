package com.chris.algorithms.leetcode.idxtree.template;

// 树状数组单点增加、范围查询模版
// 测试链接 : https://www.luogu.com.cn/problem/P3374
public class SingleAddIntervalQuery {
    public static int MAXN = 500001;

    // 原始数组的信息，根据课上说的关系，维护在树状数组中
    // 注意下标一定从1开始，不从0开始
    public static int[] tree = new int[MAXN];

    public static int n, m;

    // 得到i最右侧的1的状态
    // 其他位都是0
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

    public static int range(int l, int r) {
        return sum(r) - sum(l - 1);
    }
}
