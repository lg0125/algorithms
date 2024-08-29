package com.chris.algorithms.templates.structure;

import java.util.HashMap;
import java.util.Map;

public class MyLruCache {
    public Map<Integer, Node> map;
    public DoubleList cache;
    public int cap;

    public MyLruCache(int capacity) {
        map     = new HashMap<>();
        cache   = new DoubleList();
        cap     = capacity;
    }

    private void makeRecently(int key) {
        Node node = map.get(key);
        cache.remove(node);
        cache.addLast(node);
    }

    private void addRecently(int key, int val) {
        Node node = new Node(key, val);
        cache.addLast(node);

        map.put(key, node);
    }

    private void delete(int key) {
        Node node = map.get(key);
        cache.remove(node);

        map.remove(key);
    }

    public void removeLeastRecently() {
        Node node = cache.removeFirst();

        int key = node.key;
        map.remove(key);
    }

    public static class Node {
        public int key, val;
        public Node next, prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public static class DoubleList {
        public Node head, tail;
        public int size;

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.prev = head;

            size = 0;
        }

        public void addLast(Node x) {
            x.prev = tail.prev;
            x.next = tail;

            tail.prev.next = x;
            tail.prev = x;

            size++;
        }

        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;

            size--;
        }

        public Node removeFirst() {
            if (head.next == tail)
                return null;
            Node first = head.next;
            remove(first);
            return first;
        }

        public int size() {
            return size;
        }
    }
}
