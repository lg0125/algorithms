package com.chris.onlinejudge.kdxf.fall20240727;

import java.util.Scanner;

/**
 我们已经知道 2 进制到 10 进制表示方法，与 16 进制类似，
 我们考虑 11~36 进制，即用 a 代表 10 ，b 代表 11 等。
 我们想知道给定一个 10 进制数 n ，其在 2 ~36 进制下的所有进制表示中，含有 1的数量最多是多少。
 比如 4 在二进制下表示为 （100）2 ，只有一个 1。

 在一行上输入一个整数 n（1<=n<=3*10^5）n 代表给定的十进制数。

 在一行上输出一个整数表示答案。

 4

 2
 在 3 进制下，4 为 (11)3 ，有两个 1

 11

 3
 在 2 进制下，11有三个 1

 主要就是实现一个函数 count_ones_in_base(n, base)
 计算在给定进制下，十进制数 n 中 1 的数量。count 为0，表示最后1的数量。
 不断除base直到0为止。
 遍历2-36进制下，所有1的数量的可能，取最大值即可。
 */
public class P2 {
    // 计算在给定进制下，十进制数 n 中 1 的数量
    private static int countOnesInBase(int n, int base) {
        int count = 0;
        while (n > 0) {
            if (n % base == 1)
                count++;
            n /= base;
        }
        return count;
    }

    // 计算在 2 到 36 进制下，十进制数 n 中 1 的数量的最大值
    private static int maxOnesCount(int n) {
        int maxCount = 0;
        for (int base = 2; base <= 36; ++base) {
            int onesCount = countOnesInBase(n, base);

            if (onesCount > maxCount)
                maxCount = onesCount;
        }
        return maxCount;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        System.out.println(maxOnesCount(n));
    }
}
