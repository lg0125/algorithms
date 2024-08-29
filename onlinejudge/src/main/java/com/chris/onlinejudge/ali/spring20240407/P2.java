package com.chris.onlinejudge.ali.spring20240407;

import java.util.*;

/**
 小红为了抢她最喜欢的乐队的周边，一大早就去排队，但很不巧，
 虽然小红到的很早，但已经有n个人比她到的更早,也就是说，小红排在n＋1的位置。
 根据小道消息，小红想要的周边只有k份,每人限购一份，因此小红想排进前k位。

 若小红在i＋1位,她可以花费ai与第i个人交换位置。特别的，每个人都属于一个小团体bi
 小红可以花费cbi,这样她与第bi个小团体的人交换位置时就不需要额外花费ai。

 注:与团体交换位置时不能越过未花费的人。
 小红想知道她要排进前k位最少需要花费多少?
 在花费最少的情况下,她能排进的最前面的位置是多少?

 第—行输入一个整数n, k表示排队人数，周边个数
 第1行输n个整数表示数组 a
 第2行输n个整数表示数组 b
 第3行输n个整数表示数组 c

 输出两个整数表示答案,
 第一个表示最小花费,
 第二个表示在最小花费的前提下,能排进的最前面的位置。

 3 2
 1 3 2
 1 2 1
 2 5 1

 5 1
 花费2给小团体1,然后花费0与第3个人交换位置。花费3给第2个人，与他交换位置。
 花费0与第1个人交换位置。因此答案为5 1。

 将k以后的所有人按照团体分组，统计团体花费和每个人花费总和哪个小，就按照哪一种模式进行交换位置；
 最后再从位置k逐步向前，统计最前能到哪个位置。

*/
public class P2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), k = sc.nextInt();

        int[] a = new int[n+1], b = new int[n+1], c = new int[n+1];
        for(int i = 1; i <= n; ++i) a[i] = sc.nextInt();
        for(int i = 1; i <= n; ++i) b[i] = sc.nextInt();
        for(int i = 1; i <= n; ++i) c[i] = sc.nextInt();

        // 将k以后的所有人按照团体分组
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = k; i <= n; ++i) {
            if(!map.containsKey(b[i]))
                map.put(b[i], new ArrayList<>());
            map.get(b[i]).add(i);
        }

        Set<Integer> set = new HashSet<>();
        long ans = 0;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            Integer key = entry.getKey();
            List<Integer> val = entry.getValue();

            long sum = 0;
            for(Integer idx : val)
                sum += a[idx];

            if(sum < c[key]) {
                ans += sum;
            } else {
                ans += c[key];
                set.add(key);
            }
        }

        int pos;
        for(pos = k; pos > 1; --pos)
            if(!set.contains(b[pos-1])) break;

        System.out.println(ans + " " + pos);
    }
}
