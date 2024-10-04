package com.chris.algorithms.templates.structure.test20240916;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    Queue<Integer> que1 = new LinkedList<>();
    Queue<Integer> que2 = new LinkedList<>();

    public void push(int e) {
        que2.offer(e);
        while(!que1.isEmpty()) que2.offer(que1.poll());
        Queue<Integer> temp = que1;
        que1 = que2;
        que2 = temp;
    }

    public int pop() {
        return que1.isEmpty() ? Integer.MIN_VALUE : que1.poll();
    }

    public int peek() {
        return que1.isEmpty() ? Integer.MIN_VALUE : que1.peek();
    }
}
