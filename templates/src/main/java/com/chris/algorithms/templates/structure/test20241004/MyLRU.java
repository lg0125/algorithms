package com.chris.algorithms.templates.structure.test20241004;

import java.util.HashMap;
import java.util.Map;

public class MyLRU {

    public DoubleLinkedList cache;

    public Map<Integer, Node> map;

    public int capacity;

    public MyLRU(int cap) {
        cache       = new DoubleLinkedList();
        map         = new HashMap<>();
        capacity    = cap;
    }

    public int get(int key) {
        if(!map.containsKey(key))
            return -1;

        Node node = map.get(key);
        makeRecently(node);
        return node.val;
    }

    private void makeRecently(Node node) {
        cache.remove(node);
        cache.add(node);
    }

    public void put(int key, int val) {
        Node newNode = new Node(key, val);

        if(map.containsKey(key)) {
            Node node = map.get(key);
            delete(node);
        } else if(cache.size == capacity) {
            removeLeastRecently();
        }

        addRecently(newNode);
    }

    private void delete(Node node) {
        cache.remove(node);
        map.remove(node.key);
    }

    private void addRecently(Node node) {
        cache.add(node);
        map.put(node.key, node);
    }

    private void removeLeastRecently() {
        Node node = cache.removeFirst();
        map.remove(node.key);
    }


    public static class Node {
        public int key, val;
        public Node nxt, pre;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.pre = null;
            this.nxt = null;
        }
    }

    public static class DoubleLinkedList {
        public Node head, tail;
        public int size;

        public DoubleLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);

            head.nxt = tail;
            tail.pre = head;

            size = 0;
        }

        // 尾插法
        public void add(Node node) {
            node.pre = tail.pre;
            node.nxt = tail;

            tail.pre.nxt = node;
            tail.pre = node;

            size++;
        }

        public void remove(Node node) {
            node.pre.nxt = node.nxt;
            node.nxt.pre = node.pre;
            size--;
        }

        public Node removeFirst() {
            if(head.nxt == null)
                return null;
            Node first = head.nxt;
            remove(first);
            return first;
        }
    }
}
