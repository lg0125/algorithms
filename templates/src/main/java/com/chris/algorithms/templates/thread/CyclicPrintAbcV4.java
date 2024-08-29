package com.chris.algorithms.templates.thread;

import java.util.Map;
import java.util.concurrent.locks.LockSupport;

public class CyclicPrintAbcV4 {

    private static String letter = "A";

    public static final Map<String, Thread> THREAD_MAP = Map.ofEntries(
            Map.entry("A", new Thread(() -> {
                for(int i = 0; i < 10; ++i)
                    CyclicPrintAbcV4.print("A", "B");
            })),
            Map.entry("B", new Thread(() -> {
                for(int i = 0; i < 10; ++i)
                    CyclicPrintAbcV4.print("B", "C");
            })),
            Map.entry("C", new Thread(() -> {
                for(int i = 0; i < 10; ++i)
                    CyclicPrintAbcV4.print("C", "A");
            }))
    );

    private static void print(String cur, String nxt) {
        if(!letter.equals(cur))
            LockSupport.park();

        System.out.println(letter);

        letter = nxt;

        LockSupport.unpark(THREAD_MAP.get(nxt));
    }

    public static void main(String[] args) {
        CyclicPrintAbcV4.THREAD_MAP.forEach((name, thread) -> thread.start());
    }
}
