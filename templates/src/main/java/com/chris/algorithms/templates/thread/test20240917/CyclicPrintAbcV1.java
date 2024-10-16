package com.chris.algorithms.templates.thread.test20240917;

public class CyclicPrintAbcV1 {
    private static char letter = 'A';
    private static final Object lock = new Object();

    public static void print(char cur, char nxt) throws InterruptedException {
        synchronized (lock) {
            while (!(letter == cur)) lock.wait();
            System.out.print(letter);
            letter = nxt;
            lock.notifyAll();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for(int i = 0; i < 10; ++i) {
                try {
                    CyclicPrintAbcV1.print('A', 'B');
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(() -> {
            for(int i = 0; i < 10; ++i) {
                try {
                    CyclicPrintAbcV1.print('B', 'C');
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(() -> {
            for(int i = 0; i < 10; ++i) {
                try {
                    CyclicPrintAbcV1.print('C', 'A');
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
