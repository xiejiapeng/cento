package linshen.zhizhen;

/*
给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
1 <= nums.length <= 3 * 104
1 <= nums[i] <= 1000
0 <= k <= 106
 */

public class L713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        long cur = 1;
        int ans = 0;
        for (int left = 0, right = 0; right < n; right++) {
            cur *= nums[right];
            //todo 记住这里left<=right的判断条件
            while (left <= right && cur >= k) {
                cur /= nums[left];
                left++;
            }
            ans += (right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L713().numSubarrayProductLessThanK(new int[]{1,2,3}, 0));
    }

}
