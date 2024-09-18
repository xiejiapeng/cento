package sulwish;

/*
给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：

子数组大小 至少为 2 ，且
子数组元素总和为 k 的倍数。
如果存在，返回 true ；否则，返回 false 。

如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。0 始终视为 k 的一个倍数。
 */

import java.util.HashMap;
import java.util.Map;

public class L523 {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer,Integer> sum = new HashMap<>();
        sum.put(0, 0);
        int s = 0;
        for (int i = 0; i < nums.length; i++){
            s += nums[i];
            s %= k;
            if(sum.containsKey(s)) {
                int t = sum.get(s);
                if(i - t + 1 >= 2)return true;
            } else {
                sum.put(s, i + 1);
            }
        }
        return false;
    }
}
