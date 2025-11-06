package linshen.greedy;

/*
给你两个长度都为 n 的整数数组 arr 和 brr 以及一个整数 k 。你可以对 arr 执行以下操作任意次：

将 arr 分割成若干个 连续的 子数组，并将这些子数组按任意顺序重新排列。这个操作的代价为 k 。
选择 arr 中的任意一个元素，将它增加或者减少一个正整数 x 。这个操作的代价为 x 。

请你返回将 arr 变为 brr 的 最小 总代价。

子数组 是一个数组中一段连续 非空 的元素序列。
 */

import java.util.Arrays;

public class L3424 {
    public long minCost(int[] arr, int[] brr, long k) {
        int c = 0;
        for (int i = 0; i < arr.length; i++){
            c += (Math.abs(arr[i] - brr[i]));
        }
        Arrays.sort(arr);
        Arrays.sort(brr);
        int cnt = 0;
        for (int i = arr.length - 1; i > -1; i--){
            if(arr[i] != brr[i]) {
                cnt+=(Math.abs(arr[i] - brr[i]));
            }
        }
        return Math.min(c, cnt + k);
    }
}
