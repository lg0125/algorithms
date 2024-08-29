package com.chris.onlinejudge.mt.fall20240810;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 小美有一个长度为 n 的数组 a1,a2,....,an ，他可以对数组进行如下操作：
 ● 删除第一个元素 a1，同时数组的长度减一，花费为 x。
 ● 删除整个数组，花费为 k*MEX(a) （其中 MEX(a) 表示 a 中未出现过的最小非负整数。
 例如 [0,1,2,4] 的 MEX 为 3 ）。
 小美想知道将 a 数组全部清空的最小代价是多少，请你帮帮他吧。

 每个测试文件均包含多组测试数据。
 第一行输入一个整数 T(1<=T<=1000) 代表数据组数，
 每组测试数据描述如下：
 第一行输入三个正整数 n,k,x(1<=n<=2*10^5, 1<=k,x<=10^9) 代表数组中的元素数量、删除整个数组的花费系数、删除单个元素的花费。
 第二行输入 n 个整数 a1,a2,....,an(0<=ai<=n)，表示数组元素。
 除此之外，保证所有的 n 之和不超过 2*10^5。

 对于每一组测试数据，在一行上输出一个整数表示将数组中所有元素全部删除的最小花费。

 1
 6 3 3
 4 5 2 3 1 0

 15

 若不执行操作一就全部删除，MEX{4,5,2,3,1,0} = 6，花费 18 ；
 若执行一次操作一后全部删除，MEX{5,2,3,1,0} = 4，花费 3+12；
 若执行两次操作一后全部删除，MEX{2,3,1,0} = 4，花费 6+12 ；
 若执行三次操作一后全部删除，MEX{3,1,0} = 2，花费 9+6 ；
 若执行四次操作一后全部删除，MEX{1,0} = 2，花费 12+6 ；
 若执行五次操作一后全部删除，MEX{0} = 1，花费 15+3；
 若执行六次操作一，MEX{}=0，花费 18；

 动态规划 + 维护动态最小未出现的整数。
 f[i]表示从i往后考虑的最小花费，选择就是选择第一个元素或者直接删除后续所有的元素。
 对于删除后续所有的元素的选项，我们必须要直到MEX是多少，
 我们可以用在更新dp的过程中，用一个suffix不断地更新当前的最小未出现的整数。
 虽然这里出现了两层循环的嵌套，但是并不会重置参数，因此复杂度是O(n).
 */
public class P2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while(T-- > 0) {
            int n   = sc.nextInt();
            long k  = sc.nextLong();
            long x  = sc.nextLong();

            long[] a = new long[n];
            for(int i = 0; i < n; ++i)
                a[i] = sc.nextLong();

            long[] dp = new long[n + 1];
            Arrays.fill(dp, Long.MAX_VALUE);
            dp[n] = 0;

            Set<Long> set = new HashSet<>();
            long suffixMex = 0;
            for(int i = n - 1; i >= 0; --i) {
                set.add(a[i]);
                while(set.contains(suffixMex))
                    ++suffixMex;
                dp[i] = Math.min(dp[i+1] + x, k * suffixMex);
            }

            System.out.println(dp[0]);
        }
    }
}
