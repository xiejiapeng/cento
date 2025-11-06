package linshen.binary;

/*
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
 */

public class L240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        return find(matrix, 0, matrix[0].length-1, target);
    }

    private boolean find(int[][] matrix, int x, int y, int target) {
        if(x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length)return false;
        if(matrix[x][y] == target)return true;
        else if(matrix[x][y] > target) {
            return find(matrix, x, y-1, target);
        } else {
            return find(matrix, x + 1, y, target);
        }
    }
}
