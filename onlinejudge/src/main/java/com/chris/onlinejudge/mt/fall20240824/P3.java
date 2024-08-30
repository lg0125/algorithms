package com.chris.onlinejudge.mt.fall20240824;

import java.util.Scanner;

public class P3 {
    private static class SegmentTree {
        int size;
        int[] tree;
        int[] data;

        void init(int len, int[] d) {
            size = len;
            tree = new int[4 * size];
            data = d;
        }

        void build(int node, int start, int end) {
            if (start == end) {
                tree[node] = start;
            } else {
                int mid = (start + end) / 2;
                build(2 * node, start, mid);
                build(2 * node + 1, mid + 1, end);
                tree[node] = compare(tree[2 * node], tree[2 * node + 1]);
            }
        }

        int compare(int idx1, int idx2) {
            if (data[idx1] > data[idx2] || (data[idx1] == data[idx2] && idx1 < idx2))
                return idx1;
            return idx2;
        }

        void update(int node, int start, int end, int idx, int value) {
            if (start == end) {
                data[idx] = value;
                tree[node] = idx;
            } else {
                int mid = (start + end) / 2;
                if (idx <= mid)
                    update(2 * node, start, mid, idx, value);
                else
                    update(2 * node + 1, mid + 1, end, idx, value);
                tree[node] = compare(tree[2 * node], tree[2 * node + 1]);
            }
        }

        int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l)
                return -1;
            if (l <= start && end <= r)
                return tree[node];
            int mid = (start + end) / 2;
            int leftResult = query(2 * node, start, mid, l, r);
            int rightResult = query(2 * node + 1, mid + 1, end, l, r);
            if (leftResult == -1) return rightResult;
            if (rightResult == -1) return leftResult;
            return compare(leftResult, rightResult);
        }

        void add(int idx, int value) {
            update(1, 1, size, idx, value);
        }

        void remove(int idx) {
            update(1, 1, size, idx, -1);
        }

        int getMaxIndex(int l, int r) {
            return query(1, 1, size, l, r);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOps = sc.nextInt();
        int numElems = sc.nextInt();

        int[][] arr = new int[2][numElems + 1];
        for (int i = 0; i < 2; i++)
            for (int j = 1; j <= numElems; j++)
                arr[i][j] = -1;

        int[] A = new int[numElems];
        int[] B = new int[numElems];
        for (int i = 0; i < numElems; i++)
            A[i] = sc.nextInt();

        for (int i = 0; i < numElems; i++) {
            B[i] = sc.nextInt();
            arr[B[i]][i + 1] = A[i];
        }

        SegmentTree[] segment = new SegmentTree[2];
        for (int i = 0; i < 2; i++) {
            segment[i] = new SegmentTree();
            segment[i].init(numElems, arr[i]);
            segment[i].build(1, 1, numElems);
        }

        while (numOps-- > 0) {
            int left    = sc.nextInt();
            int right   = sc.nextInt();
            int type    = sc.nextInt();
            int numQueries = sc.nextInt();

            while (numQueries-- > 0) {
                int index = segment[type].getMaxIndex(left, right);
                int value = segment[type].data[index];

                segment[type].remove(index);

                System.out.print((value != -1 ? index : -1) + " ");

                if (value == -1)
                    break;
            }

            System.out.println();
        }
    }
}
