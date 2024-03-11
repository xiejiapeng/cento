package sulwish;

/*
给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，
则返回 数组中连续 1 的最大个数 。
 */

public class L1004 {
    public int longestOnes(int[] nums, int k) {
        int sum = 0;
        int max = 0;
        for (int left = 0, right = 0; right < nums.length; right++){
            sum += nums[right];
            while (left <= right && (right - left + 1  - sum) > k) {
                sum -= nums[left];
                left++;
            }
            if(left <= right) max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
