package linshen.dp;

/*
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，
相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。

给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 */

public class L213 {
    public int rob(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n+1][2];
        //0 means stole the tail
        f[n-1][0] = nums[n-1];
        f[n-1][1] = 0;
        for (int i = n - 2; i > -1; i--){
            if(i != 0) {
                f[i][0] = Math.max(nums[i] + f[i+2][0], f[i+1][0]);
                f[i][1] = Math.max(nums[i] + f[i+2][1], f[i+1][1]);
            } else {
                f[i][0] = f[i+1][0];
                f[i][1] = Math.max(nums[i] + f[i+2][1], f[i+1][1]);
            }
        }
        return Math.max(f[0][0], f[0][1]);
    }

    public static void main(String[] args) {
        System.out.println(new L213().rob(new int[]{2,3,2}));
    }
}
