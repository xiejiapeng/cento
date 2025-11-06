package linshen.greedy;

/*
给你两个下标从 0 开始的数组 nums 和 cost ，分别包含 n 个 正 整数。

你可以执行下面操作 任意 次：

将 nums 中 任意 元素增加或者减小 1 。
对第 i 个元素执行一次操作的开销是 cost[i] 。

请你返回使 nums 中所有元素 相等 的 最少 总开销。
 */

import java.util.Arrays;
import java.util.Comparator;

public class L2448 {
    public long minCost(int[] nums, int[] cost) {
        int n = nums.length;
        int[][] all = new int[n][2];
        long sum = 0;
        for (int i = 0; i < n; i++){
            all[i] = new int[]{nums[i], cost[i]};
            sum += cost[i];
        }
        Arrays.sort(all, Comparator.comparingInt(o -> o[0]));
        int target = -1;
        long s = 0;
        for (int i = 0; i < n; i++){
            s += all[i][1];
            if(s > sum / 2) {
                target = i;
                break;
            }
        }
        long c = 0;
        for (int i = 0; i < n; i++){
            c += (long)all[i][1] * (Math.abs(all[i][0] - all[target][0]));
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(new L2448().minCost(new int[]{1,3,5,2}, new int[]{2,3,1,14}));
    }
}
