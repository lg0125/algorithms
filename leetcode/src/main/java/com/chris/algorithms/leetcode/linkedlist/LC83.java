package com.chris.algorithms.leetcode.linkedlist;

/*
Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
Return the linked list sorted as well.

Input: head = [1,1,2,3,3]
Output: [1,2,3]
 */
public class LC83 {
    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return null;
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null) {
            if(fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);

        System.out.println(head);

        System.out.println(deleteDuplicates(head));
    }
}
