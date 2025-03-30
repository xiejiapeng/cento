package linshen.binary;

/*
给你一个长度为 n 的整数数组 nums ，和一个长度为 m 的整数数组 queries 。

返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度  。

子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。

n == nums.length
m == queries.length
1 <= n, m <= 1000
1 <= nums[i], queries[i] <= 106
 */

import java.util.Arrays;

public class L2389 {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        int m = queries.length;
        int[] s = new int[n+1];
        int[] ans = new int[m];
        for (int i = 1; i <= n; i++){
            s[i] = s[i-1] + nums[i-1];
        }
        for (int i = 0; i < m; i++){
            int t = findLargest(s, 0, n, queries[i]);
            ans[i] = t;
        }
        return ans;
    }

    private int findLargest(int[] nums, int left, int right ,int limit) {
        if(left > right)return -1;
        else if(left == right) {
            if(nums[right] <= limit)return right;
            else return -1;
        } else if(left == right - 1) {
            if(nums[right] <= limit)return right;
            else if(nums[left] <= limit)return left;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(nums[mid] <= limit)return findLargest(nums, mid, right, limit);
            else return findLargest(nums, left, mid - 1, limit);
        }
    }

    public static void main(String[] args) {
        System.out.println(new L2389().answerQueries(new int[]{4,5,2,1}, new int[]{3,10,21}));
    }
}
