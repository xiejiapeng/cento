package sulwish;

/*
给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
子数组 是数组的一段连续部分。
 */

import java.util.HashMap;
import java.util.Map;

public class L930 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int cnt = 0;
        int curSum = 0;
        for(int left = 0, right = 0; right < nums.length; right++){
            curSum += nums[right];
            while(left <= right && curSum > goal) {
                curSum -= nums[left];
                left++;
            }
            if(left <= right && curSum == goal) {
                cnt += 1;
                int t = left;
                while (t + 1 <= right && curSum - nums[t] == goal) {
                    t++;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new L930().numSubarraysWithSum(new int[]{0,1,0}, 0));
    }
}
