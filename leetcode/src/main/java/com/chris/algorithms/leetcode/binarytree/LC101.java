package com.chris.algorithms.leetcode.binarytree;

/*
Given the root of a binary tree,
    check whether it is a mirror of itself (i.e., symmetric around its center).
Input: root = [1,2,2,3,4,4,3]
Output: true
Input: root = [1,2,2,null,3,null,3]
Output: false
 */
public class LC101 {
    public static boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return dfs(root.left, root.right);
    }

    private static boolean dfs(TreeNode l, TreeNode r) {
        if(l == null && r == null)
            return true;
        if(l == null || r == null)
            return false;
        if(l.val != r.val)
            return false;
        return dfs(l.left, r.right) && dfs(l.right, r.left);
    }
}
