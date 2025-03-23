package linshen.zhizhen;

/*
给你一个整数数组 nums 和一个整数 k。如果某个连续子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
请返回这个数组中 「优美子数组」 的数目。
 */

import java.util.HashMap;
import java.util.Map;

public class L1248 {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> all = new HashMap<>();
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i] % 2 != 0) sum++;
            int target = sum - k;
            ans += (all.getOrDefault(target, 0));
            if(sum == k)ans++;
            all.put(sum, all.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}
