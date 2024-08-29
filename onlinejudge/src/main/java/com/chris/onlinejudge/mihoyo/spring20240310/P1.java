package com.chris.onlinejudge.mihoyo.spring20240310;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 地图上有 n 个格子排成一排，最左边的格子为1，最右边的格子为n。
 第0 秒时，每个格子都有一只史莱姆。
 第i 只史莱姆的跳跃方向用数组a 表示。
 ai=0 表示史莱姆跳跃的方向是往左。若第 i 秒史莱姆位于格子 x，那么第i+1 秒史莱姆会跳到格子x-1 。如果当前史莱姆在格子1，则下一秒史莱姆将跳出地图。
 ai=1 表示史莱姆跳跃的方向是往右。若第 i 秒史莱姆位于格子x，那么第 i+1 秒史莱姆会跳到格子x+1 。如果当前史莱姆在格子 n，则下一秒史莱姆将跳出地图。
 米小游想知道第 1-n 秒，地图上有多少个格子没有史莱姆。

 第一行包含一个整数n，n∈[1,3000]，表示地图上的格子数量。
 第二行包含n 个整数ai，ai∈[0,1]，表示每只史莱姆的跳跃方向。

 输出包含一行n 个整数，用空格隔开，第i 个数表示第i 秒没有史莱姆的格子数量。

 3
 1 0 1

 1 2 3

 史莱姆1-3 的跳跃方向分别为，往右，往左，往右。
 第 1 秒，史莱姆1 跳到格子 2，史莱姆2 跳到格子1，史莱姆3 跳出地图，格子3 没有史莱姆。
 第 2 秒，史莱姆 1 跳到格子3，史莱姆 2 跳出地图，格子 1,2 没有史莱姆。
 第 3 秒，史莱姆1 跳出地图，格子1,2,3 没有史莱姆。

 使用哈希表记录每个时刻每个位置有多少个0和1，循环n次更新即可。
 */
public class P1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] a = new int[n];
        for(int i = 0; i < n; ++i)
            a[i] = sc.nextInt();

        Map<Integer, int[]> map = new HashMap<>();
        for(int i = 0; i < n; ++i)
            map.put(i, new int[]{0, 0});
        for(int i = 0; i < n; ++i) {
            int dir = a[i];

            int[] v = map.get(i);
            v[dir] += 1;

            map.put(i, v);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        int[] res = new int[n];
        for(int i = 0; i < n; ++i) {
            Map<Integer, int[]> tmp = new HashMap<>();
            for (int j = 0; j < n; j++)
                tmp.put(j, new int[]{0, 0});

            for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
                Integer key = entry.getKey();
                int[] val   = entry.getValue();

                int left = val[0], right = val[1];

                ans[key] -= left + right;
                if(key - 1 >= 0)
                    ans[key - 1] += left;
                if(key + 1 < n)
                    ans[key + 1] += right;

                if(key - 1 >= 0) {
                    int[] v = tmp.get(key - 1);
                    v[0] += left;
                    tmp.put(key - 1, v);
                }
                if(key + 1 < n) {
                    int[] v = tmp.get(key + 1);
                    v[1] += right;
                    tmp.put(key + 1, v);
                }
            }

            map = tmp;

            int cnt = 0;
            for(int num : ans)
                if(num == 0) cnt++;
            res[i] = cnt;
        }

        for(int num : res)
            System.out.print(num + " ");
        System.out.println();
    }
}
