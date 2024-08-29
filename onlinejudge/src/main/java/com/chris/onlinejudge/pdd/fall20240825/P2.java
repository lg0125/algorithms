package com.chris.onlinejudge.pdd.fall20240825;

import java.util.Arrays;
import java.util.Scanner;

/*
多多有一列正整数组成的数列，支持两种操作：
    选取一个偶数，使其值减半
    选取两个数字，移除并替换为两个数字的和
多多希望最终能够得到一个全为奇数的数列，请计算最少需要操作几次

第一行一个数字T,代表测试用例组数
对于每个测试用例：第一行为n，代表数组长度 10^6；第二行n个正整数 10^9
对于每个测试用例，输出一个数字，代表最少需要操作次数

2
3
2 4 4
5
1 2 5 4 6

3
2
如果有奇数，让所有偶数依次和他相加就可以变成奇数了。
没有奇数的话，就先找一个除2最快变成奇数的偶数，变成奇数。
 */
public class P2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();
        while (T-- > 0) {
            int n = scanner.nextInt();
            boolean flag = false;
            int sum = 0, mins = 100000000;

            for (int i = 1; i <= n; i++) {
                long x = scanner.nextLong();

                if ((x & 1) == 1) {
                    flag = true;
                } else {
                    sum++;
                    int s = 0;
                    while ((x & 1) == 0) {
                        s++;
                        x >>= 1;
                    }
                    mins = Math.min(s, mins);
                }
            }

            int res = sum - 1 + ((flag) ? 1 : mins);
            System.out.println(res);
        }
    }
}
