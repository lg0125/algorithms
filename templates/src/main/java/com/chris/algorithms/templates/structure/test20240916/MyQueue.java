package com.chris.algorithms.templates.structure.test20240916;

import java.util.Stack;

public class MyQueue {
    Stack<Integer> stk1 = new Stack<>();
    Stack<Integer> stk2 = new Stack<>();
    public void push(int e) {
        stk1.push(e);
    }
    public int pop() {
        if(stk2.isEmpty())
            while (!stk1.isEmpty()) stk2.push(stk1.pop());
        return stk2.pop();
    }
    public int peek() {
        if(stk2.isEmpty())
            while (!stk1.isEmpty()) stk2.push(stk1.pop());
        return stk2.peek();
    }
}
