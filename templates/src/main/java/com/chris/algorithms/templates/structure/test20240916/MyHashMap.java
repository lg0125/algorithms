package com.chris.algorithms.templates.structure.test20240916;

public class MyHashMap {
    Node[] nodes = new Node[1_000_9];
    public int get(int key) {
        int idx = hash(key);
        Node node = nodes[idx];
        if(node != null) {
            while (node != null) {
                if (key == node.key) return node.val;
                node = node.next;
            }
        }
        return -1;
    }
    public void put(int key, int val) {
        int idx = hash(key);
        Node cur = nodes[idx];
        if(cur != null) {
            Node pre = null;
            while(cur != null) {
                if(key == cur.key) {
                    cur.val = val;
                    return;
                }
                pre = cur;
                cur = cur.next;
            }
            cur = pre;
        }
        Node node = new Node(key, val);
        if(cur != null) cur.next = node;
        else nodes[idx] = node;
    }
    private int hash(int key) {
        int hashcode = Integer.hashCode(key);
        hashcode ^= (hashcode >>> 16);
        return hashcode % nodes.length;
    }
    public static class Node {
        int key, val;
        Node next;
        Node(int _key, int _val) {
            key = _key;
            val = _val;
        }
    }
}
