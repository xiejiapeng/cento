package index.binary;

/*
给你一个满足下述两条属性的 m x n 整数矩阵：

每行中的整数从左到右按非严格递增顺序排列。
每行的第一个整数大于前一行的最后一个整数。
给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 */

public class L74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        return search(matrix, target, 0, matrix.length * matrix[0].length - 1);
    }

    private boolean search(int[][] matrix, int target ,int left, int right) {
        int n = matrix[0].length;

        if(left > right)return false;
        else if(left == right) {
            int row = left / n;
            int column = left % n;
            return matrix[row][column] == target;
        } else {
            int mid = (left + right) / 2;
            int row = mid / n;
            int column = mid % n;
            if(matrix[row][column] == target)return true;
            else if(matrix[row][column] < target) {
                return search(matrix, target, mid + 1, right);
            } else {
                return search(matrix, target, left, mid - 1);
            }
        }
    }
}
