package sulwish;

/*
给你一个非负整数数组 nums 和一个整数 target 。

向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 */
public class L494 {
    int cnt = 0;

    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, target, 0, 0);
        return cnt;
    }

    private void dfs(int[] nums, int target, int cur, int curSum) {
        if (cur == nums.length) {
            if (target == curSum) cnt++;
            return;
        } else {
            //add +
            dfs(nums, target, cur + 1, curSum + nums[cur]);

            //add -
            dfs(nums, target, cur + 1, curSum - nums[cur]);
        }
    }


}
