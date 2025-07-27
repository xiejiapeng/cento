package linshen.structure;

/*
给你一个整数数组 arr 和一个整数值 target 。
请你在 arr 中找 两个互不重叠的子数组 且它们的和都等于 target 。可能会有多种方案，
请你返回满足要求的两个子数组长度和的 最小值 。

请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1 。
提示：

1 <= arr.length <= 10^5
1 <= arr[i] <= 1000
1 <= target <= 10^8
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//todo hhhhhh 注意这道题里面用到的状态，我感觉很有用，适合做模版
//left[i]是i之前的最小值，see[k,v]表示和为k的前缀和的下一个坐标

public class L1477 {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] s = new int[n+1];
        for (int i = 1; i <= n; i++){
            s[i] = s[i-1] + arr[i-1];
        }
        int[] left = new int[n+1];
        Arrays.fill(left, Integer.MAX_VALUE);
        Map<Integer, Integer> see = new HashMap<>();
        see.put(0, 0);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            int t = s[i+1] - target;
            left[i+1] = left[i];
            if(see.containsKey(t)) {
                int start = see.get(t);
                int len = i - start + 1;
                //todo hhh 注意这里终点是start
                if(left[start] != Integer.MAX_VALUE) {
                    min = Math.min(min, left[start] + len);
                    if(min == 5) {
                        System.out.println("f");
                    }
                }
                left[i + 1] = Math.min(left[i], len);
            }

            see.put(s[i+1], i+1);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String[] args) {
        System.out.println(new L1477().minSumOfLengths(new int[]{1,1,1,2,2,2,4,4}, 6));
    }
}
