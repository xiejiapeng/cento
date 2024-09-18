package sulwish;

/*
给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。


 */

import java.util.Arrays;

public class L416 {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 != 0)return false;
        else sum = sum / 2;

        int n = nums.length;
        boolean[][] f = new boolean[n][sum+1];
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= sum; j++){
                f[i][j] = false;
                if(i == 0){
                    f[i][j] = j == nums[0];
                    continue;
                }
                if(j == 0)f[i][j] = true;
                else {
                    if(nums[i] <= j){
                        f[i][j] |= f[i-1][j-nums[i]];
                    }
                    f[i][j] |= f[i-1][j];
                }
            }
        }
        return f[n-1][sum];
    }
}
