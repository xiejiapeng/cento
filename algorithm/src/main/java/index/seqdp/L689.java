package index.seqdp;

/*
给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且全部数字和（3 * k 项）
最大的子数组，并返回这三个子数组。

以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。
如果有多个结果，返回字典序最小的一个。


 */

import java.util.Arrays;

public class L689 {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n];
        int s = 0;
        for (int i = 0; i < n; i++){
            s += nums[i];
            if(i-k>=0)s-=nums[i-k];
            if(i >= k-1)sum[i] = s;
        }
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];

        int[][] ansa = new int[n][1];
        int[][] ansb = new int[n][2];
        int[][] ansc = new int[n][3];
        for (int i = 0; i < n; i++) {
            if(i == 6) {
                System.out.println("f");
            }
            if(i >= k - 1) {
                if(i-1<0 || sum[i] > a[i-1]) {
                    a[i] = sum[i];
                    ansa[i] = new int[]{i-k+1};
                } else {
                    a[i] = a[i-1];
                    ansa[i] = ansa[i-1];
                }
            }

            if(i >= 2*k-1) {
                if(sum[i] + a[i-k] > b[i-1]) {
                    b[i] = sum[i] + a[i-k];
                    ansb[i] = new int[]{ansa[i-k][0], i-k+1};
                } else {
                    b[i] = b[i-1];
                    ansb[i] = ansb[i-1];
                }
            }

            if(i >= 3 * k - 1) {
                if(i-k>-1 && sum[i] + b[i-k] > c[i-1]) {
                    c[i] = sum[i] + b[i-k];
                    ansc[i] = new int[]{ansb[i-k][0], ansb[i-k][1], i-k+1};
                } else {
                    c[i] = c[i-1];
                    ansc[i] = ansc[i-1];
                }
            }

        }

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));
        return ansc[n-1];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L689().maxSumOfThreeSubarrays(new int[]{4,5,10,6,11,17,4,11,1,3}, 1)));
    }
}
