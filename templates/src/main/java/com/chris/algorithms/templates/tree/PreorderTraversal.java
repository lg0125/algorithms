package com.chris.algorithms.templates.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PreorderTraversal {
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res           = new ArrayList<>();
        LinkedList<TreeNode> stk    = new LinkedList<>();

        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                // 执行
                res.add(root.val);

                stk.push(root);
                root = root.left;
            }

            root = stk.pop();

            root = root.right;
        }

        return res;
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {}
        public TreeNode(int val) { this.val = val; }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val    = val;
            this.left   = left;
            this.right  = right;
        }
    }
}
