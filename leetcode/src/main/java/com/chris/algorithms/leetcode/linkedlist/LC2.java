package com.chris.algorithms.leetcode.linkedlist;

/*
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order, and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
342 + 465 = 807
 */
public class LC2 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;

        ListNode p1 = l1, p2 = l2;
        int carry = 0;
        while (p1 != null || p2 != null || carry > 0) {
            int val = carry;
            if(p1 != null) {
                val += p1.val;
                p1 = p1.next;
            }
            if(p2 != null) {
                val += p2.val;
                p2 = p2.next;
            }

            carry = val / 10;
            val = val % 10;

            p.next = new ListNode(val);
            p = p.next;
        }
        return dummy.next;
    }

    private static ListNode reverse(ListNode head) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = head;

        while (cur != null) {
            nxt = cur.next;

            cur.next = pre;

            pre = cur;
            cur = nxt;
        }

        return pre;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(2);
        l1 = reverse(l1);

        System.out.println(l1);

        ListNode l2 = new ListNode(4);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(5);
        l2 = reverse(l2);

        System.out.println(l2);

        ListNode res = addTwoNumbers(l1, l2);
        res = reverse(res);
        System.out.println(res);
    }
}
