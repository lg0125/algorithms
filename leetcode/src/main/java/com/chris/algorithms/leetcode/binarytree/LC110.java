package com.chris.algorithms.leetcode.binarytree;

/*
Given a binary tree, determine if it is height-balanced.

Input: root = [3,9,20,null,null,15,7]
Output: true
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false
 */
public class LC110 {
    public static boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    private static int height(TreeNode root) {
        if(root == null)
            return 0;
        int l = height(root.left);
        int r = height(root.right);
        if(l == -1 || r == -1 || Math.abs(l - r) > 1)
            return -1;
        else
            return Math.max(l, r) + 1;
    }
}
