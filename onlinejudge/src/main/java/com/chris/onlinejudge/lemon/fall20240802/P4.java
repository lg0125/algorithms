package com.chris.onlinejudge.lemon.fall20240802;

/*
编写一个方法，该方法接收两个整数作为输入。第一个整数为16进制字符串，第二个整数为10进制整数。字母大写 整数N，X都是无符号64bit整数。

第一行包含一个正整数N 第二行包含一个正整数X(64位无符号整数)

第一行包含正整数的保留后的结果 第二行包含整数N乘以X求和的结果

编码规则为16进制字符串 字母大写 整数N，X都是无符号64bit整数

100
0XE10X07

0X64
999

100 = 0x64 = 0b1100100, 低位7bit为0b1100100 = 0x64，
应该有高位为0b100111, 所以输出字符串最后结果为0X64
0x64 = 0b1100100; 取低7bit, x = 0b0000111;
0b0000111 * 100 = 0b0000111100100 = 999

需要模拟加解密。

加密：主要先要得到数字的二进制，
    然后7个7个一组进行分割，
    最高位主要判断是否存在下一个，起始也就是代码中的num是否为0.

解密：首先需要将0X全删掉，然后解密为二进制，
    然后8个8个为一组提取出来，8个bit中低7位组装成整数，通过移位赋给res即可。
    如果最高位是0就break即可。
*/

import java.util.Scanner;

public class P4 {

    public static final int p7 = 1 << 7;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String x = sc.next().trim();

        // 编码整数为16进制字符串
        String hex = encode(n);
        System.out.println(hex);

        // 解码16进制字符串为整数
        long num = decode(x);
        System.out.println(num);
    }

    /**
     * 解码带有"0X"前缀的16进制字符串为整数
     * @param hex 带有"0X"前缀的16进制字符串
     * @return 解码后的整数
     */
    private static long decode(String hex) {
        hex = hex.toUpperCase().replaceAll("0X", ""); // 移除所有的 "0X" 前缀

        int bitIdx = 0;
        long res = 0;
        for (int i = 0; i < hex.length(); i += 2) {
            if (i + 2 > hex.length())
                break;

            String bs           = hex.substring(i, i + 2);
            int bv              = Integer.parseInt(bs, 16);
            boolean isMore      = (bv & p7) != 0; // 最高位为1表示有更多字节

            bv      &= p7 - 1; // 取低7位的数据部分
            res     |= ((long)bv << bitIdx); // 合并到结果中
            bitIdx  += 7;

            if (!isMore)
                break; // 如果没有更多字节，停止处理
        }
        return res;
    }

    /**
     * 编码整数为带有特殊编码规则的16进制字符串
     * @param num 要编码的整数
     * @return 编码后的16进制字符串，带有"0X"前缀
     */
    private static String encode(int num) {
        if (num == 0)
            return "0X00";

        StringBuilder hex = new StringBuilder();
        while (num > 0) {
            int byteVal = num & (p7-1); // 取低7位数据

            num >>= 7; // 右移7位
            if (num > 0)
                byteVal |= p7; // 设置最高位为1，表示后续还有数据

            hex.insert(0, String.format("0X%02X", byteVal));
        }
        return hex.toString();
    }
}
