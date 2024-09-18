package sulqn;

/*
给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。

子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。


 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L1218 {
    public static void main(String[] args) {
        System.out.println(new L1218().longestSubsequence(new int[]{3, 4, -3, -2, -4}, -5));
    }

    public int longestSubsequence(int[] arr, int difference) {
        int n = arr.length;
        int[] ans = new int[n];

        int max = 1;
        Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (i == 3) {
                System.out.println("H");
            }
            ans[i] = 1;


            if (idx.containsKey(arr[i] - difference)) {
                ans[i] = Math.max(ans[i], 1 + ans[idx.get(arr[i] - difference)]);
            }
            max = Math.max(max, ans[i]);
            idx.put(arr[i], i);
        }
        System.out.println(Arrays.toString(ans));
        return max;
    }
}
