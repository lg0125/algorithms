package com.chris.onlinejudge.pdd.fall20240825;

/*
塔子哥有一题n个节点的树，树中每一条边都有一个权值，多多还有一个长度为n的正整数序列:v1,v2,...,vn
删除树中若干条边之后(或者不删)，就会变成一个有x个连通块的图，此时的得分为：剩余边权和+vi（两个可以互相到达的节点属于同一个连通块，注意一个孤点也是一个连通块）
塔子哥可以删除图中若干条边（可以不删）。现在塔子哥想知道，最多能够得到多少分。现在请你告诉塔子哥答案

第一行一个整数T，接下来有T组数据
每组数据第一行一个整数n(2 <= n <= 10^5)
第二行n个整数v1,v2,...,vn(1 <= vi <= 10^9)
接下来n-1行，每行3个数a,b,w(1 <= a, b <= n. 1 <= w <= 10^4)，表示节点a与节点b之间有一条权值为w的边

对于每组数据，输出一行一个整数，表示最多能够得到多少分

1
3
1 3 4
1 2 1
2 3 2

5

删除1与2之间的边，此时剩余边权和=2，连通块数量=2，得分=2+v[2]=2+3=5

2
3
3 3 4
1 2 1
2 3 2
3
1 2 5
1 2 1
2 3 2

6
5

第一组数据：不删除任何边第二组数据：删除所有边

一个点可以为一个连通块，每连接两个点，都会由两个连通块合并为一个连通块。
如果最开始将所有边都删除，则得到n个连通块，此时答案即为v[n]。
之后每连接一条边，则连通块减少一个，要让得分最大，就需要连接最大的边。
对边进行排序后，从大到小扫描边并逆序更新答案即可。
 */

import java.util.Arrays;
import java.util.Scanner;

public class P1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt(); // 输入测试用例的数量
        while (t-- > 0) { // 处理每个测试用例
            int n = scanner.nextInt(); // 输入数组的大小
            int[] v = new int[n + 1]; // 定义数组v，大小为n+1
            for (int i = 1; i <= n; i++) {
                v[i] = scanner.nextInt(); // 输入v数组的值
            }

            int[] w = new int[n - 1]; // 定义数组w，大小为n-1
            for (int i = 0; i < n - 1; i++) {
                int u = scanner.nextInt();
                int v1 = scanner.nextInt();
                int val = scanner.nextInt(); // 输入边的两个端点和权重
                w[i] = val; // 将权重存入w数组
            }

            Arrays.sort(w); // 对w数组进行排序
            long ans = v[n]; // 初始化ans为v数组的最后一个元素
            long sum = 0; // 初始化sum为0
            for (int i = n - 1; i > 0; i--) {
                sum += w[i - 1]; // 累加w数组中的元素到sum
                ans = Math.max(ans, v[i] + sum); // 更新ans为当前的最大值
            }

            System.out.println(ans); // 输出结果
        }
    }
}
