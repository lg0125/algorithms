package com.chris.algorithms.leetcode.binarytree;

import java.util.HashMap;

/*
Given two integer arrays inorder and postorder
    where inorder is the inorder traversal of a binary tree
    and postorder is the postorder traversal of the same tree,
construct and return the binary tree.
Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
 */
public class LC106 {
    private static final HashMap<Integer, Integer> INORDER_VALUE2INDEX = new HashMap<>();

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        for(int i = 0; i < n; ++i)
            INORDER_VALUE2INDEX.put(inorder[i], i);
        return f(
                inorder, 0, n - 1,
                postorder, 0, n - 1
        );
    }

    private static TreeNode f(
            int[] inorder, int inStart, int inEnd,
            int[] postorder, int postStart, int postEnd) {
        if(inStart > inEnd || postStart > postEnd)
            return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        int inRoot = INORDER_VALUE2INDEX.get(root.val);
        int leftCnt = inRoot - inStart;

        root.left = f(
                inorder, inStart, inRoot - 1,
                postorder, postStart, postStart + leftCnt - 1
        );

        root.right = f(
                inorder, inRoot + 1, inEnd,
                postorder, postStart + leftCnt, postEnd - 1
        );

        return root;
    }
}
