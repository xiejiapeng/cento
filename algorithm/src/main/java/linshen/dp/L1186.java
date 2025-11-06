package linshen.dp;

/*
给你一个整数数组，返回它的某个 非空 子数组（连续元素）在执行一次可选的删除操作后，
所能得到的最大元素总和。换句话说，你可以从原数组中选出一个子数组，并可以决定要不要从中删
除一个元素（只能删一次哦），（删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素
总和是所有子数组之中最大的。

注意，删除一个元素后，子数组 不能为空。
提示：

1 <= arr.length <= 105
-104 <= arr[i] <= 104

 */
public class L1186 {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        /*
        sum[i,j] = sum[j+1] - sum[i]
         */
        long[] sum = new long[n+1];
        for (int i = 0; i < n; i++){
            sum[i+1] = sum[i] + arr[i];
        }
        int[] left = new int[n+1];
        int[] right = new int[n+1];

        right[n-1] = n-1;
        for (int i = n - 2; i > -1; i--) {
            right[i] = i;
            if(sum[right[i+1] + 1] - sum[i] >= arr[i]) {
                right[i] = right[i+1];
            }
        }
        left[0] = 0;
        for (int i = 1; i < n; i++){
            left[i] = i;
            if(sum[i+1] - sum[left[i-1]] >= arr[i]) {
                left[i] = left[i-1];
            }
        }

        long max = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            //include i
            long x = sum[i+1] - sum[left[i]] + sum[right[i]+1] - sum[i] - arr[i];
            //exclude i
            long y = sum[i+1] - sum[left[i]] - arr[i] + sum[right[i]+1] - sum[i] - arr[i];
            max = Long.max(max, x);
            if(right[i] - left[i] > 0)max = Long.max(max, y);
        }
        return (int)(max);
    }

    public static void main(String[] args) {
        System.out.println(new L1186().maximumSum(new int[]{1,-2,-2,3}));
    }
}
