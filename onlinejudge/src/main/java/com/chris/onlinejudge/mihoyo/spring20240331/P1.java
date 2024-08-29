package com.chris.onlinejudge.mihoyo.spring20240331;
/*

本题目由抽崩坏三茶歇时刻—番赏真实事件改(乱)编!
一番赏初始有n个抽赏，有m个人排队购买，在队列最前面的人可以购买1个抽赏，买完之后这个人可以再次排队。
每一个抽赏都对应一个从'A'到'I'之间的大写字母，A赏比B赏稀有，B赏比C赏稀有，以此类推。买走最后一个抽赏的人将额外获赠一个last赏，为了简化，我们将last赏计为S赏，S赏比A赏稀有。
米小游知道一个只由大写字母组成的字符串s，表示购买第i个抽赏可以获得si赏，她也知道排队购买抽赏的顺序数组a，表示购买i个抽赏的是第ai个人。米小游想知道每个人抽到了哪些赏，按稀有度排序，最稀有的排在最前面。(数据保证每个人至少购买了1个抽赏)

第—行输入两个整数n, m表示抽赏个数、人数。
第二行输入一个长度为n的只由'A'到'I'之间的大写字母组成的字符串s
第三行输入一个长度为n的数组a，表示排队购买抽赏的顺序数组

输出m行，每行输出一个字符串表示第i个人获得的抽赏，按稀有度顺序排序

3 2
CAB
1 1 2

AC
BS


6 5
ABCDEA
1 2 3 4 5 1

AAS
B
C
D
E

只要统计每个人得到的抽赏集合，再按照字典序排序即可（注意'S'优先级最高）
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), m = sc.nextInt();

        String str = sc.next();
        char[] s = str.toCharArray();

        List<List<Character>> ans = new ArrayList<>();
        for(int i = 0; i < n; ++i)
            ans.add(new ArrayList<>());
        for(int i = 0; i < n; ++i) {
            int a = sc.nextInt();

            ans.get(a-1).add(s[i]);
            if(i == n-1)
                ans.get(a-1).add('S');
        }

        for (int i = 0; i < n; i++) {
            ans.get(i).sort((c1, c2) -> {
                if(c1 == 'S') return 1;
                if(c2 == 'S') return -1;
                return c1.compareTo(c2);
            });

            for(Character c : ans.get(i))
                System.out.print(c);
            System.out.println();
        }
    }
}
