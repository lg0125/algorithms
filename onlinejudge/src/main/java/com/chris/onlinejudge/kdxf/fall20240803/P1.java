package com.chris.onlinejudge.kdxf.fall20240803;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 给定一棵树，每个节点有一个权值。
 现在每次可以交换任意两个节点的权值，
 请问,最少多少次交换可以使得每个节点的权值等于它的编号?
 保证给出的权值是一个排列，也就是说保证一定有解。

 第一行输入一个正整数n，代表树上的节点数量。
 第二行输入n个正整数，第个正整数是号节点的权值，互不相同。
 接下来的n - 1行，每行输入两个正整数和，代表号节点和号节点有一条边相连。
 n 1000

 一个整数，代表最小的交换次数。

 4
 2 1 4 3
 1 2
 2 3
 2 4

 2
 */
public class P1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n + 1];
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
            indexMap.put(a[i], i);
        }

        for (int i = 1; i < n; i++) {
            scanner.nextInt();
        }

        int res = 0;

        for (int i = n; i > 0; i--) {
            while (a[i] != i) {
                res++;
                int temp = a[i];
                a[i] = a[temp];
                a[temp] = temp;

                // 更新哈希表
                indexMap.put(a[temp], temp);
                indexMap.put(a[i], i);
            }
        }

        System.out.println(res);
    }
}
