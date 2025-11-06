package linshen.binary;

/*
给你一个满足下述两条属性的 m x n 整数矩阵：

每行中的整数从左到右按非严格递增顺序排列。
每行的第一个整数大于前一行的最后一个整数。
给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 */

public class L74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int u = findLargest(matrix, 0, matrix.length-1, target);
        if(u == -1)return false;
        else {
            int v = find(matrix[u], 0, matrix[u].length-1, target);
            return v != -1;
        }
    }

    private int find(int[] a, int left, int right, int target) {
        if(left > right)return -1;
        else if(left == right) {
            if(a[left] == target)return left;
            else return -1;
        } else if(left == right - 1) {
            if(a[left] == target)return left;
            else if(a[right] == target)return right;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(a[mid] == target)return mid;
            else if(a[mid] > target)return find(a, left, mid - 1, target);
            else return find(a, mid + 1, right, target);
        }
    }

    private int findLargest(int[][] matrix, int left, int right, int target) {
        if(left > right)return -1;
        else if(left == right) {
            if(matrix[left][0] <= target)return left;
            else return -1;
        } else if(left == right - 1) {
            if(matrix[right][0] <= target)return right;
            else if(matrix[left][0] <= target)return left;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(matrix[mid][0] <= target)return findLargest(matrix, mid, right, target);
            else return findLargest(matrix, left, mid - 1, target);
        }
    }

}
