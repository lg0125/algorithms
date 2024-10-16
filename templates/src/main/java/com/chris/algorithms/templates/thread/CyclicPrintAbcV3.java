package com.chris.algorithms.templates.thread;

import java.util.Map;
import java.util.concurrent.Semaphore;

public class CyclicPrintAbcV3 {
    private static final Map<String, Semaphore> SEMAPHORE_MAP = Map.ofEntries(
            Map.entry("A", new Semaphore(1)),
            Map.entry("B", new Semaphore(0)),
            Map.entry("C", new Semaphore(0))
    );

    public static void print(String cur, String nxt) {
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
            for(int i = 0; i < 10; ++i) CyclicPrintAbcV3.print("A", "B");
        }).start();

        new Thread(() -> {
            for(int i = 0; i < 10; ++i) CyclicPrintAbcV3.print("B", "C");
        }).start();

        new Thread(() -> {
            for(int i = 0; i < 10; ++i) CyclicPrintAbcV3.print("C", "A");
        }).start();
    }
}
