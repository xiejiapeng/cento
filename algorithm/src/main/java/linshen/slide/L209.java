package linshen.slide;

/*
给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其总和大于等于 target 的长度最小的 子数组
 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 */

public class L209 {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int curSum = 0;
        int min = Integer.MAX_VALUE;
        for (int left = 0, right = 0; right < n; right++){
            curSum += nums[right];
            //todo 这种情况不要更新min
            if(curSum < target)continue;
            while (left < right && curSum - nums[left] >= target) {
                //todo 不要忘了更新状态
                curSum -= nums[left];
                left++;
            }
            min = Math.min(min, right - left + 1);
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static void main(String[] args) {
        System.out.println(new L209().minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    }
}
