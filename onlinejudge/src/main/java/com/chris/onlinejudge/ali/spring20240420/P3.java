package com.chris.onlinejudge.ali.spring20240420;

/*
在一个闭合的环形公路上，共有n个加油站排列成一个圈，每个加油站都有—定数量的汽油供应。现在有—辆汽车打算在这条环形公路上行驶一周，它将从某个加油站出发，并希望在不断加油的情况下，顺利驶回起点。已知汽车的油箱容量是无限的，但为了保证环保，出发时汽车的油箱是空的。给定两个整数数组gas 和cost。数组gas[i]表示第i个加油站有gas们]升汽油可供加油;数组cost[i]表示从第i个加油站前往下—个加油站(即第(i+1)%n个加油站）需要消耗的汽油量。
你的任务是判断汽车是否能够在某个加油站出发，并绕环路行驶一周后返回该加油站。如果存在这样的出发点，请返回其加油站的编号;如果不存在，返回-1。题目如果有解，则输出索引最小的加油站开始的解。

第—行:—个整数n，代表环形公路上的加油站数量。
第二行:n个整数，由空格分隔，表示数组gas，其中gas[1]是第i个加油站的汽油供应量。
第三行:n个整数，由空格分隔，表示数组cost，其中cost[i]是从第i个加油站前往下—站所需的汽油量。

—行—个整数:如果汽车可以绕环路行驶—周，则返回其出发加油站的编号;否则返回-1。

5
1 2 3 4 5
3 4 5 1 2

3

在此情况下，汽车从第3个加油站出发(编号从О开始计算)，将按顺序拜访加油站，并在每站加注足够的汽油以前往下—站。它将完成—次环形旅行并返回出发点，所有加油站的汽油供应和耗费情况如下:
出发点(加油站3)︰汽油供应4升，前往下—站需耗费1升加油站4∶汽油供应5升，前往下—站需耗费2升
加油站0:汽油供应1升，前往下—站需耗费3升加油站1:汽油供应2升，前往下—站需耗费4升加油站2:汽油供应3升，前往下一站需耗费5升
从这些数据可以看出，尽管加油站3不是汽油供应最多的站点，但它是唯——个能保证汽车绕环路行驶—周的起点。

运用贪心思想，累加gas[i] - cost[i]，出现最小值的时候，它的下一个位置即为起点。如果累加和小于0，输出-1。
*/


import java.util.Scanner;

public class P3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] gas = new int[n], cost = new int[n];
        for(int i = 0; i < n; ++i)
            gas[i] = sc.nextInt();
        for(int i = 0; i < n; ++i)
            cost[i] = sc.nextInt();

        int sum = 0;
        int minDiff = Integer.MAX_VALUE;
        int idx = 0;

        for (int i = 0; i < n; ++i) {
            sum += gas[i] - cost[i];

            if (sum < minDiff) {
                minDiff = sum;
                idx = i;
            }
        }

        if (sum < 0) {
            System.out.println(-1);
        } else {
            System.out.println((idx + 1) % n);
        }
    }
}
