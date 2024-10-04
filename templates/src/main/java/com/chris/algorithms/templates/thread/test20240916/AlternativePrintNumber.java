package com.chris.algorithms.templates.thread.test20240916;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class AlternativePrintNumber {
    private static final String ODD = "odd";
    private static final String EVEN = "even";

    private static final Map<String, Semaphore> SEMAPHORE_MAP = new HashMap<>(){{
        put(ODD, new Semaphore(1));
        put(EVEN, new Semaphore(0));
    }};

    private static void printOdd(int number) {
        if(number % 2 == 0) return;

        try {
            SEMAPHORE_MAP.get(ODD).acquire();
            System.out.println(number);
            SEMAPHORE_MAP.get(EVEN).release();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printEven(int number) {
        if(number % 2 == 1) return;

        try {
            SEMAPHORE_MAP.get(EVEN).acquire();
            System.out.println(number);
            SEMAPHORE_MAP.get(ODD).release();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for(int i = 1; i <= 100; ++i)
                AlternativePrintNumber.printOdd(i);
        }).start();
        new Thread(() -> {
            for(int i = 1; i <= 100; ++i)
                AlternativePrintNumber.printEven(i);
        }).start();
    }
}
