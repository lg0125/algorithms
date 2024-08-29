package com.chris.onlinejudge.lemon.fall20240802;

/*
请实现以下函数给定链表按照其中的value字段进行升序排序：Node sortList(Node head);

输入描述：
第一行为一个整数n，表示链表中节点的个数，1≤n≤10^5
第二行为n个整数，表示链表中各节点的value值

输出描述： 将排序后链表中各节点的value字段依次输出（数字间用空格分隔）
1 3 6 9 2 4 3 10 6

1 2 3 3 4 6 6 9 10

链表，模拟排序即可，注意的是不能交换值只能交换节点
*/


import java.util.Scanner;

public class P1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] nums = new int[n];
        for(int i = 0; i < n; ++i)
            nums[i] = sc.nextInt();

        Node head       = build(nums);

        Node sortedHead = sort(head);

        print(sortedHead);
    }

    private static void print(Node head) {
        Node cur = head;
        while(cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    // bubble sort
    private static Node sort(Node head) {
        if(head == null || head.next == null)
            return head;

        Node dummy = new Node(-1);
        dummy.next = head;

        Node last = null;
        while(dummy.next != last) {
            Node pre = dummy;
            Node cur = dummy.next;

            while(cur.next != last) {
                if (cur.value > cur.next.value) {
                    // 交换节点 current 和 current.next
                    Node tmp = cur.next;
                    cur.next = tmp.next;
                    tmp.next = cur;
                    pre.next = tmp;
                } else {
                    cur = cur.next;
                }
                pre = pre.next;
            }

            last = cur;
        }

        return dummy.next;
    }

    private static Node build(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return null;

        Node head = new Node(nums[0]);
        Node cur = head;
        for(int i = 1; i < n; ++i) {
            cur.next = new Node(nums[i]);
            cur = cur.next;
        }
        return head;
    }

    public static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }
}
