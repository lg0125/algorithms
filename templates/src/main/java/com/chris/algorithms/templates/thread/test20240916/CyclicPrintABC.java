package com.chris.algorithms.templates.thread.test20240916;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class CyclicPrintABC {

    private static final Map<Character, Semaphore> SEMAPHORE_MAP = new HashMap<>(){{
        put('A', new Semaphore(1));
        put('B', new Semaphore(0));
        put('C', new Semaphore(0));
    }};

    private static void print(char cur, char nxt) {
        try {
            SEMAPHORE_MAP.get(cur).acquire();
            System.out.print(cur);
            SEMAPHORE_MAP.get(nxt).release();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for(int i = 0; i < 10; ++i) CyclicPrintABC.print('A', 'B');
        }).start();
        new Thread(() -> {
            for(int i = 0; i < 10; ++i) CyclicPrintABC.print('B', 'C');
        }).start();
        new Thread(() -> {
            for(int i = 0; i < 10; ++i) CyclicPrintABC.print('C', 'A');
        }).start();
    }
}
