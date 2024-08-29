package com.chris.algorithms.templates.structure;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyMinStack {
    Deque<Integer> data = new ArrayDeque<>(); // 用于存储元素
    Deque<Integer> help = new ArrayDeque<>(); // 通过控制 help 的压栈逻辑来实现：help 栈顶中始终存放着栈内元素的最小值

    public void push(int val) {
        data.addLast(val);

        if (help.isEmpty() || help.peekLast() >= val)
            help.addLast(val);
        else
            help.addLast(help.peekLast());
    }

    public void pop() {
        data.pollLast();
        help.pollLast();
    }

    public int top() {
        Integer e = data.peekLast();
        return e != null ? e : Integer.MAX_VALUE;
    }

    public int getMin() {
        Integer e = help.peekLast();
        return e != null ? e : Integer.MAX_VALUE;
    }
}
