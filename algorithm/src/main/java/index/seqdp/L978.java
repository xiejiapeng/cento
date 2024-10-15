package index.seqdp;

/*
给定一个整数数组 arr ，返回 arr 的 最大湍流子数组的长度 。
如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是 湍流子数组 。

更正式地来说，当 arr 的子数组 A[i], A[i+1], ..., A[j] 满足仅满足下列条件时，我们称其为湍流子数组：

若 i <= k < j ：
当 k 为奇数时， A[k] > A[k+1]，且
当 k 为偶数时，A[k] < A[k+1]；
或 若 i <= k < j ：
当 k 为偶数时，A[k] > A[k+1] ，且
当 k 为奇数时， A[k] < A[k+1]。

 */

public class L978 {
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int[][] f = new int[n][2];
        int max = 1;
        for (int i = n - 1; i > -1; i--) {
            f[i][0] = 1;
            f[i][1] = 1;
            if(i == n - 1) {
                f[i][0] = 1;
                f[i][1] = 1;
            } else {
                for (int j = i + 1; j < n; j++){
                    if(arr[j] > arr[i]) {
                        f[i][0] =Math.max(f[i][0], f[j][1]+1);
                    } else if(arr[j] < arr[i]) {
                        f[i][1] = Math.max(f[i][1], f[j][0] + 1);
                    }
                }
            }

            max = Math.max(max, f[i][0]);
            max = Math.max(max, f[i][1]);
        }
        return max;
    }
}
