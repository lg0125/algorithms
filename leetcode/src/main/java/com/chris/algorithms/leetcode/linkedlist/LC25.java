package com.chris.algorithms.leetcode.linkedlist;

/*
Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
k is a positive integer and is less than or equal to the length of the linked list.
If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
You may not alter the values in the list's nodes, only nodes themselves may be changed.

1 2 3 4 5   k = 2
2 1 4 3 5
 */
public class LC25 {
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null)
            return null;

        ListNode a = head, b = head;
        for(int i = 0; i < k; ++i) {
            if(b == null)
                return head;
            b = b.next;
        }

        ListNode newHead = reverse(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    private static ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;

        while (cur != b) {
            nxt = cur.next;

            cur.next = pre;

            pre = cur;
            cur = nxt;
        }

        return pre;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println(head);

        head = reverseKGroup(head, 2);

        System.out.println(head);
    }
}
