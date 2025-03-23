package linshen.zhizhen;

/*
给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。

子数组 是数组的一段连续部分。
 */

import java.util.HashMap;
import java.util.Map;

public class L930 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int ans = 0;
        Map<Integer, Integer> all = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < n; i++){
            sum += nums[i];
            int target = sum - goal;
            ans += all.getOrDefault(target, 0);
            //todo 将put放到最后，否则ans将会加两次。同时要注意sum == goal时，ans也要加1
            all.put(sum, all.getOrDefault(sum, 0) + 1);
            if(sum == goal)ans++;

        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L930().numSubarraysWithSum(new int[]{0,0,0}, 0));
    }
}
