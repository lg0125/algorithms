package com.chris.algorithms.leetcode.binarytree;

/*
Given the root of a binary tree, return its maximum depth.
A binary tree's maximum depth is the number of nodes
    along the longest path from the root node down to the farthest leaf node.
Input: root = [3,9,20,null,null,15,7]
Output: 3
 */
public class LC104 {
    public static int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        return Math.max(l, r) + 1;
    }
}
