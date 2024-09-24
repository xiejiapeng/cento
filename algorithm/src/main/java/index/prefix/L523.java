package index.prefix;

/*
给你一个整数数组 nums 和一个整数 k ，
如果 nums 有一个 好的子数组 返回 true ，否则返回 false：

一个 好的子数组 是：

长度 至少为 2 ，且
子数组元素总和为 k 的倍数。
注意：

子数组 是数组中 连续 的部分。
如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。0 始终 视为 k 的一个倍数。
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L523 {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer,Integer> r = new HashMap<>();
        r.put(0, -1);
        int s = 0;
        for (int i = 0; i < n; i++){
            s += nums[i];
            int x = r.getOrDefault(s % k, -5);
            if(x != -5 && (i-x)>=2)return true;
            if(x == -1){
                r.put(s % k, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new L523().checkSubarraySum(new int[]{23,2,6,4,7}, 13));
    }
}
