package index.dualpointer;

/*
如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。

例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。

子数组 是数组中的一个连续序列。

1 <= nums.length <= 5000
-1000 <= nums[i] <= 1000

 */

public class L413 {
    int m = 2001;
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n][m]; // f[i][j] means ends at i, diff is m - 1000
        int ans = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if(i < 2)f[i][j] = 0;
                else if(i == 2) {
                    if(nums[i] - nums[i-1] == j-1000 && nums[i-1] - nums[i-2] == j-1000) {
                        f[i][j] = 1;
                    }
                } else {
                    if(j-1000 == nums[i] - nums[i-1]){
                        f[i][j] += f[i-1][j];
                        if(nums[i-1]-nums[i-2] == j-1000)f[i][j]+=1;
                    } else {
                        f[i][j] = 0;
                    }
                }

                ans += f[i][j];
            }
        }
        return ans;
    }


}
