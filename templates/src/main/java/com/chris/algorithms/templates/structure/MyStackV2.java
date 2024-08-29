package com.chris.algorithms.templates.structure;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyStackV2 {
    Deque<Integer> data = new ArrayDeque<>();

    public void push(int x) {
        data.addLast(x);
    }

    public int pop() {
        int size = data.size();
        while (size-- > 1)
            data.addLast(data.pollFirst());

        Integer u = data.pollFirst();
        return u != null ? u : Integer.MAX_VALUE;
    }

    public int top() {
        int size = data.size();
        while (size-- > 1)
            data.addLast(data.pollFirst());

        Integer u = data.peekFirst();

        data.addLast(data.pollFirst());

        return u != null ? u : Integer.MAX_VALUE;
    }

    public boolean empty() {
        return data.isEmpty();
    }
}
