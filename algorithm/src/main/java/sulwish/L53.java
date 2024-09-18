package sulwish;

/*
给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

s[j+1] - s[i] = sum[a[i],...,a[j])

子数组
是数组中的一个连续部分。
 */

public class L53 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int ans = Integer.MIN_VALUE;
        int[] s = new int[n+1];
        s[0] = 0;
        int curMin = 0;
        for (int i = 1; i <= n; i++){
            s[i] = s[i-1] + nums[i-1];
            ans = Math.max(ans, s[i] - curMin);
            curMin = Math.min(s[i], curMin);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L53().maxSubArray(new int[]{-1}));
    }
}
