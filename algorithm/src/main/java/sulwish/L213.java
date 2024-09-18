package sulwish;

/*
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。

给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。

[1 2 3 4 5]


 */

public class L213 {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1)return nums[0];
        int[] f = new int[n]; //f must not steal the last room
        int[] g = new int[n]; //g has no limits

        for (int i = n - 1; i > -1; i--){
            if(i == n - 1)f[i] = 0;
            else if(i == n - 2)f[i] = nums[n-2];
            else {
                f[i] = Math.max(nums[i] + f[i+2], f[i+1]);
            }
        }



        for(int i = n - 1; i > -1; i--){
            if(i == n - 1) {
                g[i] = nums[n-1];
            } else if(i == n - 2) {
                g[i] = Math.max(nums[n-1], nums[n-2]);
            } else if(i > 0) {
                g[i] = Math.max(nums[i] + g[i+2], g[i+1]);
            } else {
                //i == 0
                g[i] = Math.max(nums[0] + f[2], g[1]);
            }

        }
        return g[0];
    }

    public static void main(String[] args) {
        System.out.println(new L213().rob(new int[]{2,3,2}));
    }
}
