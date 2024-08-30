package com.chris.onlinejudge.kdxf.fall20240727;

import java.util.*;

/**
 小强有n个朋友，每个朋友有一定数量的金币，现在他们要购买房子，一共有m个房子，
 每个房子有两个参数：舒适度和价格，当一个人的金币大于等于一个房子的价格时，才可以购买房子，且要满足以下条件：
 1.一个人至多购买一个房子。
 2.一个房子至多被一个人购买。
 现在小强想知道n个朋友购买的房子的舒适度之和最大可能是多少？

 第一行两个整数n,m
 接下来一行n个数，第i个整数x表示第i个人的金币x, 1<=x<=10^9
 接下来m行每行两个整数表示每个房子的舒适度a和价格b，1<=a,b<=10^9,1<=n,m<=2*10^5

 输出一个数表示最大可能的舒适度之和

 2 2
 2 2
 2 2
 2 2

 4
 每个朋友都会买一个舒适度为2的房子

 贪心+搜索树。
 对于每一个人，购买舒适度尽可能大的，并且价格尽可能接近的 。
 因此，可以将所有的房子都按照舒适度从大到小排序，然后将每个人的金币数存储进二叉搜索树（CPP是set、Java是TreeSet、Python需要额外的数据结构）。
 按顺序遍历已有的房子，对于每个房子，搜索找到第一个不小于房子价格的金币数量，并从红黑树中删除对应的金币数量。
 */
public class P1 {
    // 定义房子类
    private static class House {
        long comfort;
        long price;

        House(long comfort, long price) {
            this.comfort = comfort;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 读取朋友的金币数量
        TreeMap<Long, Integer> friends = new TreeMap<>();
        for (int i = 0; i < n; ++i) {
            long gold = scanner.nextLong();
            friends.put(gold, friends.getOrDefault(gold, 0) + 1);
        }

        // 读取房子的舒适度和价格
        List<House> houses = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            long comfort = scanner.nextLong();
            long price = scanner.nextLong();
            houses.add(new House(comfort, price));
        }

        // 按照价格从高到低排序房子
        houses.sort((a, b) -> Long.compare(b.price, a.price));

        long maxComfortSum = 0;

        // 处理每个房子
        for (House house : houses) {
            Map.Entry<Long, Integer> entry = friends.ceilingEntry(house.price);

            if (entry != null) {
                // 找到第一个不小于房子价格的金币数量
                maxComfortSum += house.comfort;

                int count = entry.getValue();
                if (count == 1) {
                    // 如果该数量的金币只剩下一个，则移除
                    friends.remove(entry.getKey());
                } else {
                    // 否则减少其计数
                    friends.put(entry.getKey(), count - 1);
                }
            }
        }

        // 输出最大舒适度之和
        System.out.println(maxComfortSum);
    }
}
