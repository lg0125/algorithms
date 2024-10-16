package com.chris.algorithms.leetcode.linkedlist;

/*
You are given the head of a singly linked-list. The list can be represented as:
    L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:
    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes.
Only nodes themselves may be changed.
Input: head = [1,2,3,4]
Output: [1,4,2,3]
 */
public class LC143 {
    public static ListNode reorderList(ListNode head) {
        ListNode m = middle(head);

        ListNode l2 = m.next;

        m.next = null;

        l2 = reverse(l2);

        ListNode l1 = head;

        return merge(l1, l2);
    }

    private static ListNode middle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
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

    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode p1 = l1, p2 = l2;
        while (p1 != null && p2 != null) {
            p.next = p1;
            p1  = p1.next;
            p   = p.next;

            p.next = p2;
            p2  = p2.next;
            p   = p.next;
        }
        p.next = (p1 != null) ? p1 : p2;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        System.out.println(head);

        System.out.println(reorderList(head));
    }
}
