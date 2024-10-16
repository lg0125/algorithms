package com.chris.algorithms.leetcode.linkedlist;

public class ListNode {
    public int val;
    public ListNode next;

    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode p = this;
        while(p.next != null) {
            sb.append(p.val).append("->");
            p = p.next;
        }
        sb.append(p.val);
        return sb.toString();
    }
}
