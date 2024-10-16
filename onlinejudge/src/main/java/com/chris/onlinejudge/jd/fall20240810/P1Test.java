package com.chris.onlinejudge.jd.fall20240810;

import java.util.Scanner;

public class P1Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();

        int x = 0, y = 0;

        char dir = 'N';
        for(char c : s.toCharArray()) {
            if(c == 'W') {
                if(dir == 'N')
                    y++;
                else if(dir == 'E')
                    x++;
                else if(dir == 'S')
                    y--;
                else if(dir == 'W')
                    x--;
            } else if(c == 'A') {
                if(dir == 'N')
                    dir = 'W';
                else if(dir == 'E')
                    dir = 'N';
                else if(dir == 'S')
                    dir = 'E';
                else if(dir == 'W')
                    dir = 'S';
            } else if(c == 'D') {
                if(dir == 'N')
                    dir = 'E';
                else if(dir == 'E')
                    dir = 'S';
                else if(dir == 'S')
                    dir = 'W';
                else if(dir == 'W')
                    dir = 'N';
            }
        }

        System.out.println(x + " " + y);
    }
}
