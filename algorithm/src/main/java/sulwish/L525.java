package sulwish;

/*
给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。


 */

import java.util.HashMap;
import java.util.Map;

public class L525 {
    public int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                nums[i] = -1;
            }
        }

        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 0);
        int s = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++){
            s += nums[i];
            if(m.containsKey(s)) {
                ans = Math.max(ans, i - m.get(s) + 1);
            } else {
                m.put(s, i + 1);
            }
        }
        return ans;
    }
}
