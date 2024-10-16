package com.chris.algorithms.leetcode.binarytree;

/*
Given the root of a binary tree and an integer targetSum,
return true if the tree has a root-to-leaf path
    such that adding up all the values along the path equals targetSum.
A leaf is a node with no children.

Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
 */
public class LC112 {
    public static boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)
            return false;
        sum -= root.val;
        if(root.left == null && root.right == null && sum == 0)
            return true;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
}
