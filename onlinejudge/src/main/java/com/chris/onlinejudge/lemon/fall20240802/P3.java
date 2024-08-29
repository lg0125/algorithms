package com.chris.onlinejudge.lemon.fall20240802;

import java.util.Scanner;
import java.util.TreeSet;

/*
就在某个宁静的日子里，塔子哥收到了一个引人入胜的问题。这个问题涉及到一条蜿蜒的道路，而这条道路被分成了块区域。每个地块都拥有自己独特的高度，记为，其中。
当塔子哥站在第块区域上时，他会选择跳到高度严格大于的、下标大于的区域中，而且他会选择高度最小的那块区域作为下一跳的目的地。
塔子哥希望清楚他自己在每个区域上下一次跳跃的目的地的高度，如果下一次不会跳跃，则答案为-1.
请你帮他解决这个问题。

求数列d
    如果，hj := j>i && hj>hi 存在 则 di = min{hj|j>i && hj>hi}
    否则 di = -1

第一行包括一个正整数n
第二行包括n个正整数 h

输出n个正整数 d

求下标比目标大，且值也比它大的最小的整数，
    从后往前用treeset存储，
    之后通过treeset的higher进行二分查找即可

6
33 16 38 72 76 23

38 23 72 76 -1 -1
*/
public class P3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] h = new int[n];
        for(int i = 0; i < n; ++i)
            h[i] = sc.nextInt();

        int[] d = helper(h);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; ++i) {
            if(i > 0)
                sb.append(" "); // 添加空格分隔符，避免第一个元素前出现空格
            sb.append(d[i]); // 添加结果数组的元素
        }
        System.out.println(sb);
    }

    /**
     * 找到每个元素的下一个更高值
     * @param h 输入的高度数组
     * @return 每个元素的下一个更高值的数组，如果没有则返回-1
     */
    private static int[] helper(int[] h) {
        TreeSet<Integer> set = new TreeSet<>(); // 使用TreeSet保持有序性

        int n = h.length; // 结果数组，用于存储每个元素的下一个更高值
        int[] d = new int[n];
        for(int i = n-1; i >= 0; --i) {
            Integer next = set.higher(h[i]); // 找到比当前高度大的最小值

            d[i] = (next != null) ? next : -1; // 如果存在更高值，则记录，否则记录-1

            set.add(h[i]); // 将当前高度加入TreeSet
        }
        return d;
    }
}
