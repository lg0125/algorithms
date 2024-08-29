package com.chris.algorithms.templates.thread;

import java.util.Map;
import java.util.concurrent.Semaphore;

public class AlternativePrintAlphabetV3 {

    private static final Map<String, Semaphore> SEMAPHORE_MAP = Map.ofEntries(
            Map.entry("A", new Semaphore(1)),
            Map.entry("B", new Semaphore(0))
    );

    public static void printA() {
        try {
            SEMAPHORE_MAP.get("A").acquire();

            System.out.print("A");

            SEMAPHORE_MAP.get("B").release();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printB() {
        try {
            SEMAPHORE_MAP.get("B").acquire();

            System.out.print("B");

            SEMAPHORE_MAP.get("A").release();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for(int i = 0; i < 10; ++i)
                AlternativePrintAlphabetV3.printA();
        }).start();

        new Thread(() -> {
            for(int i = 0; i < 10; ++i)
                AlternativePrintAlphabetV3.printB();
        }).start();
    }
}
