package com.chris.algorithms.leetcode.array.hot100;

/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 */
public class LC200 {
    public static int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        int res = 0;
        for(int row = 0; row < m; ++row)
            for(int col = 0; col < n; ++col)
                if(grid[row][col] == '1') {
                    res = res + 1;

                    dfs(grid, row, col);
                }
        return res;
    }

    private static void dfs(char[][] grid, int row, int col) {
        int m = grid.length;
        int n = grid[0].length;

        if(row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == '0')
            return;

        grid[row][col] = '0';

        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numIslands(grid));
    }
}
