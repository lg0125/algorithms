package com.chris.onlinejudge.kdxf.fall20240803;

import java.util.Scanner;

/**
 东八区(UTC/GMT+08:00)是比世界协调时间(UTC)/格林尼治时间(GMT)快8小时的时区，
 理论上的位智是位于东经112.5度至127.5度之间，
 在此15度的范围内，统一采用以东经120度中心线的地方时间为准。
 是东盟标准的其中一个候选时区。
 当格林尼治标准时间为00:00时东八区的标准时间为08:00。

 每个测试文件均包含多组测试数据
 第一行输入一个整数T
 在一行上输入一个格式为"小时:分钟"的北京时间(24小时制)

 对于每一组测试数据，在一行上输出对应的世界协调时间，注意小时和分钟都应当补0至两位数。

 2
 8:30
 00:00

 00:30
 16:00

 这是一道签到题，把当前时间减去8小时就是结果。
 为了便于计算，可以把时间先统一化成以分为单位。
 也就是以:作为分隔符任何处理成minutes的单位即可。

 */
public class P2 {
    public static String convertTime(String timeStr) {
        String[] parts = timeStr.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int totalMin = h * 60 + m - 8 * 60;
        if (totalMin < 0) {
            totalMin += 24 * 60;
        }
        int newH = totalMin / 60;
        int newM = totalMin % 60;
        return String.format("%02d:%02d", newH, newM);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        String[] results = new String[t];
        for (int i = 0; i < t; i++) {
            String timeStr = scanner.nextLine();
            results[i] = convertTime(timeStr);
        }
        for (String result : results) {
            System.out.println(result);
        }
    }
}
