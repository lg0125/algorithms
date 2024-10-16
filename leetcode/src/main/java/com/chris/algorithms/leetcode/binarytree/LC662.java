package com.chris.algorithms.leetcode.binarytree;

import java.util.HashMap;
import java.util.Map;

/*
Given the root of a binary tree, return the maximum width of the given tree.
The maximum width of a tree is the maximum width among all levels.
The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
    where the null nodes between the end-nodes that would be present in a complete binary tree
        extending down to that level are also counted into the length calculation.
It is guaranteed that the answer will in the range of a 32-bit signed integer.

Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
Input: root = [1,3,2,5,null,null,9,6,null,7]
Output: 7
Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
 */
public class LC662 {
    private static final Map<Integer, Integer> MIN_INDEX = new HashMap<>();

    public static int widthOfBinaryTree(TreeNode root) {
        return dfs(root, 1, 0);
    }

    private static int dfs(TreeNode root, int depth, int index) {
        if(root == null)
            return 0;
        // 后序遍历 -> 每一层最先访问到的节点会是最左边的节点，即每一层编号的最小值
        MIN_INDEX.putIfAbsent(depth, index);
        int left   = dfs(root.left, depth + 1, index * 2 + 1);
        int right = dfs(root.right, depth + 1, index * 2 + 2);
        return Math.max(
                index - MIN_INDEX.get(depth) + 1,
                Math.max(left, right)
        );
    }

}
