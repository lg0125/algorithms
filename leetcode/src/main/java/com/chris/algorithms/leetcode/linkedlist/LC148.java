package com.chris.algorithms.leetcode.linkedlist;

/*
Given the head of a linked list,
return the list after sorting it in ascending order.

Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
 */
public class LC148 {
    public static ListNode sortList(ListNode head) {
        return mergesort(head);
    }

    private static ListNode mergesort(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode m = middle(head);

        ListNode r = mergesort(m.next);

        m.next = null;

        ListNode l = mergesort(head);

        return merge(l, r);
    }

    private static ListNode middle(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static ListNode merge(ListNode l, ListNode r) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (l != null && r != null) {
            if(l.val > r.val) {
                p.next = r;
                r = r.next;
            } else {
                p.next = l;
                l = l.next;
            }
            p = p.next;
        }
        p.next = (l != null) ? l : r;
        return dummy.next;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(-1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(0);

        System.out.println(head);

        head = mergesort(head);

        System.out.println(head);
    }
}
