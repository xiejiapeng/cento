package linshen.dp;

/*
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

子序列 是由数组派生而来的序列，删除（或不删除）数组中的元
素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

提示：
1 <= nums.length <= 2500
-104 <= nums[i] <= 104
 */

public class L300 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int cur = 0;
        for (int i = 0; i < n; i++){
            if(cur == 0) {
                dp[cur] = nums[i];
                cur++;
            } else {
                int t = findFirstLarger(dp, 0, cur - 1, nums[i]);
                if(t == -1) {
                    dp[cur] = nums[i];
                    cur++;
                } else {
                    dp[t] = nums[i];
                }
            }
        }
        return cur;
    }

    private int findFirstLarger(int[] dp, int left, int right, int x) {
        if(left == right) {
            if(dp[left] >= x)return left;
            else return -1;
        } else if(left == right - 1) {
            if(dp[left] >= x)return left;
            else if(dp[right] >= x)return right;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(dp[mid] >= x) return findFirstLarger(dp, left, mid, x);
            else return findFirstLarger(dp, mid + 1, right, x);
        }
    }
}
