package com.chris.algorithms.leetcode.binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Given the root of a binary tree, return the level order traversal of its nodes' values.
(i.e., from left to right, level by level).
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
 */
public class LC102 {
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if(root == null)
            return ans;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            List<Integer> level = new LinkedList<>();
            int sz = que.size();
            for(int i = 0; i < sz; ++i) {
                TreeNode cur = que.poll();
                level.add(cur.val);

                if(cur.left != null)
                    que.offer(cur.left);
                if(cur.right != null)
                    que.offer(cur.right);
            }
            ans.add(level);
        }
        return ans;
    }
}
