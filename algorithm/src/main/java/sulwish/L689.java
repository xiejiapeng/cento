package sulwish;

/*
给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、
且全部数字和（3 * k 项）最大的子数组，并返回这三个子数组。

以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。
如果有多个结果，返回字典序最小的一个。
 */

import java.util.Comparator;

public class L689 {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int[] ans = new int[3];
        Comparator<int[]> comparator = (o1, o2) -> {
            if(o1[0] != o2[0])return Integer.compare(o1[0], o2[0]);
            else if(o1[1] != o2[1])return Integer.compare(o1[1], o2[1]);
            else return Integer.compare(o1[2], o2[2]);
        };

        int[] s = new int[n+1];
        for (int i = 1; i <= n; i++){
            s[i] = s[i-1] + nums[i-1];
        }
        int[] sum = new int[n];
        for (int i = 0; i < n; i++){
            if(i < k - 1)sum[i] = 0;
            else {
                sum[i] = s[i+1] - s[i+1-k];
            }
        }
        int[][] left = new int[n][2];
        left[0] = new int[]{sum[0],0};
        for (int i = 1; i < n; i++){
            int lastMax = left[i-1][0];
            if(sum[i] > lastMax){
                left[i] = new int[]{sum[i], i};
            } else {
                left[i] = left[i-1];
            }
        }
        int[][] right = new int[n][2];
        right[n-1] = new int[]{sum[n-1],n-1};
        for (int i = n - 2; i > -1; i--){
            int lastMax = right[i+1][0];
            if(sum[i] >= lastMax){
                right[i] = new int[]{sum[i], i};
            } else {
                right[i] = right[i+1];
            }
        }

        /*
            [i-k-k+1,i-k][i-k+1,i][i+1,i+k]
         */
        for (int i = 0; i < n; i++){
            if(i-k>=0&&i+k<n){
                int mid = sum[i];
                int l = left[i-k][0];
                int lid = left[i-k][1];
                int r = right[i+k][0];
                int rid = right[i+k][1];
                if(mid + l + r > max) {
                    max = mid + l + r;
                    ans = new int[]{lid, i, rid};
                } else if(mid + l + r == max){
                    int[] tmp = new int[]{lid, i, rid};
                    if(comparator.compare(tmp, ans) < 0){
                        ans = tmp;
                    }
                }
            }


        }
        return ans;
    }
}
