package com.chris.algorithms.templates.thread;

import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CyclicPrintAbcV2 {

    private static String letter = "A";

    private static final Lock lock = new ReentrantLock();
    private static final Map<String, Condition> CONDITION_MAP = Map.ofEntries(
            Map.entry("A", lock.newCondition()),
            Map.entry("B", lock.newCondition()),
            Map.entry("C", lock.newCondition())
    );

    public static void print(String cur, String nxt) {
        lock.lock();
        try {
            if(!letter.equals(cur))
                CONDITION_MAP.get(cur).await();

            System.out.println(letter);

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
            for(int i = 0; i < 10; ++i)
                CyclicPrintAbcV2.print("A", "B");
        }).start();

        new Thread(() -> {
            for(int i = 0; i < 10; ++i)
                CyclicPrintAbcV2.print("B", "C");
        }).start();

        new Thread(() -> {
            for(int i = 0; i < 10; ++i)
                CyclicPrintAbcV2.print("C", "A");
        }).start();
    }
}
