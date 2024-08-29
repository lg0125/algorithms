package com.chris.algorithms.templates.structure;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * O(1)-只需要保证，输入的元素总是跟在前面的输入元素的后面，而输出元素总是最早输入的那个元素即可
 * 「哈希表」底层是通过数组实现的
 * 正常情况下，计算元素在哈希桶的位置，然后放入哈希桶，复杂度为 O(1)，
 *  假定是通过简单的“拉链法”搭配「头插法」方式来解决哈希冲突
 * 但当某次元素插入后，「哈希表」达到扩容阈值，则需要对底层所使用的数组进行扩容，这个复杂度是O(n)
 *  显然「扩容」操作不会发生在每一次的元素插入中，因此扩容的O(n)都会伴随着 n 次的O(1)
 *  也就是 O(n) 的复杂度会被均摊到每一次插入当中，因此哈希表插入仍然是 O(1) 的
 * 同理，的「倒腾」不是发生在每一次的「输出操作」中，而是集中发生在一次「输出栈为空」的时候，
 *  因此 pop 和 peek 都是均摊复杂度为 O(1) 的操作
 */
public class MyQueueV2 {
    // in 用于处理输入操作 push，使用 in 时要确保 out 为空
    Deque<Integer> in   = new ArrayDeque<>();
    // out 用于处理输出操作 pop 和 peek，使用 out 时要确保 in 为空
    Deque<Integer> out  = new ArrayDeque<>();

    public void push(int x) {
        in.addLast(x);
    }

    public int pop() {
        if (out.isEmpty())
            while (!in.isEmpty())
                out.addLast(in.pollLast());
        Integer e = out.pollLast();
        return e != null ? e : Integer.MAX_VALUE;
    }

    public int peek() {
        if (out.isEmpty())
            while (!in.isEmpty())
                out.addLast(in.pollLast());
        Integer e = out.peekLast();
        return e != null ? e : Integer.MAX_VALUE;
    }

    public boolean empty() {
        return out.isEmpty() && in.isEmpty();
    }
}
