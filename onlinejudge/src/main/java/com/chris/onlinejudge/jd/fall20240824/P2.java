package com.chris.onlinejudge.jd.fall20240824;

import java.util.Scanner;

/**
 给定一个大小为n * m 的网格板，网格板是由n * m个1x1的单元格组成，
 最初所有的单元格是白色的;现在给出k个操作:
 c x y将位置为(x,y)的单元格涂成黑色
 l x y从位置为(x,y)的单元格向左寻找最先出现的白色单元格(不包含(x,y))，并输出其坐标;
 r x y从位置为(x,y)的单元格向右寻找最先出现的白色单元格(不包含(x,y))，并输出其坐标
 u x y从位置为(x,y)的单元格向上寻找最先出现的白色单元格(不包含(x,y))，并输出其坐标;
 d x y从位置为(x,y)的单元格向下寻找最先出现的白色单元格(不包含(x,y))，并输出其坐标
 注:网格板的左上角的单元格坐标为(1,1)

 第一行给出正整数n,m,k;代表网格板的大小以及操作的次数;
 随后k行，每行一个操作命令格式为si, xi, yi.
 si中仅包含l r u d.
 1≤n,m<=100; 1<=k<=10^4,1<x_i<n,l≤y_i< m

 对于以l r u d的命令，每行输出对应的操作后的坐标。若没有这样的坐标，输出一1。

 5 5 20
 c 2 4
 c 4 2
 r 3 5
 c 4 4
 c 5 2
 u 5 2
 c 3 2
 c 1 4
 l 4 4
 c 3 5
 c 5 3
 c 3 3
 c 3 1
 u 1 4
 c 3 4
 r 1 1
 d 3 3
 d 3 4
 c 1 1
 c 5 4

 -1
 3 2
 4 3
 -1
 1 2
 4 3
 5 4
 */
public class P2 {
    private static int n, m, k;
    private static int[][] a;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        a = new int[n + 1][m + 1];

        for (int i = 1; i <= k; i++) {
            char c = sc.next().charAt(0);
            int x = sc.nextInt();
            int y = sc.nextInt();

            if (c == 'c') {
                a[x][y] = 1;
            } else {
                get(c, x, y);
            }
        }
    }

    private static void get(char c, int x, int y) {
        int dx, dy;
        if (c == 'l') {
            dx = 0;
            dy = -1;
        } else if (c == 'r') {
            dx = 0;
            dy = 1;
        } else if (c == 'u') {
            dx = -1;
            dy = 0;
        } else {
            dx = 1;
            dy = 0;
        }

        boolean flag = false;
        x += dx;
        y += dy;

        while (x >= 1 && x <= n && y >= 1 && y <= m) {
            if (a[x][y] == 0) {
                System.out.println(x + " " + y);

                flag = true;

                break;
            }
            x += dx;
            y += dy;
        }

        if (!flag)
            System.out.println(-1);
    }
}
