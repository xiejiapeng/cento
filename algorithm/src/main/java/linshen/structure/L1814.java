package linshen.structure;

/*
给你一个数组 nums ，数组中只包含非负整数。定义 rev(x) 的值为将整数 x 各个数字位反转得到的结果。
比方说 rev(123) = 321 ， rev(120) = 21 。我们称满足下面条件的下标对 (i, j) 是 好的 ：

0 <= i < j < nums.length
nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
请你返回好下标对的数目。由于结果可能会很大，请将结果对 109 + 7 取余 后返回。
提示：
1 <= nums.length <= 105
0 <= nums[i] <= 109
 */

import java.util.HashMap;
import java.util.Map;

public class L1814 {
    int mod = (int)(1e9+7);
    public int countNicePairs(int[] nums) {
        Map<Long, Long> cnt = new HashMap<>();
        for (int x : nums) {
            long diff = x - reverse(x);
            cnt.put(diff, cnt.getOrDefault(diff, 0L) + 1);
        }
        long ans = 0;
        for (long c : cnt.values()) {
            ans += ((c%mod) * (c%mod-1))/ 2;
            ans %= mod;
        }
        return (int)(ans);
    }

    private long reverse(int x) {
        long ans = 0;
        while (x != 0) {
            ans = ans * 10 + (x % 10);
            x /= 10;
        }
        return ans;
    }
}
