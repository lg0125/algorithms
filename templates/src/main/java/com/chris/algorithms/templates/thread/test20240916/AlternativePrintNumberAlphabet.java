package com.chris.algorithms.templates.thread.test20240916;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class AlternativePrintNumberAlphabet {
    private static final String NUMBER = "Number";
    private static final String ALPHABET = "Alphabet";

    private static final Map<String, Semaphore> SEMAPHORE_MAP = new HashMap<>(){{
        put("Number", new Semaphore(1));
        put("Alphabet", new Semaphore(0));
    }};

    private static void printNumber(int number) {
        try {
            SEMAPHORE_MAP.get(NUMBER).acquire();
            System.out.print(number);
            SEMAPHORE_MAP.get(ALPHABET).release();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printAlphabet(char alphabet) {
        try {
            SEMAPHORE_MAP.get(ALPHABET).acquire();
            System.out.print(alphabet);
            SEMAPHORE_MAP.get(NUMBER).release();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for(int number = 1; number <= 26; ++number) AlternativePrintNumberAlphabet.printNumber(number);
        }).start();
        new Thread(() -> {
            for(char alphabet = 'A'; alphabet <= 'Z'; ++alphabet) AlternativePrintNumberAlphabet.printAlphabet(alphabet);
        }).start();
    }
}
