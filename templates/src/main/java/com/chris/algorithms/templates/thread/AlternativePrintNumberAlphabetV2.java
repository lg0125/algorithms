package com.chris.algorithms.templates.thread;

import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AlternativePrintNumberAlphabetV2 {

    private static boolean isPrintNumber = true;

    private static final Lock lock = new ReentrantLock();
    private static final Map<String, Condition> CONDITION_MAP = Map.ofEntries(
            Map.entry("Number", lock.newCondition()),
            Map.entry("Alphabet", lock.newCondition())
    );

    public static void printNumber(int number) {
        lock.lock();
        try {
            if(!isPrintNumber)
                CONDITION_MAP.get("Number").await();

            System.out.print(number);

            isPrintNumber = !isPrintNumber;

            CONDITION_MAP.get("Alphabet").signal();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public static void printAlphabet(char alphabet) {
        lock.lock();
        try {
            if(isPrintNumber)
                CONDITION_MAP.get("Alphabet").await();

            System.out.print(alphabet);

            isPrintNumber = !isPrintNumber;

            CONDITION_MAP.get("Number").signal();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for(int number = 1; number <= 26; ++number)
                AlternativePrintNumberAlphabetV2.printNumber(number);
        }).start();
        new Thread(() -> {
            for(char alphabet = 'A'; alphabet <= 'Z'; ++alphabet)
                AlternativePrintNumberAlphabetV2.printAlphabet(alphabet);
        }).start();
    }
}
