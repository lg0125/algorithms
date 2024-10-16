package com.chris.algorithms.leetcode.linkedlist;

/*
You are given the heads of two sorted linked lists list1 and list2.
Merge the two lists into one sorted list.
The list should be made by splicing together the nodes of the first two lists.
Return the head of the merged linked list.

Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
 */
public class LC21 {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode p1 = l1, p2 = l2;
        while (p1 != null && p2 != null) {
            if(p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }
        p.next = (p1 != null) ? p1 : p2;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        System.out.println(l1);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        System.out.println(l2);

        System.out.println(mergeTwoLists(l1, l2));
    }
}
