package com.chris.algorithms.templates.thread;

import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AlternativePrintAlphabetV2 {

    public static boolean isPrintA = true;

    private static final Lock lock = new ReentrantLock();
    private static final Map<String, Condition> CONDITION_MAP = Map.ofEntries(
            Map.entry("A", lock.newCondition()),
            Map.entry("B", lock.newCondition())
    );

    public static void printA() {
        lock.lock();
        try {
            if(!isPrintA)
                CONDITION_MAP.get("A").await();

            System.out.print("A");

            isPrintA = !isPrintA;

            CONDITION_MAP.get("B").signal();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public static void printB() {
        lock.lock();
        try {
            if(isPrintA)
                CONDITION_MAP.get("B").await();

            System.out.print("B");

            isPrintA = !isPrintA;

            CONDITION_MAP.get("A").signal();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for(int i = 0; i < 10; ++i)
                AlternativePrintAlphabetV2.printA();
        }).start();

        new Thread(() -> {
            for(int i = 0; i < 10; ++i)
                AlternativePrintAlphabetV2.printB();
        }).start();
    }
}
