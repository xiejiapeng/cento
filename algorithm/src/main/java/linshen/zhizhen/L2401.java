package linshen.zhizhen;

/*
给你一个由 正 整数组成的数组 nums 。

如果 nums 的子数组中位于 不同 位置的每对元素按位 与（AND）运算的结果等于 0 ，则称该子数组为 优雅 子数组。

返回 最长 的优雅子数组的长度。
[1,3,8,48,10]
000001
000011
001000
110000
001010
 */

public class L2401 {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int or = 0;
        int ans = 1;
        //todo h: 将区间的状态压缩到or这个单一变量中，可以对区间是否满足条件进行整体判断
        for (int left = 0, right = 0; right < n; right++){
            while ((or & nums[right]) != 0) {
                or ^= nums[left];
                left++;
            }
            or |= nums[right];
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
