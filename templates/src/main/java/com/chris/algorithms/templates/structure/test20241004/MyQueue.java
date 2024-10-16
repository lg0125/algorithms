package com.chris.algorithms.templates.structure.test20241004;

import java.util.Stack;

// O(1)-只需要保证，输入的元素总是跟在前面的输入元素的后面，而输出元素总是最早输入的那个元素即可
// 「倒腾」不是发生在每一次的「输出操作」中，而是集中发生在一次「输出栈为空」的时候
// pop 和 peek 都是均摊复杂度为 O(1) 的操作
public class MyQueue {
    public Stack<Integer> in  = new Stack<>(); // 用于处理push操作，使用时要确保out为空
    public Stack<Integer> out = new Stack<>(); // 用于处理pop和peek操作，使用时要确保in为空

    public void push(int e) {
        in.push(e);
    }

    public int pop(int e) {
        if(out.isEmpty())
            while (!in.isEmpty())
                out.push(in.pop());
        return out.pop();
    }

    public int peek(int e) {
        if(out.isEmpty())
            while (!in.isEmpty())
                out.push(in.pop());
        return out.peek();
    }
}
