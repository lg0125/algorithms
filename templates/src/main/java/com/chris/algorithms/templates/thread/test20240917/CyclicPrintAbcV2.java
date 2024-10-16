package com.chris.algorithms.templates.thread.test20240917;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CyclicPrintAbcV2 {
    private static char letter = 'A';

    private static final Lock lock = new ReentrantLock();
    private static final Map<Character, Condition> CONDITION_MAP = new HashMap<>(){{
        put('A', lock.newCondition());
        put('B', lock.newCondition());
        put('C', lock.newCondition());
    }};

    private static void print(char cur, char nxt) {
        lock.lock();
        try {
            if(!(letter == cur)) CONDITION_MAP.get(cur).await();
            System.out.print(letter);
            letter = nxt;
            CONDITION_MAP.get(nxt).signal();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for(int i = 0; i < 10; ++i) CyclicPrintAbcV2.print('A', 'B');
        }).start();
        new Thread(() -> {
            for(int i = 0; i < 10; ++i) CyclicPrintAbcV2.print('B', 'C');
        }).start();
        new Thread(() -> {
            for(int i = 0; i < 10; ++i) CyclicPrintAbcV2.print('C', 'A');
        }).start();
    }
}
