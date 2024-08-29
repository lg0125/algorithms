package com.chris.onlinejudge.jd.fall20240810;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
给定长度为N的序列a和非负整数X。找出满足ai+aj=X的(i,j)对的数量

第一行给出一个序列长度N和一个非负整数X,用空格分隔
在第二行中，给出了序列a的N个元素a:,用空格分隔

输出一个整数表示最多可以找到多少个满足要求的数对
1 <= N <=   10^5
0 <= X <=   10^9
0 <= ai <=  10^5

3 4
1 2 3

3

三种情况分别为2+2=4, 1+3=4, 3+1=4

用map统计一下每个数字出现的次数，然后枚举(i,j)对里的i，
对应的j的数量就是map中x-a[i]的次数
* */
public class P2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n   = sc.nextInt();
        long x  = sc.nextLong();

        long[] a = new long[n];
        Map<Long, Integer> map = new HashMap<>();
        for(int i = 0; i < n; ++i) {
            a[i] = sc.nextLong();
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
        }

        long ans = 0;
        for(int i = 0; i < n; ++i) {
            long y = x - a[i];
            ans += map.getOrDefault(y, 0);
        }
        System.out.println(ans);
    }
}
