package linshen.structure;

/*
给你一个长度为 n 的数组 nums 和一个 正 整数 k 。
如果 nums 的一个子数组中，第一个元素和最后一个元素 差的绝对值恰好 为 k ，
我们称这个子数组为 好 的。换句话说，如果子数组 nums[i..j] 满足 |nums[i] - nums[j]| == k ，那么它是一个好子数组。
请你返回 nums 中 好 子数组的 最大 和，如果没有好子数组，返回 0 。

todo hhhhhhh 记住，这道题没有那么简单
提示：

2 <= nums.length <= 105
-109 <= nums[i] <= 109
1 <= k <= 109
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L3026 {
    public long maximumSubarraySum2(int[] nums, int k) {
        int n = nums.length;
        long[] s = new long[n+1];
        for (int i = 1; i <= n; i++){
            s[i] = s[i-1] + nums[i-1];
        }

        long max = Long.MIN_VALUE;
        Map<Integer, Set<Integer>> all = new HashMap<>();
        int cnt = 0;
        for (int i = n - 1; i > -1; i--){
            Set<Integer> o1 = all.getOrDefault(nums[i] + k, new HashSet<>());
            for (int y : o1) {
                cnt++;
                max = Math.max(max, s[y+1]-s[i]);
            }
            Set<Integer> o2 = all.getOrDefault(nums[i] - k, new HashSet<>());
            for (int y : o2) {
                cnt++;
                max = Math.max(max, s[y+1]-s[i]);
            }
            all.putIfAbsent(nums[i], new HashSet<>());
            all.get(nums[i]).add(i);
        }
        System.out.println(cnt);
        return max == Long.MIN_VALUE ? 0 : max;
    }

    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] s = new long[n+1];
        for (int i = 1; i <= n; i++){
            s[i] = s[i-1] + nums[i-1];
        }

        long max = Long.MIN_VALUE;
        Map<Integer, Long> all = new HashMap<>();
        for (int i = 0; i < n; i++){
            long x = s[i+1];
            int t1 = nums[i] - k;
            if(all.containsKey(t1)){
                max = Math.max(max, x - all.get(t1) + t1);
            }
            int t2 = nums[i] + k;
            if(all.containsKey(t2)) {
                max = Math.max(max, x - all.get(t2) + t2);
            }
            all.put(nums[i], Math.min(all.getOrDefault(nums[i], Long.MAX_VALUE), x));
        }
        return max == Long.MIN_VALUE ? 0 : max;
    }
}
