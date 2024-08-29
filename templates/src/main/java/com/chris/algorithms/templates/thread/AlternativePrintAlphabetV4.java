package com.chris.algorithms.templates.thread;

import java.util.Map;
import java.util.concurrent.locks.LockSupport;

public class AlternativePrintAlphabetV4 {

    private static boolean isPrintA = true;

    public static final Map<String, Thread> THREAD_MAP = Map.ofEntries(
            Map.entry("A", new Thread(() -> {
                for(int i = 0; i < 10; ++i)
                    AlternativePrintAlphabetV4.printA();
            })),
            Map.entry("B", new Thread(() -> {
                for(int i = 0; i < 10; ++i)
                    AlternativePrintAlphabetV4.printB();
            }))
    );

    private static void printA() {
        if(!isPrintA)
            LockSupport.park();

        System.out.print("A");

        isPrintA = !isPrintA;

        LockSupport.unpark(THREAD_MAP.get("B"));
    }

    private static void printB() {
        if(isPrintA)
            LockSupport.park();

        System.out.print("B");

        isPrintA = !isPrintA;

        LockSupport.unpark(THREAD_MAP.get("A"));
    }

    public static void main(String[] args) {
        AlternativePrintAlphabetV4.THREAD_MAP.forEach((name, thread) -> thread.start());
    }
}
