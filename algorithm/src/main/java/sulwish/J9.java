package sulwish;

/*
给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
 */

public class J9 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0;
        long m = 1;
        for (int left = 0, right = 0; left < nums.length; left++){
            if(right <= left)right = left;
            while (right < nums.length && m * nums[right] < k) {
                m *= nums[right];
                right++;
            }
            ans += (right - left);
            if(right > 0)m /= nums[left];

        }
        return ans;
    }

    private int cnt(int l, int r) {
        if(l == r)return 0;
        else {
            int len = r - l;
            return len + (len - 1) * len / 2;
        }
    }

    public static void main(String[] args) {
        System.out.println(new J9().numSubarrayProductLessThanK(new int[]{1,2,3}, 0));
    }
}
