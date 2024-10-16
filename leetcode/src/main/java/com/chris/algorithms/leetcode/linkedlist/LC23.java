package com.chris.algorithms.leetcode.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
 */
public class LC23 {
    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)
            return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                lists.length,
                Comparator.comparingInt(a -> a.val)
        );
        for(ListNode head : lists)
            if(head != null)
                pq.add(head);

        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            if(node.next != null)
                pq.add(node.next);
            p.next = node;
            p = p.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next= new ListNode(5);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);

        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);

        ListNode[] lists = new ListNode[]{head1, head2, head3};
        System.out.println(mergeKLists(lists));
    }
}
