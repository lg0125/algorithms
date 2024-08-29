package com.chris.algorithms.templates.thread;

public class AlternativePrintNumberV1 {

    private static final Object lock = new Object();

    private static int count = 1;

    public static void printOdd() {
        synchronized (lock) {
            while (count <= 100) {
                if(count % 2 != 0) {
                    System.out.println(count++);
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    public static void printEven() {
        synchronized (lock) {
            while (count <= 100) {
                if(count % 2 == 0) {
                    System.out.println(count++);
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(AlternativePrintNumberV1::printOdd).start();
        new Thread(AlternativePrintNumberV1::printEven).start();
    }
}
