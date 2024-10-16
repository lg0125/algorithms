package com.chris.algorithms.leetcode.linkedlist;

/*
Given the head of a singly linked list, reverse the list, and return the reversed list.

1 2 3 4 5
5 4 3 2 1
 */
public class LC206 {
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println(head);

        head = reverseList(head);

        System.out.println(head);
    }
}
