package index.seqdp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，
子集中每一元素对 (answer[i], answer[j]) 都应当满足：
answer[i] % answer[j] == 0 ，或
answer[j] % answer[i] == 0
如果存在多个有效解子集，返回其中任何一个均可。


 */

public class L368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        int max = -1;
        List[] dp = new ArrayList[n];
        for (int i = 0; i < n; i++){
            dp[i] = new ArrayList();
            if(i == 0) {
                dp[i].add(nums[0]);
            } else {
                for (int j = i; j > -1; j--){
                    if(nums[i] % nums[j] == 0) {
                        if(1 + dp[j].size() > dp[i].size()) {
                            dp[i] = new ArrayList(dp[j]);
                            dp[i].add(nums[i]);
                        }
                    }
                }
            }
            if(dp[i].size() > max) {
                max = dp[i].size();
                ans = dp[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L368().largestDivisibleSubset(new int[]{3,4,16,8}));
    }
}
