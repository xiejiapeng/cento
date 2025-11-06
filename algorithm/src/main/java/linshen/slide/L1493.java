package linshen.slide;

/*
给你一个二进制数组 nums ，你需要从中删掉一个元素。

请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。

如果不存在这样的子数组，请返回 0 。
 */

public class L1493 {
    public int longestSubarray(int[] nums) {
        int max = 0;
        int n = nums.length;
        int cnt = 0; // number of 0
        for (int left = 0, right = 0; right < n; right++) {
            if(nums[right] == 0){
                cnt++;
            }
            while (cnt > 1) {
                if(nums[left] == 0)cnt--;
                left++;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }
}
