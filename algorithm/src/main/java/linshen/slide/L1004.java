package linshen.slide;

/*
给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。


 */

public class L1004 {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int max = 0;
        int cnt = 0;
        for (int left = 0, right = 0; right < n; right++){
            if(nums[right] == 0) {
                cnt++;
                while (cnt > k) {
                    if(nums[left] == 0)cnt--;
                    left++;
                }
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
