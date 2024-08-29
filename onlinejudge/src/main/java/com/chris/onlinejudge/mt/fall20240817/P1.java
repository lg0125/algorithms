package com.chris.onlinejudge.mt.fall20240817;

import java.util.Scanner;

/*
小美对 gdc (最大公约数) 很感兴趣, 她会询问你 t 次。

每次询问给出一个大于 1 的正整数 n ,
    你是否找到一个数字 m (2 <= m <= n) ，使得 gcd(n, m) 为素数.

 第一行k(2 <= k <= 10^5)，表示k个待测数据
 接下来的k行，每行包含一个整数n(2 <= n <= 10^9)，表示待测数字

对于每一组测试数据, 在一行上输出一个整数，代表数字m 。如果有多种合法答案，您可以输出任意一种

2
114
15

57
5

所有大于1的合数均可以表示为若干个素数的积，即x = p(1, k1) * p(2, k2) * ... * p(n, kn)
所以我们只需要找到x的一个素因子k，那么答案即为x / k, 由于n最大为10^5，
所以我们只需要筛一次素数，然后再判断即可。
* */
public class P1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while(T-- > 0) {
            int n = sc.nextInt();

            boolean isPrime = true;
            for(int div = 2 ; div * div <= n; ++div) {
                if(n % div == 0) {
                    System.out.println(div);
                    isPrime = false;
                    break;
                }
            }

            if(isPrime)
                System.out.println(n);
        }
    }
}
