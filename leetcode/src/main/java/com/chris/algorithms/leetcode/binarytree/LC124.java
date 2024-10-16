package com.chris.algorithms.leetcode.binarytree;

import java.util.List;

/*
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
    A node can only appear in the sequence at most once.
    Note that the path does not need to pass through the root.
The path sum of a path is the sum of the node's values in the path.
Given the root of a binary tree, return the maximum path sum of any non-empty path.

Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 */
public class LC124 {
    private static int ans = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        oneSideMax(root);
        return ans;
    }

    private static int oneSideMax(TreeNode node) {
        if(node == null)
            return 0;
        int l = Math.max(0, oneSideMax(node.left));
        int r = Math.max(0, oneSideMax(node.right));

        ans = Math.max(ans, l + r + node.val);

        return Math.max(l, r) + node.val;
    }
}
