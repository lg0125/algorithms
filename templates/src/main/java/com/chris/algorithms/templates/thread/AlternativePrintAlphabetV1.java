package com.chris.algorithms.templates.thread;

public class AlternativePrintAlphabetV1 {

    private static final Object lock = new Object();

    public static boolean isPrintA = true;

    public static void printA() {
        synchronized (lock) {
            while (!isPrintA) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.print("A");
            isPrintA = !isPrintA;
            lock.notify();
        }
    }

    public static void printB() {
        synchronized (lock) {
            while (isPrintA) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.print("B");
            isPrintA = !isPrintA;
            lock.notify();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for(int i = 0; i < 10; ++i) AlternativePrintAlphabetV1.printA();
        }).start();
        new Thread(() -> {
            for(int i = 0; i < 10; ++i) AlternativePrintAlphabetV1.printB();
        }).start();
    }
}
