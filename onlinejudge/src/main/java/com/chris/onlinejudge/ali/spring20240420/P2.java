package com.chris.onlinejudge.ali.spring20240420;


/*
给定一段英文字符串，以单词为单位进行翻转字符串输出。

第—行包含整数T,表示共有T组测试数据。每组数据占一行，为将要翻转的字符串，每行字符串的长度不会超过1023个字节。

每组数据输出—行结果，表示答案。

3
game
hello world
welcome to lingxi

game
world hello
lingxi to welcome

按照空格分割，再翻转输出即可
*/


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class P2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        sc.nextLine();
        while(T-- > 0) {
            String str = sc.nextLine();

            String[] words = str.split(" ");
            List<String> res = new ArrayList<>();
            Collections.addAll(res, words);

            res.reversed();

            for(String word : res)
                System.out.print(word + " ");
            System.out.println();
        }
    }
}
