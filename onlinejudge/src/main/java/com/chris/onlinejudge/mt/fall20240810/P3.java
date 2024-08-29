package com.chris.onlinejudge.mt.fall20240810;

import java.util.*;

/*
小美的彩带是由一条长度为 n 的彩带一直无限循环得到的，彩带的每一个位置都有一个颜色，用 ai 表示。
因此, 当 i>n 时，ai = ai-n 。
小美每次会从左往后或从右往左剪一段长度为 x 的彩带，
她想知道她每次剪下来的彩带有多少种颜色。

第一行输入两个整数 n,q(1<=n,q<=2*10^5) 代表彩带长度、剪彩带次数。
第二行输入 n 个整数 a1,a2,...,an(1<=ai<=10^9) 代表彩带每一个位置的颜色。
此后 q 行，每行输入一个字符 c 和一个整数 x(1<=x<=10^9; c∈L,R) 代表裁剪方向和裁剪长度，
    其中 'L' 说明从左往右剪， 'R' 说明从右往左剪 。

对于每一次裁剪彩带，在一行上输出一个整数代表颜色数量。

6 3
1 1 4 5 1 4
L 2
L 3
R 12

1
3
3

第一次剪彩带，剪下来的是[1,1]，有{1}这 1 种颜色；
第二次剪彩带，剪下来的是[4,5,1]，有{1,4,5}这 3 种颜色；
第三次剪彩带，剪下来的是[1,1,4,5,1,4,1,1,4,5,1,4]，有{1,4,5}这 3 种颜色。
 */
public class P3 {
    private static final int MAXN = 2000007;

    private static final long[] a   = new long[MAXN];
    private static final long[] ans = new long[MAXN];
    private static final long[] hsh = new long[MAXN];
    private static final long[] vis = new long[MAXN];
    private static final long[] c   = new long[MAXN];

    private static class Query {
        int l, r, id;

        Query(int l, int r, int id) {
            this.l = l;
            this.r = r;
            this.id = id;
        }
    }

    private static int lowbit(int x) {
        return x & -x;
    }

    private static void update(int x, int k) {
        for (int i = x; i < MAXN; i += lowbit(i))
            c[i] += k;
    }

    private static long getSum(int x) {
        long sum = 0;
        for (int i = x; i > 0; i -= lowbit(i))
            sum += c[i];
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();

        List<Query> queries = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextLong();
            hsh[i] = a[i];
        }

        Arrays.sort(hsh, 1, n + 1);
        int len = 1;
        for (int i = 2; i <= n; i++)
            if (hsh[i] != hsh[len])
                hsh[++len] = hsh[i];

        for (int i = 1; i <= n; i++) {
            a[i] = Arrays.binarySearch(hsh, 1, len + 1, a[i]) + 1;
            a[n + i] = a[i];
        }

        int nl = 1, nr = n * 2;
        for (int i = 1; i <= q; i++) {
            char c = sc.next().charAt(0);

            int x   = sc.nextInt();
            x       = Math.min(x, n);

            if (c == 'L') {
                if (nl > n)
                    nl -= n;
                queries.add(new Query(nl, nl + x - 1, i));
                nl += x;
            } else {
                if (nr <= n)
                    nr += n;
                queries.add(new Query(nr - x + 1, nr, i));
                nr -= x;
            }
        }

        queries.sort(Comparator.comparingInt(a -> a.r));

        int cur = 1;

        for (Query query : queries) {
            for (int i = cur; i <= query.r; i++) {
                if (vis[(int) a[i]] > 0)
                    update((int) vis[(int) a[i]], -1);
                update(i, 1);
                vis[(int) a[i]] = i;
            }
            cur = query.r + 1;
            ans[query.id] = getSum(query.r) - getSum(query.l - 1);
        }

        for (int i = 1; i <= q; i++)
            System.out.println(ans[i]);
    }
}
