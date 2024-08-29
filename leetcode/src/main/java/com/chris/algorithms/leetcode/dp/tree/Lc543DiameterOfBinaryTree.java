package com.chris.algorithms.leetcode.dp.tree;

/** 二叉树的直径
 给你一棵二叉树的根节点，返回该树的直径
 二叉树的 直径 是指树中任意两个节点之间最长路径的长度
 这条路径可能经过也可能不经过根节点 root
 两节点之间路径的 长度 由它们之间边数表示
 测试链接 : <a href="https://leetcode.cn/problems/diameter-of-binary-tree/">...</a>
 */
public class Lc543DiameterOfBinaryTree {
    public static class Solution {
        // 不要提交这个类
        public static class TreeNode {
            public int val;
            public TreeNode left;
            public TreeNode right;
        }

        // 提交如下的方法
        public static class Info {
            public int diameter;
            public int height;

            public Info(int a, int b) {
                diameter = a;
                height = b;
            }
        }

        // 提交如下的方法
        public static int diameterOfBinaryTree(TreeNode root) {
            return f(root).diameter;
        }

        public static Info f(TreeNode x) {
            if (x == null)
                return new Info(0, 0);

            Info leftInfo   = f(x.left);
            Info rightInfo  = f(x.right);

            int height      = Math.max(leftInfo.height, rightInfo.height) + 1;

            int diameter    = Math.max(leftInfo.diameter, rightInfo.diameter);
            diameter        = Math.max(diameter, leftInfo.height + rightInfo.height);

            return new Info(diameter, height);
        }
    }
}
