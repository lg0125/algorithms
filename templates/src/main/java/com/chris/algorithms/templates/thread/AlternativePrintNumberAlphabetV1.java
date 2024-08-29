package com.chris.algorithms.templates.thread;

public class AlternativePrintNumberAlphabetV1 {

    private static final Object lock = new Object();

    private static int count = 1;

    private static char letter = 'A';


    public static void printNumber() {
        synchronized (lock) {
            while (count <= 26) {
                System.out.print(count++);

                lock.notify();
                try {
                    if (count <= 26)
                        lock.wait(); // 让线程A等待
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void printAlphabet() {
        synchronized (lock) {
            while (letter <= 'Z') {
                System.out.print(letter++);

                lock.notify(); // 唤醒等待的线程A
                try {
                    if (letter <= 'Z')
                        lock.wait(); // 让线程B等待
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(AlternativePrintNumberAlphabetV1::printNumber).start();
        new Thread(AlternativePrintNumberAlphabetV1::printAlphabet).start();
    }
}
