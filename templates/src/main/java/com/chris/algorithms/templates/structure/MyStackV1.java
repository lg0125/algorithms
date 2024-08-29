package com.chris.algorithms.templates.structure;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 通常「倒腾」两个队列的操作可以放在「输入」的 push 里面，也可以放在「输出」的 pop 和 top 中
 * 这取决于该数据结构是「偏写入」还是「偏读取」
 * 由于「倒腾」的操作是 O(n)
 * 如果是一个「偏写入」的数据结构，那么应该确保 push 是 O(1)
 *      应该把「倒腾」的操作放在「输出」的 pop 和 top
 * 如果是一个「偏读取」的数据结构，那么应该确保 pop 和 top 是 O(1)
 *      应该把「倒腾」的操作放在「输入」的 push
 */
public class MyStackV1 {
    Deque<Integer> data = new ArrayDeque<>(); // 用于存储元素
    Deque<Integer> help = new ArrayDeque<>(); // 帮助实现 LIFO 的输出

    
    
    public void push(int x) {
        data.addLast(x);
    }

    public int pop() {
        while (data.size() > 1) 
            help.addLast(data.pollFirst());
        
        Integer u = data.pollFirst();
        
        swap();
        
        return u != null ? u : Integer.MAX_VALUE;
    }

    public int top() {
        while (data.size() > 1)
            help.addLast(data.pollFirst());

        Integer u = data.peekFirst();

        help.addLast(data.pollFirst());

        swap();

        return u != null ? u : Integer.MAX_VALUE;
    }

    public boolean empty() {
        return data.isEmpty() && help.isEmpty();
    }

    private void swap() {
        Deque<Integer> tmp = data;
        data = help;
        help = tmp;
    }
}
