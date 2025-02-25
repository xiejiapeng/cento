package index.seqdp;

/*
给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。

子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L1218 {
    public int longestSubsequence(int[] arr, int difference) {
        int n = arr.length;
        Map<Integer,Integer> all = new HashMap<>();
        int[] f = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++){
            f[i] = 1;
            int target = arr[i] - difference;
            if(all.containsKey(target)) {
                int t = all.get(target);
                f[i] = Math.max(f[i], 1 + f[t]);
            }
            all.put(arr[i], i);
            max = Math.max(max, f[i]);
        }
        System.out.println(Arrays.toString(f));
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L1218().longestSubsequence(new int[]{1,5,7,8,5,3,4,2,1}, -2));
    }
}
