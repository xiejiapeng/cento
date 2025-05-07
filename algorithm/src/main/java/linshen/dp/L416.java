package linshen.dp;

/*
给你一个 只包含正整数 的 非空 数组 nums 。
请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

提示：

1 <= nums.length <= 200
1 <= nums[i] <= 100
 */

import java.util.Arrays;

public class L416 {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        boolean[][] can = new boolean[n+1][20005];
        for (int i = 1; i <= n; i++){
            for (int j = 0; j <= 20000; j++){
                if(i == 1) {
                    if(j == nums[i-1])can[i][j] = true;
                    else can[i][j] = false;
                } else {
                    can[i][j] = can[i-1][j];
                    if(j >= nums[i-1]) {
                        can[i][j] |= can[i-1][j-nums[i-1]];
                    }
                }
            }
        }
        int sum = Arrays.stream(nums).sum();
        return sum % 2 == 0 && can[n][sum / 2];
    }
}
