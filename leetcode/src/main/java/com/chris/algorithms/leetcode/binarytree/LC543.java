package com.chris.algorithms.leetcode.binarytree;

/*
Given the root of a binary tree, return the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
This path may or may not pass through the root.
The length of a path between two nodes is represented by the number of edges between them.

Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 */
public class LC543 {
    private static int ans = 0;

    private static int diameterOfBinaryTree(TreeNode root) {
        postorder(root);
        return ans;
    }

    private static int postorder(TreeNode root) {
        if(root == null)
            return 0;

        int l = postorder(root.left);
        int r = postorder(root.right);

        ans = Math.max(ans, l + r);

        return Math.max(l, r) + 1;
    }
}
