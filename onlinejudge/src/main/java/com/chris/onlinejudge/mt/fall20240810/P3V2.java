package com.chris.onlinejudge.mt.fall20240810;

import java.util.*;

public class P3V2 {
    private static final int MAXN = 2000007;

    private static final long[] a = new long[MAXN];

    private static final long[] hsh = new long[MAXN];

    private static final long[] vis = new long[MAXN];

    private static final long[] ans = new long[MAXN];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();
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

        List<Query> queries = new ArrayList<>();
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
                    add((int) vis[(int) a[i]], -1);
                add(i, 1);
                vis[(int) a[i]] = i;
            }
            cur = query.r + 1;
            ans[query.id] = sum(query.r) - sum(query.l - 1);
        }

        for (int i = 1; i <= q; i++)
            System.out.println(ans[i]);
    }

    private static class Query {
        int l, r, id;

        Query(int l, int r, int id) {
            this.l = l;
            this.r = r;
            this.id = id;
        }
    }

    private static final long[] tree = new long[MAXN];

    private static int lowbit(int i) {
        return i & -i;
    }

    public static void add(int i, long v) {
        while(i <= MAXN) {
            tree[i]   += v;
            i         += lowbit(i);
        }
    }

    public static long sum(int i) {
        long sum = 0;
        while(i > 0) {
            sum += tree[i];
            i   -= lowbit(i);
        }
        return sum;
    }
}
