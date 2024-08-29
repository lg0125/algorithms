package com.chris.onlinejudge.mt.fall20240810;

import java.util.*;
import java.util.stream.Collectors;

/**
 小美的密码
 小美准备登录美团，需要输入密码，小美忘记了密码，只记得密码可能是 n个字符串中的一个。

 小美会按照密码的长度从小到大依次尝试每个字符串，对于相同长度的字符串，小美随机尝试，并且相同的密码只会尝试一次。

 小美想知道，她最少需要尝试多少次才能登录成功，最多需要尝试多少次才能登录成功。
 小美不会重新尝试已经尝试过的字符串。成功登录后会立即停止尝试。

 第一行输入一个整数 n(1<=n<=1000)代表密码字符串的个数。
 第二行输入一个只由小写字母组成的字符串 s(1<=|s|<=1000)代表正确的密码。
 接下来 n 行，每行输入一个长度不超过 1000的字符串，代表小美记得的密码。

 在一行上输出两个整数，表示最少和最多尝试次数。

 4
 ab
 abc
 ab
 ac
 ac

 1 2

 小美可能按照 ["ab", "ac", "abc"] 的顺序尝试，第一次尝试成功，
 也可能按照 ["ac", "ab", "abc"] 的顺序尝试，第二次尝试成功。
 小美在尝试 "ac" 发现不正确后不会继续尝试 "ac"。

 哈希表。
 题目要求的是从短的开始遍历，于是我们可以使用哈希表来统计不同长度的各个密码的组合。

 我们思考：
 如何最短？那么必然按照长度从小到大遍历的过程中，这一次遇到了答案，第一次就直接就选择。
 如何最长？那么必然按照长度从小到大遍历的过程中，这一次遇到了答案，最后一次才选择。
 * */
public class P1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String s = sc.next();

        Map<Integer, Set<String>> map = new HashMap<>();
        for(int i = 0; i < n; ++i) {
            String str = sc.next();
            map.computeIfAbsent(str.length(), k -> new HashSet<>()).add(str);
        }

        List<Set<String>> sortedPassword = map.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        int step = 0;
        int minCnt = -1, maxCnt = -1;
        for(Set<String> set : sortedPassword) {
            if(set.contains(s)) {
                minCnt = step + 1;
                maxCnt = step + set.size();
            } else {
                step += set.size();
            }
        }
        System.out.println(minCnt + " " + maxCnt);
    }
}
