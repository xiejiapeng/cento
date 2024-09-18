package sulwish;

/*
给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其总和大于等于 target 的长度最小的 连续
子数组
 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 */

import java.util.TreeMap;

public class L209 {
    public int minSubArrayLen(int target, int[] nums) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        int s = 0;
        map.put(0, -1);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++){
            s += nums[i];
            map.put(s, i);
            Integer t = map.floorKey(s - target);
            if(t != null){
                ans = Math.min(ans, i - map.get(t));
            }
        }
        if(ans == Integer.MAX_VALUE)return 0;
        else return ans;
    }
}
