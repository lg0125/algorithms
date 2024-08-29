package com.chris.onlinejudge.jd.fall20240810;

import java.util.Scanner;

/*
牛牛有一个长度为n且值都为0的数组a。
对于这个数组, 牛牛每次操作可以选择一个区间[l,r]，
    对于[l,r]上的每一个数牛牛必须让其加一或者乘二
    （元素之间操作独立，可以选择一些元素乘二，一些元素加一，但是区间内每个元素都要操作）。
牛牛还有一个目标数组b,

牛牛想知道对于初始数组a来说，其最少操作多少次可以将其变为b呢。

第一行为t，表示有t组数据。
接下来有2×t行，每组数据的第一行为一个n。
第二行为n个整数，表示目标数组的元素bi。

输出为t行，每行为一组答案表示牛牛的最小操作次数。

2
5
1 1 2 1 1
5
1 2 3 4 5

2
4

先算出每个数字的最小操作次数，
看每个数字的二进制，*2其实就是二进制左移一位，+1就是二进制+1，
数字的最小操作次数就是（二进制的位数-1+二进制里1的数量）

经典贪心——关键思想是理解：如何使得操作次数最小？
将一个数变成另一个数的最小操作次数可以从其二进制表示中得出：
    对于数 x，我们可以通过将 x 从0变成目标值 b[i] 的最小操作次数为：操作次数=目标值的二进制位数−1+目标值中1的个数
    其中，目标值的二进制位数 - 1 是因为将 x 从0左移到目标值需要的位数，加上目标值中1的个数（每个1都需要一个加1操作）
 */
public class P3 {
    public static long f(long x) {
        long cnt = 0;
        while (x > 0) {
            if ((x & 1) == 1) {
                cnt++;
            }
            x >>= 1;
            cnt++;
        }
        return cnt - 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();

            long[] b = new long[n + 1];
            for (int i = 1; i <= n; i++)
                b[i] = f(sc.nextLong());

            long ans = 0;
            for (int i = 2; i <= n; i++)
                if (b[i] > b[i - 1])
                    ans += b[i] - b[i - 1];

            System.out.println(ans + b[1]);
        }
    }
}
