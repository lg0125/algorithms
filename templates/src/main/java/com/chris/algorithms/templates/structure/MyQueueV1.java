package com.chris.algorithms.templates.structure;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * O(n)-在每次的「入栈」和「出栈」操作中都进行「倒腾」
 */
public class MyQueueV1 {
    // in 用于处理输入操作 push，使用 in 时要确保 out 为空
    Deque<Integer> in   = new ArrayDeque<>();
    // out 用于处理输出操作 pop 和 peek，使用 out 时要确保 in 为空
    Deque<Integer> out  = new ArrayDeque<>();

    public void push(int x) {
        while (!out.isEmpty())
            in.addLast(out.pollLast());
        in.addLast(x);
    }

    public int pop() {
        while (!in.isEmpty())
            out.addLast(in.pollLast());
        Integer e = out.pollLast();
        return e != null ? e : Integer.MAX_VALUE;
    }

    public int peek() {
        while (!in.isEmpty())
            out.addLast(in.pollLast());
        Integer e= out.peekLast();
        return e != null ? e : Integer.MAX_VALUE;
    }

    public boolean empty() {
        return out.isEmpty() && in.isEmpty();
    }
}
