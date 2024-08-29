package com.chris.onlinejudge.mt.fall20240817;

import java.util.Arrays;
import java.util.Scanner;

/*
小黄有一个长度为n的数组，每次操作可以选择两个下标i和j，
将aj减去1，ai加上1，小美想知道最少需要多少次操作，可以使数组的极差最小
(数组的极差 := 数组的最大值和最小值之差)

第一行输入1个整数n(2-10^5)，表示数组长度
第二行输入n个整数a1, a2, ..., an(1-10^9)，代表数组的元素

在一行上输出一个整数,表示最少需要多少次操作

5
1 2 3 4 5

3

显然，要让极差最小，只需要将所有数都靠近平均值就行了。
如果所有数都可以变成平均值，那么极差为0，否则极差为1。
设平均值为x(向下取整)，当极差为1时，数组为若干个x和若干个x+1
 */
public class P2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long sum = 0;
        long[] a = new long[n+1];
        for(int i = 1; i <= n; ++i) {
            a[i] = sc.nextLong();
            sum += a[i];
        }

        long avg = sum / n;
        long ans = 0;
        if(avg * n == sum) {
            for(int i = 1; i <= n; ++i)
                if(a[i] < avg)
                    ans += avg - a[i];
        } else {
            int cnt = (int) (sum - avg * n);
            cnt     = n - cnt;

            Arrays.sort(a, 1, n+1);
            for (int i = 1; i <= n; ++i) {
                if (i <= cnt && a[i] < avg)
                    ans += avg - a[i];
                else if (i > cnt && a[i] < avg + 1)
                    ans += avg + 1 - a[i];
            }
        }

        System.out.println(ans);
    }
}
