package linshen.dp;

/*
给你一个整数数组 nums 和一个 正 整数 k 。
nums 的一个 子序列 sub 的长度为 x ，如果其满足以下条件，则称其为 有效子序列 ：

(sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k
返回 nums 的 最长 有效子序列 的长度。

提示：

2 <= nums.length <= 103
1 <= nums[i] <= 107
1 <= k <= 103
 */

import java.util.HashMap;
import java.util.Map;

public class L3202 {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer>[] dp = new Map[n];
        for (int i = 0; i < n; i++){
            dp[i] = new HashMap<>();
        }
        int max = 0;
        for (int i = n - 2; i > -1; i--){
            for (int j = i + 1; j < n; j++){
                int m = (nums[i] + nums[j]) % k;
                if(dp[j].containsKey(m)) {
                    int x = dp[j].get(m);
                    dp[i].compute(m, (key,value) -> {
                        if(value == null)value = 1 + x;
                        else {
                            value = Math.max(value, 1 + x);
                        }
                        return value;
                    });
                } else {
                    if(!dp[i].containsKey(m)){
                        dp[i].put(m, 2);
                    }
                }
                max = Math.max(max, dp[i].get(m));
            }
        }
        return max;
    }
}
