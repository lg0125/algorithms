package com.chris.algorithms.templates.structure.test20241004;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    public Queue<Integer> data = new LinkedList<>();
    public Queue<Integer> help = new LinkedList<>();

    public void push(int e) {
        help.offer(e);
        while (!data.isEmpty())
            help.offer(data.poll());

        Queue<Integer> temp = data;
        data = help;
        help = temp;
    }

    public int pop() {
        return data.poll();
    }

    public int peek() {
        return data.peek();
    }
}
