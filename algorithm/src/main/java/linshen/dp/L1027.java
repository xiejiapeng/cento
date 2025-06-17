package linshen.dp;

/*
给你一个整数数组 nums，返回 nums 中最长等差子序列的长度。

回想一下，nums 的子序列是一个列表 nums[i1], nums[i2], ..., nums[ik] ，
且 0 <= i1 < i2 < ... < ik <= nums.length - 1。
并且如果 seq[i+1] - seq[i]( 0 <= i < seq.length - 1) 的值都相同，那么序列 seq 是等差的。

提示：

2 <= nums.length <= 1000
0 <= nums[i] <= 500
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L1027 {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        Map<Integer,Integer>[] dp = new Map[n];
        for (int i = 0; i < n; i++){
            dp[i] = new HashMap<Integer,Integer>();
        }
        for (int i = n - 2; i > -1; i--) {
            for (int j = i + 1; j < n; j++){
                int diff = nums[j] - nums[i];
                if(dp[j].containsKey(diff)) {
                    int x = dp[j].get(diff);
                    dp[i].compute(diff, (k,v) -> {
                        if(v == null)v = 1 + x;
                        else {
                            v = Math.max(v, 1 + x);
                        }
                        return v;
                    });
                } else {
                    //todo m 不要急着赋值，这里也需要判断大小
                    if(!dp[i].containsKey(diff)) {
                        dp[i].put(diff, 2);
                    }

                }
            }
        }
        for (Map<Integer, Integer> x : dp) {
            System.out.println(x);
        }
        return Arrays.stream(dp).map(d -> d.values().stream().mapToInt(i -> i).max().orElse(0)).mapToInt(i -> i).max().orElse(0);
    }

    public static void main(String[] args) {
        System.out.println(new L1027().longestArithSeqLength(new int[]{44,46,22,68,45,66,43,9,37,30,50,67,32,47}));
    }
}
