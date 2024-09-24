package index.prefix;

/*
给定一个二进制数组 nums ,
 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 */

import java.util.HashMap;
import java.util.Map;

public class L525 {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n+1];
        Map<Integer,Integer> s = new HashMap<>();
        s.put(0, 0);
        int max = 0;
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + (nums[i-1] == 0 ? -1 : 1);
            if(s.containsKey(sum[i])) {
                max = Math.max(max, i - s.get(sum[i]));
            }
            if(!s.containsKey(sum[i])) {
                s.put(sum[i], i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L525().findMaxLength(new int[]{0,0,1,0,0,0,1,1}));
    }
}
