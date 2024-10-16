package com.chris.algorithms.leetcode.linkedlist;

/*
Given the head of a singly linked list and two integers left and right where left <= right,
reverse the nodes of the list from position left to position right,
and return the reversed list.

Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
 */
public class LC92 {
    static ListNode successor = null;

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == 1)
            return reverseN(head, right);
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    private static ListNode reverseN(ListNode head, int n) {
        if(n == 1) {
            successor = head.next;
            return head;
        }

        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println(head);

        reverseBetween(head, 2, 4);

        System.out.println(head);
    }
}
