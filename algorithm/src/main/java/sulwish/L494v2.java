package sulwish;

/*
给你一个非负整数数组 nums 和一个整数 target 。

向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 */

public class L494v2 {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int[][] f = new int[n][40001];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 40000; j++) {
                if (i == 0) {
                    if (j == nums[i] + 20000) f[i][j] += 1;
                    if (j == -nums[i] + 20000) f[i][j] += 1;
                } else {
                    if (j - nums[i] >= 0) {
                        f[i][j] += f[i - 1][j - nums[i]];
                    }
                    if (j + nums[i] <= 40000) {
                        f[i][j] += f[i - 1][j + nums[i]];
                    }
                }


            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(f[i][0] + ",");
        }
        return f[n - 1][target + 20000];
    }
}
