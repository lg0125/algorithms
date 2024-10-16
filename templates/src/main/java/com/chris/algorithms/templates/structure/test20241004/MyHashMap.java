package com.chris.algorithms.templates.structure.test20241004;

public class MyHashMap {
    private static final int N = 1_000_9;

    private static final Node[] nodes = new Node[N];

    public int get(int key) {
        int idx = hash(key);

        Node loc = nodes[idx];
        if(loc != null) {
            while (loc != null) {
                if(key == loc.key)
                    return loc.val;
                loc = loc.nxt;
            }
        }

        return -1;
    }

    public void put(int key, int val) {
        int idx = hash(key);

        Node loc = nodes[idx];
        Node pre = null;
        if(loc != null) {
            while (loc != null) {
                if(key == loc.key) {
                    loc.val = val;
                    return;
                }
                pre = loc;
                loc = loc.nxt;
            }
        }

        Node node = new Node(key, val);
        if(pre != null) {
            pre.nxt = node;
        } else {
            nodes[idx] = node;
        }
    }

    private int hash(int key) {
        int h = Integer.hashCode(key);
        h ^= (h >>> 16);
        return h % nodes.length;
    }

    public static class Node {
        public int key;
        public int val;

        public Node nxt;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.nxt = null;
        }
    }
}
