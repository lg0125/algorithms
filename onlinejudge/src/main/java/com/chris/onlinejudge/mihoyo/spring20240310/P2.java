package com.chris.onlinejudge.mihoyo.spring20240310;

import java.util.*;

/**
 一番赏初始有 n 个抽赏，大家需要排队购买，只有在队列最前面的人可以选择购买或者不购买，所有人随时都可以离开队列，离开队列后也可以随时加入队列。

 米小游陪着她的好朋友莫娜来买一番赏，她时不时会看一眼队列中有多少人。也就是说，共有 4 种事件：
 1 s ：名字为 s 的人加入队列的结尾（保证图片不在队列中）。
 2 s ：名字为s 的人离开队列（保证 s 在队列中，但不一定在队列最前面）。
 3 x ：队列最前面的人购买了x 个抽赏（保证在抽赏个数大于 0 时，当前至少有x 个抽赏）。
 4 ：米小游查看队列人数。
 还有一个特殊规则：当抽赏个数小于等于m 时，处于队列最前面的人一定会把剩余的所有抽赏全部买走。当抽赏全部被买走后，队列会立即清空，后续的所有事件都将失效。

 米小游想知道，她每次查看队列人数时，队列中有多少人。以及最后抽赏全部卖完或者所有事件结束后，每个参与过排队的人各买了多少个抽赏？（按名字字典序升序输出）

 第一行输入三个整数 n,m (1<=m<=n<=10^9)，q(1<=q<=2*10^5 表示抽赏个数、特殊规则限制、事件个数。
 接下来q 行，首先输入一个整数 op(1<=op<=4) 表示事件类型：
 若事件类型为 1 或 2，则再输入一个长度不超过 10 的只由大小写字母组成的字符串s 表示名字；
 若事件类型为 3，则再输入一个整数x 表示购买的抽赏个数；
 若事件类型为 4，则不再输入。

 对于每一个类型为 4 的事件，输出一行，包含一个整数表示队列中的人数。
 最后每一行按字典序升序输出每一个参与过排队的人的名字和购买的抽赏个数（用一个空格隔开）。

 70 20 8
 1 Mona
 1 Kaveh
 4
 2 Mona
 1 Mona
 2 Kaveh
 4
 1 Kaveh

 2
 1
 Kaveh 0
 Mona 0

 初始时队列为空。

 第1个事件：Mona加入队列，队列为：["Mona"]。
 第2个事件：Kaveh加入队列，队列为：["Mona","Kaveh"]。
 第3个事件：查看队列人数，队列为：["Mona","Kaveh"]，共有2人。
 第4个事件：Mona离开队列，队列为：["Kaveh"]。
 第5个事件：Mona加入队列，队列为：["Kaveh","Mona"]。
 第6个事件：Kaveh离开队列，队列为：["Mona"]。
 第7个事件：查看队列人数，队列为：["Mona"]，共有1人。
 第8个事件：Kaveh加入队列，队列为：["Mona","Kaveh"]。
 Kaveh共购买了0个抽赏。
 Mona共购买了0个抽赏。

 70 20 13
 1 Mona
 1 Kaveh
 4
 2 Mona
 1 Eden
 1 Mona
 2 Kaveh
 4
 1 Kaveh
 4
 3 50
 2 Eden
 4

 2
 2
 3
 Eden 70
 Kaveh 0
 Mona 0

 初始时队列为空。
 第1个事件：Mona加入队列，队列为：["Mona"]。
 第2个事件：Kaveh加入队列，队列为：["Mona","Kaveh"]。
 第3个事件：查看队列人数，队列为：["Mona","Kaveh"]，共有2人。
 第4个事件：Mona离开队列，队列为：["Kaveh"]。
 第5个事件：Eden加入队列，队列为：["Kaveh","Eden"]。
 第6个事件：Mona加入队列，队列为：["Kaveh","Eden","Mona"]。
 第7个事件：Kaveh离开队列，队列为：["Eden","Mona"]。
 第8个事件：查看队列人数，队列为：["Eden","Mona"]，共有2人。
 第9个事件：Kaveh加入队列，队列为：["Eden","Mona","Kaveh"]。
 第10个事件：查看队列人数，队列为：["Eden","Mona","Kaveh"]，共有3人。
 第11个事件：Eden购买了50个抽赏，队列为：["Eden","Mona","Kaveh"]，但此时抽赏剩余数量小于等于20，因此处于队列最前方的Eden还会买走剩下的20个抽赏，总共买走了70个。
 第12个事件：由于抽赏全部被买走，事件失效。
 第13个事件：由于抽赏全部被买走，事件失效。
 Eden共购买了70个抽赏。
 Kaveh共购买了0个抽赏。
 Mona共购买了0个抽赏。

 核心思想涉及到维护和更新一个队列（que）和两个映射（ans 和 p2n）来记录某些特定信息。
 当读入操作为 1 时，将一个新的名字加入到模拟中。这涉及到将名字加入到 que 队列和 p2n 映射中，并确保 ans 映射中有这个名字的初始值。
 操作 2：当操作为 2 时，移除一个指定的名字。这需要从 que 队列和 p2n 映射中删除相应的条目。
 操作 3：操作 3 涉及到修改 n 的值，并根据条件更新 ans 映射中的值。这可能模拟了某种资源的分配过程，其中资源数量 x 从队列的开头开始分配，并且如果资源不足以继续分配，则终止操作。
 第二题需要使用哈希表进行模拟，时间复杂度O(q + q log q)
 */

public class P2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int q = scanner.nextInt();

        Map<String, Integer> ans = new HashMap<>();
        Map<String, Integer> p2n = new HashMap<>();

        TreeSet<Pair<Integer, String>> set = new TreeSet<>(Comparator.comparing(Pair::getFirst));

        for (int i = 0; i < q; i++) {
            int op = scanner.nextInt();

            if (op == 1) {
                String name = scanner.next();

                if (!ans.containsKey(name))
                    ans.put(name, 0);

                set.add(new Pair<>(i, name));

                p2n.put(name, i);
            } else if (op == 2) {
                String name = scanner.next();

                int idx = p2n.get(name);

                set.remove(new Pair<>(idx, name));

                p2n.remove(name);
            } else if (op == 3) {
                int x = scanner.nextInt();

                Pair<Integer, String> p = set.first();
                set.remove(p);

                p2n.remove(p.getSecond());

                ans.put(p.getSecond(), ans.getOrDefault(p.getSecond(), 0) + x);

                n -= x;
                if (n <= m) {
                    ans.put(p.getSecond(), ans.getOrDefault(p.getSecond(), 0) + n);
                    break;
                }
            } else {
                System.out.println(set.size());
            }
        }

        for (Map.Entry<String, Integer> entry : ans.entrySet())
            System.out.println(entry.getKey() + " " + entry.getValue());
    }

    public static class Pair<K, V> {
        private final K first;
        private final V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        public K getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }
    }
}
