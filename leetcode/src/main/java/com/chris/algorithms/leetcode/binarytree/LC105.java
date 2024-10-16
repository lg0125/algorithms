package com.chris.algorithms.leetcode.binarytree;

import java.util.HashMap;

/*
Given two integer arrays preorder and inorder
    where preorder is the preorder traversal of a binary tree
    and inorder is the inorder traversal of the same tree,
construct and return the binary tree.

Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
 */
public class LC105 {
    private static final HashMap<Integer, Integer> INORDER_VALUE2INDEX = new HashMap<>();

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        for(int i = 0; i < n; ++i)
            INORDER_VALUE2INDEX.put(inorder[i], i);
        return f(
                preorder, 0, n-1,
                inorder, 0, n-1
        );
    }

    private static TreeNode f(
            int[] preorder, int preStart, int preEnd,
            int[] inorder, int inStart, int inEnd) {
        if(preStart > preEnd || inStart > inEnd)
            return null;
        TreeNode root   = new TreeNode(preorder[preStart]);

        int inRoot      = INORDER_VALUE2INDEX.get(root.val);
        int leftCnt     = inRoot - inStart;

        root.left = f(
                preorder, preStart + 1, preStart + leftCnt,
                inorder, inStart, inRoot - 1
        );

        root.right = f(
                preorder, preStart + leftCnt + 1, preEnd,
                inorder, inRoot + 1, inEnd
        );

        return root;
    }
}
