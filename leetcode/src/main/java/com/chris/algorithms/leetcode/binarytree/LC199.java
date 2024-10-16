package com.chris.algorithms.leetcode.binarytree;

import java.util.LinkedList;
import java.util.List;

/*
Given the root of a binary tree, imagine yourself standing on the right side of it,
return the values of the nodes you can see ordered from top to bottom.

Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]
 */
public class LC199 {
    private static List<Integer> ans = new LinkedList<>();

    public static List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private static void dfs(TreeNode root, int depth) {
        if(root == null)
            return;
        if(depth == ans.size())
            ans.add(root.val);
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    }
}
