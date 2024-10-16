package com.chris.algorithms.leetcode.binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 (i.e., from left to right, then right to left for the next level and alternate between).

Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
 */
public class LC103 {
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if(root == null)
            return ans;
        boolean isLeft = true;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            int sz = que.size();
            for(int i = 0; i < sz; ++i) {
                TreeNode cur = que.poll();
                if(isLeft)
                    level.offerLast(cur.val);
                else
                    level.offerLast(cur.val);

                if(cur.left != null)
                    que.offer(cur.left);
                if(cur.right != null)
                    que.offer(cur.right);
            }
            ans.add(level);
            isLeft = !isLeft;
        }
        return ans;
    }
}
