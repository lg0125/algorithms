package com.chris.onlinejudge.ali.spring20240407;

import java.util.Scanner;

/*
有一个n行m列的矩阵,每个格子中都有一些数字,小苯一开始位于其左上角(1,1)点。
他想要一列一列地走完整个矩阵(即如果他想要走到第j＋1列,那么第j列的所有格子必须都走完),并且不能走到已经走过的地方,而且还要满足经过的相邻两个格子中的数字的差值不小于k。
现在已知矩阵,和他的要求数字k,请你判断他能否做到

本题包含多组测试数据。第一行一个正整数T（(1<T<100),表示测试数据组数。
接下来,对于每组测试数据: 输入包含n＋1行。第一行三个正整数n, m, k(1<n,m ≤500), (1 <k<10),表示矩阵的大小,和小苯要求的数字差。接下来n行,每行m个数字,表示矩阵的元素

输出包含一行一个字符串,如果小苯可以走完整个矩阵，输出"YES"，否则输出"NO"。(不含双引号)

2
4 4 2
1 2 5 4
3 5 1 1
1 2 3 4
4 6 1 6
2 2 4
1 2
2 3

YES
NO
*/
public class P1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while(T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();

            int[][] g = new int[n][m];
            for(int i = 0; i < n; ++i)
                for(int j = 0; j < m; ++j)
                    g[i][j] = sc.nextInt();

            long p = Integer.MIN_VALUE;
            boolean ok = true, flag = false;
            for(int j = 0; j < m; ++j) {
                for(int i = 0; i < n; ++i) {
                    long c = (!flag) ? g[i][j] : g[n-1-i][j];
                    if(Math.abs(c - p) < k) {
                        ok = false;
                        break;
                    }
                    p = c;
                }

                flag = !flag;

                if(!ok) break;
            }

            if(ok) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
