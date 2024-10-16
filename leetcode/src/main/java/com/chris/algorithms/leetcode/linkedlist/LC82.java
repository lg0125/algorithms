package com.chris.algorithms.leetcode.linkedlist;

/*
Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
Return the linked list sorted as well.

Input: head = [1,1,1,2,3]
Output: [2,3]
 */
public class LC82 {
    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return head;
        ListNode dummy = new ListNode(-1, head);
        ListNode p = dummy;
        while (p.next != null && p.next.next != null) {
            if(p.next.val == p.next.next.val) {
                int val = p.next.val;
                while (p.next != null && p.next.val == val)
                    p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(3);

        System.out.println(head);

        System.out.println(deleteDuplicates(head));
    }
}
