package com.chris.onlinejudge.ali.spring20240420;

import java.util.Scanner;

/*
给定两个无符号4字节长度整型x和y，将x和y经过zorder计算后得出—个无符号8字节长度整型值z。zorder的计算规则如下: 1.将x和y转成二进制，z二进制数值的每两位分别从x和y中取2.每次获取，x作为二进制的高位，y作为低位 3.直到x和y的32位取完，即得出z值

第—行只有—个数n，表示后面有多少行数据第二行开始有n行，每行有两组数据分别是x和y

有n行，每行只有—个数据为Z

3
18 52
178 532
321 943

1816
297752
484439

如:x是18，二进制00010010。y是52,二进制00110100。则z为1816，二进制为011100011000.
第一轮:从x取0，从y 取0，则z:00
第二轮:从x取1，从y取0，则z:1000
第三轮:从x取0，从y 取1，则z:011000
第四轮:从x取0，从y取0，则z:00011000
第五轮:从x取1，从y 取1，则z:1100011000
第六轮:从x取0，从y取1，则z:011100011000

穿插两个数字的二进制位
*/
public class P1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        while(n-- > 0) {
            long x = sc.nextLong();
            long y = sc.nextLong();

            long ans = 0;
            for(int i = 0; i < 32; ++i) {
                int a = (int) ((y >> i) & 1);
                if(a == 1)
                    ans |= (1L << (2 * i));

                a = (int) ((x >> i) & 1);
                if(a == 1)
                    ans |= (1L << (2 * i + 1));
            }
            System.out.println(ans);
        }
    }
}
