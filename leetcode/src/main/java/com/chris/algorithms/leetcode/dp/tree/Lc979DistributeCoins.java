package com.chris.algorithms.leetcode.dp.tree;

/** 在二叉树中分配硬币
 给你一个有 n 个结点的二叉树的根结点 root
 其中树中每个结点 node 都对应有 node.val 枚硬币
 整棵树上一共有 n 枚硬币
 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点
 移动可以是从父结点到子结点，或者从子结点移动到父结点
 返回使每个结点上 只有 一枚硬币所需的 最少 移动次数
 测试链接 : <a href="https://leetcode.cn/problems/distribute-coins-in-binary-tree/">...</a>
 */
public class Lc979DistributeCoins {
    public static class Solution {
        // 不要提交这个类
        public static class TreeNode {
            public int val;
            public TreeNode left;
            public TreeNode right;
        }

        // 提交如下的方法
        public static class Info {
            public int cnt;
            public int sum;
            public int move;

            public Info(int a, int b, int c) {
                cnt = a;
                sum = b;
                move = c;
            }
        }

        // 提交如下的方法
        public static int distributeCoins(TreeNode root) {
            return f(root).move;
        }

        public static Info f(TreeNode x) {
            if (x == null)
                return new Info(0, 0, 0);

            Info info_left  = f(x.left);
            Info info_right = f(x.right);

            int cnts    = info_left.cnt + info_right.cnt + 1;
            int sums    = info_left.sum + info_right.sum + x.val;
            int moves   = info_left.move + info_right.move + Math.abs(info_left.cnt - info_left.sum) + Math.abs(info_right.cnt - info_right.sum);

            return new Info(cnts, sums, moves);
        }
    }
}
