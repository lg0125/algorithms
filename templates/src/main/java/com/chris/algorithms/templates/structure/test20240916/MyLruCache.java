package com.chris.algorithms.templates.structure.test20240916;

import java.util.*;

public class MyLruCache {
    Map<Integer, Node> map;
    DoubleList cache;
    int capacity;
    public MyLruCache(int _capacity) {
        map         = new HashMap<>();
        cache       = new DoubleList();
        capacity    = _capacity;
    }
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        makeRecently(key);
        return map.get(key).val;
    }
    public void put(int key, int val) {
        if(map.containsKey(key)) {
            delete(key);
            addRecently(key, val);
            return;
        }
        if(cache.size == capacity) removeLeastRecently();
        addRecently(key, val);
    }
    private void makeRecently(int key) {
        Node node = map.get(key);
        cache.remove(node);
        cache.addLast(node);
    }

    private void delete(int key) {
        Node node = map.get(key);
        cache.remove(node);
        map.remove(key);
    }

    private void addRecently(int key, int val) {
        Node node = new Node(key, val);
        cache.addLast(node);
        map.put(key, node);
    }

    private void removeLeastRecently() {
        Node node = cache.removeFirst();
        map.remove(node.key);
    }

    public static class Node {
        int key, val;
        Node prev, next;
        Node(int _key, int _val) {
            key = _key;
            val = _val;
        }
    }

    public static class DoubleList {
        Node head, tail;
        int size;
        DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        void addLast(Node node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
            size++;
        }
        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
        Node removeFirst() {
            if(head.next == null) return null;
            Node node = head.next;
            remove(node);
            return node;
        }
    }
}
