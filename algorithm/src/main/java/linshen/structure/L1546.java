package linshen.structure;

/*
给你一个数组 nums 和一个整数 target 。

请你返回 非空不重叠 子数组的最大数目，且每个子数组中数字和都为 target 。
 */

import java.util.HashMap;
import java.util.Map;

public class L1546 {
    public int maxNonOverlapping(int[] nums, int target) {
        int n = nums.length;
        int[] s = new int[n+1];
        for (int i = 1; i <= n; i++){
            s[i] = s[i-1] + nums[i-1];
        }
        Map<Integer, Integer> see = new HashMap<>();
        see.put(0, 0);
        int cnt = 0;
        int right = -1;
        for (int i = 0; i < n; i++){
            int t = s[i+1] - target;
            if(see.containsKey(t)) {
                int start = see.get(t);
                //todo h 参考1477模版，另外 这里别用continue，后面还要更新see
                if(start > right){
                    cnt++;
                    right = i;
                }
            }
            see.put(s[i+1], i+1);
            System.out.println("i="+i+",right="+right);
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new L1546().maxNonOverlapping(new int[]{-1,-2,8,-3,8,-5,5,-4,5,4,1}, 5));
    }
}
