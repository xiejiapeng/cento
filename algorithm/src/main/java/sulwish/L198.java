package sulwish;

/*
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。


 */

public class L198 {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n <= 1)return nums[n-1];
        int[] f = new int[n];
        f[n-1] = nums[n-1];
        f[n-2] = Math.max(nums[n-1], nums[n-2]);
        for (int i = n - 3; i > -1; i--){
            f[i] = Math.max(nums[i] + f[i+2], f[i+1]);
        }
        return f[0];
    }
}
