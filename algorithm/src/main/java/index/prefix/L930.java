package index.prefix;

/*
给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal
的 非空 子数组。

子数组 是数组的一段连续部分。
 */

import java.util.HashMap;
import java.util.Map;

public class L930 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer,Integer> all = new HashMap<>();
        all.put(0, 1);
        int s = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++){
            s += nums[i];
            int target = s - goal;
            ans += all.getOrDefault(target, 0);
            all.put(s, all.getOrDefault(s, 0) + 1);
        }
        return ans;
    }
}
