package com.chris.onlinejudge.jd.fall20240810;

import java.util.Scanner;

/*
牛牛昨晚喝醉了，走路开始疯狂摇摆，方向也分不清了。
假设他所在的地方是一个二维平面，开始它位于坐标为(0,0)的地方，并且面朝北方即y轴正方向。
W表示牛牛向前走，A表示牛牛把当前方向向左转90度，D表示牛牛把方向向右转90度，S表示牛牛呆在原地。
给出一个字符串表示牛牛的酒后行为方式，你能告诉他走完后它位于哪个坐标点吗？

输入仅有一行，一个字符串S(1≤|S1|≤10)，并且仅有"W,S,A,D'这四种字符表示牛牛的行为方式。

输出仅有一行，坐标位置x和用空格分开，表示牛牛的最终位置。

WAW

-1 1

第一步后牛牛面朝上位于(0,1)，第二步后牛牛面朝西位于(0,1)，第三步后牛牛面朝西位于(-1,1)；

这是一个简单的模拟问题，可以通过跟踪牛牛的方向和位置来解决。
初始位置为 (0, 0)，初始方向为 N（北）。
每当牛牛走一步（W），根据当前方向更新位置。
A 表示向左转90度，D 表示向右转90度，方向更新。
S 表示原地不动，所以不影响位置和方向。
* */
public class P1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        int x = 0, y = 0;
        char dir = 'N';

        for (char ch : s.toCharArray()) {
            if (ch == 'W') {
                if (dir == 'N')       y++;
                else if (dir == 'E')  x++;
                else if (dir == 'S')  y--;
                else if (dir == 'W')  x--;
            } else if (ch == 'A') {
                if (dir == 'N')       dir = 'W';
                else if (dir == 'W')  dir = 'S';
                else if (dir == 'S')  dir = 'E';
                else if (dir == 'E')  dir = 'N';
            } else if (ch == 'D') {
                if (dir == 'N')       dir = 'E';
                else if (dir == 'E')  dir = 'S';
                else if (dir == 'S')  dir = 'W';
                else if (dir == 'W')  dir = 'N';
            }
        }

        System.out.println(x + " " + y);
    }
}
