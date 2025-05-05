package linshen.dp;

/*
给你一个整数数组 nums ，你可以对它进行一些操作。

每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，
你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。

开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L740 {
    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);
        Map<Integer,Integer> last = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            last.put(nums[i], i);
        }

        int n = nums.length;
        int[] f = new int[n+1];
        f[n-1] = nums[n-1];
        for (int i = n - 2; i > -1; i--) {
            int s = last.get(nums[i]);
            if(s+1 < n && nums[s+1] == nums[i] + 1) {
                int t = last.get(nums[s+1]);
                f[i] = Math.max((s-i+1) * nums[i] + f[t+1], f[s+1]);
            } else {
                f[i] = nums[i] + f[i+1];
            }
        }
        System.out.println(Arrays.toString(f));
        return f[0];
    }

    public static void main(String[] args) {
        System.out.println(new L740().deleteAndEarn(new int[]{2,2,3,3,3,4}));
    }


}
