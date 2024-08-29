package com.chris.onlinejudge.lemon.fall20240802;

import java.util.Scanner;

/*
本题中模式字符串中仅包含字符和符号 "*"。

匹配规则：
字符 "." 匹配任意一个字符
字符 "*" 表示它前面的字符可以出现任意次（包括0次）

与模式字符串中的一个字符匹配成功，匹配位置向后移动一位

字符 "*" 匹配检查它前面的字符，如果匹配成功，匹配位置不移动

不会出现连续的星号或点号开头的字符串。

使用语言提供的正则表达式将被作为无效答案。

输入的第一行为要匹配的用例个数，
接下来的每一行包含两个字符串，前一个字符串为匹配字符串， 后一个字符串为模式字符串。 每个字符串的长度不超过10。
对于每一个测试用例，如果匹配成功则输出一行 "true"，否则输出一行"false"。


模拟match，逻辑如下：

首先需要判断第一个字母是否匹配，如果匹配，那存在多种情况，
    比如会配合*或者?进行多字母匹配，或者就是单纯匹配一个字符。
        如果配合*，即使第一个字母没匹配，也可以继续往下匹配（因为*是可以为0的），
        所以if语句
            先判断*，
            接着其他情况必须要求第一个字母匹配，所以第二个条件要是首字母判断，
            如果首字母匹配，那就剩下两种情况，要么配合?, 要么单字符，分情况就行。
        退出条件肯定是要模式串为空时，文本串也要为空。非空的话就说明不匹配。
*/
public class P2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while(T-- > 0) {
            String str = sc.next(), pattern = sc.next();
            System.out.println(match(str, pattern) ? "true" : "false");
        }
    }

    /**
     * 递归地检查字符串str是否与模式字符串pattern匹配
     * 支持模式中的特殊字符：'.'匹配任意单个字符，'*'匹配零个或多个前面的元素，'?'匹配前一个元素零次或一次
     * @param str 输入字符串
     * @param pattern 模式字符串
     * @return 如果字符串与模式匹配，返回true
     */
    private static boolean match(String str, String pattern) {
        if(pattern.isEmpty())
            return str.isEmpty();

        char[] s = str.toCharArray(), p = pattern.toCharArray();
        boolean isMatchedCur = (!(s.length == 0)) && check(s[0], p[0]); // 检查当前字符是否匹配

        int last = 0;
        if(p.length >= 2 && p[1] == '*') // 处理模式中带有'*'的情况
            return helper(str, pattern, last);
        else if(!isMatchedCur) // 当前字符不匹配且不含有'*'
            return false;
        else if(p.length >= 2 && p[1] == '?') // 处理模式中带有'?'的情况
            return helper(str, pattern, last);
        else
            return match(str.substring(1), pattern.substring(1));
    }

    /**
     * 处理模式中带有'*'或'?'的情况
     * @param str 输入字符串
     * @param pattern 模式字符串
     * @param last 当前匹配位置
     * @return 是否匹配成功
     */
    private static boolean helper(String str, String pattern, int last) {
        char[] s = str.toCharArray(), p = pattern.toCharArray();

        int n = s.length;
        // 尝试匹配多个字符
        while(last < n && check(s[last], p[0]))
            last++;
        // 尝试继续匹配剩余的部分
        return match(str.substring(last), pattern.substring(2));
    }

    /**
     * 检查字符a和b是否匹配，支持通配符'.'
     * @param a 输入字符串中的字符
     * @param b 模式字符串中的字符
     * @return 如果字符匹配或模式字符为'.'，返回true
     */
    private static boolean check(char a, char b) {
        return a == b || b == '.';
    }
}
