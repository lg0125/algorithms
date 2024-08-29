package com.chris.algorithms.templates.thread;

public class CyclicPrintAbcV1 {

    private static String letter = "A";

    private static final Object lock = new Object();

    public static void print(String cur, String nxt) throws InterruptedException {
        synchronized (lock) {
            while(!letter.equals(cur))
                lock.wait();

            System.out.println(letter);

            letter = nxt;

            lock.notifyAll();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for(int i = 0; i < 10; ++i) {
                try {
                    CyclicPrintAbcV1.print("A", "B");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            for(int i = 0; i < 10; ++i) {
                try {
                    CyclicPrintAbcV1.print("B", "C");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            for(int i = 0; i < 10; ++i) {
                try {
                    CyclicPrintAbcV1.print("C", "A");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
