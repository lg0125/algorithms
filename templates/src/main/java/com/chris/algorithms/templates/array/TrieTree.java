package com.chris.algorithms.templates.array;

import java.util.Arrays;

public class TrieTree {
    private static final int MAX_N = 150001;

    private static final int[][] tree   = new int[MAX_N][26];
    private static final int[] end      = new int[MAX_N];
    private static final int[] pass     = new int[MAX_N];

    private static int cnt;

    public static void build() { cnt = 1; }

    public static void insert(String word) {
        int cur = 1;

        ++pass[cur];
        int path, n = word.length();
        for(int i = 0; i < n; ++i) {
            path = word.charAt(i) - 'a';
            if(tree[cur][path] == 0)
                tree[cur][path] = ++cnt;
            cur = tree[cur][path];
            ++pass[cur];
        }
        ++end[cur];
    }

    public static int search(String word) {
        int cur = 1;

        int path, n = word.length();
        for(int i = 0; i < n; ++i) {
            path = word.charAt(i) - 'a';
            if(tree[cur][path] == 0)
                return 0;
            cur = tree[cur][path];
        }
        return end[cur];
    }

    public static void delete(String word) {
        if(search(word) > 0) {
            int cur  = 1;

            int path, n = word.length();
            for(int i = 0; i < n; ++i) {
                path = word.charAt(i) - 'a';
                if(--pass[tree[cur][path]] == 0) {
                    tree[cur][path] = 0;
                    return;
                }
                cur = tree[cur][path];
            }
            --end[cur];
        }
    }

    public static int prefixNumber(String pre) {
        int cur = 1;

        int path, n = pre.length();
        for(int i = 0; i < n; ++i) {
            path = pre.charAt(i) - '0';
            if(tree[cur][path] == 0)
                return 0;
            cur = tree[cur][path];
        }
        return pass[cur];
    }

    public static void clear() {
        for(int i = 1; i <= cnt; ++i) {
            Arrays.fill(tree[i], 0);
            end[i] = 0;
            pass[i] = 0;
        }
    }
}
