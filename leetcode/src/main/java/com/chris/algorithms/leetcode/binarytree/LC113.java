package com.chris.algorithms.leetcode.binarytree;

import java.util.LinkedList;
import java.util.List;

/*
Given the root of a binary tree and an integer targetSum,
return all root-to-leaf paths where the sum of the node values in the path equals targetSum.
Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node.
A leaf is a node with no children.

Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whose sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22
 */
public class LC113 {
    private static List<List<Integer>> res = new LinkedList<>();

    public static List<List<Integer>> pathSum(TreeNode root, int target) {
        LinkedList<Integer> path = new LinkedList<>();

        dfs(root, target, path);

        return res;
    }

    private static void dfs(TreeNode root, int target, LinkedList<Integer> path) {
        if(root == null)
            return;

        path.add(root.val);
        target -= root.val;
        if(root.left == null && root.right == null && target == 0)
            res.add(new LinkedList<>(path));

        dfs(root.left, target, path);
        dfs(root.right, target, path);

        target += root.val;
        path.removeLast();
    }
}
